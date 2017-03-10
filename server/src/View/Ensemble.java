package View;


import java.util.ArrayList;

/**
 * Created by deutsch on 09/03/17.
 */
public class Ensemble {

    private int id;
    private String nom;
    private float dropRate;
    private ArrayList<MiniatureCarte> cartes;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getDropRate() {
        return dropRate;
    }
    public void setDropRate(float dropRate) {
        this.dropRate = dropRate;
    }

    public ArrayList<MiniatureCarte> getCartes() {
        return cartes;
    }
    public void setCartes(ArrayList<MiniatureCarte> cartes) {
        this.cartes = cartes;
    }

    public Ensemble(int id, String nom){
        this.id = id;
        this.nom = nom;
        this.dropRate = 0;
        this.cartes = null;
    }
    public Ensemble(int id, String nom, ArrayList<MiniatureCarte> cartes){
        this.id = id;
        this.nom = nom;
        this.dropRate = 0;
        this.cartes = cartes;
    }
    public Ensemble(){
        super();
    }

}
