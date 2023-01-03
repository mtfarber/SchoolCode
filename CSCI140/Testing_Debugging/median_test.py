from median import *
import unittest

class Functions_Tester(unittest.TestCase):

    def test_function_median_one(self):
        print('\nfunction_median_one:')
        result = median([5,6,7])
        self.assertEqual(result, 6)


if __name__ == '__main__':
  unittest.main()