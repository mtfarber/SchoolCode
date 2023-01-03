#include <stdio.h>
#include "cards.h"
#include "callouts.h"

int main(int argc, char* argv[])
{
    int players = argc -2;

    CARD_T *cards = NULL;

    init_cards(players,&cards,argv);
    init_callouts(argv[argc-1]);

    printf("\n");
    print_cards(cards);

    CALLOUT_T callout;
    int count = 0;
    int win = 0;
    while (win == 0)
    {
        callout = get_callout();
        printf("Callout: ");
        printf("%s",get_callout_str(callout));
        count += 1;
        win = update_cards(callout,cards);

        if((count%5 == 0)&&(win == 0))
        {
            printf("\n");
            print_cards(cards);
        }
    }
    print_cards(cards);
    free_cards(&cards);
    free_callouts();
    
    
    return 0;
}