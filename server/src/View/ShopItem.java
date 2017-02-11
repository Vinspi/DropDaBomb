package View;

/**
 * Created by vinspi on 27/01/17.
 */
public abstract class ShopItem {
    protected int monnaieIG;
    protected int monnaieIRL;
    protected int id_element;
    protected String nom;
    protected String image;
    protected String description;

    public int getMonnaieIG() {
        return monnaieIG;
    }

    public int getMonnaieIRL() {
        return monnaieIRL;
    }

    public int getId_element() {
        return id_element;
    }

    public String getNom(){
        return nom;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
