import numpy as np


# List out some distance metrics for calculating the distance between two vectors
## 1. Euclidean Distance
def euclidean_distance(x, y):
    return np.sqrt(np.sum((x - y) ** 2))


## 2. Manhattan Distance
def manhattan_distance(x, y):
    return np.sum(np.abs(x - y))


## 3. Minkowski Distance
def minkowski_distance(x, y, p=2):
    return np.sum(np.abs(x - y) ** p) ** (1 / p)


## 4. Cosine Distance
def cosine_distance(x, y):
    return np.dot(x, y) / (np.linalg.norm(x) * np.linalg.norm(y))


## 5. Jaccard Distance
def jaccard_distance(x, y):
    return np.sum(np.abs(x - y)) / np.sum(np.abs(x + y))


## 6. Hamming Distance
def hamming_distance(x, y):
    return np.sum(x != y)


# Test the distance metrics
x = np.array([1, 2, 3])
y = np.array([4, 5, 6])

print(euclidean_distance(x, y))
print(manhattan_distance(x, y))
print(minkowski_distance(x, y))
print(cosine_distance(x, y))
print(jaccard_distance(x, y))
print(hamming_distance(x, y))
