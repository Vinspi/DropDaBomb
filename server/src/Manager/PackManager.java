package Manager;

import View.PackView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.LinkedList;

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


        public Ensemble(int id, String nom){
            this.id = id;
            this.nom = nom;
            this.dropRate = 0;
            this.cartes = null;
        }
        public Ensemble(int id, String nom, ArrayList<Integer> cartes){
            this.id = id;
            this.nom = nom;
            this.dropRate = 0;
            this.cartes = cartes;
        }
        public Ensemble(){
            super();
        }
    }

    public class LootPack{
        int id;
        String nom;
        int qte;
        ArrayList<Ensemble> ensembles = new ArrayList<Ensemble>();

        public LootPack(int id, String nom){
            this.id = id;
            this.nom = nom;
            this.qte = 0;
            this.ensembles = null;
        }
        public LootPack(int id, String nom, ArrayList<Ensemble> ensembles){
            this.id = id;
            this.nom = nom;
            this.qte = 0;
            this.ensembles = ensembles;
        }
        public LootPack(){
            super();
        }
    }

    public class Pack {
        int id;
        String nom;
        ArrayList<LootPack> lootPacks = new ArrayList<LootPack>();


        public Pack(int id, String nom){
            this.id = id;
            this.nom = nom;
            this.lootPacks = null;
        }
        public Pack(int id, String nom, ArrayList<LootPack> lootPacks){
            this.id = id;
            this.nom = nom;
            this.lootPacks = lootPacks;
        }
        public Pack(){
            super();
        }

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


    public void getEvthgAlrdCreated(){
        listPacks = new ArrayList<>();
        String queryPacks = "SELECT id_Pack, nomPack, id_Ensemble, nomEnsemble, id_LootPack, nomLootPack, id_Carte" +
                " FROM Pack" +
                " JOIN LootPackPack USING (id_Pack)" +
                " JOIN LootPackEnsemble USING (id_LootPack)" +
                " JOIN EnsembleCarte USING (id_Ensemble)" +
                " ORDER BY id_Pack,id_LootPack,id_Ensemble,id_Carte";


        int id_Pack = -1, id_LootPack = -1, id_Ensemble = -1;
        String nomPack = "", nomLootPack = "", nomEnsemble = "";

        ArrayList<LootPack> tmpLootPack = new ArrayList();
        ArrayList<Ensemble> tmpEnsemble = new ArrayList();
        ArrayList<Integer> tmpCarte = new ArrayList();

        ResultSet resultSet = Manager.getManager().sendRequestQuery(queryPacks,connection);
        try {
            while (resultSet.next()) {
               if (id_Pack != resultSet.getInt("id_Pack")) { //Nouveau Pack

                   tmpEnsemble.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
                   listEnsembles.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));

                   tmpLootPack.add(new LootPack(id_LootPack,nomLootPack,tmpEnsemble));
                   listLootPacks.add(new LootPack(id_LootPack,nomLootPack,tmpEnsemble));

                   listPacks.add(new Pack(id_Pack,nomPack,tmpLootPack));

                   tmpCarte = new ArrayList<>();
                   tmpEnsemble = new ArrayList<>();
                   tmpLootPack = new ArrayList<>();

                   tmpCarte.add(resultSet.getInt("id_Carte"));

                   id_Pack = resultSet.getInt("id_Pack");
                   nomPack = resultSet.getString("nomPack");
                   id_LootPack = resultSet.getInt("id_LootPack");
                   nomLootPack = resultSet.getString("nomLootPack");
                   id_Ensemble = resultSet.getInt("id_Ensemble");
                   nomEnsemble = resultSet.getString("nomEnsemble");
               }
               else {
                    if(id_LootPack != resultSet.getInt("id_LootPack")){

                        tmpEnsemble.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
                        listEnsembles.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
                        tmpLootPack.add(new LootPack(id_LootPack,nomLootPack,tmpEnsemble));
                        listLootPacks.add(new LootPack(id_LootPack,nomLootPack,tmpEnsemble));

                        tmpEnsemble = new ArrayList<>();
                        tmpCarte = new ArrayList<>();
                        tmpCarte.add(resultSet.getInt("id_Carte"));

                        id_LootPack = resultSet.getInt("id_LootPack");
                        id_Ensemble = resultSet.getInt("id_Ensemble");
                        nomPack = resultSet.getString("nomPack");
                        nomEnsemble = resultSet.getString("nomEnsemble");
                    }
                    else {
                        if (id_Ensemble != resultSet.getInt("id_Ensemble")){

                            tmpEnsemble.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
                            listEnsembles.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));

                            tmpCarte = new ArrayList<>();
                            tmpCarte.add(resultSet.getInt("id_Carte"));

                            id_Ensemble = resultSet.getInt("id_Ensemble");
                            nomEnsemble = resultSet.getString("nomEnsemble");
                        }
                        else {
                            tmpCarte.add(resultSet.getInt("id_Carte"));
                        }
                    }
               }
            }
            tmpEnsemble.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
            listEnsembles.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
            tmpLootPack.add(new LootPack(id_LootPack,nomLootPack,tmpEnsemble));
            listLootPacks.add(new LootPack(id_LootPack,nomLootPack,tmpEnsemble));
            listPacks.add(new Pack(id_Pack,nomPack,tmpLootPack));

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
            }catch (SQLException e){}
        }
    }



    public void getCurrentPack(){


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
            tmp = "INSERT INTO LootPackPack (id_LootPack, id_Pack, qteCartePack ) VALUES ("+lp.id+","+currentPack.id+","+lp.qte+");";
            queryLootPack.add(tmp);

            for(Ensemble e : lp.ensembles){
                tmp = "INSERT INTO LootPackEnsemble (id_Pack, id_Ensemble, dropRatePack) VALUES ("+e.id+","+e.dropRate+");";
                queryEnsemble.add(tmp);

                for(Integer c : e.cartes){
                    tmp = "INSERT INTO EnsembleCarte (id_Ensemble, id_cartes) VALUES ("+e.id+","+c+")";
                    queryEC.add(tmp);

                }

            }

        }

        Manager.getManager().sendRequestUpdate(queryPack,connection);
        Manager.getManager().sendRequestUpdate(queryOffre,connection);
        Manager.getManager().sendMultipleRequestUpdate(queryLootPack,connection);
        Manager.getManager().sendMultipleRequestUpdate(queryEnsemble,connection);
        Manager.getManager().sendMultipleRequestUpdate(queryLPE,connection);
        Manager.getManager().sendMultipleRequestUpdate(queryEC,connection);

        try {
            if (connection != null) connection.close();
        }catch(SQLException e){

        }

        return 0;
    }
}
