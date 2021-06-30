package structures.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	List<Card> cards = new ArrayList<>(20);
	
	//number is the number of the deck
	public Deck(int number) {
		
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Card drawCard() {
		int i = 0;
		while(!(cards.get(i) instanceof Card)) {
			++i;
		}
		return cards.get(i);
	}
	
}
