import numpy as np


def divide(a, b):
    return a / b


def sum(a, b):
    return a + b


def sum_of_squares(a, b):
    return a**2 + b**2


def mahalanobis_distance(x, y):
    return np.sqrt(np.sum((x - y) ** 2))


def euclidean_distance(x, y):
    return np.sqrt(np.sum((x - y) ** 2))


def manhattan_distance(x, y):
    return np.sum(np.abs(x - y))


print(mahalanobis_distance(np.array([1, 2, 3]), np.array([4, 5, 6])))
print(euclidean_distance(np.array([1, 2, 3]), np.array([4, 5, 6])))
print(manhattan_distance(np.array([1, 2, 3]), np.array([4, 5, 6])))

print(sum_of_squares(1, 2))

print(sum(1, 2))

print(divide(1, 2))

print(divide(1, 0))
