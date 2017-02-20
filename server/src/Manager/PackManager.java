package Manager;

import java.util.ArrayList;
import java.util.function.LongToDoubleFunction;

/**
 * Created by deutsch on 17/02/17.
 */
public class PackManager {

    Pack currentPack = new Pack();
    LootPack currentLootPack = new LootPack();
    Ensemble currentEnsemble = new Ensemble();


    ArrayList<Ensemble> listEnsembles = new ArrayList<>();
    ArrayList<LootPack> listLootPacks = new ArrayList<>();
    ArrayList<Pack> listPacks = new ArrayList<>();

    int current_idEnsemble, current_idLootPack, current_idPack;



    public class Ensemble{
        int id;
        String nom;
        float dropRate;
        ArrayList<Integer> cartes;

        /*public Ensemble(String nom, float dropRate){
            this.id = current_idEnsemble;
            current_idEnsemble++;
            this.nom = nom;
            this.dropRate = dropRate;
            this.cartes = new ArrayList<>();
        }*/
    }

    public class LootPack{
        int id;
        String nom;
        int qte;
        ArrayList<Ensemble> ensembles = new ArrayList<Ensemble>();

        /*public LootPack(String nom, int qte) {
            this.id = current_idLootPack;
            current_idLootPack++;
            this.nom = nom;
            this.qte = qte;
            this.ensembles = new ArrayList<>();
        }*/
    }

    public class Pack {
        int id;
        ArrayList<LootPack> lootPacks = new ArrayList<LootPack>();

        /*public Pack(){
            this.id = current_idPack;
            this.lootPacks = new ArrayList<>();
        }*/
    }


    //Ajout d'une carte dans le currentEnsemble
    public void addCarteToEnsemble(int id_Carte){
        currentEnsemble.cartes.add(id_Carte);
    }

    public void addEnsembleToLootPack(String nom,int drop){
        currentEnsemble.id = current_idEnsemble;
        currentEnsemble.dropRate = drop;
        currentEnsemble.nom = nom;
        currentLootPack.ensembles.add(currentEnsemble);
        currentEnsemble = new Ensemble();
        current_idEnsemble++;
    }

    public void addLootPackToPack(String nom,int qte){
        currentLootPack.id = current_idLootPack;
        currentLootPack.qte = qte;
        currentLootPack.nom = nom;
        currentPack.lootPacks.add(currentLootPack);
        currentLootPack = new LootPack();
        current_idLootPack++;
    }

    public int createPack(String nom,String description, int prixIG, int prixIRL, String image, int id_Offre){
        String queryPack = "INSERT INTO Pack (id_Pack,nomPack,descriptionPack,imageMiniaturePack) VALUES ("+current_idPack+",'"+nom+"','"+description+"','"+image+"');";
        String queryOffre = "INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,id_Pack,typeOffre ) VALUES ("+id_Offre+","+prixIG+","+prixIRL+","+current_idPack+",'Pack');";

        ArrayList<String> queryLootPack = new ArrayList<>();
        ArrayList<String> queryEnsemble = new ArrayList<>();
        String tmpLootPack;



        for(LootPack lp : currentPack.lootPacks){
            tmpLootPack = "INSERT INTO LootPack (id_LootPack, id_Pack, qteCarte ) VALUES ("+lp.id+","+currentPack.id+","+lp.qte+");";

        }


        return 0;
    }
}
