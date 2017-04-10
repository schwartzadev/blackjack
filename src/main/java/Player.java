import java.util.ArrayList;

/**
 * Created by werdn on 4/7/17.
 */
class Player {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int total;

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

    Card findCard(int i) {
        return this.getCards().get(i);
    }

    int getTotal() {
        int aces = 0;
        this.total = 0;
        for (Card c : this.getCards()) {
            this.total += c.getValue();
            if (c.getValue() == 11) {
                aces++;
            }
        }
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }
        return total;
    }
}
