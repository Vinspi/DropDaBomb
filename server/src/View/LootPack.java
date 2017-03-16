package View;

import java.util.ArrayList;

/**
 * Created by deutsch on 09/03/17.
 */
public class LootPack {

    private int id;
    private String nom;
    private int qte;
    private ArrayList<Ensemble> ensembles = new ArrayList<Ensemble>();

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

    public int getQte() {
        return qte;
    }
    public void setQte(int qte) {
        this.qte = qte;
    }

    public ArrayList<Ensemble> getEnsembles() {
        return ensembles;
    }
    public void setEnsembles(ArrayList<Ensemble> ensembles) {
        this.ensembles = ensembles;
    }

    public LootPack(int id, String nom){
        this.id = id;
        this.nom = nom;
        this.qte = 0;
        this.ensembles = new ArrayList<>();
    }
    public LootPack(int id, String nom, ArrayList<Ensemble> ensembles){
        this.id = id;
        this.nom = nom;
        this.qte = -1;
        this.ensembles = ensembles;
    }


    public LootPack(int id, String nom, int qte, ArrayList<Ensemble> ensembles){
        this.id = id;
        this.nom = nom;
        this.qte = qte;
        this.ensembles = ensembles;
    }
    public LootPack(){
            super();
        }


}
