import java.util.Scanner;

/**
 * Created by werdn on 4/7/17.
 */
class Play {
    static void Game() {
        Deck deck = new Deck();
        Player comp = new Player(); // init comp player
        Player user  = new Player(); // init user player
        comp.getCards().add(deck.draw()); // draw two cards each
        user.getCards().add(deck.draw());
        comp.getCards().add(deck.draw());
        user.getCards().add(deck.draw());

        System.out.println("The dealer has a " + comp.getCards().get(0) + " face up.");
        System.out.print("Your hand: "); printHand(user, user.getTotal());
        Scanner s = new Scanner(System.in);
        if (comp.getCards().get(0).getValue() == 1 && comp.getCards().get(1).getValue() == 10) {
            System.out.println("Dealer has blackjack!");
        }

        Scanner ace = new Scanner(System.in);
        if (user.getCards().get(0).getValue() == 11 || user.getCards().get(1).getValue() == 11) {
            System.out.println("Do you want your ace to be worth 11(y) or 1(n)?");
            String acePrompt = ace.nextLine();
            if (acePrompt.equals("n")) {
                user.setTotal(user.getTotal()-10);
            }
        }

        while (user.getTotal() <= 21) { // while user hasn't broken
            System.out.println("Would you like to hit (h) or stick (s)?");
            String selection = s.nextLine();
            if (selection.equals("h")) {
                Card d = deck.draw();
                if (d.value == 11) {
                    System.out.println("You drew an " + d + ". Do you want it to be worth 11(y) or 1(n)?");
                    String acePrompt = ace.nextLine();
                    if (acePrompt.equals("n")) { d.value = 1; }
                }
                user.getCards().add(d);
                user.setTotal(user.getTotal() + d.getValue());
                System.out.print("Your hand: "); printHand(user, user.getTotal());
            } else if (selection.equals("s")) { break; }
        }

        if (user.getTotal() <= 21) { // assuming user hasn't broken, let dealer take turn
            System.out.println("The dealer has: ");
            printHand(comp, comp.getTotal());
            while (comp.getTotal() <= 17) {
                Card d = deck.draw();
                comp.getCards().add(d);
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
        System.out.println("\n\n");
    }
    static void printHand(Player p, int total) {
        for (Card c : p.cards) {System.out.print(c + " ");}
        System.out.println("(" + total + ")");
    }
}
