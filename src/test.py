import numpy as np


def divide(a, b):
    return a / b


b = 1
x = divide(1, 0)
print(x)


def divide_by_zero():
    return 1 / 0


x = divide_by_zero()
print(x)
