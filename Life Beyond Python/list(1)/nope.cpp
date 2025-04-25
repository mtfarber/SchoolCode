#include <memory>

void foo(void)
{
  auto p = std::shared_ptr<int[]>(new int[42]);
  delete p;  // You cannot explicitly delete a shared pointer!
}
