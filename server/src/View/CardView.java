package View;

/**
 * Created by vinspi on 29/01/17.
 */
public class CardView extends InventoryItem {

    private String rareteCarte;
    private String typeCarte;
    private String coutCarte;
    private int id_carte;

    public CardView(String image, String description, int id_carte, String name, String rareteCarte, String typeCarte, String coutCarte) {
        this.image = image;
        this.description = description;
        this.id_carte = id_carte;
        this.name = name;
        this.rareteCarte = rareteCarte;
        this.typeCarte = typeCarte;
        this.coutCarte = coutCarte;
    }
}
