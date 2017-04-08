/**
 * Created by werdn on 4/7/17.
 */
class Card {
    int value;
    String kind;
    String suit;

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return (kind + suit);
    }

    public int getValue() {
        return value;
    }

}
