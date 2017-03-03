package Manager;

import java.lang.reflect.Array;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.sql.Connection;
import java.util.function.LongToDoubleFunction;

/**
 * Created by deutsch on 17/02/17.
 */
public class PackManager {

    Connection connection = null;
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
    }

    public class LootPack{
        int id;
        String nom;
        int qte;
        ArrayList<Ensemble> ensembles = new ArrayList<Ensemble>();
    }

    public class Pack {
        int id;
        ArrayList<LootPack> lootPacks = new ArrayList<LootPack>();
    }


    //Ajout d'une carte dans le currentEnsemble
    public void addCarteToEnsemble(int id_Carte){
        currentEnsemble.cartes.add(id_Carte);
    }


    public void createEnsemble(){


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

    public void getAllLootPack(){


    }


    public int createPack(String nom,String description, int prixIG, int prixIRL, String image, int id_Offre){
        String queryPack = "INSERT INTO Pack (id_Pack,nomPack,descriptionPack,imageMiniaturePack) VALUES ("+current_idPack+",'"+nom+"','"+description+"','"+image+"');";
        String queryOffre = "INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,id_Pack,typeOffre ) VALUES ("+id_Offre+","+prixIG+","+prixIRL+","+current_idPack+",'Pack');";

        ArrayList<String> queryLootPack = new ArrayList<>();
        ArrayList<String> queryEnsemble = new ArrayList<>();
        ArrayList<String> queryLPE = new ArrayList<>();
        ArrayList<String> queryEC = new ArrayList<>();
        String tmp;


        for(LootPack lp : currentPack.lootPacks){
            tmp = "INSERT INTO LootPack (id_LootPack, id_Pack, qteCarte ) VALUES ("+lp.id+","+currentPack.id+","+lp.qte+");";
            queryLootPack.add(tmp);

            for(Ensemble e : lp.ensembles){
                tmp = "INSERT INTO Ensemble (id_Ensemble, dropRatePack) VALUES ("+e.id+","+e.dropRate+");";
                queryEnsemble.add(tmp);
                tmp = "INSERT INTO LootPackEnsemble (id_LootPack,id_Ensemble) VALUES ("+lp.id+","+e.id+");";
                queryLPE.add(tmp);

                for(Integer c : e.cartes){
                    tmp = "INSERT INTO EnsembleCarte (id_Ensemble, id_cartes) VALUES ("+e.id+","+c+")";
                    queryEC.add(tmp);

                }

            }

        }
        //ResultSet setPack = Manager.getManager().sendRequestQuery(query,connection);

        Manager.getManager().sendRequestUpdate(queryPack,connection);
        Manager.getManager().sendRequestUpdate(queryOffre,connection);
        Manager.getManager().sendMultipleRequestUpdate(queryLootPack,connection);
        Manager.getManager().sendMultipleRequestUpdate(queryEnsemble,connection);
        Manager.getManager().sendMultipleRequestUpdate(queryLPE,connection);
        Manager.getManager().sendMultipleRequestUpdate(queryEC,connection);

        if (connection != null) connection = null;


        return 0;
    }
}
