import me.aschwartz.cards.*;

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
        System.out.print("Your hand: "); user.getHand().print();
        if (comp.getHand().hasBlackjack()) { System.out.println("Dealer: Blackjack!"); }
        Scanner s = new Scanner(System.in);
        while (!user.getHand().isBusted() && !comp.getHand().hasBlackjack() && !user.getHand().hasTwentyOne()) { // while user hasn't broken
            System.out.println("Hit (h) or stick (s)?");
            String selection = s.nextLine();
            if (selection.equals("h")) { // if user hits...
                Card d = deck.draw();
                user.addCard(d);
                System.out.print("You: "); user.getHand().print();
            } else if (selection.equals("s")) { break; } // if user sticks, break out of loop
        }

        if (!user.getHand().isBusted() && !user.getHand().hasTwentyOne() && !comp.getHand().hasBlackjack()) { // assuming user hasn't broken, let dealer take turn
            System.out.print("Dealer: "); comp.getHand().print();
            while (comp.getHand().getMaxTotal() <= 16) { // dealer hits
                Card d = deck.draw();
                comp.addCard(d);
            }
            if (comp.getHand().getCards().size() - 2 > 0) { // if dealer drew
                System.out.print("Dealer: draws " + (comp.getHand().getCards().size() - 2) + " card");
                System.out.print((comp.getHand().getCards().size() - 2 == 1) ? ", now with: " : "s, now with: "); // fix plurality of card(s) above
                comp.getHand().print();
            }
        }

        if (comp.getHand().isBusted()) {
            System.out.println("Dealer: Bust!");
            System.out.println("You: Win!");
            Main.bet.win();
        } else if (user.getHand().hasBlackjack()) {
            System.out.println("You: Blackjack!");
            Main.bet.blackjack();
        } else if (user.getHand().hasTwentyOne() && !user.getHand().hasBlackjack()) { // auto win @21 but different output, scoring for blackjack
            System.out.println("You: Win!");
            Main.bet.win();
        } else if (user.getHand().isBusted()) {
            System.out.println("You: Lose! (" + user.getHand().getMaxTotal() + ")");
            Main.bet.lose();
        } else if (comp.getHand().getMaxTotal() > user.getHand().getMaxTotal()) {
            System.out.println("You: Lose. Dealer had " + comp.getHand().getMaxTotal() + ", you had " + user.getHand().getMaxTotal() + ".");
            Main.bet.lose();
        } else if (user.getHand().getMaxTotal() > comp.getHand().getMaxTotal()) {
            System.out.println("You: Win! Dealer had " + comp.getHand().getMaxTotal() + ", you had " + user.getHand().getMaxTotal() + ".");
            Main.bet.win();
        } else if (user.getHand().getMaxTotal() == comp.getHand().getMaxTotal()) {
            System.out.println("Push at " + user.getHand().getMaxTotal() + ".");
        }
        try {
            TimeUnit.MILLISECONDS.sleep(700); // pause for .7 seconds between rounds for better playability
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }
}
