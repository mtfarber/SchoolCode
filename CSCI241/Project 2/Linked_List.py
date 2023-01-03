class Linked_List:
  
  class __Node:
    
    def __init__(self, val):
      # declare and initialize the private attributes
      # for objects of the Node class.
      # TODO replace pass with your implementation
      self.next = None
      self.prev = None
      self.value = val
      

  def __init__(self):
    # declare and initialize the private attributes
    # for objects of the sentineled Linked_List class
    # TODO replace pass with your implementation
    self.__header = self.__Node(None)
    self.__trailer = self.__Node(None)
    self.__header.next = self.__trailer
    self.__trailer.prev = self.__header
    self.__len = 0

  def __len__(self):
    # return the number of value-containing nodes in 
    # this list.
    # TODO replace pass with your implementation
    return self.__len

  def append_element(self, val):
    # increase the size of the list by one, and add a
    # node containing val at the new tail position. this 
    # is the only way to add items at the tail position.
    # TODO replace pass with your implementation
    newest = self.__Node(val)
    current = self.__trailer.prev
    newest.next = self.__trailer
    newest.prev = current
    current.next = newest
    self.__trailer.prev = newest
    self.__len += 1
    

  def insert_element_at(self, val, index):
    # assuming the head position (not the __header node)
    # is indexed 0, add a node containing val at the 
    # specified index. If the index is not a valid 
    # position within the list, raise an IndexError 
    # exception. This method cannot be used to add an 
    # item at the tail position.
    # TODO replace pass with your implementation
    newest = self.__Node(val)
    if index >= 0 and index < self.__len and index <= self.__len//2:
        current = self.__header
        for i in range(index):
            current = current.next
        newest.next = current.next
        newest.prev = current
        current.next = newest
        current = current.next.next
        current.prev = newest
        self.__len += 1
    elif index >= 0 and index < self.__len and index > self.__len//2:
        current = self.__trailer
        for i in range(self.__len - index):
            current = current.prev
        newest = self.__Node(val)
        newest.next = current
        newest.prev = current.prev
        current.prev = newest
        current = current.prev.prev
        current.next = newest
        self.__len += 1
    else:
        raise IndexError

  def remove_element_at(self, index):
    # assuming the head position (not the header node)
    # is indexed 0, remove and return the value stored 
    # in the node at the specified index. If the index 
    # is invalid, raise an IndexError exception.
    # TODO replace pass with your implementation
    if index >= 0 and index < self.__len and index <= self.__len//2:
        current = self.__header
        for i in range(index):
            current = current.next
        current.next = current.next.next
        current = current.next
        current.prev = current.prev.prev
        self.__len -= 1
    elif index >= 0 and index < self.__len and index > self.__len//2:
        current = self.__trailer
        for i in range(self.__len - index):
            current = current.prev
        current.prev = current.prev.prev
        current = current.prev
        current.next = current.next.next
        self.__len -= 1
    else:
        raise IndexError

  def get_element_at(self, index):
    # assuming the head position (not the header node)
    # is indexed 0, return the value stored in the node 
    # at the specified index, but do not unlink it from 
    # the list. If the specified index is invalid, raise 
    # an IndexError exception.
    # TODO replace pass with your implementation
    if index >= 0 and index < self.__len and index <= self.__len//2:
        current = self.__header
        for i in range(index):
            current = current.next
        current = current.next
        return current.value
    elif index >= 0 and index < self.__len and index > self.__len//2:
        current = self.__trailer
        for i in range(self.__len - index):
            current = current.prev
        current = current.prev
        return current.value
    else:
        raise IndexError

  def rotate_left(self):
    # rotate the list left one position. Conceptual indices
    # should all decrease by one, except for the head, which
    # should become the tail. For example, if the list is
    # [ 5, 7, 9, -4 ], this method should alter it to
    # [ 7, 9, -4, 5 ]. This method should modify the list in
    # place and must not return a value.
    # TODO replace pass with your implementation.
    holder = self.__header.next
    current = self.__header
    current.next = current.next.next
    current = current.next
    current.prev = current.prev.prev
    current = self.__trailer.prev
    holder.next = self.__trailer
    holder.prev = current
    current.next = holder
    self.__trailer.prev = holder
    
    
  def __str__(self):
    # return a string representation of the list's
    # contents. An empty list should appear as [ ].
    # A list with one element should appear as [ 5 ].
    # A list with two elements should appear as [ 5, 7 ].
    # You may assume that the values stored inside of the
    # node objects implement the __str__() method, so you
    # call str(val_object) on them to get their string
    # representations.
    # TODO replace pass with your implementation
    if self.__len == 0:
        string = '[ ]'
    else:
        string = '['
        current = self.__header
        for i in range(self.__len):
            current = current.next
            string = string + ', ' + str(current.value)
        string = string + ' ]'
        string = string[:1] + string[2:]
    return string
        

  def __iter__(self):
    # initialize a new attribute for walking through your list
    # TODO insert your initialization code before the return
    # statement. do not modify the return statement.
    self.pointer = self.__header
    self.val = self.__next__()
    return self

  def __next__(self):
    # using the attribute that you initialized in __iter__(),
    # fetch the next value and return it. If there are no more 
    # values to fetch, raise a StopIteration exception.
    # TODO replace pass with your implementation
    if self.pointer.next is not None:
        val = self.pointer.value
        self.pointer = self.pointer.next
        return val
    else:
        raise StopIteration

if __name__ == '__main__':
    # Your test code should go here. Be sure to look at cases
    # when the list is empty, when it has one element, and when 
    # it has several elements. Do the indexed methods raise exceptions
    # when given invalid indices? Do they position items
    # correctly when given valid indices? Does the string
    # representation of your list conform to the specified format?
    # Does removing an element function correctly regardless of that
    # element's location? Does a for loop iterate through your list
    # from head to tail? Your writeup should explain why you chose the
    # test cases. Leave all test cases in your code when submitting.
    # TODO replace pass with your tests
    a = Linked_List()
    for i in range(1,11):
        a.append_element(i)
    print(a)
    
    try:
        #Should fail. Only append can insert at tail position
        a.insert_element_at(2,10)
    except IndexError:
        print('Correctly caught error, index can not insert at the tail.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')

    try:
        #Should work without error
        a.insert_element_at(99,5)
    except IndexError:
        print('Error: Index is out of range.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')
    
    try:
        #Should work without error
        a.insert_element_at(30,8)
    except IndexError:
        print('Error: Index is out of range.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')
    
    try:
        #Should work without error
        a.insert_element_at(15,0)
    except IndexError:
        print('Error: Index is out of range.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')
    
    try:
        #Shoul fail. Index is out of range
        a.insert_element_at(50,-9)
    except IndexError:
        print('Correctly caught error, index is out of range.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')

    try:
        #Should work without error
        a.remove_element_at(6)
    except IndexError:
        print('Error: Index is out of range.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')
    
    try:
        #Should fail. Index is out of range
        a.remove_element_at(50)
    except IndexError:
        print('Correctly caught error, index is out of range.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')
    
    try:
        #Should fail. Index is out of range
        a.remove_element_at(-7)
    except IndexError:
        print('Correctly caught error, index is out of range.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')
    
    try:
        #Should fail. Index is out of range
        print(a.get_element_at(15))
    except IndexError:
        print('Correctly caught error, index is out of range.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')

    try:
        #Should work without error
        print(a.get_element_at(-5))
    except IndexError:
        print('Correctly caught error, index is out of range.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')

    try:
        #Should work without error
        print(a.get_element_at(4))
    except IndexError:
        print('Error: Index out of range.')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')

    try:
        #Should work without error
        for i in range(6):
            a.rotate_left()
    except IndexError:
        print('Error: Index is out of range')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')
    
    try:
        #Should work without error
        for i in range(20):
            a.rotate_left()
    except IndexError:
        print('Error: Index is out of range')
    print(a)
    print('Linked list has ' + str(len(a)) + ' elements')
    
    for val in a:
        print(val)