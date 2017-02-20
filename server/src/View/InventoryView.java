package View;

import java.util.LinkedList;

/**
 * Created by vinspi on 29/01/17.
 */
public class InventoryView {

    private LinkedList<CardView> playerCards;
    private DeckView deck1;
    private DeckView deck2;

    public LinkedList<CardView> getPlayerCards() {
        return playerCards;
    }

    public DeckView getDeck1() {
        return deck1;
    }

    public DeckView getDeck2() {
        return deck2;
    }

    public InventoryView(LinkedList<CardView> playerCards, DeckView deck1, DeckView deck2) {
        this.playerCards = playerCards;
        this.deck1 = deck1;
        this.deck2 = deck2;
    }
}
