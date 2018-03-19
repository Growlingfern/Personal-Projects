#include <iostream>
#include <ctime>
#include <vector>
#include <stdlib.h> /* exit, EXIT_SUCCESS */


using namespace std;

// Global Variables //

struct Card
{
	int value; //initialising variables
	char suit;

	Card() {} //default constructor
	Card(int value, char suit) : value(value), suit(suit) {} // a card is defined by taking value and suit from the deck setup function
};

vector<Card> deck;

void setupDeck()
{
	deck.clear(); //gives us a black array to refresh our deck during setup
	static const char suit[] = { 'c','d','h','s' }; //defining our constant: suits

	for (int s = 0; s<4; s++) // a shorthand way of giving values appropriately to each suit, for each of our four suits, 13 cards are given a value before moving on to the next suit
		for (int val = 1; val <= 13; val++)
			deck.push_back(Card(val, suit[s]));
}

Card drawRandomCard()
{ //you should probably handle the case when the deck is empty
	srand(time(NULL));
	int randIndex = rand() % deck.size();
	Card drawnCard = deck[randIndex];
	deck.erase(deck.begin() + randIndex);
	return drawnCard;
	
}

void main() {
	setupDeck();
	Card result = drawRandomCard();
	cout << result.value << result.suit;
	


}