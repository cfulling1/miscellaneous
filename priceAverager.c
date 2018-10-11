#include <stdio.h>

int main() {
	char letter;
	int itemCount = 0;
	float price;
	float totalPrice = 0;
	int min;
	int max;

	FILE * fp = fopen("prices.html", "r");
	if (fp == NULL) {
		printf("There was an error opening your source file.\n");
		return 0;
	}

	printf("Enter in your min price followed by enter (just the number, no dollar sign): ");
	scanf("%d", &min);
	printf("Enter in your max price followed by enter (just the number, no dollar sign): ");
	scanf("%d", &max);

	while (fscanf(fp, "%c", &letter) == 1) {
		if (letter == '$') {
			if (fscanf(fp, "%f", &price) == 1) {
				itemCount++;
				totalPrice += price;
			}
		}
	}
	printf("Average price: $%.2f\n", (totalPrice - min - max) / (itemCount - 2));
	return 0;
}
