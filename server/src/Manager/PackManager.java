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



    public class MiniatureCarte {
        int id;
        String name;
        String img;

        public MiniatureCarte(int id,String name,String img){
            this.id = id;
            this.name = name;
            this.img = img;
        }

    }

    Connection connection = null;
    Pack currentPack = new Pack();
    public LootPack currentLootPack = new LootPack();
    public Ensemble currentEnsemble = new Ensemble();


    public ArrayList<MiniatureCarte> listCards = new ArrayList<>();
    public ArrayList<Ensemble> listEnsembles = new ArrayList<>();
    public ArrayList<LootPack> listLootPacks = new ArrayList<>();
    public ArrayList<Pack> listPacks = new ArrayList<>();

    public int current_idEnsemble, current_idLootPack, current_idPack;



    public class Ensemble{
        int id;
        String nom;
        float dropRate;
        ArrayList<MiniatureCarte> cartes;


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
        int misEnVente;
        ArrayList<LootPack> lootPacks = new ArrayList<LootPack>();


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


    //Ajout d'une carte dans le currentEnsemble
    public void addCarteToEnsemble(int id_Carte){
        for(MiniatureCarte m : listCards){
            if(m.id == id_Carte) currentEnsemble.cartes.add(m);
            break;
        }
    }

    //A refaire
    public void addEnsembleToLootPack(String nom,int drop){
        currentEnsemble.id = current_idEnsemble;
        currentEnsemble.dropRate = drop;
        currentEnsemble.nom = nom;
        currentLootPack.ensembles.add(currentEnsemble);
        currentEnsemble = new Ensemble();
        current_idEnsemble++;
    }

    //A refaire
    public void addLootPackToPack(String nom,int qte){
        currentLootPack.id = current_idLootPack;
        currentLootPack.qte = qte;
        currentLootPack.nom = nom;
        currentPack.lootPacks.add(currentLootPack);
        currentLootPack = new LootPack();
        current_idLootPack++;
    }

    //Remplis listEnsembles, listLootPacks, listPacks, contenant tout les Ensembles, LootPacks et Packs.
    public void getEvthgAlrdCreated(){
        listPacks = new ArrayList<>();
        listLootPacks = new ArrayList<>();
        listEnsembles = new ArrayList<>();
        String queryPacks = "SELECT id_Pack, nomPack, misEnVentePack, id_Ensemble, nomEnsemble, id_LootPack, nomLootPack, id_Carte, nomCarte, imageCarte" +
                " FROM Pack" +
                " JOIN LootPackPack USING (id_Pack)" +
                " JOIN LootPackEnsemble USING (id_LootPack)" +
                " JOIN EnsembleCarte USING (id_Ensemble)" +
                " JOIN Carte USING (id_Carte)" +
                " ORDER BY id_Pack,id_LootPack,id_Ensemble,id_Carte";


        int id_Pack = -1, id_LootPack = -1, id_Ensemble = -1, misEnVentePack = 0, maxLootPack = -1, maxEnsemble = -1;
        String nomPack = "", nomLootPack = "", nomEnsemble = "";

        ArrayList<LootPack> tmpLootPack = new ArrayList();
        ArrayList<Ensemble> tmpEnsemble = new ArrayList();
        ArrayList<MiniatureCarte> tmpCarte = new ArrayList();

        ResultSet resultSet = Manager.getManager().sendRequestQuery(queryPacks,connection);
        try {
            while (resultSet.next()) {
               if (id_Pack != resultSet.getInt("id_Pack")) { //Nouveau Pack

                   if(id_Pack != -1) {     //Premiere iteration, on ne fait qu'ajouter la carte et initialiser les variables.
                       tmpEnsemble.add(new Ensemble(id_Ensemble, nomEnsemble, tmpCarte));
                       listEnsembles.add(new Ensemble(id_Ensemble, nomEnsemble, tmpCarte));

                       tmpLootPack.add(new LootPack(id_LootPack, nomLootPack, tmpEnsemble));
                       listLootPacks.add(new LootPack(id_LootPack, nomLootPack, tmpEnsemble));

                       listPacks.add(new Pack(id_Pack, nomPack, misEnVentePack, tmpLootPack));

                       tmpCarte = new ArrayList<>();
                       tmpEnsemble = new ArrayList<>();
                       tmpLootPack = new ArrayList<>();

                   }
                   tmpCarte.add(new MiniatureCarte(resultSet.getInt("id_Carte"),resultSet.getString("nomCarte"),resultSet.getString("imageCarte")));

                   id_Pack = resultSet.getInt("id_Pack");
                   nomPack = resultSet.getString("nomPack");
                   misEnVentePack = resultSet.getInt("misEnVentePack");

                   id_LootPack = resultSet.getInt("id_LootPack");
                   nomLootPack = resultSet.getString("nomLootPack");
                   if(maxLootPack < id_LootPack) maxLootPack = id_LootPack;

                   id_Ensemble = resultSet.getInt("id_Ensemble");
                   nomEnsemble = resultSet.getString("nomEnsemble");
                   if(maxEnsemble < id_Ensemble) maxEnsemble = id_Ensemble;
               }
               else {
                    if(id_LootPack != resultSet.getInt("id_LootPack")){

                        tmpEnsemble.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
                        listEnsembles.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
                        tmpLootPack.add(new LootPack(id_LootPack,nomLootPack,tmpEnsemble));
                        listLootPacks.add(new LootPack(id_LootPack,nomLootPack,tmpEnsemble));

                        tmpEnsemble = new ArrayList<>();
                        tmpCarte = new ArrayList<>();
                        tmpCarte.add(new MiniatureCarte(resultSet.getInt("id_Carte"),resultSet.getString("nomCarte"),resultSet.getString("imageCarte")));


                        id_LootPack = resultSet.getInt("id_LootPack");
                        nomLootPack = resultSet.getString("nomLootPack");
                        if(maxLootPack < id_LootPack) maxLootPack = id_LootPack;


                        id_Ensemble = resultSet.getInt("id_Ensemble");
                        nomEnsemble = resultSet.getString("nomEnsemble");
                        if(maxEnsemble < id_Ensemble) maxEnsemble = id_Ensemble;

                    }
                    else {
                        if (id_Ensemble != resultSet.getInt("id_Ensemble")){

                            tmpEnsemble.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
                            listEnsembles.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));

                            tmpCarte = new ArrayList<>();
                            tmpCarte.add(new MiniatureCarte(resultSet.getInt("id_Carte"),resultSet.getString("nomCarte"),resultSet.getString("imageCarte")));


                            id_Ensemble = resultSet.getInt("id_Ensemble");
                            nomEnsemble = resultSet.getString("nomEnsemble");
                            if(maxEnsemble < id_Ensemble) maxEnsemble = id_Ensemble;
                        }
                        else {
                            tmpCarte.add(new MiniatureCarte(resultSet.getInt("id_Carte"),resultSet.getString("nomCarte"),resultSet.getString("imageCarte")));

                        }
                    }
               }
            }
            tmpEnsemble.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
            listEnsembles.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
            tmpLootPack.add(new LootPack(id_LootPack,nomLootPack,tmpEnsemble));
            listLootPacks.add(new LootPack(id_LootPack,nomLootPack,tmpEnsemble));
            listPacks.add(new Pack(id_Pack,nomPack,misEnVentePack,tmpLootPack));

            current_idPack = id_Pack;
            current_idLootPack = maxLootPack;
            current_idEnsemble = maxEnsemble;


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
            }catch (SQLException e){}
        }
    }

    public void getAllCards(){
        listCards = new ArrayList<>();
        String queryCards = "SELECT id_Carte, nomCarte, imageCarte FROM Carte";
        ResultSet setCards = Manager.getManager().sendRequestQuery(queryCards,connection);
        try {
            while(setCards.next()){
                listCards.add(new MiniatureCarte(setCards.getInt("id_Carte"),setCards.getString("nomCarte"),setCards.getString("imageCarte")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(connection != null) connection.close();
            }catch (SQLException e){

            }
        }
    }


    // /!\INJ
    //Créer un pack dans la base de données, reliant ainsi les LootPacks à ce même Pack.
    public int createPack(String nom,String description, int prixIG, int prixIRL, String image, int id_Offre){
        String queryPack = "INSERT INTO Pack (id_Pack,nomPack,descriptionPack,imageMiniaturePack) VALUES ("+current_idPack+",'"+nom+"','"+description+"','"+image+"');";
        String queryOffre = "INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,id_Pack,typeOffre ) VALUES ("+id_Offre+","+prixIG+","+prixIRL+","+current_idPack+",'Pack');";

        ArrayList<String> queryLootPackPack = new ArrayList<>();
        String tmp;


        for(LootPack lp : currentPack.lootPacks){
            tmp = "INSERT INTO LootPackPack (id_LootPack, id_Pack, qteCartePack ) VALUES ("+lp.id+","+currentPack.id+","+lp.qte+");";
            queryLootPackPack.add(tmp);
        }
        Manager.getManager().sendRequestUpdate(queryPack,connection);
        Manager.getManager().sendRequestUpdate(queryOffre,connection);
        Manager.getManager().sendMultipleRequestUpdate(queryLootPackPack,connection);

        try {
            if (connection != null) connection.close();
        }catch(SQLException e){

        }

        return 0;
    }

    // /!\INJ
    //Créer un LootPack dans la db, reliant ainsi ses Ensembles à ce même LootPack.
    public int createLootPack(String nom){

        ArrayList<String> queryLootPack = new ArrayList<>();

        for(Ensemble e : currentLootPack.ensembles){
            queryLootPack.add("INSERT INTO LootPackEnsemble (id_LootPack, id_Ensemble, nomEnsemble, dropRatePack) VALUES ("+current_idLootPack+","+e.id+","+ nom +","+e.dropRate+");");
        }
        Manager.getManager().sendMultipleRequestUpdate(queryLootPack,connection);
        try {
            if (connection != null) connection.close();
        }catch (SQLException e){

        }

        return 0;
    }

    // /!\INJ
    //Créer un Ensemble dans la db, le liant à ses cartes.
    public int createEnsemble(String nom){

        ArrayList<String> queryEnsemble = new ArrayList<>();

        for(MiniatureCarte m : currentEnsemble.cartes){
            queryEnsemble.add("INSERT INTO EnsembleCarte (id_Ensemble, id_Carte, nomEnsemble) VALUES ("+current_idEnsemble+","+m.id+","+nom+");");
        }
        Manager.getManager().sendMultipleRequestUpdate(queryEnsemble,connection);
        try {
            if (connection != null) connection.close();
        }catch (SQLException e){

        }

        return 0;
    }

    public int switchMiseEnVente(int idPack){
        String updateMisEnVente;

        //Recherche du pack par son id
        for(Pack p : listPacks){
            if (p.id == idPack) {

                if(p.misEnVente == 0) {      //Pack non mis en vente
                    updateMisEnVente = "UPDATE Pack SET misEnVentePack = 1 WHERE id_Pack="+idPack+";";
                    Manager.getManager().sendRequestUpdate(updateMisEnVente,connection);
                    p.misEnVente = 1;
                }
                else {                       //Pack actuellement en vente.
                    updateMisEnVente = "UPDATE Pack SET misEnVentePack = 0 WHERE id_Pack="+idPack+";";
                    Manager.getManager().sendRequestUpdate(updateMisEnVente,connection);
                    p.misEnVente = 0;
                }
                return 0;
            }
        }
        return -1;
    }

    public void removeCarteFromEnsemble(int idCarte) {

        int k = -1;
        for (int i = 0; i < currentEnsemble.cartes.size(); i++) {
            if (currentEnsemble.cartes.get(i).id == idCarte) {
                k = i;
                currentEnsemble.cartes.remove(k);
                break;
            }
        }

        //Si l'ensemble est un ensemble existant, on supprime la carte de l'ensemble dans la DB (effectif sur toutes les instances de l'Ensemble, donc pour tout les Packs associés)
        if (k >= 0) {
            for(int j = 0; j < listEnsembles.size(); j++) {
                if (listEnsembles.get(j).id == currentEnsemble.id) {
                    String queryRmCrd = "DELETE FROM EnsembleCartes WHERE id_Carte="+idCarte+" AND id_Ensemble="+currentEnsemble.id+";";
                    Manager.getManager().sendRequestUpdate(queryRmCrd,connection);
                    listEnsembles.get(j).cartes.remove(k);
                    break;
                }
            }
        }
        try {
            if (connection != null) connection.close();
        }catch (SQLException e){

        }
    }
    //ALTER TABLE tbl_magazine_issue
    //DROP FOREIGN KEY FK_tbl_magazine_issue_mst_users


    
    public void choosePack(int idPack){
        for(Pack p : listPacks){
            if (p.id == idPack) {
                currentPack = p;
                break;
            }
        }
    }

    public void chooseLootPack(int idLP){
        for(LootPack lp : listLootPacks){
            if (lp.id == idLP) {
                currentLootPack = lp;
                break;
            }
        }
    }

    public void chooseEnsemble(int idEns){
        for(Ensemble e : listEnsembles){
            if (e.id == idEns) {
                currentEnsemble = e;
                break;
            }
        }
    }

}
