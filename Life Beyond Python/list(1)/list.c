/******************************************************
 * Include the file list.h in the current directory.
 */

#include "list.h"

/******************************
 * The public interface.  
 */

LIST *new_list(const char *value)
{
  /* Create a new list and initialize its single node to "value". */
  LIST *list = (LIST*) malloc(sizeof(LIST));

  return list;  /* Return a pointer to the new list. */
}

void prepend(LIST *list, const char *value)
{
  /* Add a new node at the head of the list. */
  /* Update the head pointer in the list. */
}

void append(LIST *list, const char *value)
{
  /* Add a new node at the tail of the list. */
  /* Update the tail pointer in the list. */  
}

void delete_list(LIST *list)
{
  /* Delete a list and free its allocated memory. */
}

/*********************************************************
 * The following code is provided for your convenience.
 * You do not have to use it.
 */

NODE *new_node(const char *value)
{
  /* Create a new node containing the string "value". */
  NODE *node = (NODE*) malloc(sizeof(NODE));

  node->value = (char*) malloc((strlen(value) + 1) * sizeof(char));
  strcpy(node->value, value);
  node->previous = NULL;
  node->next = NULL;
  
  return node;  /* Return a pointer to the new node. */
}

void print_list(const LIST *list)
{
  /* Print the contents of a list. */
  for (NODE *node = list->head; node != NULL; node = node->next) {
    printf("%s\n", node->value);
  }
}
