import java.util.Scanner;

/**
 * Created by werdn on 4/7/17.
 */
class Play {
    static void Game() {
        Deck deck = new Deck();
        Players players = new Players(); // init user and comp players
        players.getComp().getCards().add(deck.draw()); // draw two cards each
        players.getUser().getCards().add(deck.draw());
        players.getComp().getCards().add(deck.draw());
        players.getUser().getCards().add(deck.draw());

        System.out.println("The dealer has a " + players.getComp().getCards().get(0) + " face up.");
        System.out.print("Your hand: "); printHand(players.getUser(), players.getUser().getTotal());
        Scanner s = new Scanner(System.in);
        if (players.getComp().getCards().get(0).getValue() == 1 && players.getComp().getCards().get(1).getValue() == 10) {
            System.out.println("Dealer has blackjack!");
        }

        if (players.getUser().getCards().get(0).getValue() == 1 || players.getUser().getCards().get(1).getValue() == 1) {
            System.out.println("Do you want your ace to be worth 11(y) or 1(n)?");
            String acePrompt = s.nextLine();
            if (acePrompt.equals("n")) {
                players.getUser().setTotal(players.getUser().getTotal()-10);
            }
        }

        while (players.getUser().getTotal() <= 21) { // while user hasn't broken
            System.out.println("Would you like to hit (h) or stick (s)?");
            String selection = s.nextLine();
            if (selection.equals("h")) {
                Card d = deck.draw();
                players.getUser().getCards().add(d);
                players.getUser().setTotal(players.getUser().getTotal() + d.getValue());
                System.out.print("Your hand:");
                printHand(players.getUser(), players.getUser().getTotal());
            } else if (selection.equals("s")) { break; }
        }

        if (players.getUser().getTotal() <= 21) { // assuming user hasn't broken, let dealer take turn
            System.out.println("The dealer has: ");
            printHand(players.getComp(), players.getComp().getTotal());
            while (players.getComp().getTotal() <= 17) {
                Card d = deck.draw();
                players.getComp().getCards().add(d);
                players.getComp().setTotal(players.getComp().getTotal() + d.getValue());
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
