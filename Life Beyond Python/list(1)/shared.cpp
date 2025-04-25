#include <iostream>
#include <memory>

void foo3(std::shared_ptr<int[]> &pt0)
{
  auto pt3 = pt0;
  std::cout << "In call to foo3().  No. of pointers to array: " << pt0.use_count() << std::endl;
  // At this point pt3 goes out of scope and the number of pointers to the array is decremented.  
}

void foo2(std::shared_ptr<int[]> &pt0)
{
  auto pt2 = pt0;
  std::cout << "Just before call to foo3().  No. of pointers to array: " << pt0.use_count() << std::endl;  
  foo3(pt0);
  std::cout << "Just after call to foo3().   No. of pointers to array: " << pt0.use_count() << std::endl;
  // At this point pt2 goes out of scope and the number of pointers to the array is decremented.  
}

void foo1(std::shared_ptr<int[]> &pt0)
{
  auto pt1 = pt0;
  std::cout << "Just before call to foo2().  No. of pointers to array: " << pt0.use_count() << std::endl;  
  foo2(pt0);
  std::cout << "Just after call to foo2().   No. of pointers to array: " << pt0.use_count() << std::endl;
  // At this point pt1 goes out of scope and the number of pointers to the array is decremented.  
}

void foo0(void)
{
  auto pt0 = std::shared_ptr<int[]>(new int[42]);  // Allocate an array of ints.
  std::cout << "Just before call to foo1().  No. of pointers to array: " << pt0.use_count() << std::endl;  
  foo1(pt0);
  std::cout << "Just after call to foo1().   No. of pointers to array: " << pt0.use_count() << std::endl;
  // At this point pt0 goes out of scope and the number of pointers to the array is decremented.
  // The number of pointers to the array becomes zero so it is safe to deallocate the array.
}

int main(void)
{
  foo0();
}
