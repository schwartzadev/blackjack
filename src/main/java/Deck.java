import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by werdn on 4/7/17.
 */
class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Deck() {
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(i, "\u2660");
            cards.add(card);
        }
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(i, "\u2665");
            cards.add(card);
        }
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(i, "\u2666");
            cards.add(card);
        }
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(i, "\u2663");
            cards.add(card);
        }
        this.shuffle();
        this.fixFaceCards();
    }

    Card draw() {
        Card c = this.cards.get(0);
        this.cards.remove(0);
        return c;
    }

    void shuffle() {
        Collections.shuffle(this.cards);
    }

    void fixFaceCards() {
        for (Card c : cards) {
            if (c.getValue() == 1) {
                c.setKind("A");
                c.setValue(11);
            } else if (c.getValue() == 11) {
                c.setKind("J");
                c.setValue(10);
            } else if (c.getValue() == 12) {
                c.setKind("Q");
                c.setValue(10);
            } else if (c.getValue() == 13) {
                c.setKind("K");
                c.setValue(10);
            }
            else {
                c.setKind(String.valueOf(c.getValue()));
            }
        }
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
