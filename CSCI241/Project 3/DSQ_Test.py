import unittest
from Deque_Generator import get_deque
from Stack import Stack
from Queue import Queue

class DSQTester(unittest.TestCase):
    
    def setUp(self):
        self.__deque = get_deque()
        self.__stack = Stack()
        self.__queue = Queue()
    
    def test_push_front_empty_deque(self):
        self.__deque.push_front('Data')
        self.assertEqual('[ Data ]', str(self.__deque))
    
    def test_pop_front_empty_deque(self):
        self.assertEqual('[ ]', str(self.__deque))
    
    def test_peek_front_empty_deque(self):
        self.assertEqual('[ ]', str(self.__deque))
        
    def test_push_back_empty_deque(self):
        self.__deque.push_back('Data')
        self.assertEqual('[ Data ]', str(self.__deque))
    
    def test_pop_back_empty_deque(self):
        self.assertEqual('[ ]', str(self.__deque))
        
    def test_peek_back_empty_deque(self):
        self.assertEqual('[ ]', str(self.__deque))
###
    def test_push_front_multiple(self):
        self.__deque.push_front('Last')
        self.__deque.push_front('Middle')
        self.__deque.push_front('First')
        self.assertEqual('[ First, Middle, Last ]', str(self.__deque))
    
    def test_pop_front_with_one(self):
        self.__deque.push_front('Data')
        self.assertEqual('Data', self.__deque.pop_front())
    
    def test_peek_front_with_one(self):
        self.__deque.push_front('Data')
        self.assertEqual('Data', self.__deque.peek_front())
        
    def test_push_back_with_multiple(self):
        self.__deque.push_back('First')
        self.__deque.push_back('Middle')
        self.__deque.push_back('Last')
        self.assertEqual('[ First, Middle, Last ]', str(self.__deque))
    
    def test_pop_back_with_one(self):
        self.__deque.push_back('Data')
        self.assertEqual('Data', self.__deque.pop_back())
        
    def test_peek_back_with_one(self):
        self.__deque.push_front('Data')
        self.assertEqual('Data', self.__deque.peek_back())
###

    def test_pop_front_with_multiple(self):
        self.__deque.push_back('Data')
        self.__deque.push_back('Structures')
        self.__deque.push_back('Is')
        self.__deque.push_back('Fun')
        self.assertEqual('Data', self.__deque.pop_front())
    
    def test_peek_front_with_multiple(self):
        self.__deque.push_back('Data')
        self.__deque.push_back('Structures')
        self.__deque.push_back('Is')
        self.__deque.push_back('Fun')
        self.assertEqual('Data', self.__deque.peek_front())
    
    def test_pop_back_with_multiple(self):
        self.__deque.push_front('Data')
        self.__deque.push_front('Structures')
        self.__deque.push_front('Is')
        self.__deque.push_front('Great')
        self.assertEqual('Data', self.__deque.pop_back())
        
    def test_peek_back_with_multiple(self):
        self.__deque.push_front('Data')
        self.__deque.push_front('Structures')
        self.__deque.push_front('Is')
        self.__deque.push_front('Great')
        self.assertEqual('Data', self.__deque.peek_back())
###
        
    def test_pop_front_with_emptied_deque(self):
        self.__deque.push_back('Data')
        self.__deque.push_back('Structures')
        self.__deque.push_back('Is')
        self.__deque.push_back('Fun')
        self.__deque.pop_front()
        self.__deque.pop_front()
        self.__deque.pop_front()
        self.__deque.pop_front()
        self.assertEqual(None, self.__deque.pop_front())
    
    def test_peek_front_with_emptied_deque(self):
        self.__deque.push_back('Data')
        self.__deque.push_back('Structures')
        self.__deque.push_back('Is')
        self.__deque.push_back('Fun')
        self.__deque.pop_front()
        self.__deque.pop_front()
        self.__deque.pop_front()
        self.__deque.pop_front()
        self.assertEqual(None, self.__deque.peek_front())
    
    def test_pop_back_with_emptied_deque(self):
        self.__deque.push_front('Data')
        self.__deque.push_front('Structures')
        self.__deque.push_front('Is')
        self.__deque.push_front('Fun')
        self.__deque.pop_back()
        self.__deque.pop_back()
        self.__deque.pop_back()
        self.__deque.pop_back()
        self.assertEqual(None, self.__deque.pop_back())
        
    def test_peek_back_with_emptied_deque(self):
        self.__deque.push_front('Data')
        self.__deque.push_front('Structures')
        self.__deque.push_front('Is')
        self.__deque.push_front('Fun')
        self.__deque.pop_back()
        self.__deque.pop_back()
        self.__deque.pop_back()
        self.__deque.pop_back()
        self.assertEqual(None, self.__deque.peek_back())
###
###
    
    def test_push_stack_empty(self):
        self.__stack.push('Data')
        self.assertEqual('[ Data ]', str(self.__stack))
    
    def test_pop_stack_empty(self):
        self.assertEqual('[ ]', str(self.__stack))
    
    def test_peek_stack_empty(self):
        self.assertEqual('[ ]', str(self.__stack))

###
    def test_push_stack_multiple(self):
        self.__stack.push('Last')
        self.__stack.push('Middle')
        self.__stack.push('First')
        self.assertEqual('[ First, Middle, Last ]', str(self.__stack))

    def test_pop_stack_with_one(self):
        self.__stack.push('Data')
        self.assertEqual('Data', self.__stack.pop())
    
    def test_peek_stack_with_one(self):
        self.__stack.push('Data')
        self.assertEqual('Data', self.__stack.peek())
###

    def test_pop_stack_with_multiple(self):
        self.__stack.push('Data')
        self.__stack.push('Structures')
        self.__stack.push('Is')
        self.__stack.push('Fun')
        self.assertEqual('Fun', self.__stack.pop())
    
    def test_peek_stack_with_multiple(self):
        self.__stack.push('Data')
        self.__stack.push('Structures')
        self.__stack.push('Is')
        self.__stack.push('Fun')
        self.assertEqual('Fun', self.__stack.peek())
    
###
        
    def test_pop_with_emptied_stack(self):
        self.__stack.push('Data')
        self.__stack.push('Structures')
        self.__stack.push('Is')
        self.__stack.push('Fun')
        self.__stack.pop()
        self.__stack.pop()
        self.__stack.pop()
        self.__stack.pop()
        self.assertEqual(None, self.__stack.pop())
    
    def test_peek_with_emptied_stack(self):
        self.__stack.push('Data')
        self.__stack.push('Structures')
        self.__stack.push('Is')
        self.__stack.push('Fun')
        self.__stack.pop()
        self.__stack.pop()
        self.__stack.pop()
        self.__stack.pop()
        self.assertEqual(None, self.__stack.peek())

###
###
        
    def test_enqueue_queue_empty(self):
        self.__queue.enqueue('Data')
        self.assertEqual('[ Data ]', str(self.__queue))
    
    def test_dequeue_queue_empty(self):
        self.assertEqual('[ ]', str(self.__queue))
    
    def test_peek_queue_empty(self):
        self.assertEqual('[ ]', str(self.__queue))

###
    def test_enqueue_queue_multiple(self):
        self.__queue.enqueue('Last')
        self.__queue.enqueue('Middle')
        self.__queue.enqueue('First')
        self.assertEqual('[ First, Middle, Last ]', str(self.__queue))

    def test_dequeue_queue_with_one(self):
        self.__queue.enqueue('Data')
        self.assertEqual('Data', self.__queue.dequeue())
    
    def test_peek_queue_with_one(self):
        self.__queue.enqueue('Data')
        self.assertEqual('Data', self.__queue.peek())
###

    def test_dequeue_queue_with_multiple(self):
        self.__queue.enqueue('Data')
        self.__queue.enqueue('Structures')
        self.__queue.enqueue('Is')
        self.__queue.enqueue('Fun')
        self.assertEqual('Data', self.__queue.dequeue())
    
    def test_peek_queue_with_multiple(self):
        self.__queue.enqueue('Data')
        self.__queue.enqueue('Structures')
        self.__queue.enqueue('Is')
        self.__queue.enqueue('Fun')
        self.assertEqual('Data', self.__queue.peek())
    
###
        
    def test_dequeue_with_emptied_queue(self):
        self.__queue.enqueue('Data')
        self.__queue.enqueue('Structures')
        self.__queue.enqueue('Is')
        self.__queue.enqueue('Fun')
        self.__queue.dequeue()
        self.__queue.dequeue()
        self.__queue.dequeue()
        self.__queue.dequeue()
        self.assertEqual(None, self.__queue.dequeue())
    
    def test_peek_with_emptied_queue(self):
        self.__queue.enqueue('Data')
        self.__queue.enqueue('Structures')
        self.__queue.enqueue('Is')
        self.__queue.enqueue('Fun')
        self.__queue.dequeue()
        self.__queue.dequeue()
        self.__queue.dequeue()
        self.__queue.dequeue()
        self.assertEqual(None, self.__queue.peek())
        

  # TODO add your test methods here. Like Linked_List_Test.py,
  # each test should me in a method whose name begins with test_

if __name__ == '__main__':
  unittest.main()

