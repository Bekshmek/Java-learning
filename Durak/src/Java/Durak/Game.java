package Java.Durak;

import Java.Durak.Objects.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Game {

    Scanner reader = new Scanner(System.in);

    static List<Pair<Card, Card>> Table = new ArrayList<>();

    static List<Card> Deck = new ArrayList<>();

    private static Card TrumpCard;

    private Player[] players;

    Game(Player... players) {
        this.players = players;
    }

    /**
     * Start a game.
     */

    public void play() {
        if (players.length == 0) System.out.println("No players playing");
        fillDeck();
        String action;
        while (true) {
            for (int i = 0; i < players.length; i++) {
                action = reader.nextLine();
            }
        }
    }

    /**
     * Fills the deck with cards.
     */

    static void fillDeck() {
        Deck.clear();
        int j = 6;
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        while (j <= 10) {
            for (String i : suits) {
                Deck.add(new Java.Durak.Objects.Card(i, Integer.toString(j)));
            }
            j++;
        }

        String[] l = {"J", "Q", "K", "A"};
        for (String i : new String[]{"Spades", "Hearts", "Diamonds", "Clubs"}) {
            Deck.add(new Java.Durak.Objects.Card(i, l[0]));
            Deck.add(new Java.Durak.Objects.Card(i, l[1]));
            Deck.add(new Java.Durak.Objects.Card(i, l[2]));
            Deck.add(new Java.Durak.Objects.Card(i, l[3]));
        }
        shuffle();
        detTrumpCard();
    }

    static int deckSize() {
        return Deck.size();
    }

    private static void shuffle() {
        Collections.shuffle(Deck);
    }

    /**
     * Reveals trump card.
     */

    private static void detTrumpCard() {
        var card = Deck.get(deckSize() - 1);
        card.trump = true;
        TrumpCard = card;
        System.out.println(TrumpCard);
        Deck.forEach(it -> it.trump = it.getSuit().equals(TrumpCard.getSuit()));
    }

    private void clearDeck() {
        Deck.clear();
        TrumpCard = null;
    }

    public static void lookInsideDeck() {
        Deck.forEach(System.out::println);
    }

    /**
     * Resets game.
     */

    /*private void give(Player player,int index){
        Table.add(player.Hand.get(index));
        player.Hand.remove(index);
    }*/

    void endGame(Player player) {
        Deck.clear();
        for (Player p : players) {
            p.Hand.clear();
        }
        Table.clear();
        System.out.println(player.nickname);

    }

    /**
     * Checks whether the given card is beatable
     *
     * @param card_1 is given card.
     * @param card_2 is beating card.
     * @return true if beatable.
     */

    private boolean canBeat(Card card_1, Card card_2) {
        switch (card_1.value){
            case "J":
                card_1.value="11";
            case "Q":
                card_1.value="12";
            case "K":
                card_1.value="13";
            case "A":
                card_1.value="14";
        }
        if (card_2.trump && !card_1.trump) {
            return true;
        } else if (card_2.trump || !card_1.trump) {
            return Integer.parseInt(card_2.value) > Integer.parseInt(card_1.value);
        }
        return false;
    }
}
