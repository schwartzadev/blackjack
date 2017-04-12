import java.util.ArrayList;

/**
 * Created by werdn on 4/9/17.
 */
class Hand {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int total;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean hasBlackjack() {
        return (this.cards.get(0).isAce() && this.cards.get(1).isFace()) || (this.cards.get(1).isAce() && (this.cards.get(0).isFace()));
    }

    public boolean isBusted() {
        return this.getTotal() > 21;
    }

    int getTotal() {
        int aces = 0;
        this.total = 0;
        for (Card c : this.getCards()) {
            // total += c.isFace() ? 10 : c.getValue();
            if (!c.isFace()) {
                this.total += c.getValue();
            }
            if (c.isFace()) {
                this.total += 10;
            }
            if (c.isAce()) {
                aces++;
            }
        }
        while (total+(aces*10) <= 21 && aces > 0) {
            total += 10;
            aces--;
        }
        return total;
    }

    public void print() {
        for (Card c : this.getCards()) {
            System.out.print(c + " ");
        }
        System.out.println("(" + this.getTotal() + ")");
    }

}
