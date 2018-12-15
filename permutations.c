#include <iostream>
using namespace std;

void niceString(int *Array, int i, int difference, int n);

int main() {
	cout<<" hello" << endl;
	int n;
	cout<<" enter an odd number >= 3: ";
	cin >> n;
	int *Array=new int [n];
	niceString(Array,0,0,n);
	cout<< endl;
	cout <<" to exit enter a number: ";
	int t;
	cin >> t;
}

void niceString(int *Array, int i, int difference, int n) {
	int countOnes=0;
	int countZeros=0;
	int count=0;
	if (i==n) {
		if ( difference == 3) {
			for(int x=0; x<n; x++) {
          			if(Array[x]==1) countOnes++;
          			if(Array[x]==0) countZeros++;
          			if(countOnes<=countZeros)  return;
			}
			if((countOnes-countZeros)==3) {
          			cout<<" new string = ";
          			for(int y=0; y<n; y++) {
            			cout<< Array[y]<< ",";
            			count++;
            			if(count==n)  cout<<"\n";
          			}
			}
		}
		return;
	}
	if ( difference < 0 ) return;
	Array[i]=0;
	niceString (Array,i+1,difference-1,n);
	Array[i]=1;
	niceString (Array,i+1,difference+1,n);
}
