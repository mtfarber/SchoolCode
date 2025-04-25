#include <iostream>
#include <memory>

int main(void)
{
  auto p = std::unique_ptr<int[]>(new int[42]);
//  auto q = p;  // You cannot clone a unique pointer!
  auto r = std::move(p);
}
