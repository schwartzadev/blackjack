import java.util.ArrayList;

/**
 * Created by werdn on 4/7/17.
 */
class Player {
    public ArrayList<Card> getCards() {
        return cards;
    }

    ArrayList<Card> cards = new ArrayList<Card>();
    int Total;

    public void setTotal(int total) {
        Total = total;
    }

    public int getTotal() {
        this.Total = 0;
        for (Card c : this.getCards()) {this.Total += c.value;}
        return Total;
    }

    public Player() {
        Total = 0;
    }
}
