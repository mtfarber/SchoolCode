import unittest
from Binary_Search_Tree import Binary_Search_Tree

class DSQTester(unittest.TestCase):
    
    def setUp(self):
        self.__tree = Binary_Search_Tree()
    
    def test_insert_at_root(self):
        self.__tree.insert_element(5)
        self.assertEqual('[ 5 ]',str(self.__tree))
    
    def test_insert_left_child(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(3)
        self.assertEqual('[ 3, 5 ]',str(self.__tree))
        
    def test_insert_right_child(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(7)
        self.assertEqual('[ 5, 7 ]',str(self.__tree))
        
    def test_insert_duplicate_value(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(7)
        with self.assertRaises(ValueError):
            returned = self.__tree.insert_element(5)
        self.assertEqual('[ 5, 7 ]', str(self.__tree))
        
###
        
    def test_in_order(self):
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
        
    def test_post_order(self):
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
        self.assertEqual('[ 2, 1, 4, 3, 6, 7, 10, 9, 8, 5 ]',self.__tree.post_order())
    
    def test_pre_order(self):
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
        self.assertEqual('[ 5, 3, 1, 2, 4, 8, 7, 6, 9, 10 ]',self.__tree.pre_order())

###
        
    def test_height_of_perfect_tree(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(2)
        self.__tree.insert_element(8)
        self.assertEqual(2,self.__tree.get_height())
    
    def test_height_with_larger_left_side(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(2)
        self.__tree.insert_element(8)
        self.__tree.insert_element(1)
        self.assertEqual(3,self.__tree.get_height())
    
    def test_height_with_larger_right_side(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(2)
        self.__tree.insert_element(8)
        self.__tree.insert_element(9)
        self.assertEqual(3,self.__tree.get_height())
    
    def test_height_of_empty_tree(self):
        self.assertEqual(0,self.__tree.get_height())
    
    def test_height_after_tree_is_emptied(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(8)
        self.__tree.insert_element(2)
        self.__tree.remove_element(5)
        self.__tree.remove_element(2)
        self.__tree.remove_element(8)
        self.assertEqual(0,self.__tree.get_height())
        
    def test_height_after_single_child_removal(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(2)
        self.__tree.remove_element(5)
        self.assertEqual(1,self.__tree.get_height())
        
    def test_height_after_two_children_removal(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(2)
        self.__tree.insert_element(8)
        self.__tree.remove_element(5)
        self.assertEqual(2,self.__tree.get_height())
    
    def test_height_after_leaf_removal(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(2)
        self.__tree.insert_element(8)
        self.__tree.remove_element(2)
        self.assertEqual(2,self.__tree.get_height())
###
        
    def test_remove_empty_tree(self):
        with self.assertRaises(ValueError):
            returned = self.__tree.remove_element(5)
        self.assertEqual('[ ]', str(self.__tree))
    
    def test_remove_single_node(self):
        self.__tree.insert_element(6)
        self.__tree.remove_element(6)
        self.assertEqual('[ ]',str(self.__tree))
        
    def test_remove_leaf_node(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(6)
        self.__tree.remove_element(6)
        self.assertEqual('[ 5 ]',str(self.__tree))
    
    def test_remove_with_left_child(self):
        self.__tree.insert_element(2)
        self.__tree.insert_element(1)
        self.__tree.remove_element(1)
        self.assertEqual('[ 2 ]',str(self.__tree))
    
    def test_remove_with_right_child(self):
        self.__tree.insert_element(2)
        self.__tree.insert_element(3)
        self.__tree.remove_element(3)
        self.assertEqual('[ 2 ]',str(self.__tree))
        
    def test_remove_with_two_children_unbalanced_left(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(4)
        self.__tree.insert_element(3)
        self.__tree.remove_element(5)
        self.assertEqual('[ 3, 4 ]',str(self.__tree))
    
    def test_remove_with_two_children_unbalanced_right(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(7)
        self.__tree.insert_element(8)
        self.__tree.remove_element(5)
        self.assertEqual('[ 7, 8 ]',str(self.__tree))
        
    def test_remove_with_two_children_perfect_tree(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(2)
        self.__tree.insert_element(8)
        self.__tree.remove_element(5)
        self.assertEqual('[ 2, 8 ]',str(self.__tree))
        
    def test_remove_missing_value(self):
        self.__tree.insert_element(5)
        self.__tree.insert_element(2)
        self.__tree.insert_element(8)
        with self.assertRaises(ValueError):
            returned = self.__tree.remove_element(9)
        self.assertEqual('[ 2, 5, 8 ]', str(self.__tree))

if __name__ == '__main__':
  unittest.main()