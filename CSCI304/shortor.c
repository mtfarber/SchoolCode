// Matthew Farber, 05/07/2021, TR 2:00-3:20, Assignment 8

#include <stdio.h>

int neg (int sum){
  return (0 - sum);
}

int rmult (int x, int y){
  if (x < y){
    return rmult(y, x);
  }
  else if(y != 0){
    return (x + rmult(x, y - 1));
  }
  else{
    return 0;
  }
}

int square (int num){
  if (num<0){
    num = neg(num);
  }
  return rmult(num,num);
}

int rfact (int n){
  int result;
  if (n <=1){
    result = 1;
  }
  else{
    result = n * rfact(n-1);
  }
  return result;
}

void main(){
  char arr[] = {4, -9, 13, 0};
  int num;
  int num2;
  int temp;
  //iteration variable
  int i = 0;
  num = arr[0];
  while (num != 0){
    printf("%12d    0x%08x\n", num, num);

    temp = neg(num);
    printf("%12d    0x%08x\n", temp, temp);

    temp = square(num);
    printf("%12d    0x%08x\n", temp, temp);

    if (num<0){
      num2 = neg(num);
    }
    else{
      num2 = num;
    }
    temp = rfact(num2);
    printf("%12d    0x%08x\n", temp, temp);


    int status = 0;
    if (num%2 != 0){
      //set last bit to 1
      status += 1;
    }
    if (num < 0){
      //set second to last bit to 1
      status += 2;
    }
    if (square(num) > 100){
      //set third bit to 1
      status += 4;
    }
    //set 2^4 bit to 1
    status += 16;
    printf("                0x%08x\n", status);
    printf("                0x11111111\n");
    i++;
    num = arr[i];
  }
}
