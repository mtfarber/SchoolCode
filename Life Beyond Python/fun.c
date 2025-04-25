#include <stdlib.h>
#include "fun.h"

VECTOR *plus(const VECTOR *x, const VECTOR *y){
	if(x -> n != y -> n){
		return NULL;
	}

	VECTOR *pt = malloc(sizeof(VECTOR));
	pt -> n = x -> n;
	pt -> a = (double *) malloc(pt -> n * sizeof(double));

	for(long int i = 0; i < pt -> n; i++){
		pt -> a[i] = x -> a[i] + y -> a[i];
	}

	return pt;

}

VECTOR *times(const VECTOR *x, const VECTOR *y){
	if(x -> n != y -> n){
		return NULL;
	}

	VECTOR *pt = malloc(sizeof(VECTOR));
	pt -> n = x -> n;
	pt -> a = (double *) malloc(pt -> n * sizeof(double));

	for(long int i = 0; i < pt -> n; i++){
		pt -> a[i] = x -> a[i] * y -> a[i];
	}

	return pt;
}

VECTOR *concat(const VECTOR *x, const VECTOR *y){
	VECTOR *pt = malloc(sizeof(VECTOR));
	pt -> n = x -> n + y -> n;
	pt -> a = (double *) malloc(pt -> n * sizeof(double));

	long int i;
	for(i = 0; i < x -> n; i++){
		pt -> a[i] = x -> a[i];
	}
	for(i = 0; i < y -> n; i++){
		pt -> a[x -> n + i] = y -> a[i];
	}

	return pt;
}
