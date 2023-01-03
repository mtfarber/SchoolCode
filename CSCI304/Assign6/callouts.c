#include <stdio.h>
#include <stdlib.h>
#include "callouts.h"
#include "bingo.h"

static CALLOUT_T *calloutArr;

static void init_callouts (char *fn)
{
    int lines = 0;
    char *temp;
    char str[5];

    FILE *fptr = fopen(fn,"r");
    
    if(fptr == NULL)
        printf("File not found, please check the file name. \n");
    
    while(fscanf(fptr,"%*[^\n]%*c") == 0)
        lines += 1;
    
    
    fseek(fptr,0,SEEK_SET);
    calloutArr = (CALLOUT_T*) calloc(lines,sizeof(CALLOUT_T));

    if(calloutArr == NULL)
        printf("Error: Memory incorrectly allocated \n");
    
    char letters;
    int numbers;
    CALLOUT_T callout;
    
    for (int i = 0; i < lines; i++)
    {
        fscanf(fptr,"%s",str);
        sscanf(str,"%c%i",&letters,&numbers);

        switch(letters)
        {
            case 'B':
                callout.letter = B;
                break;
            case 'I':
                callout.letter = I;
                break;
            case 'N':
                callout.letter = N;
                break;
            case 'G':
                callout.letter = G;
                break;
            case 'O':
                callout.letter = O;
                break;
        }
        callout.num = numbers;
        calloutArr[i] = callout;
    }
    fclose(fptr);
    

//reads the file (named fn) once to find out how many callouts are listed
//dynamically allocates array to hold callouts (static and global in this callouts.c file)
//iterates through file again to store each callout in the array; characters are changed to BINGO_T enumerations
}

CALLOUT_T get_callout(void)
{
    static int index = -1;
    
    index +=1;

    return calloutArr[index];

}
char *get_callout_str (CALLOUT_T callout){
    //converts callout to printable string CALLOUT_T get_callout (void)
    //returns next callout from array (use static variable to hold current index)
    static char str[5];
    sprintf(str,"%c%i", "BINGO"[callout.letter],callout.num);
    return str;

}
void free_callouts (void){
    free(calloutArr);
    calloutArr = NULL;
//deallocates space for static callouts array
}