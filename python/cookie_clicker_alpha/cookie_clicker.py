#!/usr/bin/env python

""" Snippet of code that solves <Problem B: Cookie Clicker Alpha> 
    To be able to test 'large input', locally, you have to increase 
    the stack size that your operating system allocates for the
    python process.
"""

import sys
import decimal


sys.setrecursionlimit(1000000)


def read_lines(file_path):
    for line in open(file_path, 'r').readlines():
        if line.strip():
            yield line.strip("\n")


def get_test_case_number(lines):
    return int(lines.next())


def get_parameter(lines):
    return tuple([float(nbr) for nbr in str(lines.next()).split(' ')])


def get_min_time(c, f, x, time_min, t0=0, i=0):
	t0 = t0 + (c / (2 + f * i))
	t = x / (2 + f * (i + 1)) + t0
	if t >= time_min:
		return time_min
	else:
		i += 1
		return get_min_time(c, f, x, t, t0, i)


def get_case_result(lines):
    c, f, x = get_parameter(lines)
    time_min = x / 2
    if x < c:
        return time_min
    else:
        return get_min_time(c, f, x, time_min)

def get_approximation(time_min):
	d = decimal.Decimal(str(time_min))
        return '%.7f' % d

if __name__ == '__main__':
    file_content = read_lines(sys.argv[1])
    test_case_number = get_test_case_number(file_content)
    current_case = 1
    while current_case <= test_case_number:
        time_min = get_case_result(file_content)
        time_min = get_approximation(time_min)
        print("Case #{0}: {1}".format(current_case, time_min))
        current_case += 1

