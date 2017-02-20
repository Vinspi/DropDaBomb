package View;

/**
 * Created by vinspi on 29/01/17.
 */
public class CardView extends InventoryItem {

    private String rareteCarte;
    private String typeCarte;
    private String coutCarte;
    private int id_carte;

    public String getImageCarte(){
        return this.image;
    }

    public String getRareteCarte() {
        return rareteCarte;
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public String getCoutCarte() {
        return coutCarte;
    }

    public int getId_carte() {
        return id_carte;
    }

    public String webify(){
        return "<div class=\"col s3 m3 l3\">" +
                "                        <div class=\"card carte-deck\">" +
                "                            <div class=\"card-image grow\" onclick=\"selectCardDeck($(this).attr('id'))\" id=\"a" + this.getId_carte() + "\"> " +
                "                               <img src=\"../img/img-carte/"+this.getImageCarte()+"\">" +
                "                              </div>" +
                "                           </div>" +
                "   </div>";
    }


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
