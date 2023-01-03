#include <stdio.h>
#include <stdlib.h>
#include "cards.h"
#include "bingo.h"
#include "callouts.h"



static void init_card (int grid [][5], char *fn)
{
    FILE *fptr = fopen(fn,"r");
    int val = 0;
    if(fptr == NULL)
    {
        printf("File not found, please check the name \n");
        exit(0);
    }
    while (!feof(fptr))
    {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
            {
                fscanf(fptr,"%d",&val);
                grid[i][j] = val;
            }
    }
    fclose(fptr);
//- initializes the rows of the BINGO card grid with the values found in the file named fn
}
void init_cards (int num_players, CARD_T **list, char *fns [])
{
    for (int i = 1; i < num_players + 1; i++)
    {
        CARD_T *node = (CARD_T*) malloc (sizeof(CARD_T));
        node->player = i;
        init_card(node->grid,fns[i]);
        int counter;
        for (counter = B; counter <= O; ++counter)
            node->called[counter] = 0;
        node->called[2] = 1;
        node->next = NULL;
        if(*list == NULL)
            *list = node;
        else
        {
            CARD_T *curr = *list;
            while (curr->next != NULL)
                curr = curr->next;
        }
        curr->next = node;
        
    }
//- allocates space for each card and uses files (with names in fns) ; uses 1 through num_players in loop
//- sets player number field
// calls init_card to initialize numbers in grid
//initializes all called row char’s to 0
//sets middle entry in called for free space
//appends each card to end of list
}
void update_grid (CARD_T *pcard, CALLOUT_T callout){
    for (int r = 0; r < 5; ++r)
        if(pcard->grid[r][callout.letter] == callout.num)
            pcard->called[r] = (1 << callout.letter);
//sets any callout value in called entry using bit-wise operation
}
int check_grid (CARD_T *pcard)
{
    int win = 1;
    int horizontal = 1;
    int vertical = 1;
    int diagonal = 1;
    int row, col;
    for ( row = 0; row < 5; ++row)
    {
        for ( col = 0; col < 5; ++col)
        {
            if(*pcard->grid[row][col] == 0)
            {
                horizontal = 0;
                break;
            }
        }
    }
    for (int col = 0; col < 5; ++col)
    {
        for (int row = 0; row < 5; ++row)
        {
            if(*pcard->grid[row][col] == 0)
            {
                vertical = 0;
                break;
            }
        }
    }
    for (int rowCol = 0; rowCol < 5; ++rowCol)
    {
            if(*pcard->grid[row][col] == 0)
            {
                diagonal = 0;
                break;
            }
        
    }
    if (horizontal == 0 && vertical ==0 && diagonal ==0)
        win =0;
    return win;

//checks grid in pcard for winner (row, column, or either diagonal)
//returns 1 if winner, 0 otherwise
}
int update_cards (CALLOUT_T call_num, CARD_T *list)
{
    CARD_T *node;
    *node = *list;
    int check = -1;
    int ret = 0;
    while (*node != NULL)
    {
        update_grid(*node,call_num);
        check = check_grid(*node);
        if(check == 1)
        {
            printf("%d",*node->player);
            printf("%s"," BINGO!");
            ret = 1;
        }
    }
    return ret;

//loops through each node in the list, calls update_grid for each card
//calls check_grid for each card, looking for a winner
//if one is found prints player number and “BINGO!”
//returns 1 if winner found, 0 otherwise
}
void print_cards (CARD_T *list)
{
    while(list != NULL){
        printf("Player %i                   Callouts Marked\n",list->player);
        int counter = 0;
        printf("---------------------------------------------------------------------------------------------\n");
        printf("|   B   |   I   |   N   |   G   |   O   |           |   B   |   I   |   N   |   G   |   O   |\n");
        printf("|-------|-------|-------|-------|-------|-----------|-------|-------|-------|-------|-------|\n");

        for (int i = 0; i < 5; i++)
        {
            printf("|");
            for (int count = B; count <=O; count++)
            {
                if(i==2 && count == N)
                    printf("    *   |");
                else
                {
                    printf("    %2i |",list->grid[i][counter]);
                }
            }
            printf("\n");
            if(i == 4)
                printf("----------------------------------------             ----------------------------------------\n");
            else
            {
                printf("|-------|-------|-------|-------|-------|           |-------|-------|-------|-------|-------|\n");
            }
            
        }
        printf("\n");
        list = list->next;
    }

//prints player number, original BINGO card, and BINGO card with covered callouts for each card in the list
}
void free_cards (CARD_T **list)
{
    while(*list != NULL)
    {
        CARD_T *curr = *list;
        (*list) = (*list)->next;
        free(curr);
    }

//deallocates space for each card in the list
}