import unittest
from std_dev import *

class Function_Tester(unittest.TestCase): #fill in your five tests below the example given
    
    def test_example_case(self): 
        print('\nExample template case you can copy-paste:')
        result_of_function_call = std_dev('test_data.txt') #put a call to std_dev function here
        expected_result = 1.5 #what you expect
        print('Observed:',result_of_function_call,'Expected:', expected_result)
        self.assertEqual(result_of_function_call, expected_result)
        
    def test_negative_numbers(self): 
        print('\nCombination of negative and positive numbers:')
        result_of_function_call = round(std_dev('test_data_negative.txt'),4) #put a call to std_dev function here
        expected_result = 15.8291 #what you expect
        print('Observed:',result_of_function_call,'Expected:', expected_result)
        self.assertEqual(result_of_function_call, expected_result)
        
    def test_empty_file(self): 
        print('\nFile with nothing inside of it:')
        result_of_function_call = std_dev('test_data_empty.txt') #put a call to std_dev function here
        expected_result = None #what you expect
        print('Observed:',result_of_function_call,'Expected:', expected_result)
        self.assertEqual(result_of_function_call, expected_result)
        
    def test_numbers_with_letters(self): 
        print('\nCombination of positive numbers with one letter:')
        result_of_function_call = std_dev('test_data_numbers_with_letters.txt') #put a call to std_dev function here
        expected_result = None #what you expect
        print('Observed:',result_of_function_call,'Expected:', expected_result)
        self.assertEqual(result_of_function_call, expected_result)
        
    def test_file_decimals(self): 
        print('\nPositive Numbers with decimals:')
        result_of_function_call = round(std_dev('test_data_decimals.txt'),4) #put a call to std_dev function here
        expected_result = 3.1972 #what you expect
        print('Observed:',result_of_function_call,'Expected:', expected_result)
        self.assertEqual(result_of_function_call, expected_result)
        
    def test_one_number(self): 
        print('\nOnly one number in the file and n=False:')
        result_of_function_call = std_dev('test_data_one_number.txt',False) #put a call to std_dev function here
        expected_result = None #what you expect
        print('Observed:',result_of_function_call,'Expected:', expected_result)
        self.assertEqual(result_of_function_call, expected_result)
     
if __name__ == '__main__': #do not change this line
  unittest.main() #do not change this line