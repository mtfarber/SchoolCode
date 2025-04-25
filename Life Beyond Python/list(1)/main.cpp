#include <iostream>
#include <string>

#include "list.hpp"

int main(int argc, char **argv)
{
  if (argc == 1) {
    printf("Usage: %s word1 word2 ...\n", argv[0]);
    return 0;
  }

  csci320::LIST<std::string> my_list(std::string("start"));

  for (int i = 1; i < argc; i++) {
    my_list.prepend(std::string(argv[i]));
    my_list.append(std::string(argv[i]));
    std::cout << my_list;
  }
  
  // rml_delete_list(my_list);
  
  return 0;
}

// int main(void)
// {
//   auto list = csci320::LIST<int>(42);
//   list.prepend(54);
//   list.append(54);
// }
