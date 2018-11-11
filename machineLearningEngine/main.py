from machineLearningEngine.TaskManager import TaskManager
import plotly.offline as py
import plotly.graph_objs as go

'''
main.py
Just a script to run the ML algorithm.
prints cross-validation error, and trained model parameters
also shows histogram of cross-validation errors using 
'''


errors = list()
m = TaskManager("IBM", "RHT")
error, means, cov_matrices = m.run()
print(error)
print(means)
print(cov_matrices)
for i in range(1000):

    errors.append(m.run())

data = [go.Histogram(x=errors)]
py.plot(data)