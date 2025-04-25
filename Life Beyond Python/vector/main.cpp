#include <iostream>
#include <vector>

#include "Vector.hpp"

int main (int argc, char **argv)
{
  std::cout << "line " << __LINE__+1 << ": ";      
  csci320::Vector<double> foo(42);

  std::cout << "line " << __LINE__+1 << ": ";    
  auto bar = csci320::Vector<double>(42);

  std::cout << "line " << __LINE__+1 << ": ";      
  std::vector<csci320::Vector<float>> foovec(3, csci320::Vector<float>(42));

  for (int i = 0; i < foo.size(); i++) {
    foo[i] = bar[i] = i;
  }
  
  std::cout << "line " << __LINE__+1 << ":\n";
  auto foobar = foo + bar;
  std::cout << foobar;

  std::cout << "line " << __LINE__+1 << ":\n";
  return 0;
}
