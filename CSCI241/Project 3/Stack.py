from Deque_Generator import get_deque

class Stack:

  def __init__(self):
    # TODO replace pass with your implementation.
    self.__dq = get_deque()
    self.__size = 0

  def __str__(self):
    # TODO replace pass with your implementation.
    return str(self.__dq)

  def __len__(self):
    # TODO replace pass with your implementation.
    return self.__size

  def push(self, val):
    # TODO replace pass with your implementation.
    self.__dq.push_front(val)
    self.__size += 1

  def pop(self):
    # TODO replace pass with your implementation.
    if self.__size > 0:
        self.__size -= 1
        return self.__dq.pop_front()

  def peek(self):
    # TODO replace pass with your implementation.
    if self.__size > 0:
        return self.__dq.peek_front()

# Unit tests make the main section unneccessary.
if __name__ == '__main__':
    q = Stack()
    print(q)
    q.push(1)
    print(q)
    print(q.pop())
    print(q)
    q.push(2)
    print(q)
    q.push(3)
    print(q)
    print(q.pop())
    print(q)
    q.push(4)
    print(q)
    q.push(5)
    print(q)
    q.push(6)
    print(q)
    print(q.pop())
    print(q)
    print(q.peek())
    print(q)
