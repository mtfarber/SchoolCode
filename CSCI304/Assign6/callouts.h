#ifndef callouts_h
#define callouts_h
#include "bingo.h"


typedef struct 
{
    enum BINGO_T letter;
    int num;
} CALLOUT_T;

/* static void */ init_callouts (char *fn);

char *get_callout_str(CALLOUT_T callout);

CALLOUT_T get_callout(void);

void free_callouts(void);

#endif

