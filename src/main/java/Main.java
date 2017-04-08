import java.util.Scanner;

/**
 * Created by werdn on 4/7/17.
 */
public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Players players = new Players();
        players.getComp().getCards().add(deck.draw());
        players.getComp().getCards().add(deck.draw());
        players.getUser().getCards().add(deck.draw());
        players.getUser().getCards().add(deck.draw());

        System.out.println("The dealer has a " + players.getComp().getCards().get(0) + " face up.");
        System.out.print("Your hand: "); printHand(players.getUser(), players.getUser().getTotal());
        Scanner s = new Scanner(System.in);
        while (players.getUser().getTotal() <= 21) {
            System.out.println("Would you like to hit (h) or stick (s)?");
            String selection = s.nextLine();
            if (selection.equals("h")) {
                Card d = deck.draw();
                players.getUser().getCards().add(d);
                players.getUser().setTotal(players.getUser().getTotal() + d.value);
                System.out.print("Your hand:");
                printHand(players.getUser(), players.getUser().getTotal());
            } else if (selection.equals("s")) {
                break;
            }
        }
        s.close();

        if (players.getUser().getTotal() <= 21 && players.getComp().getTotal() < 21) {
            System.out.println("The dealer has: " + players.getComp().getTotal());
            printHand(players.getComp(), players.getComp().getTotal());
            while (players.getComp().getTotal() <= 17) {
                Card d = deck.draw();
                players.getComp().getCards().add(d);
                players.getComp().setTotal(players.getComp().getTotal() + d.value);
                System.out.println("The dealer now has: ");
                printHand(players.getComp(), players.getComp().getTotal());
            }
        }
        if (players.getComp().getTotal() > 21) {
            System.out.println("Dealer Busted!");
            System.out.println("You Win! Dealer busted at " + players.getComp().getTotal() + ", you had " + players.getUser().getTotal() + ".");
        } else if (players.getUser().getTotal() > 21) {
            System.out.println("You busted at " + players.getUser().getTotal() + "!");
        } else if (players.getComp().getTotal() > players.getUser().getTotal()) {
            System.out.println("Dealer Wins! Dealer had " + players.getComp().getTotal() + ", you had " + players.getUser().getTotal() + ".");
        } else if (players.getUser().getTotal() > players.getComp().getTotal()) {
            System.out.println("You Win! Dealer had " + players.getComp().getTotal() + ", you had " + players.getUser().getTotal() + ".");
        } else if (players.getUser().getTotal() == players.getComp().getTotal()) {
            System.out.println("Push (aka tie). Dealer had " + players.getComp().getTotal() + ", you had " + players.getUser().getTotal() + ".");
        }
    }
    static void printHand(Player p, int total) {
        for (Card c : p.cards) {System.out.print(c + " ");}
        System.out.println("(" + total + ")");
    }
}
