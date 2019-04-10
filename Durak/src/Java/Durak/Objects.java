package Java.Durak;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static Java.Durak.Game.Table;

public class Objects {

    static class Card {

        String suit;
        String value;
        Pair<String, String> pair;
        boolean trump = false;

        Card(String suit, String value) {
            this.suit = suit;
            this.value = value;
            this.pair = new Pair<>(suit, value);
        }

        @Override
        public String toString() {
            return (value + " of " + suit + (trump ? " +" : " -"));
        }

        String getSuit() {
            return suit;
        }
    }

    public static class Player {

        String nickname;

        List<Card> Hand = new ArrayList<>();

        Player(String nickname) {
            this.nickname = nickname;
        }

        void draw() {
            Card card = Game.Deck.get(0);
            Hand.add(card);
            Game.Deck.remove(card);
        }

        void placeCard(int index) {
            boolean flag = false;
            for (var pair : Table){
                if (pair.getKey().value.equals(Hand.get(index).value) || pair.getValue().value.equals(Hand.get(index).value)){
                    flag = true;
                    break;
                }
            }
                if (Table.isEmpty() || flag) {
                    Table.add(new Pair<>(Hand.get(index), null));
                    Hand.remove(index);
                }
        }

        void printHand() {
            System.out.println(nickname + "'s hand:");
            Hand.forEach(it -> System.out.println((Hand.indexOf(it) + 1) + ". " + it));
        }

        int handSize() {
            return Hand.size();
        }
    }
}