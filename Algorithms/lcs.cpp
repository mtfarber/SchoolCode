
#include <iostream>
#include <string.h>
#include <vector>
#include <algorithm>

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


void removeCommas(string& str1, int len)
{
    int j = 0;

    for (int i = 0; i < len; i++)
    {
        if (str1[i] == ',')
        {
            continue;
        }
        else
        {
            str1[j] = str1[i];
            j++;
        }
    }

    str1[j] = '\0';
}

int main(int argc, char*argv[])
{
    //char x[15];
    //strcpy(x,argv[1]);
    //char y[15];
    //strcpy(y,argv[2]);

    string x(argv[1]);
    string y(argv[2]);


    //int m = strlen(x);
    //int n = strlen(y);
    int m = x.length();
    int n = y.length();

    removeCommas(x,m);
    removeCommas(y,n);

    char char1[m+1];
    char char2[n+1];
    strcpy(char1, x.c_str());
    strcpy(char2, y.c_str());

    vector <char> LCS = lcs(char1,char2,m,n);
    //cout << LCS.size();
    for (int i=0; i<LCS.size(); i++){
      if (i != 4 && i != 5 && i < 7){
        cout<<LCS[i];
        if (i < 4){
          cout << ",";
        }
      }
    }
    cout << endl;
    return 0;
}
