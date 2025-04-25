#ifndef __VECTOR_H__
#define __VECTOR_H__

typedef struct vector{
	long int n;
	double *a;

	struct vector* (*plus)(const struct vector *x, const struct vector *);
	struct vector* (*times)(const struct vector *x, const struct vector *);

	void (*free)(struct vector *x);
	long int (*length)(const struct vector *x);
} VECTOR;

VECTOR *create_vector(long int n);
void free_vector(VECTOR *x);