#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <math.h>
#include "calc.h"

unsigned short get_binary_op (char *bin){
  //col holds the value of each position of the binary string and is incremented each time
  short col = 1;
  unsigned short val = 0;
  //val holds total value and col is added every time a 1 is detected in the string
  short i;
  for (i=(strlen(bin) - 1); i>=0; i--){
    if(bin[i] == '1'){
      val += col;
    }
    col *= 2;
  }
  return(val);
}

void convert_to_binary (short acc, char *bin){
  //b is a placeholder string
  bin_str b = "0000000000000000";
  //col is initialized to 2^15 becasue that is the highest value for the last binry bit
  unsigned short col = pow(2,15);
  //duplicate of acc is made to uacc so we can work with unisgned values
  unsigned short uacc = acc;
  short i;
  //for loop runs through the user given short and subtracts the powers of 2 accordingly to get the binary string
  //there is a 1 placed into the string is the value of col is less than acc
  for (i=0; i<=15; i++){
    if(acc>=col){
      b[i] = '1';
      uacc -= col;
    }
    col = (col/2);
  }
  //runs through the string of bits and places them with spaces into the passsed in value bin
  short counter = 0;
  for(i=0; i<=15; i+=5){
    bin[i] = b[counter];
    bin[i+1] = b[counter+1];
    bin[i+2] = b[counter+2];
    bin[i+3] = b[counter+3];
    bin[i+4] = ' ';
    counter += 4;
  }
}

short get_operand (char mode)//takes in mode and reads in value accordingly
{
  short value;
  bin_str b;
  switch(mode){
    case 'O':
      printf("Enter octal value: ");
      scanf("%ho",&value);
      break;
    case 'H':
      printf("Enter hex value: ");
      scanf("%hx",&value );
      break;
    case 'D':
    printf("Enter decimal value: ");
    scanf("%hd",&value);
    break;
    case 'B':
      printf("Enter binary value: ");
      scanf("%s",b);
      //retrieves the short int value from the string entered by the user
      value = get_binary_op(b);
    break;
  }
  return(value);
}

void add (short *acc, char mode){
  //duplicate acc is made to preserve the original value
  short original = *acc;
  //the value to be added is retrieved from the user
  short op = get_operand(mode);
  //value is added to the mutable short, acc
  *acc += op;
  //if statements check for both types of overflow by comparing values
  if (original > 0 && op > 0 && *acc < 0){
    printf("Positive Overflow\n");
  }
  else if (original < 0 && op < 0 && *acc > 0){
    printf("Negative Overflow\n");
  }
}

void subtract (short *acc, char mode){
  //duplicate acc is made to preserve the original value
  short original = *acc;
  short op = get_operand(mode);
  //value is subtracted from the mutable short, acc
  *acc -= op;
  //if statements check for both types of overflow by comparing values
  if (original < 0 && op > 0 && *acc > 0){
    printf("Negative Overflow\n");
  }
  else if (original > 0 && op < 0 && *acc < 0){
    printf("Positive Overflow\n");
  }
}

void print_acc (short acc, char mode)
{
//prints out accumulator while converting to binary, octal, hexadecimal, and decimal
//bin_str b is used to hold the string representation of the decimal value
  bin_str b;
  convert_to_binary(acc,b);
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
  if (mode=='B'){
    printf("Bin");
  }
  printf(" *\n");
  printf("*   Binary  :  %s    *\n",b);
  printf("*   Hex     :  %04hX                    *\n",acc);
  printf("*   Octal   :  %06ho                  *\n",acc);
  printf("*   Decimal :  %-6hd                  *\n",acc);
  printf("****************************************\n\n");

}

char print_menu (void)//main menu loop that calls functions depending on the selction
{
  char mode[10];
  char new_mode;
  char valid_input[17] = "BOHDCSQ&|^~<>+-N";
  char valid_characters[] = "BOHDCSQ";
  int tracker = 0;

    //print function to create the menu
    do{
        if(tracker>0){
            printf("\nInvalid Option: %s\n\n",mode);
        }
      printf("Please select one of the following options:\n\n");
      printf("B  Binary Mode             &  AND with Accumulator           +  Add to Accumulator\n");
      printf("O  Octal Mode              |  OR  with Accumulator           -  Subtract from Accumulator\n");
      printf("H  Hexidecimal Mode        ^  XOR with Accumulator           N  Negate Accumulator\n");
      printf("D  Decimal Mode            ~  Complement Accumulator\n\n");
      printf("C  Clear Accumulator       <  Shift Accumulator Left\n");
      printf("S  Set Accumulator         >  Shift Accumulator Right\n\n");
      printf("Q  Quit\n\n");
      printf("Option: ");
      scanf("%s",mode);
      if (strchr(valid_characters,mode[0])){
        new_mode = toupper(mode[0]);
      }
      else{
        new_mode = mode[0];
      }
      tracker += 1;
    } while((strlen(mode) !=1) || (!strchr(valid_input,new_mode)));/*(new_mode != 'O' && new_mode != 'H' && new_mode != 'D' && new_mode != 'C' && new_mode != 'S' && new_mode != 'Q'))*/
    return new_mode;
}

int main (void)
{
//placeholder variables for the default mode and accumulator value of 0
  short acc = 0;
  char mode = 'D';
  char saved_mode = 'D';
  short shift;
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
      case '&':
        acc = acc&get_operand(mode);
        break;
      case '|':
        acc = acc|get_operand(mode);
        break;
      case '^':
        acc = acc^get_operand(mode);
        break;
      case '~':
      acc = ~acc;
        break;
      case '<':
        printf("Enter number of positions to left shift accumulator: \n");
        scanf("%hd",&shift);
        acc = acc << shift;
        break;
      case '>':
        printf("Enter number of positions to right shift accumulator: \n");
        scanf("%hd",&shift);
        acc = acc >> shift;
        break;
      case '+':
        //passes mutable acc and changes the value by adding the user inputed value
        add(acc,mode);
        break;
      case '-':
      //passes mutable acc and changes the value by subtracting the user inputed value
        subtract(acc,mode);
        break;
      case 'N':
      acc = -acc;
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
        if (mode == 'B'){
          printf("Mode is Binary\n");
    }
        break;

  }
}
}
