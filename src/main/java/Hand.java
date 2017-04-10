import java.util.ArrayList;

/**
 * Created by werdn on 4/9/17.
 */
public class Hand {
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    private ArrayList<Card> cards = new ArrayList<Card>();

}
