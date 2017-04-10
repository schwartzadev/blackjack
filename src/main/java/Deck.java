import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by werdn on 4/7/17.
 */
class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Deck() {
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(i, Card.Suit.spades);
            cards.add(card);
        }
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(i, Card.Suit.hearts);
            cards.add(card);
        }
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(i, Card.Suit.diamonds);
            cards.add(card);
        }
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(i, Card.Suit.clubs);
            cards.add(card);
        }
        this.shuffle();
    }

    Card draw() {
        Card c = this.cards.get(0);
        this.cards.remove(0);
        return c;
    }

    void shuffle() {
        Collections.shuffle(this.cards);
    }


    @Override
    public String toString() {
        return cards.toString();
    }
}
