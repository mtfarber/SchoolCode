#include <stdlib.h>
int *change (int amount){
  if (amount < 0){
    void *null = NULL;
    return null;
  }
  int *result;
  result = (int*)malloc(4*sizeof(int));
  int change[4] = {25, 10, 5, 1};
  for (int i = 0; i<4; i++){
    result[i] = amount/change[i];
    amount -= result[i]*change[i];
  }
  return(result); 
}
