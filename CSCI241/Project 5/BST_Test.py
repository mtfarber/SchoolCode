import unittest
from Binary_Search_Tree import Binary_Search_Tree

class DSQTester(unittest.TestCase):
    
    def setUp(self):
        self.__tree = Binary_Search_Tree()
        
    def test_increasing_insertion(self):
        self.__tree.insert_element(1)
        self.__tree.insert_element(2)
        self.__tree.insert_element(3)
        self.__tree.insert_element(4)
        self.__tree.insert_element(5)
        self.__tree.insert_element(6)
        self.__tree.insert_element(7)
        self.__tree.insert_element(8)
        self.__tree.insert_element(9)
        self.__tree.insert_element(10)
        self.assertEqual('[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ]',str(self.__tree))
        self.assertEqual('[ 4, 2, 1, 3, 6, 5, 8, 7, 9, 10 ]',self.__tree.pre_order())
        self.assertEqual('[ 1, 3, 2, 5, 7, 10, 9, 8, 6, 4 ]',self.__tree.post_order())
        
    def test_decreasing_insertion(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(3)
        self.__tree.insert_element(1)
        self.__tree.insert_element(2)
        self.__tree.insert_element(4)
        self.__tree.insert_element(8)
        self.__tree.insert_element(7)
        self.__tree.insert_element(9)
        self.__tree.insert_element(10)
        self.__tree.insert_element(6)
        self.assertEqual('[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ]',str(self.__tree))
        self.assertEqual('[ 5, 3, 1, 2, 4, 8, 7, 6, 9, 10 ]',self.__tree.pre_order())
        self.assertEqual('[ 2, 1, 4, 3, 6, 7, 10, 9, 8, 5 ]',self.__tree.post_order())
    
    def test_random_insertion(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(3)
        self.__tree.insert_element(1)
        self.__tree.insert_element(2)
        self.__tree.insert_element(4)
        self.__tree.insert_element(8)
        self.__tree.insert_element(7)
        self.__tree.insert_element(9)
        self.__tree.insert_element(10)
        self.__tree.insert_element(6)
        self.assertEqual('[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ]',str(self.__tree))
        self.assertEqual('[ 5, 3, 1, 2, 4, 8, 7, 6, 9, 10 ]',self.__tree.pre_order())
        self.assertEqual('[ 2, 1, 4, 3, 6, 7, 10, 9, 8, 5 ]',self.__tree.post_order())
        
###
    
    def test_one_removal(self):
        self.__tree.insert_element(1)
        self.__tree.insert_element(2)
        self.__tree.insert_element(3)
        self.__tree.insert_element(4)
        self.__tree.insert_element(5)
        self.__tree.insert_element(6)
        self.__tree.insert_element(7)
        self.__tree.insert_element(8)
        self.__tree.insert_element(9)
        self.__tree.remove_element(6)
        self.assertEqual('[ 1, 3, 2, 5, 9, 8, 7, 4 ]',self.__tree.post_order())
        
    def test_three_removals(self):
        self.__tree.insert_element(1)
        self.__tree.insert_element(2)
        self.__tree.insert_element(3)
        self.__tree.insert_element(4)
        self.__tree.insert_element(5)
        self.__tree.insert_element(6)
        self.__tree.insert_element(7)
        self.__tree.insert_element(8)
        self.__tree.insert_element(9)
        self.__tree.insert_element(10)
        self.__tree.remove_element(6)
        self.__tree.remove_element(1)
        self.__tree.remove_element(9)
        self.assertEqual('[ 2, 3, 5, 7, 10, 8, 4 ]',self.__tree.post_order())
        
    def test_removal_until_empty(self):
        self.__tree.insert_element(1)
        self.__tree.insert_element(2)
        self.__tree.insert_element(3)
        self.__tree.insert_element(4)
        self.__tree.insert_element(5)
        self.__tree.remove_element(2)
        self.__tree.remove_element(4)
        self.__tree.remove_element(1)
        self.__tree.remove_element(5)
        self.__tree.remove_element(3)
        self.assertEqual('[ ]',self.__tree.post_order())
        
###
    
    def test_height_after_empty(self):
        self.__tree.insert_element(1)
        self.__tree.insert_element(2)
        self.__tree.insert_element(3)
        self.__tree.insert_element(4)
        self.__tree.insert_element(5)
        self.__tree.remove_element(2)
        self.__tree.remove_element(4)
        self.__tree.remove_element(1)
        self.__tree.remove_element(5)
        self.__tree.remove_element(3)
        self.assertEqual(0,self.__tree.get_height())
        
    def test_height_after_insertion(self):
        self.__tree.insert_element(1)
        self.__tree.insert_element(2)
        self.__tree.insert_element(3)
        self.__tree.insert_element(4)
        self.__tree.insert_element(5)
        self.__tree.insert_element(6)
        self.__tree.insert_element(7)
        self.__tree.insert_element(8)
        self.__tree.insert_element(9)
        self.__tree.insert_element(10)
        self.assertEqual(5,self.__tree.get_height())
        
    def test_height_random_removal(self):
        self.__tree.insert_element(1)
        self.__tree.insert_element(2)
        self.__tree.insert_element(3)
        self.__tree.insert_element(4)
        self.__tree.insert_element(5)
        self.__tree.insert_element(6)
        self.__tree.insert_element(7)
        self.__tree.insert_element(8)
        self.__tree.insert_element(9)
        self.__tree.insert_element(10)
        self.__tree.remove_element(1)
        self.__tree.remove_element(8)
        self.__tree.remove_element(5)
        self.assertEqual(6,self.__tree.get_height())

if __name__ == '__main__':
  unittest.main()