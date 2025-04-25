#ifndef __FOO_HPP__
#define __FOO_HPP__

#include <iostream>
#include <memory>
#include <vector>

/*
 * C++ programming uses the idiom RAII/RRID: 
 *    Resource Acquisition Is Initialisation / Resource Release Is Destruction
 * These are implemented via the Big Five methods of a class.
 *
 * This code is intended to illustrate the Big Three,
 *  1. the copy constructor,
 *  2. the assignment operator, and
 *  3. the destructor,
 * vs. the Big Five,
 *  1. the copy constructor,
 *  2. the assignment operator, 
 *  3. the destructor,
 *  4. the move constructor, and
 *  5. the move assignment operator.
 */

namespace csci320 {
  template <typename T>
  class Vector {
  public:

    // Default constructor.
    Vector() : n{0}, pt{nullptr} {
      std::cout << "Calling default destructor: Vector(const int)...\n";    
    }

    // Allocate-only constructor.
    Vector(const int n) : n{n} {
      std::cout << "Calling allocate-only constructor: Vector(const int)...\n";
      pt = std::unique_ptr<T[]>(new T[n]);
    }

    // Copy constructor (deep copy).
    Vector(const Vector &foo) : n{foo.n} {
      std::cout << "Calling copy constructor: Vector(const Vector&)...\n";
      pt = std::unique_ptr<T[]>(new T[n]);
      for (long int i = 0; i < n; i++) {
        pt[i] = foo.pt[i];
      }
    }

    // Move constructor.
    //  Vector(Vector &&foo) : n{foo.n}, pt{foo.pt} {
    Vector(Vector &&foo) {  
      std::cout << "Calling move constructor: Vector(consts Vector&&)...\n";
      n = {foo.n};
      foo.pt = std::move(pt);
    
      foo.n = 0;
      foo.pt = nullptr;
    }

    // We don't need an explicit destructor!
    // ~Vector() {
    //   std::cout << "~Vector()...\n";
    // }
  
    // Deep copy assignment.
    Vector &operator=(const Vector &foo) {
      std::cout << "Calling deep copy assignment: operator=(const Vector&)...\n";    
      n = foo.n;
      pt = std::unique_ptr<T[]>(new T[n]);
      for (long int i = 0; i < n; i++) {
        pt[i] = foo.pt[i];
      }    
      return *this;
    }
    
    // Move assignment.
    Vector &operator=(Vector &&foo) {
      std::cout << "Calling move assignment: operator=(const Vector&&)...\n";    
      n = foo.n;
      pt = foo.pt;

      foo.n = 0;
      foo.pt = nullptr;
    
      return *this;
    }

    // Indexing operator.
    inline T &operator[](const long int &i) const {return pt[i];}

    // Size method.
    inline long int size(void) const {return n;}

    // Return the address of the storage buffer.
    inline std::unique_ptr<T[]> get_ptr(void) const {return pt;}    

  private:
    long int n;
    std::unique_ptr<T[]> pt;
  };
}

  /* 
   * Guideline: Prefer these guidelines for making an operator a 
   * member vs. nonmember function:
   *   unary operators are members;
   *   = () [] and -> must be members;
   *   the assignment operators (+= –= /= *= etc.) must be members;
   *   all other binary operators are nonmembers.
   *                     -- Herb Sutter (C++ guru)
   */

  template <typename T>
  csci320::Vector<T> operator+(const csci320::Vector<T> &foo, const csci320::Vector<T> &bar) {
    csci320::Vector<T> sum(foo.size());
    for (long int i = 0; i < foo.size(); i++) {
      sum[i] = foo[i] + bar[i];
    }
    return sum;
  }

  template <typename T>
  std::ostream &operator<<(std::ostream &os, const csci320::Vector<T> &foo) {
    long int i;

    // os << "Buffer address: " << foo.get_ptr() << std::endl;
    for (i = 0; i < foo.size()-1; i++) {
      os << foo[i] << " ";
    }
    os << foo[i] << std::endl;
  
    return os;
  }
#endif
