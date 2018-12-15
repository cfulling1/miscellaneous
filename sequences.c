#include <iostream>
using namespace std;

void permute(int *Array, int *bufArray, int i, int n);

int main() {
	cout<<" hello" << endl;
	int n;
	cout<<" enter a number for array size: ";
	cin >> n;
	int *Array=new int [n];
	int *bufArray=new int [n];
	int j;
	for (j=0; j < n; j++)
		Array[j]=j+1;
	permute(Array,bufArray,0,n);
	cout<< endl;
	cout <<" to exit enter a number: ";
	int t;
	cin >> t;
}


void permute(int *Array, int *bufArray, int i, int n) {
  int flag = 0;
  int count = 0;
	if (i==n) {
		for(int j=0; j < n; j++)  bufArray[j]=Array[j];
		for(int x=0; x < n; x++) {
     			if(bufArray[x]==2)  flag=1;
      		if((bufArray[x]==1) && (flag==1)) {
        			for(int y=0; y<n; y++) {
          				cout<< Array[y];
          				count++;
          				if(count<n)  cout<<",";
          				if(count==n) cout<<"\n";
        			}
      		}
		}
		return;
  	}
	else {
		int temp;
		int t;
		for (t=i; t < n; t++) { // exchange Array[i],Array[t]
			temp=Array[i];
			Array[i]=Array[t];
			Array[t]=temp;
			permute(Array,bufArray,i+1,n); // exchange back 			Array[t],Array[i]
			temp=Array[i];
			Array[i]=Array[t];
			Array[t]=temp;
		}
 	}
}
