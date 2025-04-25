/* DO NOT CHANGE THIS FILE! */

#ifndef __LIST_H__
#define __LIST_H__

typedef struct node {
  char *value;  /* Pointer to the string we are storing. */
  struct node *previous;  /* Pointer to the preceding node in the list. */
  struct node *next;  /* Pointer to the next node in the list. */
} NODE;

typedef struct list {
  NODE *head;  /* Pointer to the first node in the list. */
  NODE *tail;  /* Pointer to the last node in the list. */
} LIST;

/* Function prototypes the public interface. */
LIST *new_list(const char *value);
void prepend(LIST *list, const char *value);
void append(LIST *list, const char *value);
void delete_list(LIST *list);

#endif
