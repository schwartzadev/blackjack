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
        if (comp.hand.hasBlackjack()) { System.out.println("Dealer: Blackjack!"); }
        Scanner s = new Scanner(System.in);
        while (!user.hand.isBusted() && !comp.hand.hasBlackjack() && !user.hand.hasTwentyOne()) { // while user hasn't broken
            System.out.println("Hit (h) or stick (s)?");
            String selection = s.nextLine();
            if (selection.equals("h")) { // if user hits...
                Card d = deck.draw();
                user.addCard(d);
                System.out.print("You: "); user.hand.print();
            } else if (selection.equals("s")) { break; } // if user sticks, break out of loop
        }

        if (!user.hand.isBusted() && !user.hand.hasTwentyOne() && !comp.hand.hasBlackjack()) { // assuming user hasn't broken, let dealer take turn
            System.out.print("Dealer: "); comp.hand.print();
            while (comp.hand.getMaxTotal() <= 16) { // dealer hits
                Card d = deck.draw();
                comp.addCard(d);
            }
            if (comp.hand.getCards().size() - 2 > 0) { // if dealer drew
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
            System.out.println("You: Blackjack!");
            Main.bet.blackjack();
        } else if (user.hand.hasTwentyOne() && !user.hand.hasBlackjack()) { // auto win @21 but different output, scoring for blackjack
            System.out.println("You: Win!");
            Main.bet.win();
        } else if (user.hand.isBusted()) {
            System.out.println("You: Lose! (" + user.hand.getMaxTotal() + ")");
            Main.bet.lose();
        } else if (comp.hand.getMaxTotal() > user.hand.getMaxTotal()) {
            System.out.println("You: Lose. Dealer had " + comp.hand.getMaxTotal() + ", you had " + user.hand.getMaxTotal() + ".");
            Main.bet.lose();
        } else if (user.hand.getMaxTotal() > comp.hand.getMaxTotal()) {
            System.out.println("You: Win! Dealer had " + comp.hand.getMaxTotal() + ", you had " + user.hand.getMaxTotal() + ".");
            Main.bet.win();
        } else if (user.hand.getMaxTotal() == comp.hand.getMaxTotal()) {
            System.out.println("Push at " + user.hand.getMaxTotal() + ".");
        }
        try {
            TimeUnit.MILLISECONDS.sleep(700); // pause for .7 seconds between rounds for better playability
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }
}
