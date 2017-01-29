package View;

/**
 * Created by vinspi on 27/01/17.
 */
public abstract class ItemView {


    protected int monnaieIG;
    protected int monnaieIRL;
    protected int id_element;
    protected String image;
    protected String description;

    public int getMonnaieIG() {
        return monnaieIG;
    }

    public void setMonnaieIG(int monnaieIG) {
        this.monnaieIG = monnaieIG;
    }

    public int getMonnaieIRL() {
        return monnaieIRL;
    }

    public void setMonnaieIRL(int monnaieIRL) {
        this.monnaieIRL = monnaieIRL;
    }

    public int getId_element() {
        return id_element;
    }

    public void setId_element(int id_element) {
        this.id_element = id_element;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}