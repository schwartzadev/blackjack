import java.util.ArrayList;

/**
 * Created by werdn on 4/7/17.
 */
class Player {
    public ArrayList<Card> getCards() {
        return cards;
    }

    ArrayList<Card> cards = new ArrayList<Card>();
    int total;

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        this.total = 0;
        for (Card c : this.getCards()) {this.total += c.value;}
        return total;
    }

    public Player() {
        total = 0;
    }
}
