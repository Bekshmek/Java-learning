package Java.Durak;

import Java.Durak.Objects.*;

public class Expo {

    public static void main(String[] args) {
        Player player1 = new Player("Beks");
        Game.fillDeck();
        System.out.println(Game.deckSize());
        System.out.println("" +
                "");
        while (player1.handSize() < 6) player1.draw();
        player1.printHand();
    }
}
