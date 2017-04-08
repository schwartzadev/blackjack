import java.util.ArrayList;

/**
 * Created by werdn on 4/7/17.
 */
class Player {
    ArrayList<Card> cards = new ArrayList<Card>();
    int total;

    public Player() {
        this.total = 0;
    }

    void setTotal(int total) {
        this.total = total;
    }

    ArrayList<Card> getCards() {
        return cards;
    }

    void addCard(Card c) {
        this.getCards().add(c);
    }

    int getTotal() {
        this.total = 0;
        for (Card c : this.getCards()) {this.total += c.value;}
        return total;
    }
}
