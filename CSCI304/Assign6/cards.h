#ifndef cards_h
#define cards_h
#include <stdio.h>
#include "callouts.h"

typedef struct CARD{
    int player;
    int grid[5][5];
    char called[5];
    struct CARD *next;
}CARD_T;

void init_cards(int num_players, CARD_T **list, char *fns []);

/* static */ void init_card(int grid[][5],char *fn);

void update_grid(CARD_T *pcard, CALLOUT_T callout);

int check_grid(CARD_T *pcard);

int update_cards (CALLOUT_T cell_num,CARD_T *list);

void print_cards (CARD_T *list);

void free_cards(CARD_T **list);
#endif
