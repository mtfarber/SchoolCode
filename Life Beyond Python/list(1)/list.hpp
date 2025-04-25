#ifndef __LIST_HPP__
#define __LIST_HPP__

#include <memory>
#include <ostream>

namespace csci320 {
  template <typename T>
  class NODE {
  public:
    NODE(const T &value) {
      this ->value = value;
      set_next(NULL);
      set_previous(NULL);
    }

    ~NODE() {
      // The following reduces the number of shared pointers pointing
      // to the preceding and next nodes by one.  This is necessary to
      // insure the shared pointers to nodes are eventually all freed.
      // This destructor call also frees the memory for value since C++
      // knows that it is about to go out of scope.
      previous = next = nullptr;
    }

    // Some helper functions follow; you are under no obligation to use them.

    // Get values of members of a node.
    std::shared_ptr< NODE<T> > get_previous(void) const {return previous;}
    std::shared_ptr< NODE<T> > get_next(void) const {return next;}
    T get_value(void) const {return value;}

    // Set values of members of a node.
    void set_next(const std::shared_ptr< NODE<T> > pt) {next = pt;}
    void set_previous(const std::shared_ptr< NODE<T> > pt) {previous = pt;}

  private:
    T value;
    std::shared_ptr< NODE<T> > previous, next;
  };

  template <typename T>
  class LIST {
  public:
    LIST(const T &value) {
      auto node = std::make_shared< NODE<T> > (value);
      this -> head = node;
      this -> tail = node;
    }

    ~LIST() {
      // In this particular situation there is no way for C++ to be
      // sure when a node goes out of scope and can be safely deleted
      // unless we explicitly tell it.  Explicit destructor calls are
      // relatively rare.
      for (auto node = head; node != nullptr; /* */) {
        auto next_node = node->get_next();
        node->~NODE();
        node = next_node;
      }
    }

    std::shared_ptr< NODE<T> > get_head(void) const {return head;}
    std::shared_ptr< NODE<T> > get_tail(void) const {return tail;}

    void prepend(const T &value) {
      // Add a new node at the head of the list.
      auto new_node = std::make_shared< NODE<T> > (value);
      auto old_node = this -> get_head();
      new_node -> set_next(old_node);
      old_node -> set_previous(new_node);
      head = new_node;
    }

    void append(const T &value) {
      // Add a new node at the tail of the list.
      auto new_node = std::make_shared<NODE<T> > (value);
      auto old_node = this -> get_tail();
      old_node -> set_next(new_node);
      new_node -> set_previous(old_node);
      tail = new_node;
    }

  private:
    std::shared_ptr< NODE<T> > head;
    std::shared_ptr< NODE<T> > tail;
  };
}

template <typename T>
std::ostream &operator<<(std::ostream &os, const csci320::LIST<T> &list)
{
  // Output operator for a list.
  for (auto node = list.get_head(); node != nullptr; node = node->get_next()) {
    os << node->get_value();
    os << ((node->get_next() != nullptr) ? ", " : "\n");
  }
  return os;
}

#endif
