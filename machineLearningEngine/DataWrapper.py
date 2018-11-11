import pandas as pd
import abc

'''
DataWrapper.py
Factory pattern methods for DataWrapper classes. DataWrapper class should be wrapper classes for data required to train ML models 
for specific strategies
'''

class DataWrapper(metaclass=abc.ABCMeta):
    """
    Abstract Base Class for Data Wrapper for different strategies
    """
    def __init__(self):
        self.__input_path = "C:/Users/kevin/OneDrive/Desktop/RISK ARBITRAGE DATASET/merger_data.csv"

    def __separate_data(self, k=5):
        """
        Assuming that data is already scrubbed, parsed and stuff: generate k number of subsets for cross validation
        :return: list of data frames
        """
        data = pd.read_csv(self.__input_path)
        data = data.sample(frac=1)  # randomly shuffle the data
        size = len(data)//k
        validation_sets = list()
        for i in range(k):
            validation_sets.append(data[i*size:(i+1)*size])
        return validation_sets

    def validation_sets(self, k=5):
        return self.__separate_data(k)


class RiskArbitrageDataWrapper(DataWrapper):
    """
    Wrapper class for Data required to train ML model for risk arbitrage
    """
    def __init__(self):
        super().__init__()


# TODO
class GlobalMacroDataWrapper(DataWrapper):
    """
    Wrapper class for Data required to train ML model for risk arbitrage
    """
    def __init__(self):
        super().__init__()


class DataWrapperFactory:
    @staticmethod
    def create_data_wrapper(typ):
        target_class = typ.replace(" ", "").lower()
        if target_class == "riskarbitrage":
            return RiskArbitrageDataWrapper()
        elif target_class == "globalmacro":
            return GlobalMacroDataWrapper()
        else:
            raise AssertionError("Sorry, we don't support " + typ + "yet :(")

