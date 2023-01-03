from Deque import Deque

class Array_Deque(Deque):

  def __init__(self):
    # capacity starts at 1; we will grow on demand.
    self.__capacity = 1
    self.__contents = [None] * self.__capacity
    # TODO replace pass with any additional initializations you need.
    self.__front = 0
    self.__back = 0
    self.__size = 0
    
  def __str__(self):
    # TODO replace pass with an implementation that returns a string of
    # exactly the same format as the __str__ method in the Linked_List_Deque.

    if self.__size == 0:
        string = '[ ]'
    else:
        string = '['
        #print(self.__size)
        for i in range(self.__size):
            string = string + ', ' + str(self.__contents[((self.__front+i)+len(self.__contents))%len(self.__contents)])
        string = string + ' ]'
        string = string[:1] + string[2:]
    return string
    
  def __len__(self):
    # TODO replace pass with an implementation that returns the number of
    # items in the deque. This method must run in constant time.
    self.__size

  def __grow(self):
    # TODO replace pass with an implementation that doubles the capacity
    # and positions existing items in the deque starting in cell 0 (why is
    # necessary?)
    __aux = [None] * ((self.__capacity)*2)
    for i in range(self.__size):
        __aux[i] = self.__contents[((self.__front+i)+self.__size)%self.__size]
    self.__contents = __aux
    self.__front = 0
    self.__back = (self.__size-1)
    self.__capacity = ((self.__size)*2)

  def push_front(self, val):
    # TODO replace pass with your implementation, growing the array before
    # pushing if necessary.
    if self.__size == 0:
        self.__contents[0] = val
        self.__size +=1
        self.__front = 0
        self.__back = 0
        #self.__contents[(self.__front)] = val
        #self.__size += 1
    elif self.__size < self.__capacity:
        self.__front -= 1
        #print('Front is ' + str(self.__front))
        self.__contents[(self.__front)] = val
        self.__size += 1
    else:
        self.__grow()
        self.__front -= 1
        #print('Front is ' + str(self.__front))
        self.__contents[(self.__front)] = val
        self.__size += 1
        
    
  def pop_front(self):
    # TODO replace pass with your implementation. Do not reduce the capacity.
    if self.__size > 0:
        front = self.__contents[self.__front]
        #self.__contents[self.__front] = None
        self.__front += 1
        self.__size -= 1
        return front
    
  def peek_front(self):
    # TODO replace pass with your implementation.
    if self.__size > 0:
        return self.__contents[self.__front]
    
  def push_back(self, val):
    # TODO replace pass with your implementation, growing the array before
    # pushing if necessary.
    if self.__size == 0:
        self.__contents[0] = val
        self.__size +=1
        self.__front = 0
        self.__back = 0
        #self.__contents[(self.__back)] = val
        #self.__size += 1
    elif self.__size < self.__capacity:
        self.__back +=1
        #print('Back is ' + str(self.__back))
        self.__contents[(self.__back)] = val
        self.__size += 1
    else:
        self.__grow()
        self.__back +=1
        #print('Back is ' + str(self.__back))
        self.__contents[(self.__back)] = val
        self.__size += 1
  
  def pop_back(self):
    # TODO replace pass with your implementation. Do not reduce the capacity.
    if self.__size > 0:
        back = self.__contents[self.__back]
        #self.__contents[self.__back] = None
        self.__back -= 1
        self.__size -= 1
        return back

  def peek_back(self):
    # TODO replace pass with your implementation.
    if self.__size > 0:
        return self.__contents[self.__back]

# No main section is necessary. Unit tests take its place.
if __name__ == '__main__':
    arr = Array_Deque()
    print(arr)
    arr.push_front('F1')
    print(arr)
    arr.push_front('F2')
    print(arr)
    arr.push_back('B1')
    print(arr)
    arr.push_back('B2')
    print(arr)
    arr.push_back('B3')
    print(arr)
    arr.push_back('B4')
    print(arr)
    arr.pop_front()
    print(arr)
    arr.pop_back()
    print(arr)
    arr.push_front('F3')
    print(arr)
    arr.push_front('F4')
    print(arr)
    arr.push_back('B5')