package View;

import java.util.ArrayList;

/**
 * Created by deutsch on 09/03/17.
 */
public class Pack {
    private int id;
    private String nom;
    private String img;
    private int misEnVente;
    private ArrayList<LootPack> lootPacks = new ArrayList<LootPack>();

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

    public String getImg(){ return img; }
    public void setImg(String img){ this.img = img; }

    public int getMisEnVente() {
        return misEnVente;
    }
    public void setMisEnVente(int misEnVente) {
        this.misEnVente = misEnVente;
    }

    public ArrayList<LootPack> getLootPacks() {
        return lootPacks;
    }
    public void setLootPacks(ArrayList<LootPack> lootPacks) {
        this.lootPacks = lootPacks;
    }

    public Pack(int id, String nom, String img){
        this.id = id;
        this.nom = nom;
        this.img = img;
        this.lootPacks = null;
        this.misEnVente = 0;
    }
    public Pack(int id, String nom, String img, int misEnVente, ArrayList<LootPack> lootPacks){
        this.id = id;
        this.nom = nom;
        this.img = img;
        this.misEnVente = misEnVente;
        this.lootPacks = lootPacks;
    }
    public Pack(){
        super();
    }

}
