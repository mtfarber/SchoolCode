#ifndef __FUN_H__
#define __FUN_H__

#include "vector.h"

VECTOR *plus(const VECTOR *x, const VECTOR *y);
VECTOR *times(const VECTOR *x, const VECTOR *y);

VECTOR *concat(const VECTOR *x, const VECTOR *y);

#endif