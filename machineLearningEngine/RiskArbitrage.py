import numpy as np
import scipy as sp
import pandas as pd
from sklearn import ensemble


class DataWrapper:
    """
    Wrapper class for Data required to train ML model for risk arbitrage
    """
    def __init__(self):



class MergerWrapper:
    """
    Wrapper class for information on a new merger we wanna predict on
    """
    def __init__(self, acquirer, acquiree):
        self.__acquirer = acquirer
        self.__acquiree = acquiree