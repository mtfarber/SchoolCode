
#include <string.h>
#include "utils.h"

//checks if each letter in the string is lowercase
//if it is, then 32 is subtracted from the ascii value to get the uppercase letter
void upper (char str[]){
  int i;
  for(i = 0; i < strlen(str); i++){
    if (str[i] > 96 && str[i] < 123){
      str[i] -= 32;
    }
  }
}
