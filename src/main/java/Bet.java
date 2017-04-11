/**
 * Created by werdn on 4/10/17.
 */
public class Bet {
    private int total = 0;
    private int gamble = 10;

    public void win() {
        setTotal(total += 2*gamble);
    }

    public void lose() {
        this.total -= gamble;
    }

    // TODO add blackjack scenario (*1.5)

    public void print() {
        System.out.println("Your bet is: " + this.gamble + ", and your total winnings is " + this.total);
    }

    public void setGamble(int gamble) {
        this.gamble = gamble;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
