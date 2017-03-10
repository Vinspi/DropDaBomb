package View;

import java.util.ArrayList;

/**
 * Created by deutsch on 09/03/17.
 */
public class Pack {
    private int id;
    private String nom;
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

    public Pack(int id, String nom){
        this.id = id;
        this.nom = nom;
        this.lootPacks = null;
        this.misEnVente = 0;
    }
    public Pack(int id, String nom, int misEnVente, ArrayList<LootPack> lootPacks){
        this.id = id;
        this.nom = nom;
        this.misEnVente = misEnVente;
        this.lootPacks = lootPacks;
    }
    public Pack(){
        super();
    }

}
