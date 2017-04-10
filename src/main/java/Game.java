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
        System.out.print("Your hand: "); printHand(user, user.hand.getTotal());
        if (comp.hand.hasBlackjack()) { System.out.println("Dealer has blackjack!"); }
        Scanner s = new Scanner(System.in);
        while (!user.hand.isBusted()) { // while user hasn't broken
            System.out.println("Would you like to hit (h) or stick (s)?");
            String selection = s.nextLine();
            if (selection.equals("h")) {
                Card d = deck.draw();
                user.addCard(d);
                user.setTotal(user.hand.getTotal() + d.getValue());
                System.out.print("Your hand: "); printHand(user, user.hand.getTotal());
            } else if (selection.equals("s")) { break; }
        }

        if (!user.hand.isBusted()) { // assuming user hasn't broken, let dealer take turn
            System.out.print("The dealer has: "); printHand(comp, comp.hand.getTotal());
            while (comp.hand.getTotal() <= 17) {
                Card d = deck.draw();
                comp.addCard(d);
                comp.setTotal(comp.hand.getTotal() + d.getValue());
            }
            if (comp.hand.getCards().size() - 2 > 0) {
                System.out.print("The dealer draws " + (comp.hand.getCards().size() - 2) + " more card");
                System.out.print((comp.hand.getCards().size() - 2 == 1) ? ", now with: " : "s, now with: "); // fix plurality of card(s) above
                printHand(comp, comp.hand.getTotal());
            }
        }
        if (comp.hand.isBusted()) {
            System.out.println("Dealer Busted!");
            System.out.println("You Win! Dealer busted at " + comp.hand.getTotal() + ", you had " + user.hand.getTotal() + ".");
        } else if (user.hand.isBusted()) {
            System.out.println("You busted at " + user.hand.getTotal() + "!");
        } else if (comp.hand.getTotal() > user.hand.getTotal()) {
            System.out.println("Dealer Wins! Dealer had " + comp.hand.getTotal() + ", you had " + user.hand.getTotal() + ".");
        } else if (user.hand.getTotal() > comp.hand.getTotal()) {
            System.out.println("You Win! Dealer had " + comp.hand.getTotal() + ", you had " + user.hand.getTotal() + ".");
        } else if (user.hand.getTotal() == comp.hand.getTotal()) {
            System.out.println("Push (aka tie). Dealer had " + comp.hand.getTotal() + ", you had " + user.hand.getTotal() + ".");
        }
        System.out.println("\n");
    }
    static void printHand(Player p, int total) {
        for (Card c : p.getCards()) {
            System.out.print(c + " ");
        }
        System.out.println("(" + total + ")");
    }
}
