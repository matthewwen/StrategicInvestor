from machineLearningEngine.Strategy import StrategyFactory


class TaskManager:
    """
    Wrapper class for information on a new merger we wanna predict on
    """
    def __init__(self, acquiror, target):
        self.__acquiror = acquiror
        self.__target = target
        self.__Strategy = StrategyFactory.create_strategy("RISK ARBITRAGE")

    def run(self):
        cv_error, means, cov_matrices = self.__Strategy.run(k=5)
        # print("error", cv_error)
        # print("means", means)
        # print("covmatrices", cov_matrices)
        return cv_error, means, cov_matrices



