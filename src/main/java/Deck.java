import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by werdn on 4/7/17.
 */
class Deck {
    ArrayList<Card> cards = new ArrayList<Card>();

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
            if (c.value == 1) {
                c.kind="A";
                c.value=11;
            } else if (c.value == 11) {
                c.kind="J";
                c.value=10;
            } else if (c.value == 12) {
                c.kind="Q";
                c.value=10;
            } else if (c.value == 13) {
                c.kind="K";
                c.value=10;
            }
            else {
                c.kind = String.valueOf(c.value);
            }
        }
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
