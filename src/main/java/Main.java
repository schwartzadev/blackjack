import sun.plugin2.main.client.PluginEmbeddedFrame;

import java.util.Scanner;

/**
 * Created by werdn on 4/7/17.
 */
public class Main {
    public static Deck deck = new Deck();
    public static void main(String[] args) {
        Player[] players =  {new Player(), new Player()};
        for (Player p : players) {
            p.cards.add(deck.draw());
            p.cards.add(deck.draw());
        }

        System.out.println("You draw a " + players[1].cards.get(0) + " and a " + players[1].cards.get(1) + "." +
                "\nThe dealer has a " + players[0].cards.get(0) + " face up.");
        int userTotal = 0;
        int dealerTotal = 0;
        for (Card c : players[1].cards) {userTotal += c.value;}
        System.out.print("Your hand: ");
        printHand(players[1], userTotal);
        while (userTotal <= 21) {
            System.out.println("Would you like to hit (h) or stick (s)?");
            Scanner s = new Scanner(System.in);
            String selection = s.nextLine();
            if (selection.equals("h")) {
                Card d = deck.draw();
                players[1].cards.add(d);
                userTotal += d.value;
                System.out.print("Your hand:");
                printHand(players[1], userTotal);
            } else if (selection.equals("s")) {
                break;
            } else {}
        }

        for (Card c : players[0].cards) {dealerTotal += c.value;}
        if (userTotal <= 21 && userTotal < 21) {
            System.out.println("The dealer has: " + dealerTotal);
            printHand(players[0], dealerTotal);
            while (dealerTotal <= 17) {
                Card d = deck.draw();
                players[0].cards.add(d);
                dealerTotal += d.value;
                System.out.println("The dealer now has: ");
                printHand(players[0], dealerTotal);
            }
        }
        if (dealerTotal > 21) {
            System.out.println("Dealer Busted!");
            System.out.println("You Win! Dealer busted at " + dealerTotal + ", you had " + userTotal + ".");
        } else if (userTotal > 21) {
            System.out.println("You busted at " + userTotal + "!");
        } else if (dealerTotal > userTotal) {
            System.out.println("Dealer Wins! Dealer had " + dealerTotal + ", you had " + userTotal + ".");
        } else if (userTotal > dealerTotal) {
            System.out.println("You Win! Dealer had " + dealerTotal + ", you had " + userTotal + ".");
        } else if (userTotal == dealerTotal) {
            System.out.println("Push (aka tie). Dealer had " + dealerTotal + ", you had " + userTotal + ".");
        }
    }
    public static void printHand(Player p, int total) {
        for (Card c : p.cards) {System.out.print(c + " ");}
        System.out.println("(" + total + ")");
    }
}
