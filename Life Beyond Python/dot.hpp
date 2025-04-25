#ifndef __DOT_HPP__  // These two preprocessor directives insure the contents
#define __DOT_HPP__  // of this file cannot be included more than once.

#include <vector>

namespace csci320 {
  template <typename S, typename T>  // S is the return type and T is the input type.
  S dot(const std::vector<T> &x, const std::vector<T> &y)
  {
    S sum = 0.0;
    for (long int i = 0; i < x.size(); i++){
      sum += (x[i] * y[i]);
    }
    return (sum);
  }
};

#endif
