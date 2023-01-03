class Binary_Search_Tree:
  # TODO.I have provided the public method skeletons. You will need
  # to add private methods to support the recursive algorithms
  # discussed in class

  class __BST_Node:
    # TODO The Node class is private. You may add any attributes and
    # methods you need. Recall that attributes in an inner class 
    # must be public to be reachable from the the methods.

    def __init__(self, value):
      self.value = value
      # TODO complete Node initialization
      self.height = 1
      self.right_child = None
      self.left_child = None

  def __init__(self):
    self.__root = None
    self.__string = None
    # TODO complete initialization

  def __balance(self,root):
      #check if unbalanced
      #rotate as necessary
      #return new root of balanced subtree
      def __update_height(root):
        if (root.left_child is not None):
            left_height = root.left_child.height
        else:
            left_height = 0 
        if (root.right_child is not None):
            right_height = root.right_child.height
        else:
            right_height = 0
            
        #compare height values and use larger value to adjust parent     
        if right_height > left_height:
            root.height = right_height + 1
        else:
            root.height = left_height + 1
  
      #set up balance value
      if (root.left_child is not None):
          left_height = root.left_child.height
      else:
          left_height = 0 
      if (root.right_child is not None):
          right_height = root.right_child.height
      else:
          right_height = 0
      balance = right_height - left_height
      
      #no imbalance
      if (balance <= 1) and (balance >=-1):
          __update_height(root)
          return root
      
      #right imbalance
      elif balance > 1:
          current = root.right_child
          ###
          
          if (current.left_child is not None):
              left_height = current.left_child.height
          else:
              left_height = 0 
          if (current.right_child is not None):
              right_height = current.right_child.height
          else:
              right_height = 0
          
          ###
          if (right_height - left_height) == -1:
              old_root = current
              new_root = old_root.left_child
              floater = new_root.right_child
              new_root.right_child = old_root
              old_root.left_child = floater
              __update_height(new_root)
              root.right_child = new_root
          old_root = root
          new_root = old_root.right_child
          floater = new_root.left_child
          new_root.left_child = old_root
          old_root.right_child = floater
          __update_height(new_root)
          return new_root
      
      #left imbalance
      
      elif balance < -1:
          current = root.left_child
          ###
          
          if (current.left_child is not None):
              left_height = current.left_child.height
          else:
              left_height = 0 
          if (current.right_child is not None):
              right_height = current.right_child.height
          else:
              right_height = 0
          
          ###
          if (right_height - left_height) == 1:
              old_root = current
              new_root = old_root.right_child
              floater = new_root.left_child
              new_root.left_child = old_root
              old_root.right_child = floater
              __update_height(new_root)
              root.left_child = new_root
          old_root = root
          new_root = old_root.left_child
          floater = new_root.right_child
          new_root.right_child = old_root
          old_root.left_child = floater
          __update_height(new_root)
          return new_root
      
  def insert_element(self, value):
    # Insert the value specified into the tree at the correct
    # location based on "less is left; greater is right" binary
    # search tree ordering. If the value is already contained in
    # the tree, raise a ValueError. Your solution must be recursive.
    # This will involve the introduction of additional private
    # methods to support the recursion control variable.
    # TODO replace pass with your implementation
    def __ins(value,root):
        if root is None:
            root = self.__BST_Node(value)
        elif value < root.value:
            root.left_child = __ins(value,root.left_child)
        elif value > root.value:
            root.right_child = __ins(value,root.right_child)
        else:
            raise ValueError
        return self.__balance(root)
    
    self.__root = __ins(value,self.__root)

  def remove_element(self, value):
    # Remove the value specified from the tree, raising a ValueError
    # if the value isn't found. When a replacement value is necessary,
    # select the minimum value to the from the right as this element's
    # replacement. Take note of when to move a node reference and when
    # to replace the value in a node instead. It is not necessary to
    # return the value (though it would reasonable to do so in some 
    # implementations). Your solution must be recursive. 
    # This will involve the introduction of additional private
    # methods to support the recursion control variable.
    # TODO replace pass with your implementation
    def __remove(value,root):
        if root is None:
            raise ValueError
        if value == root.value:
            if (root.left_child is None) and (root.right_child is None):
                return None
            if (root.left_child is not None) and (root.right_child is not None):
                current_node = root.right_child
                while current_node.left_child is not None:
                    current_node = current_node.left_child
                root.value = current_node.value
                root.right_child = __remove(root.value,root.right_child)
            else:
                if root.left_child is not None:
                    return root.left_child
                if root.right_child is not None:
                    return root.right_child
        elif value < root.value:
            root.left_child = __remove(value,root.left_child)
        elif value > root.value:
            root.right_child =__remove(value,root.right_child)
        else:
            raise ValueError
        return self.__balance(root)
    self.__root = __remove(value,self.__root)

  def in_order(self):
    # Construct and return a string representing the in-order
    # traversal of the tree. Empty trees should be printed as [ ].
    # Trees with one value should be printed as [ 4 ]. Trees with more
    # than one value should be printed as [ 4, 7 ]. Note the spacing.
    # Your solution must be recursive. This will involve the introduction
    # of additional private methods to support the recursion control 
    # variable.
    # TODO replace pass with your implementation
    if self.__root is None:
        string = '[ ]'
    else:
        self.__string = '['
        def __traversal(current_node):
            if current_node.left_child is not None:
                __traversal(current_node.left_child)
            self.__string = self.__string + ', ' + str(current_node.value)
            if current_node.right_child is not None:
                __traversal(current_node.right_child)
        __traversal(self.__root)
        string = self.__string
        string = string + ' ]'
        string = string[:1] + string[2:]
    return string

  def pre_order(self):
    # Construct and return a string representing the pre-order
    # traversal of the tree. Empty trees should be printed as [ ].
    # Trees with one value should be printed in as [ 4 ]. Trees with
    # more than one value should be printed as [ 4, 7 ]. Note the spacing.
    # Your solution must be recursive. This will involve the introduction
    # of additional private methods to support the recursion control 
    # variable.
    # TODO replace pass with your implementation
    if self.__root is None:
        string = '[ ]'
    else:
        self.__string = '['
        def __traversal(current_node):
            self.__string = self.__string + ', ' + str(current_node.value)
            if current_node.left_child is not None:
                __traversal(current_node.left_child)
            if current_node.right_child is not None:
                __traversal(current_node.right_child)
        __traversal(self.__root)
        string = self.__string
        string = string + ' ]'
        string = string[:1] + string[2:]
    return string

  def post_order(self):
    # Construct an return a string representing the post-order
    # traversal of the tree. Empty trees should be printed as [ ].
    # Trees with one value should be printed in as [ 4 ]. Trees with
    # more than one value should be printed as [ 4, 7 ]. Note the spacing.
    # Your solution must be recursive. This will involve the introduction
    # of additional private methods to support the recursion control 
    # variable.
    # TODO replace pass with your implementation
    if self.__root is None:
        string = '[ ]'
    else:
        self.__string = '['
        def __traversal(current_node):
            if current_node.left_child is not None:
                __traversal(current_node.left_child)
            if current_node.right_child is not None:
                __traversal(current_node.right_child)
            self.__string = self.__string + ', ' + str(current_node.value)
        __traversal(self.__root)
        string = self.__string
        string = string + ' ]'
        string = string[:1] + string[2:]
    return string

  def to_list(self):
      to_list = []
      if self.__root is None:
        return to_list
      else:
          def __traversal(current_node):
              if current_node.left_child is not None:
                  __traversal(current_node.left_child)
              to_list.append(current_node.value)
              if current_node.right_child is not None:
                  __traversal(current_node.right_child)
          __traversal(self.__root)
      return to_list

  def get_height(self):
    # return an integer that represents the height of the tree.
    # assume that an empty tree has height 0 and a tree with one
    # node has height 1. This method must operate in constant time.
    # TODO replace pass with your implementation
    if self.__root is not None:
        return self.__root.height
    else:
        return 0

  def __str__(self):
    return self.in_order()

if __name__ == '__main__':
    #unit tests make the main section unnecessary.
    a = Binary_Search_Tree()
    a.insert_element(1)
    print(a)
    a.insert_element(2)
    print(a)
    a.insert_element(3)
    print(a)
    a.insert_element(4)
    print(a)
    a.remove_element(3)
    print(a)
    a.remove_element(1)
    print(a)
