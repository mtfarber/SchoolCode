#include <stdlib.h>

#include "fun.h"
#include "vector.h"

long int length(const struct vector *x)
{
  return x->n;
}

VECTOR *create_vector(long int n)
{
  VECTOR *pt = malloc(sizeof(VECTOR));
  pt->n = n;
  pt->a = (double*) malloc(n * sizeof(double));

  pt->plus = &plus;  /* Pointer to function. */
  pt->times = &times;  /* You'll need to change this to point to times()! */
  pt->free = &free_vector;

  pt->length = &length;

  return pt;
}

void free_vector(VECTOR *x)
{
  /*
   * Free all memory associatewd with a VECTOR*.
   * No bugs here!
   */

  free(x->a);
  free(x);
}
