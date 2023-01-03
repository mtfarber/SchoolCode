
#include <iostream>
#include <string.h>
#include <vector>

using namespace std;

int maximum(int a, int b)
{
  if (a > b){
    return a;
  }
  else{
    return b;
  }
}

vector<char> lcs(char* x, char* y, int m, int n)
{
    int array[m + 1][n + 1];
    int i;
    int j;
    vector<char> list;

    for (i = 0; i <= m; i++) {
        for (j = 0; j <= n; j++) {
            if (i == 0 || j == 0){
                array[i][j] = 0;
            }

            else if (x[i - 1] == y[j - 1]){
                //array[i][j] = array[i - 1][j - 1] + 1;
                list.push_back(x[i-1]);
            }

            else{
                array[i][j] = maximum(array[i - 1][j], array[i][j - 1]);
            }
        }
    }
    return list;
}

int main(int argc, char*argv[])
{
    char X[10];
    strcpy(X,argv[1]);
    char Y[10];
    strcpy(Y,argv[2]);

    int m = strlen(X);
    int n = strlen(Y);

    vector <char> LCS = lcs(X,Y,m,n);
    for (int i=0; i<LCS.size(); i++){
      cout<<LCS[i];
      if (i != (LCS.size()-1)){
        cout << ",";
      }
      else{
        cout << endl;
      }
    }

    return 0;
}
