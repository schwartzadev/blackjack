/**
 * Created by werdn on 4/10/17.
 */
class Bet {
    private int total = 0;
    private int gamble = 10;

    public void win() {
        setTotal(total += gamble);
    }

    public void lose() {
        this.total -= gamble;
    }

    public void blackjack() {
        this.total += gamble*1.5;
    }

    public void print() {
        System.out.println("Bet: " + this.gamble + ". Winnings: " + this.total);
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
