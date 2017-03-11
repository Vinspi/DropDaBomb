package Manager;

import View.MiniatureCarte;
import View.Ensemble;
import View.LootPack;
import View.Pack;


import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.sql.Connection;



/**
 * Created by deutsch on 17/02/17.
 */
public class PackManager {

    Connection connection = null;
    private Pack currentPack = new Pack();
    private LootPack currentLootPack = new LootPack();
    private Ensemble currentEnsemble = new Ensemble();


    private ArrayList<MiniatureCarte> listCards = new ArrayList<>();
    private ArrayList<Ensemble> listEnsembles = new ArrayList<>();
    private ArrayList<LootPack> listLootPacks = new ArrayList<>();
    private ArrayList<Pack> listPacks = new ArrayList<>();

    private int current_idEnsemble, current_idLootPack, current_idPack;



    public Pack getCurrentPack() {
        return currentPack;
    }
    public void setCurrentPack(Pack currentPack) {
        this.currentPack = currentPack;
    }

    public LootPack getCurrentLootPack() {
        return currentLootPack;
    }
    public void setCurrentLootPack(LootPack currentLootPack) {
        this.currentLootPack = currentLootPack;
    }

    public Ensemble getCurrentEnsemble() {
        return currentEnsemble;
    }
    public void setCurrentEnsemble(Ensemble currentEnsemble) {
        this.currentEnsemble = currentEnsemble;
    }

    public ArrayList<MiniatureCarte> getListCards() {
        return listCards;
    }
    public void setListCards(ArrayList<MiniatureCarte> listCards) {
        this.listCards = listCards;
    }

    public ArrayList<Ensemble> getListEnsembles() {
        return listEnsembles;
    }
    public void setListEnsembles(ArrayList<Ensemble> listEnsembles) {
        this.listEnsembles = listEnsembles;
    }

    public ArrayList<LootPack> getListLootPacks() {
        return listLootPacks;
    }
    public void setListLootPacks(ArrayList<LootPack> listLootPacks) {
        this.listLootPacks = listLootPacks;
    }

    public ArrayList<Pack> getListPacks() {
        return listPacks;
    }
    public void setListPacks(ArrayList<Pack> listPacks) {
        this.listPacks = listPacks;
    }

    public int getCurrent_idEnsemble() {
        return current_idEnsemble;
    }
    public void setCurrent_idEnsemble(int current_idEnsemble) {
        this.current_idEnsemble = current_idEnsemble;
    }

    public int getCurrent_idLootPack() {
        return current_idLootPack;
    }
    public void setCurrent_idLootPack(int current_idLootPack) {
        this.current_idLootPack = current_idLootPack;
    }

    public int getCurrent_idPack() {
        return current_idPack;
    }
    public void setCurrent_idPack(int current_idPack) {
        this.current_idPack = current_idPack;
    }




    //Ajout d'une carte dans le currentEnsemble
    public void addCarteToEnsemble(int id_Carte){
        for(MiniatureCarte m : listCards){
            if(m.getId() == id_Carte) getCurrentEnsemble().getCartes().add(m);
            break;
        }
    }

    //A refaire
    public void addEnsembleToLootPack(String nom,int drop){
        currentEnsemble.setId(current_idEnsemble);
        currentEnsemble.setDropRate(drop);
        currentEnsemble.setNom(nom);
        currentLootPack.getEnsembles().add(currentEnsemble);
        currentEnsemble = new Ensemble();
        current_idEnsemble++;
    }

    //A refaire
    public void addLootPackToPack(String nom,int qte){
        currentLootPack.setId(current_idLootPack);
        currentLootPack.setQte(qte);
        currentLootPack.setNom(nom);
        currentPack.getLootPacks().add(currentLootPack);
        currentLootPack = new LootPack();
        current_idLootPack++;
    }

    //Remplis listEnsembles, listLootPacks, listPacks, contenant tout les Ensembles, LootPacks et Packs.
    public void getEvthgAlrdCreated(){
        listPacks = new ArrayList<>();
        listLootPacks = new ArrayList<>();
        listEnsembles = new ArrayList<>();
        String queryPacks = "SELECT id_Pack, nomPack, misEnVentePack, imageMiniaturePack, id_Ensemble, nomEnsemble, id_LootPack, nomLootPack, id_Carte, nomCarte, imageCarte" +
                " FROM Pack" +
                " JOIN LootPackPack USING (id_Pack)" +
                " JOIN LootPackEnsemble USING (id_LootPack)" +
                " JOIN EnsembleCarte USING (id_Ensemble)" +
                " JOIN Carte USING (id_Carte)" +
                " ORDER BY id_Pack,id_LootPack,id_Ensemble,id_Carte";


        int id_Pack = -1, id_LootPack = -1, id_Ensemble = -1, misEnVentePack = 0, maxLootPack = -1, maxEnsemble = -1;
        String nomPack = "", nomLootPack = "", nomEnsemble = "", imagePack = "";

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

                       listPacks.add(new Pack(id_Pack, nomPack, imagePack, misEnVentePack, tmpLootPack));

                       tmpCarte = new ArrayList<>();
                       tmpEnsemble = new ArrayList<>();
                       tmpLootPack = new ArrayList<>();

                   }
                   tmpCarte.add(new MiniatureCarte(resultSet.getInt("id_Carte"),resultSet.getString("nomCarte"),resultSet.getString("imageCarte")));

                   id_Pack = resultSet.getInt("id_Pack");
                   nomPack = resultSet.getString("nomPack");
                   misEnVentePack = resultSet.getInt("misEnVentePack");
                   imagePack = resultSet.getString("imageMiniaturePack");

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
            listPacks.add(new Pack(id_Pack,nomPack,imagePack,misEnVentePack,tmpLootPack));

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


        for(LootPack lp : currentPack.getLootPacks()){
            tmp = "INSERT INTO LootPackPack (id_LootPack, id_Pack, qteCartePack ) VALUES ("+lp.getId()+","+currentPack.getId()+","+lp.getQte()+");";
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

        for(Ensemble e : currentLootPack.getEnsembles()){
            queryLootPack.add("INSERT INTO LootPackEnsemble (id_LootPack, id_Ensemble, nomEnsemble, dropRatePack) VALUES ("+current_idLootPack+","+e.getId()+","+ nom +","+e.getDropRate()+");");
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

        for(MiniatureCarte m : currentEnsemble.getCartes()){
            queryEnsemble.add("INSERT INTO EnsembleCarte (id_Ensemble, id_Carte, nomEnsemble) VALUES ("+current_idEnsemble+","+m.getId()+","+nom+");");
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
            if (p.getId() == idPack) {

                if(p.getMisEnVente() == 0) {      //Pack non mis en vente
                    updateMisEnVente = "UPDATE Pack SET misEnVentePack = 1 WHERE id_Pack="+idPack+";";
                    Manager.getManager().sendRequestUpdate(updateMisEnVente,connection);
                    p.setMisEnVente(1);
                }
                else {                       //Pack actuellement en vente.
                    updateMisEnVente = "UPDATE Pack SET misEnVentePack = 0 WHERE id_Pack="+idPack+";";
                    Manager.getManager().sendRequestUpdate(updateMisEnVente,connection);
                    p.setMisEnVente(0);
                }
                return 0;
            }
        }
        return -1;
    }

    public void removeCarteFromEnsemble(int idCarte) {

        int k = -1;
        for (int i = 0; i < currentEnsemble.getCartes().size(); i++) {
            if (currentEnsemble.getCartes().get(i).getId() == idCarte) {
                k = i;
                currentEnsemble.getCartes().remove(k);
                break;
            }
        }

        //Si l'ensemble est un ensemble existant, on supprime la carte de l'ensemble dans la DB (effectif sur toutes les instances de l'Ensemble, donc pour tout les Packs associés)
        if (k >= 0) {
            for(int j = 0; j < listEnsembles.size(); j++) {
                if (listEnsembles.get(j).getId() == currentEnsemble.getId()) {
                    String queryRmCrd = "DELETE FROM EnsembleCartes WHERE id_Carte="+idCarte+" AND id_Ensemble="+currentEnsemble.getId()+";";
                    Manager.getManager().sendRequestUpdate(queryRmCrd,connection);
                    listEnsembles.get(j).getCartes().remove(k);
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
            if (p.getId() == idPack) {
                currentPack = p;
                break;
            }
        }
    }

    public void chooseLootPack(int idLP){
        for(LootPack lp : listLootPacks){
            if (lp.getId() == idLP) {
                currentLootPack = lp;
                break;
            }
        }
    }

    public void chooseEnsemble(int idEns){
        for(Ensemble e : listEnsembles){
            if (e.getId() == idEns) {
                currentEnsemble = e;
                break;
            }
        }
    }

}
