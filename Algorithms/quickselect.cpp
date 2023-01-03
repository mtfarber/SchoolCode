#include <iostream>
#include <stdlib.h>
#include <vector>

using namespace std;

int partition(vector<int> &input, int left, int right){
  int index = left + rand() % (right-left);
  swap(input[index], input[right]);

  int pivot = input[right];
  int i = left;

  for (int j = left; j < right; j++){
    if (input[j] < pivot){
      swap(input[i], input[j]);
      i++;
    }
  }

  swap(input[right], input[i]);
  return i;
}


int selection(vector<int> &input, int left, int right, int k){

  //base case
  if (left == right){
    return input[left];
  }

  int pivot = partition(input, left, right);

  if (pivot == k){
    return input[pivot];
  }

  else if (pivot < k){
    return selection(input, pivot+1, right, k);
  }

  else{
    return selection(input, left, pivot-1, k);
  }
}

int quickselect(vector<int> &input, int k){
  int n = input.size()-1;
  int left = 0;
  int value = selection(input, 0, n, n-k+1);

  return value;
}

int main(void){
  vector<int> S{999, 12, 3, 201, 350, 163, 42, 101, 321, 666, 9, 735, 233, 31, 560};
  cout << quickselect(S, 13) << endl;
  cout << quickselect(S, 8) << endl;
  cout << quickselect(S, 3) << endl;
}
