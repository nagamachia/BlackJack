import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<String> cards = new ArrayList<>();

    public Deck() {
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] suits = {"Heart","Diamond","Club","Spade"};
        for (String number: numbers){
            for (String suit: suits){
                cards.add(suit+"_"+number);
            }
        }
        Collections.shuffle(cards);
    }

    public ArrayList<String> getCards() {
        return cards;
    }
}
