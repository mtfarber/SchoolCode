from Deque_Generator import get_deque

class Queue:

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

  def enqueue(self, val):
    # TODO replace pass with your implementation.
    self.__dq.push_front(val)
    self.__size += 1

  def dequeue(self):
    # TODO replace pass with your implementation.
    if self.__size > 0:
        self.__size -= 1
        return self.__dq.pop_back()

  def peek(self):
    # TODO replace pass with your implementation.
    if self.__size > 0:
        return self.__dq.peek_back()

# Unit tests make the main section unneccessary.
if __name__ == '__main__':
    q = Queue()
    print(q)
    q.enqueue(1)
    print(q)
    print(q.dequeue())
    print(q)
    q.enqueue(2)
    print(q)
    q.enqueue(3)
    print(q)
    print(q.dequeue())
    print(q)
    q.enqueue(4)
    print(q)
    q.enqueue(5)
    print(q)
    q.enqueue(6)
    print(q)
    print(q.dequeue())
    print(q)
    print(q.peek())
    print(q)
  
