/******************************************************
 * Include the file list.h in the current directory.
 */

#include "list.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/******************************
 * The public interface.  
 */

NODE *new_node(const char *value);

LIST *new_list(const char *value)
{
  /* Create a new list and initialize its single node to "value". */
  LIST *list = (LIST*) malloc(sizeof(LIST));
  NODE *node = new_node(value);
  list -> head = node;
  list -> tail = node;

  return list;  /* Return a pointer to the new list. */
}

void prepend(LIST *list, const char *value)
{
  /* Add a new node at the head of the list. */
  /* Update the head pointer in the list. */
  NODE *new = new_node(value);
  NODE *old = list -> head;
  new -> next = old;
  old -> previous = new;
  list -> head = new;
}

void append(LIST *list, const char *value)
{
  /* Add a new node at the tail of the list. */
  /* Update the tail pointer in the list. */
  NODE *new = new_node(value);
  NODE *old = list -> tail;
  old ->next = new;
  new -> previous = old;
  list -> tail = new;
}

void delete_list(LIST *list)
{
  /* Delete a list and free its allocated memory. */
  NODE *curr = list -> head;
  while(curr -> next != NULL){
    curr = curr -> next;
    free(curr -> previous -> value);
    free(curr -> previous);
  }
  free(curr -> value);
  free(curr);
  free(list);
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
