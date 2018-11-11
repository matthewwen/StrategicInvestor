from machineLearningEngine.DataWrapper import DataWrapperFactory
from datetime import datetime
import pandas as pd
import numpy as np
from math import atan
import abc


class Strategy(metaclass=abc.ABCMeta):
    """
    Abstract base class for a wrapper classes for a specific strategy
    """

    def __init__(self):
        self.__timestamp = datetime.now()
        self._DataWrapperFactory = DataWrapperFactory()


class RiskArbitrageStrategy(Strategy):

    def __init__(self):
        super().__init__()
        self.__DataWrapper = self._DataWrapperFactory.create_data_wrapper("RISK ARBITRAGE")

    def __find_multivariate_normal_params(self, training_data):  # TODO implement ML algo here
        # Use Naive Bayesian -> Assume and find multivariate normal distribution parameters
        X = training_data[:, ~-1:-1]  # just get the X
        means = list()
        for col in X.T:
            means.append(np.mean(col))
        cov_matrix = (1/len(training_data))*(X.T@X)
        return np.asarray(means), cov_matrix

    def __validate(self, k=5):
        # k-fold cross validation
        validation_sets = self.__DataWrapper.validation_sets(k)
        validation_errors = np.zeros(k)

        # return avg
        numb_features = len(validation_sets[0].columns)-1
        final_mu, final_mu_fail, final_mu_success = \
            np.zeros(numb_features), np.zeros(numb_features), np.zeros(numb_features)
        final_V, final_V_fail, final_V_success = \
            np.zeros([numb_features, numb_features]), np.zeros([numb_features, numb_features]), np.zeros([numb_features, numb_features])
        for i in range(k):  # cross validation iteration
            # Train
            train_set = list(validation_sets)
            del(train_set[i])
            train_set = pd.concat(train_set)
            train_set = np.asarray(train_set)
            train_set = train_set.astype(np.float)
            mu, V = self.__find_multivariate_normal_params(train_set)  # mean vector, cov matrix
            mu_fail, V_fail = self.__find_multivariate_normal_params(train_set[train_set[:,-1]==0])  # mean vector, cov matrix | Fail
            mu_success, V_success = self.__find_multivariate_normal_params(train_set[train_set[:,-1]==1])  # mean vector, cov matrix | Success

            final_mu += mu
            final_mu_fail += mu_fail
            final_mu_success += mu_success
            final_V += V
            final_V_fail += V_fail
            final_V_success += V_success

            # Test
            test_set = np.asarray(validation_sets[i])
            test_set = test_set.astype(np.float)
            count = 0
            for data_point in test_set:
                count += 1
                x = data_point[~-1:-1]
                y = data_point[-1]

                # calculate probability

                p_x = ((np.linalg.det(8*atan(1)*V))**(-0.5))*np.exp(-0.5*(x-mu)@np.linalg.inv(V)@(x-mu).T)
                p_x_fail = ((np.linalg.det(8*atan(1)*V_fail))**(-0.5))*np.exp(-0.5*(x-mu_fail)@np.linalg.inv(V_fail)@(x-mu_fail).T)
                p_x_success = ((np.linalg.det(8*atan(1)*V_success))**(-0.5))*np.exp(-0.5*(x-mu_success)@np.linalg.inv(V_success)@(x-mu_success).T)
                if y == 1:
                    p_class = len(train_set[train_set[:, -1] == 1]) / len(train_set)
                    p_hat = (p_x_success*p_class) / p_x
                else:
                    p_class = len(train_set[train_set[:, -1] == 0]) / len(train_set)
                    p_hat = (p_x_fail*p_class) / p_x
                # print(p_x, p_x_fail, p_x_success)
                t = round(p_hat, 0)
                # print(p_hat)
                # print(np.abs(y-t))
                validation_errors[i] += np.abs(y-t)  # validation error
            validation_errors[i] = validation_errors[i]/count
            # print("validation error:", validation_errors[i])
        # return cross validation error, ensembled model
        final_mus = final_mu/k, final_mu_fail/k, final_mu_success/k  # 3-tuple of lists
        final_Vs = final_V/k, final_V_fail/k, final_V_success/k  # 3-tuple of cov_matrices
        return np.mean(validation_errors), final_mus, final_Vs

    def check_time(self):
        time_passed = datetime.now() - self.__timestamp
        if time_passed.days >= 30: # If it's been over 30 days since last ML runn then run it again
            cv_error, means_vectors, cov_matrices = self.__validate(k=5)
            return cv_error, means_vectors, cov_matrices
        else:
            return

    def run(self, k=5):
        return self.__validate(k)


class StrategyFactory:
    @staticmethod
    def create_strategy(typ):
        target_class = typ.replace(" ", "").lower()
        # print(target_class)
        if target_class == "riskarbitrage":
            return RiskArbitrageStrategy()
        else:
            raise AssertionError("Sorry, we don't support " + typ + "yet :(")
