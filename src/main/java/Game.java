import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
        Main.bet.print();
        System.out.println("Dealer: " + comp.findCard(0) + " face up.");
        System.out.print("Your hand: "); user.hand.print();
        if (comp.hand.hasBlackjack()) { System.out.println("Dealer: blackjack!"); }
        Scanner s = new Scanner(System.in);
        while (!user.hand.isBusted() && !comp.hand.hasBlackjack() && !(user.hand.getTotal() == 21)) { // while user hasn't broken
            System.out.println("Hit (h) or stick (s)?");
            String selection = s.nextLine();
            if (selection.equals("h")) {
                Card d = deck.draw();
                user.addCard(d);
                user.setTotal(user.hand.getTotal() + d.getValue());
                System.out.print("You: "); user.hand.print();
            } else if (selection.equals("s")) { break; }
        }

        if (!user.hand.isBusted() && !(user.hand.getTotal() == 21)) { // assuming user hasn't broken, let dealer take turn
            System.out.print("Dealer: "); comp.hand.print();
            while (comp.hand.getTotal() <= 16) {
                Card d = deck.draw();
                comp.addCard(d);
                comp.setTotal(comp.hand.getTotal() + d.getValue());
            }
            if (comp.hand.getCards().size() - 2 > 0) {
                System.out.print("Dealer: draws " + (comp.hand.getCards().size() - 2) + " card");
                System.out.print((comp.hand.getCards().size() - 2 == 1) ? ", now with: " : "s, now with: "); // fix plurality of card(s) above
                comp.hand.print();
            }
        }
        if (comp.hand.isBusted()) {
            System.out.println("Dealer: Bust!");
            System.out.println("You: Win!");
            Main.bet.win();
        } else if (user.hand.hasBlackjack()) {
            System.out.println("Nice, you got blackjack!");
            Main.bet.blackjack();
        } else if (user.hand.getTotal() == 21 && !user.hand.hasBlackjack()) {
            System.out.println("You: Win!");
            Main.bet.win();
        } else if (user.hand.isBusted()) {
            System.out.println("You: Bust! (" + user.hand.getTotal() + ")");
            Main.bet.lose();
        } else if (comp.hand.getTotal() > user.hand.getTotal()) {
            System.out.println("Dealer: Win. Dealer had " + comp.hand.getTotal() + ", you had " + user.hand.getTotal() + ".");
            Main.bet.lose();
        } else if (user.hand.getTotal() > comp.hand.getTotal()) {
            System.out.println("You Win! Dealer had " + comp.hand.getTotal() + ", you had " + user.hand.getTotal() + ".");
            Main.bet.win();
        } else if (user.hand.getTotal() == comp.hand.getTotal()) {
            System.out.println("Push at " + user.hand.getTotal() + ".");
        }
        try {
            TimeUnit.MILLISECONDS.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }
}
