#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

short get_operand (char mode)//takes in mode and reads in value accordingly
{
  short value;
  switch(mode){
    case 'O':
      printf("Enter octal value: ");
      scanf("%ho\n",&value);
    case 'H':
      printf("Enter hex value: ");
      scanf("%hx\n",&value );
    case 'D':
    printf("Enter decimal value: ");
    scanf("%hd\n",&value);
  }
  return(value);
}

void print_acc (short acc, char mode)
{
//prints out accumulator while converting to octal, hexadecimal, and decimal
  printf("****************************************\n");
  printf("* Accumulator:         Input Mode: ");
  if (mode =='O'){
    printf("Oct");
  }
  if (mode =='H'){
    printf("Hex");
  }
  if (mode =='D'){
    printf("Dec");
  }
  printf(" *\n");
  printf("*   Hex     :  %04hX                    *\n",acc);
  printf("*   Octal   :  %06ho                  *\n",acc);
  printf("*   Decimal :  %-6hd                  *\n",acc);
  printf("****************************************\n\n");

}

char print_menu (void)//main menu loop that calls functions depending on the selction
{
  char mode[10];
  char new_mode;
    //print function to create the menu
    do{
      printf("Please select one of the following options:\n\n");
      printf("O  Octal Mode\n");
      printf("H  Hexidecimal Mode\n");
      printf("D  Decimal Mode\n\n");
      printf("C  Clear Accumulator\n");
      printf("S  Set Accumulator\n\n");
      printf("Q  Quit\n\n");
      printf("Option: ");
      scanf("%s",mode);
      new_mode = toupper(mode[0]);
    } while(strlen(new_mode) == 1 && new_mode != 'O' && new_mode != 'H' && new_mode != 'D' && new_mode != 'C' && new_mode != 'S' && new_mode != 'Q');
    return new_mode;
    //use do while loop to run at least once
    //use character array for the string variable
    //scan in as a string for scanf
    //dont use & in the scanf
    //break up printf into multiple statements
}

int main (void)
{
//placeholder variables for the default mode and accumulator value of 0
  short acc = 0;
  char mode = 'D';
  char saved_mode = 'D';
//saved_mode holds the previously selected mode if the user wants to enter a value
  while(saved_mode != 'Q'){
    printf("\n");
    print_acc(acc,mode);//print accumulator first
    saved_mode = print_menu();//asks for an input and saves mode value
//decides which funtion to call based on the mode received from user
    switch(saved_mode){
      case 'C':
        acc = 0;
        break;
      case 'S':
        acc = get_operand(mode);
        break;
      case 'Q':
        break;
      default:
        //prints mode when user changes it
        mode = saved_mode;
        if (mode == 'O'){
          printf("Mode is Octal\n");
      }

        if (mode == 'H'){
          printf("Mode is Hexadecimal\n");
      }

        if (mode == 'D'){
          printf("Mode is Decimal\n");
    }
        break;

  }
}
}
