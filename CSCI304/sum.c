#include <stdio.h>
int summation (int n){//computes (7i - 10)
  int result = 0;
  int i;
  for (i = 0; i < n ; i++){//uses additon to add 7 n times
      result += 7;
  }
  result -= 10;//subtracts 10 from result of the additon
  return(result);
}

main (){//runs from 0 to n calling summation each time and adds it to an array
    int n = 10;
    int arr[n];//sets size of array to n
    int i;
    int sum = 0;
    for (i = 0; i <= n; i++){//loop adds 0 to n to the array
        sum += summation(i);
        arr[i] = sum;
    }
    int count = 0;
    for (count = 0; count <= n; count++){//loop prints out each value in the array along with its n value
        printf("%2d %2x :   %3d   %08x\n",count,count,arr[count],arr[count]);
    }
}
