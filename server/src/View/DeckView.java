package View;

import java.util.LinkedList;

/**
 * Created by vinspi on 29/01/17.
 */
public class DeckView extends InventoryItem {

    private LinkedList<CardView> cards;
    private String id_Deck;

    public LinkedList<CardView> getCards() {
        return cards;
    }

    public String getId_Deck() {
        return id_Deck;
    }

    public DeckView(String image, String description, String id_Deck, LinkedList<CardView> cards) {
        this.image = image;
        this.description = description;
        this.id_Deck = id_Deck;
        this.cards = cards;
    }

}
