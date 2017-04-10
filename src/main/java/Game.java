import java.util.Scanner;

/**
 * Created by werdn on 4/7/17.
 */
class Game {
    static void Play() {
        Deck deck = new Deck();
        Player comp = new Player(); // init comp player
        Player user  = new Player(); // init user player
        comp.addCard(deck.draw()); // draw two cards each
        user.addCard(deck.draw());
        comp.addCard(deck.draw());
        user.addCard(deck.draw());

        System.out.println("The dealer has a " + comp.findCard(0) + " face up.");
        System.out.print("Your hand: "); printHand(user, user.getTotal());
        Scanner s = new Scanner(System.in);
        if (comp.findCard(0).getValue() == 1 && comp.findCard(1).getValue() == 10) {
            System.out.println("Dealer has blackjack!");
        }

        while (user.getTotal() <= 21) { // while user hasn't broken
            System.out.println("Would you like to hit (h) or stick (s)?");
            String selection = s.nextLine();
            if (selection.equals("h")) {
                Card d = deck.draw();
                user.addCard(d);
                user.setTotal(user.getTotal() + d.getValue());
                System.out.print("Your hand: "); printHand(user, user.getTotal());
            } else if (selection.equals("s")) { break; }
        }

        if (user.getTotal() <= 21) { // assuming user hasn't broken, let dealer take turn
            System.out.println("The dealer has: ");
            printHand(comp, comp.getTotal());
            while (comp.getTotal() <= 17) {
                Card d = deck.draw();
                comp.addCard(d);
                comp.setTotal(comp.getTotal() + d.getValue());
                System.out.println("The dealer now has: ");
                printHand(comp, comp.getTotal());
            }
        }
        if (comp.getTotal() > 21) {
            System.out.println("Dealer Busted!");
            System.out.println("You Win! Dealer busted at " + comp.getTotal() + ", you had " + user.getTotal() + ".");
        } else if (user.getTotal() > 21) {
            System.out.println("You busted at " + user.getTotal() + "!");
        } else if (comp.getTotal() > user.getTotal()) {
            System.out.println("Dealer Wins! Dealer had " + comp.getTotal() + ", you had " + user.getTotal() + ".");
        } else if (user.getTotal() > comp.getTotal()) {
            System.out.println("You Win! Dealer had " + comp.getTotal() + ", you had " + user.getTotal() + ".");
        } else if (user.getTotal() == comp.getTotal()) {
            System.out.println("Push (aka tie). Dealer had " + comp.getTotal() + ", you had " + user.getTotal() + ".");
        }
        System.out.println("\n");
    }
    static void printHand(Player p, int total) {
        for (Card c : p.getCards()) {System.out.print(c + " ");}
        System.out.println("(" + total + ")");
    }
}
