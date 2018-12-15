#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define BUFFER 4
#define NUM_SUITS 4
#define NUM_VALUES 13

const char* suits[NUM_SUITS] = { "Hearts", "Diamonds", "Clubs", "Spades" };
const char* values = "A235467890JQK";
const char* values_pretty[NUM_VALUES] = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

char* read_hand(size_t* length);
void print_hand(char* hand, size_t length);
char card_value(char val, char suit);
char parse_val(char c);
char parse_suit(char c);
char get_suit(char card);
char get_value(char card);
char* print(char card);
void SortHandOfCards(char* hand, size_t length, bool sortDescending);

int main() {
	size_t length;
	char* hand = read_hand(&length);
	SortHandOfCards(hand, length, true);
	print_hand(hand, length);
	free(hand);
}

char* read_hand(size_t* length) {
	char* hand = (char*) malloc(BUFFER * sizeof(char));
	size_t cur_buf = BUFFER;
	*length = 0;
	printf("Enter your hand (ex. 3H, AD, JC, 7S): ");
	char* str = (char*) malloc(1024);
	fgets(str, 1024, stdin);
	str = strtok(str, "\n");
	char* tok = strtok(str, ",");
	while (tok) {
		if (strlen(tok) != 2) {
			break;
		}
		char val = parse_val(tok[0]);
		char suit = parse_suit(tok[1]);
		if (val == -1 || suit == -1) {
			break; // invalid card entered;
		}
		char card = card_value(val, suit);
		if (*length == cur_buf) {
			cur_buf += BUFFER;
			hand = (char*) realloc(hand, cur_buf * sizeof(char));
		}
		hand[*length] = card;
		(*length)++;
		tok = strtok(NULL, ",");
	}
	free(str);
	return hand;
}

char card_value(char val, char suit){
	return val * NUM_SUITS + suit;
}

char parse_val(char c) {
	c = toupper(c);
	for (char i = 0; i < NUM_VALUES; i++) {
		if (values[i] == c)   return i;
	}
	return -1;
}

char parse_suit(char c){
	c = toupper(c);
	for (char i = 0; i < NUM_SUITS; i++) {
		if (suits[i][0] == c) return i;
	}
	return -1;
}

void print_hand(char* hand, size_t length) {
	for (int i = 0; i < length; i++) {
		char* pretty = print(hand[i]);
		printf("%s", pretty);
		if (i != length - 1)  printf(", ");
		free(pretty);
	}
	printf("\n");
}

char get_suit(char card) {
	return card % NUM_SUITS;
}

char get_value(char card) {
	return card / NUM_SUITS;
}

char* print(char card) {
	const char* val = values_pretty[get_value(card)];
	const char* suit = suits[get_suit(card)];
	char* buf = (char*) malloc(strlen(val) + 4 + strlen(suit) + 1);
	sprintf(buf, "%s of %s", val, suit);
	return buf;
}

void swap(char* a, char* b) {
	char temp = *a;
	*a = *b;
	*b = temp;
}

void SortHandOfCards(char* hand, size_t length, bool sortDescending) {
	for (int i = 0; i < length - 1; i++)
		for (int j = 0; j < length - i - 1; j++)
			if ((hand[j] > hand[j + 1]) ^ sortDescending)
				swap(&hand[j], &hand[j + 1]);

}
