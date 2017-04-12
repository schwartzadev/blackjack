import java.util.ArrayList;

/**
 * Created by werdn on 4/7/17.
 */
class Player {
    Hand hand = new Hand();
    private int total;

    public Player() {
    }

    ArrayList<Card> getCards() {
        return hand.getCards();
    }

    void addCard(Card c) {
        this.getCards().add(c);
    }

    Card findCard(int i) {
        return this.getCards().get(i);
    }

}
