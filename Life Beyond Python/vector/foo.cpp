#include <iostream>
#include <memory>

int main(void)
{
  auto m = std::unique_ptr<int>(new int[4]);
  auto n = std::unique_ptr<int>(new int[4]);

  std::cout << m << std::endl;  
  std::cout << n << std::endl;

  m = n;
  // m = std::move(n);
  std::cout << m << std::endl;
  std::cout << n << std::endl;
}
