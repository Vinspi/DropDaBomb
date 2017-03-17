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
        String queryInsert;
        for(MiniatureCarte m : getListCards()){
            if(m.getId() == id_Carte) {
                getCurrentEnsemble().getCartes().add(m);
                if (getCurrentEnsemble().getNom() != null){
                    queryInsert = "INSERT INTO EnsembleCarte (id_Ensemble, id_Carte, nomEnsemble) VALUES ("+getCurrentEnsemble().getId()+","+id_Carte+",'"+getCurrentEnsemble().getNom()+"');";
                }
                else {
                    queryInsert = "INSERT INTO EnsembleCarte (id_Ensemble, id_Carte) VALUES ("+getCurrentEnsemble().getId()+","+id_Carte+");";
                }
                Manager.getManager().sendRequestUpdate(queryInsert,connection);
                try {
                    if(connection != null) connection.close();
                }catch(SQLException e){
                    //Do nothing.
                }
                break;
            }
        }


    }

    //A refaire
    public void addEnsembleToLootPack(int id_Ensemble,float drop){
        String queryInsert;
        for(Ensemble e : getListEnsembles()){
            if(e.getId() == id_Ensemble) {
                e.setDropRate(drop);
                getCurrentLootPack().getEnsembles().add(e);
                if (getCurrentLootPack().getNom() != null){
                    queryInsert = "INSERT INTO LootPackEnsemble (id_LootPack, id_Ensemble, dropRatePack, nomLootPack) VALUES ("+getCurrentLootPack().getId()+","+id_Ensemble+","+drop+",'"+getCurrentLootPack().getNom()+"');";
                }
                else {
                    queryInsert = "INSERT INTO LootPackEnsemble (id_LootPack, id_Ensemble, dropRatePack) VALUES ("+getCurrentLootPack().getId()+","+id_Ensemble+","+drop+");";

                }
                Manager.getManager().sendRequestUpdate(queryInsert,connection);
                try {
                    if(connection != null) connection.close();
                }catch(SQLException ex){
                    //Do nothing.
                }
                break;
            }
        }
    }

    //A refaire
    public void addLootPackToPack(int id_LootPack,int qte){
        String queryInsert;
        for(LootPack lp : getListLootPacks()){
            if(lp.getId() == id_LootPack) {
                lp.setQte(qte);
                getCurrentPack().getLootPacks().add(lp);

                queryInsert = "INSERT INTO LootPackPack (id_Pack, id_LootPack, qteCartePack) VALUES ("+getCurrentPack().getId()+","+id_LootPack+","+qte+");";

                Manager.getManager().sendRequestUpdate(queryInsert,connection);
                try {
                    if(connection != null) connection.close();
                }catch(SQLException ex){
                    //Do nothing.
                }
                break;
            }
        }
    }
    /* Query pas terrible
    SELECT id_Pack, nomPack, misEnVente, imageMiniaturePack, id_Ensemble, nomEnsemble, dropRatePack, id_LootPack, nomLootPack, qteCartePack, id_Carte, nomCarte, imageCarte
                FROM Offre
                LEFT JOIN Pack USING (id_Pack)
                        RIGHT JOIN LootPackPack USING (id_Pack)
                        RIGHT JOIN LootPackEnsemble USING (id_LootPack)
                        RIGHT JOIN EnsembleCarte USING (id_Ensemble)
                        JOIN Carte USING (id_Carte)
                        ORDER BY id_Pack,id_LootPack,id_Ensemble,id_Carte;
    */

    /* -> Query du tueur
    SELECT id_Pack, nomPack, misEnVente, imageMiniaturePack, id_Ensemble, nomEnsemble, dropRatePack, id_LootPack, nomLootPack, qteCartePack, id_Carte, nomCarte, imageCarte
FROM Offre
RIGHT JOIN
((SELECT id_Pack, nomPack, imageMiniaturePack, id_Ensemble, nomEnsemble, dropRatePack, id_LootPack, nomLootPack, qteCartePack, id_Carte, nomCarte, imageCarte FROM Pack LEFT JOIN
	(SELECT * FROM LootPackPack
                        RIGHT JOIN LootPackEnsemble USING (id_LootPack)
                        RIGHT JOIN EnsembleCarte USING (id_Ensemble)
                        JOIN Carte USING (id_Carte)) lpe USING (id_Pack))


UNION
(SELECT id_Pack, nomPack, imageMiniaturePack, id_Ensemble, nomEnsemble, dropRatePack, id_LootPack, nomLootPack, qteCartePack, id_Carte, nomCarte, imageCarte FROM Pack RIGHT JOIN
	(SELECT * FROM LootPackPack
                        RIGHT JOIN LootPackEnsemble USING (id_LootPack)
                        RIGHT JOIN EnsembleCarte USING (id_Ensemble)
                        JOIN Carte USING (id_Carte) ) lpe2 USING (id_Pack))) ali USING (id_Pack)
                       	ORDER BY id_Pack,id_LootPack,id_Ensemble,id_Carte
     */

    //Remplis listEnsembles, listLootPacks, listPacks, contenant tout les Ensembles, LootPacks et Packs.
    public void getEvthgAlrdCreated(){
        listPacks = new ArrayList<>();
        listLootPacks = new ArrayList<>();
        listEnsembles = new ArrayList<>();
        String queryPacks = "SELECT id_Pack, nomPack, misEnVente, imageMiniaturePack, id_Ensemble, nomEnsemble, dropRatePack, id_LootPack, nomLootPack, qteCartePack, id_Carte, nomCarte, imageCarte\n" +
                "FROM Offre\n " +
                "RIGHT JOIN\n " +
                " ((SELECT id_Pack, nomPack, imageMiniaturePack, id_Ensemble, nomEnsemble, dropRatePack, id_LootPack, nomLootPack, qteCartePack, id_Carte, nomCarte, imageCarte FROM Pack LEFT JOIN\n" +
                " \t(SELECT * FROM LootPackPack\n" +
                "                        RIGHT JOIN LootPackEnsemble USING (id_LootPack)\n" +
                "                        RIGHT JOIN EnsembleCarte USING (id_Ensemble)\n" +
                "                        JOIN Carte USING (id_Carte)) lpe USING (id_Pack))\n" +
                " \n" +
                " \n" +
                " UNION\n" +
                " (SELECT id_Pack, nomPack, imageMiniaturePack, id_Ensemble, nomEnsemble, dropRatePack, id_LootPack, nomLootPack, qteCartePack, id_Carte, nomCarte, imageCarte FROM Pack RIGHT JOIN\n" +
                " \t(SELECT * FROM LootPackPack\n" +
                "                        RIGHT JOIN LootPackEnsemble USING (id_LootPack)\n" +
                "                        RIGHT JOIN EnsembleCarte USING (id_Ensemble)\n" +
                "                        JOIN Carte USING (id_Carte) ) lpe2 USING (id_Pack))) ali USING (id_Pack)\n" +
                "                       \tORDER BY id_Pack,id_LootPack,id_Ensemble,id_Carte";


        int id_Pack = -1, id_LootPack = -1, id_Ensemble = -1, misEnVentePack = 0, qteCartePack = 0;
        float dropRatePack = 0;
        String nomPack = "", nomLootPack = "", nomEnsemble = "", imagePack = "";
        ArrayList<Integer> listIdEnsembles = new ArrayList<>();
        ArrayList<Integer> listIdLootPacks = new ArrayList<>();

        ArrayList<LootPack> tmpLootPack = new ArrayList();
        ArrayList<Ensemble> tmpEnsemble = new ArrayList();
        ArrayList<MiniatureCarte> tmpCarte = new ArrayList();

        ResultSet resultSet = Manager.getManager().sendRequestQuery(queryPacks,connection);
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("id_Pack") != 0 & (resultSet.getInt("id_LootPack") == 0)){     //Pack non null mais vide (sans LootPack)
                    listPacks.add(new Pack(resultSet.getInt("id_Pack"),resultSet.getString("nomPack"),resultSet.getString("imageMiniaturePack"),resultSet.getInt("misEnVente"),new ArrayList<LootPack>()));

                //Finir ici : manque un else if (Pack non null non vide mais avec ensemble incomplet (OU PAS : PEUT ËTRE PAS POSSIBLE EN FAIT)
                }
               else {

                   if (id_Pack != resultSet.getInt("id_Pack")) { //Nouveau Pack

                       if (id_Pack != -1) {     //Premiere iteration, on ne fait qu'ajouter la carte et initialiser les variables.
                           if (!(listIdEnsembles.contains(id_Ensemble))) {
                               listIdEnsembles.add(id_Ensemble);
                               listEnsembles.add(new Ensemble(id_Ensemble, nomEnsemble, tmpCarte));
                           }

                           if (id_LootPack != 0) {
                               tmpEnsemble.add(new Ensemble(id_Ensemble, nomEnsemble, dropRatePack, tmpCarte));
                               if (!(listIdLootPacks.contains(id_LootPack))) {
                                   listIdLootPacks.add(id_LootPack);
                                   listLootPacks.add(new LootPack(id_LootPack, nomLootPack, tmpEnsemble));
                               }
                           }
                           if (id_Pack != 0) {
                               tmpLootPack.add(new LootPack(id_LootPack, nomLootPack, qteCartePack, tmpEnsemble));
                               listPacks.add(new Pack(id_Pack, nomPack, imagePack, misEnVentePack, tmpLootPack));
                           }

                           tmpCarte = new ArrayList<>();
                           tmpEnsemble = new ArrayList<>();
                           tmpLootPack = new ArrayList<>();

                       }
                       tmpCarte.add(new MiniatureCarte(resultSet.getInt("id_Carte"), resultSet.getString("nomCarte"), resultSet.getString("imageCarte")));

                       id_Pack = resultSet.getInt("id_Pack");
                       nomPack = resultSet.getString("nomPack");
                       misEnVentePack = resultSet.getInt("misEnVente");
                       imagePack = resultSet.getString("imageMiniaturePack");

                       id_LootPack = resultSet.getInt("id_LootPack");
                       nomLootPack = resultSet.getString("nomLootPack");
                       qteCartePack = resultSet.getInt("qteCartePack");

                       id_Ensemble = resultSet.getInt("id_Ensemble");
                       nomEnsemble = resultSet.getString("nomEnsemble");
                       dropRatePack = resultSet.getFloat("dropRatePack");
                   } else {
                       if (id_LootPack != resultSet.getInt("id_LootPack")) {

                           tmpEnsemble.add(new Ensemble(id_Ensemble, nomEnsemble, dropRatePack, tmpCarte));
                           if (!(listIdEnsembles.contains(id_Ensemble))) {
                               listIdEnsembles.add(id_Ensemble);
                               listEnsembles.add(new Ensemble(id_Ensemble, nomEnsemble, tmpCarte));
                           }

                           if (id_LootPack != 0) {
                               tmpLootPack.add(new LootPack(id_LootPack, nomLootPack, qteCartePack, tmpEnsemble));
                               if (!(listIdLootPacks.contains(id_LootPack))) {
                                   listIdLootPacks.add(id_LootPack);
                                   listLootPacks.add(new LootPack(id_LootPack, nomLootPack, tmpEnsemble));
                               }
                           }
                           tmpEnsemble = new ArrayList<>();
                           tmpCarte = new ArrayList<>();
                           tmpCarte.add(new MiniatureCarte(resultSet.getInt("id_Carte"), resultSet.getString("nomCarte"), resultSet.getString("imageCarte")));


                           id_LootPack = resultSet.getInt("id_LootPack");
                           nomLootPack = resultSet.getString("nomLootPack");
                           qteCartePack = resultSet.getInt("qteCartePack");


                           id_Ensemble = resultSet.getInt("id_Ensemble");
                           nomEnsemble = resultSet.getString("nomEnsemble");
                           dropRatePack = resultSet.getFloat("dropRatePack");

                       } else {
                           if (id_Ensemble != resultSet.getInt("id_Ensemble")) {

                               tmpEnsemble.add(new Ensemble(id_Ensemble, nomEnsemble, dropRatePack, tmpCarte));
                               if (!(listIdEnsembles.contains(id_Ensemble))) {
                                   listIdEnsembles.add(id_Ensemble);
                                   listEnsembles.add(new Ensemble(id_Ensemble, nomEnsemble, tmpCarte));
                               }

                               tmpCarte = new ArrayList<>();
                               tmpCarte.add(new MiniatureCarte(resultSet.getInt("id_Carte"), resultSet.getString("nomCarte"), resultSet.getString("imageCarte")));


                               id_Ensemble = resultSet.getInt("id_Ensemble");
                               nomEnsemble = resultSet.getString("nomEnsemble");
                               dropRatePack = resultSet.getFloat("dropRatePack");
                           } else {
                               tmpCarte.add(new MiniatureCarte(resultSet.getInt("id_Carte"), resultSet.getString("nomCarte"), resultSet.getString("imageCarte")));

                           }
                       }
                   }
               }

            }
            tmpEnsemble.add(new Ensemble(id_Ensemble,nomEnsemble,dropRatePack,tmpCarte));
            if(!(listIdEnsembles.contains(id_Ensemble))){
                listIdEnsembles.add(id_Ensemble);
                listEnsembles.add(new Ensemble(id_Ensemble,nomEnsemble,tmpCarte));
            }
            tmpLootPack.add(new LootPack(id_LootPack,nomLootPack,qteCartePack,tmpEnsemble));
            if(!(listIdLootPacks.contains(id_LootPack))) {
                listIdLootPacks.add(id_LootPack);
                listLootPacks.add(new LootPack(id_LootPack, nomLootPack, tmpEnsemble));
            }
            listPacks.add(new Pack(id_Pack,nomPack,imagePack,misEnVentePack,tmpLootPack));




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
        String queryOffre = "INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,id_Pack,typeOffre,image) VALUES ("+id_Offre+","+prixIG+","+prixIRL+","+current_idPack+",'Pack');";

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
    public void createLootPack(String nom) {
        //Récupérer le premier identifiant disponible :
        //1) Requête à la DB (inutile en fait, traitement en objet ?)
        String queryId = "SELECT DISTINCT id_LootPack FROM LootPackEnsemble ORDER BY id_LootPack";
        ResultSet setId = Manager.getManager().sendRequestQuery(queryId,connection);
        int max = 0;
        try {
            while(setId.next()){
                if(max+1 < setId.getInt("id_LootPack")) {       //Donc qu'il y a un "trou" (un identifiant libre) entre deux identifiants d'ensemble.
                    break;
                }
                max = setId.getInt("id_LootPack");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(connection != null) connection.close();
            }catch (SQLException e){

            }
        }
        setCurrent_idLootPack(max+1);
        setCurrentLootPack(new LootPack(getCurrent_idLootPack(),nom));
        getListLootPacks().add(getCurrentLootPack());

    }


    // /!\INJ
    //Créer un Ensemble dans la db, le liant à ses cartes.
    public void createEnsemble(String nom){


        //Récupérer le premier identifiant disponible :
        //1) Requête à la DB (inutile en fait)
        String queryId = "SELECT DISTINCT id_Ensemble FROM EnsembleCarte ORDER BY id_Ensemble";
        ResultSet setId = Manager.getManager().sendRequestQuery(queryId,connection);
        int max = -1;
        try {
            while(setId.next()){
                if(max+1 < setId.getInt("id_Ensemble")) {       //Donc qu'il y a un "trou" (un identifiant libre) entre deux identifiants d'ensemble.
                    break;
                }
                max = setId.getInt("id_Ensemble");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(connection != null) connection.close();
            }catch (SQLException e){

            }
        }
        setCurrent_idEnsemble(max+1);
        setCurrentEnsemble(new Ensemble(getCurrent_idEnsemble(),nom));
        getListEnsembles().add(getCurrentEnsemble());

    }

    public void switchMiseEnVente(){
        String updateMisEnVente;

        if(getCurrentPack().getMisEnVente() == 0){
            updateMisEnVente = "UPDATE Pack SET misEnVentePack = 1 WHERE id_Pack="+getCurrentPack().getId()+";";
            Manager.getManager().sendRequestUpdate(updateMisEnVente,connection);
            getCurrentPack().setMisEnVente(1);
        }
        else {
            updateMisEnVente = "UPDATE Pack SET misEnVentePack = 0 WHERE id_Pack="+getCurrentPack().getId()+";";
            Manager.getManager().sendRequestUpdate(updateMisEnVente,connection);
            getCurrentPack().setMisEnVente(1);
        }
    }

    public void removeCarteFromEnsemble(int idCarte) {


        for (int i = 0; i < currentEnsemble.getCartes().size(); i++) {
            if (currentEnsemble.getCartes().get(i).getId() == idCarte) {
                currentEnsemble.getCartes().remove(i);
                String queryRmCrd = "DELETE FROM EnsembleCarte WHERE id_Carte="+idCarte+" AND id_Ensemble="+currentEnsemble.getId()+";";
                Manager.getManager().sendDeleteUpdate(queryRmCrd,connection);
                break;
            }
        }

        try {
            if (connection != null) connection.close();
        }catch (SQLException e){

        }
    }

    public void removeEnsembleFromLootPack(int id_ensemble){
        for (int i = 0; i < currentLootPack.getEnsembles().size(); i++) {
            if (currentLootPack.getEnsembles().get(i).getId() == id_ensemble) {
                currentLootPack.getEnsembles().remove(i);
                String queryRmEns = "DELETE FROM LootPackEnsemble WHERE id_Ensemble="+id_ensemble+" AND id_LootPack="+currentLootPack.getId()+";";
                Manager.getManager().sendDeleteUpdate(queryRmEns,connection);
                break;
            }
        }

        try {
            if (connection != null) connection.close();
        }catch (SQLException e){

        }
    }
    public void modifyDropRate(int id_ensemble, float drop){
        for (int i = 0; i < currentLootPack.getEnsembles().size(); i++) {
            if (currentLootPack.getEnsembles().get(i).getId() == id_ensemble) {
                currentLootPack.getEnsembles().get(i).setDropRate(drop);
                String updateDrop = "UPDATE LootPackEnsemble SET dropRatePack="+drop+" WHERE (id_Ensemble="+id_ensemble+" AND id_LootPack="+currentLootPack.getId()+");";
                Manager.getManager().sendRequestUpdate(updateDrop,connection);
                break;
            }
        }
        try {
            if (connection != null) connection.close();
        }catch (SQLException e){

        }
    }

    public void removeLootPackFromPack(int id_LootPack){
        for (int i = 0; i < currentPack.getLootPacks().size(); i++) {
            if (currentPack.getLootPacks().get(i).getId() == id_LootPack) {
                currentPack.getLootPacks().remove(i);
                String queryRmEns = "DELETE FROM LootPackPack WHERE id_Ensemble="+id_LootPack+" AND id_Pack="+currentPack.getId()+";";
                Manager.getManager().sendDeleteUpdate(queryRmEns,connection);
                break;
            }
        }

        try {
            if (connection != null) connection.close();
        }catch (SQLException e){

        }


    }

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
