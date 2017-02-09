package Manager;


import View.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 * Created by vinspi on 27/01/17.
 */

public class ShopManager {

    public class TripletEnsemble
    {
        int id_Ensemble;
        float dropRate;
        ArrayList<Integer> Cartes = new ArrayList<Integer>();


        public TripletEnsemble(int id, float value, ArrayList<Integer> Cartes)
        {
            this.id_Ensemble = id;
            this.dropRate = value;
            this.Cartes = Cartes;
        }

    }
    public class Doublet
    {
        int id;
        int value;

        public Doublet(int id, int value)
        {
            this.id = id;
            this.value = value;
        }
    }

    public class ListEnsemble{
        int id;
        ArrayList<TripletEnsemble> ensembles;
        public ListEnsemble(int id, ArrayList<TripletEnsemble> Le){
            this.id = id;
            this.ensembles = Le;
        }


    }

    Connection connection = null;
    private List<PackView> listPackView = new LinkedList<>();
    private List<BoostView> listBoostView = new LinkedList<>();
    private List<SkinMapView> listSkinMapView = new LinkedList<>();
    private List<SkinCartonView> listSkinCartonView = new LinkedList<>();

    public void addPackView(PackView packView){
        listPackView.add(packView);
    }
    public void addBoostView(BoostView boostView){
        listBoostView.add(boostView);
    }
    public void addSkinMapView(SkinMapView skinMapView){
        listSkinMapView.add(skinMapView);
    }
    public void addSkinCartonView(SkinCartonView skinCartonView){ listSkinCartonView.add(skinCartonView); }

    public List<PackView> getListPackView() {
        return listPackView;
    }
    public void setListPackView(List<PackView> listPackView) {
        this.listPackView = listPackView;
    }

    public List<BoostView> getListBoostView() {
        return listBoostView;
    }
    public void setListBoostView(List<BoostView> listBoostView) {
        this.listBoostView = listBoostView;
    }

    public List<SkinMapView> getListSkinMapView() {
        return listSkinMapView;
    }
    public void setListSkinMapView(List<SkinMapView> listSkinMapView) {
        this.listSkinMapView = listSkinMapView;
    }

    public List<SkinCartonView> getListSkinCartonView() {
        return listSkinCartonView;
    }
    public void setListSkinCartonView(List<SkinCartonView> listSkinCartonView) {
        this.listSkinCartonView = listSkinCartonView;
    }

    public LinkedList<PackView> getAllPackOffers(){

        LinkedList<PackView> listItem = new LinkedList<>();



        String query = "SELECT prixMonnaieIG, prixMonnaieIRL, id_Pack, descriptionPack, imageMiniaturePack FROM Offre" +
                " JOIN Pack USING(id_pack);";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){

                listItem.add((new PackView(resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_Pack"),resultSet.getString("imageMiniaturePack"),resultSet.getString("descriptionPack"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null)
                    connection.close();
            }catch (SQLException e){

            }
        }

        return listItem;
    }
    public LinkedList<BoostView> getAllBoostOffers(){

        LinkedList<BoostView> listItem = new LinkedList<>();


        String query = "SELECT prixMonnaieIG, prixMonnaieIRL, id_Boost, imageMiniatureBoost, descriptionBoost FROM Offre " +

                "JOIN OffreBoost USING (id_Offre)" +
                " JOIN Boost USING(id_Boost);";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){

                listItem.add((new BoostView(resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_Boost"),resultSet.getString("imageMiniatureBoost"),resultSet.getString("descriptionBoost"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null)
                    connection.close();
            }catch (SQLException e){

            }
        }

        return listItem;
    }
    public LinkedList<SkinCartonView> getAllCartonOffers(String pseudo){

        LinkedList<SkinCartonView> listItem = new LinkedList<>();

        String query =  "SELECT prixMonnaieIRL, prixMonnaieIG, id_SkinCartonCarte, imageMiniatureCarton, descriptionCarton FROM Offre JOIN OffreCartonCarte USING (id_Offre)" +
                " JOIN SkinCartonCarte USING (id_SkinCartonCarte) WHERE id_SkinCartonCarte NOT IN (SELECT id_SkinCartonCarte FROM posséderSkinCartonCarte WHERE Pseudo LIKE '"+pseudo+"');";
        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                listItem.add((new SkinCartonView(resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_SkinCartonCarte"),resultSet.getString("imageMiniatureCarton"),resultSet.getString("descriptionCarton"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null)
                    connection.close();
            }catch (SQLException e){

            }
        }
        return listItem;
    }
    public LinkedList<SkinMapView> getAllMapOffers(String pseudo){

        LinkedList<SkinMapView> listItem = new LinkedList<>();

        String query =  "SELECT prixMonnaieIG, prixMonnaieIRL, id_SkinMap, imageMiniatureMap, descriptionMap FROM Offre JOIN OffreMap USING (id_Offre) JOIN Map USING (id_SkinMap) WHERE id_SkinMap NOT IN (SELECT id_SkinMap FROM posséderSkinMap WHERE Pseudo LIKE '"+pseudo+"');";
        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                listItem.add((new SkinMapView(resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_SkinMap"),resultSet.getString("imageMiniatureMap"),resultSet.getString("descriptionMap"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null)
                    connection.close();
            }catch (SQLException e){

            }
        }
        return listItem;
    }
    public LinkedList<IconeView> getAllIconeOffers(String pseudo){
        LinkedList<IconeView> listIcone = new LinkedList<>();
        String query = "SELECT prixMonnaieIRL, prixMonnaieIG, id_IconeJoueur, imageMiniatureIcone, descriptionIcone FROM Offre JOIN OffreIcone USING (id_Offre)" +
                " JOIN IconeJoueur USING (id_IconeJoueur) WHERE id_IconeJoueur NOT IN (SELECT id_IconeJoueur FROM posséderIconeJoueur WHERE Pseudo LIKE '"+pseudo+"');";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                listIcone.add((new IconeView(resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_IconeJoueur"),resultSet.getString("imageMiniatureIcone"),resultSet.getString("descriptionIcone"))));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) connection.close();
            }catch (SQLException e){
                //Ignorer
            }
        }

        return listIcone;
    }

    //Tout les fonctions de gestion d'achat retournent des ArrayList de Doublet (id, value) où id sont les id des achats et value la quantité. Le but étant de renvoyer cette AL pour faciliter un potentiel affichage.

    //Autre point de vue :  on renvoie chaque carte une par une pour faire un affichage et faire "patienter" le client pendant les animations.
    public ArrayList<Doublet> gererAchatPackRandomFullOpti(String pseudo, String id_Offre) {


        long deb = System.nanoTime();
        int x, i = -1, y = -1, a = -1, b = -1;
        float r;
        float c = -1, d = -1;
        float rand;

        String queryEnsemble;
        String queryListLoot = "SELECT id_LootPack, qteCarte, id_Ensemble, dropRatePack, id_Carte" +     //Récupère TOUT
                " FROM EnsembleCarte" +
                " JOIN Ensemble USING (id_Ensemble)" +
                " JOIN LootPackEnsemble USING (id_Ensemble)" +
                " JOIN LootPack USING (id_LootPack)" +
                " JOIN Pack USING (id_Pack)" +
                " JOIN Offre USING (id_Pack)" +
                " WHERE id_Offre = "+id_Offre +
                " ORDER BY id_LootPack,id_Ensemble;";

        String queryCartesPossedees = "SELECT id_Carte " +        //Récupère toutes les cartes possédées par le joueur.
                "FROM JoueurCarteDeck " +
                "WHERE (Pseudo LIKE '"+pseudo+"' AND id_Deck LIKE '"+pseudo+"0');";


        ArrayList<String> queryInsertions = new ArrayList<>();          //Liste des query d'insertions
        ArrayList<Integer> ListCartesPossedees = new ArrayList<>();     //Liste des cartes possédées par le joueur.
        ArrayList<Doublet> ListLoot = new ArrayList<>();                //Liste des doublets (id_LootPack,qteCarte), càd le nombre de cartes à drop dans chaque LootPack.
        ArrayList<Integer> ListCartesObtenus = new ArrayList<>();       //Liste des (id_Carte). Arrangement de la liste en fin de fonction de façon à renvoyer une liste s.
        ArrayList<Integer> ListCopiesCartes = new ArrayList<>();        //Trouver un autre moyen si besoin d'optimisation.
//        ArrayList<Couple> ListEnsemble = new ArrayList<>();                                 //Liste des couples (id_Ensemble,dropRatePack), càd la liste des Ensembles associés à chaque LootPack ainsi que la probabilité qu'on rand dans cet ensemble.
        ArrayList<Doublet> s = new ArrayList<>();                       //Résultat : liste de doublets (id_Carte,qteCarte).

        ArrayList<Integer> ListCartes = new ArrayList<>();              //Liste de cartes composant un Ensemble.
        ArrayList<TripletEnsemble> tmpListSet = new ArrayList<>();
        ArrayList<ListEnsemble> ListSet = new ArrayList<>();

        long fquery = System.nanoTime();
        ResultSet setPack = Manager.getManager().sendRequestQuery(queryListLoot,connection);
        System.out.println("fquery = "+(System.nanoTime()-fquery)/Math.pow(10,9));
        try{
            long set = System.nanoTime();
            while (setPack.next()){
                if (i != setPack.getInt("id_LootPack")){
                    i = setPack.getInt("id_LootPack");
                    a = setPack.getInt("id_Ensemble");
                    c = setPack.getFloat("dropRatePack");

                    ListLoot.add(new Doublet(i,setPack.getInt("qteCarte")));
                    if (!(tmpListSet.isEmpty())) {
                        tmpListSet.add(new TripletEnsemble(b,d,ListCartes));
                        ListSet.add(new ListEnsemble(y,tmpListSet));
                    }

                    tmpListSet = new ArrayList<>();
                    ListCartes = new ArrayList<>();
                    ListCartes.add(setPack.getInt("id_Carte"));

                }
                else {
                    if (y != setPack.getInt("id_LootPack")) {
                        y = setPack.getInt("id_LootPack");
                    }


                    if (a == setPack.getInt("id_Ensemble")){
                        if (b != setPack.getInt("id_Ensemble")){
                            b = setPack.getInt("id_Ensemble"); d = setPack.getFloat("dropRatePack");
                        }
                        ListCartes.add(setPack.getInt("id_Carte"));

                    }
                    else {
                        a = setPack.getInt("id_Ensemble"); c = setPack.getFloat("dropRatePack") ;
                        tmpListSet.add(new TripletEnsemble(b,d,ListCartes));
                        ListCartes = new ArrayList<>();
                        ListCartes.add(setPack.getInt("id_Carte"));
                    }

                }

            }
            tmpListSet.add(new TripletEnsemble(a,c,ListCartes));
            ListSet.add(new ListEnsemble(i,tmpListSet));
            System.out.println("set = "+(System.nanoTime()-set)/Math.pow(10,9));

            /*for(Doublet doublet : ListLoot){
                System.out.println(doublet.id + "     " + doublet.value);
            }
            for (ListEnsemble le : ListSet){
                System.out.print(le.id+":      ");
                for(TripletEnsemble tripletEnsemble : le.ensembles) {
                    System.out.print(tripletEnsemble.id_Ensemble + "/" + tripletEnsemble.dropRate + " [");
                    for (int id_carte : tripletEnsemble.Cartes){
                        System.out.print(id_carte+" ");
                    }
                    System.out.print("]     ");
                }
                System.out.println();
            }*/

            long queryC = System.nanoTime();
            ResultSet setCartesPossedees = Manager.getManager().sendRequestQuery(queryCartesPossedees,connection);
            while(setCartesPossedees.next()){
                ListCartesPossedees.add(setCartesPossedees.getInt("id_Carte"));
            }
            System.out.println("queryC = "+(System.nanoTime()-queryC)/Math.pow(10,9));

            long loop = System.nanoTime();
            for(Doublet doubletLoot : ListLoot){                     //On choisit un LootPack coupleLoot (càd une partie du Pack)
                for(int q = 0; q < doubletLoot.value; q++) {
                    r = 0;
                    int id_Carte;
                    rand = (float) Math.random();

                    for (TripletEnsemble coupleEnsemble : ListSet.get(doubletLoot.id).ensembles) {
                        r += coupleEnsemble.dropRate;
                        if (rand < r) {
                            id_Carte = coupleEnsemble.Cartes.get((int) (Math.random() * coupleEnsemble.Cartes.size()));
                            //for(int q = 0; q < doubletLoot.value; q++){
                            ListCartesObtenus.add(id_Carte);
                            ListCopiesCartes.add(id_Carte);
                            //}
                            break;
                        }
                    }
                }
            }
            System.out.println("loop = "+(System.nanoTime()-loop)/Math.pow(10,9));
            //Arrangement de la liste : regroupement des doublons.
            //s = new ArrayList<>();
            long doublons = System.nanoTime();
            for(int p : ListCartesObtenus){
                x = 0;
                while(ListCopiesCartes.remove((Object) p)){
                    x++;
                }
                if (x > 0) s.add(new Doublet(p,x));
            }
            System.out.println("doublons = "+(System.nanoTime()-doublons)/Math.pow(10,9));
            long insert = System.nanoTime();
            //INSERT dans la table JoueurCarteDeck
            for(Doublet doublet : s){
                if (ListCartesPossedees.contains(doublet.id)) queryInsertions.add("UPDATE JoueurCarteDeck SET qteCarteDeck=qteCarteDeck+"+doublet.value+" WHERE (id_Deck LIKE '"+pseudo+"0' AND id_Carte ="+doublet.id+");");
                else queryInsertions.add("INSERT INTO JoueurCarteDeck (Pseudo,id_Deck,id_Carte,qteCarteDeck) VALUES ('"+pseudo+"','"+pseudo+"0',"+doublet.id+","+doublet.value+");");
            }
            Manager.getManager().sendMultipleRequestUpdate(queryInsertions,connection);

            System.out.println("insert = "+(System.nanoTime()-insert)/Math.pow(10,9));
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {

                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e){

            }
        }
        System.out.println((System.nanoTime()- deb)/Math.pow(10,9));
        System.out.println(ListCartesObtenus);
        return s;
    }
    public ArrayList<Doublet> gererAjoutMap(String pseudo, String id_Offre){


        String queryMap = "SELECT id_SkinMap" +
                " FROM OffreMap" +
                " WHERE id_Offre = "+id_Offre+";";
        ArrayList<Doublet> s = new ArrayList<>();
        ArrayList<String> queryInsert = new ArrayList<>();
        int id_Map;
        ResultSet setMap = Manager.getManager().sendRequestQuery(queryMap,connection);
        try {
            while (setMap.next()) {
                id_Map = setMap.getInt("id_SkinMap");
                queryInsert.add("INSERT INTO posséderSkinMap (Pseudo,id_SkinMap) VALUES ('" + pseudo + "'," + id_Map + ");");
                s.add(new Doublet(id_Map,1));
            }
            Manager.getManager().sendMultipleRequestUpdate(queryInsert,connection);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){

            }
        }


        return s;
    }
    public ArrayList<Doublet> gererAjoutCartonCarte(String pseudo, String id_Offre) {

        ArrayList<Doublet> s = new ArrayList<>();
        String queryCarton = "SELECT id_SkinCartonCarte" +
                " FROM OffreCartonCarte" +
                " WHERE id_Offre = "+id_Offre+";";
        ArrayList<String> queryInsert = new ArrayList<>();
        int id_Carton;
        ResultSet setCarton = Manager.getManager().sendRequestQuery(queryCarton,connection);
        try {
            while (setCarton.next()){
                id_Carton = setCarton.getInt("id_SkinCartonCarte");
                queryInsert.add("INSERT INTO posséderSkinCartonCarte (Pseudo,id_SkinCartonCarte) VALUES ('"+pseudo+"',"+ id_Carton+");");
                s.add(new Doublet(id_Carton,1));
            }
            Manager.getManager().sendMultipleRequestUpdate(queryInsert,connection);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if (connection != null) connection.close();
            }catch(SQLException e){
                //Ignorer
            }
        }

        return s;
    }
    public ArrayList<Doublet> gererAjoutBoost(String pseudo, String id_Offre){
        ArrayList<Doublet> s = new ArrayList<>();

        String queryBoost = "SELECT id_Boost, typeBoost" +
                " FROM OffreBoost" +
                " JOIN Boost USING (id_Boost)" +
                " WHERE id_Offre = "+id_Offre+";";
        String queryActiver = "SELECT id_Boost" +
                " FROM activer" +
                " WHERE Pseudo LIKE '"+pseudo+"';";

        ArrayList<Integer> boostActif = new ArrayList<>();
        ArrayList<String> queryAjout = new ArrayList<>();

        int id_Boost;
        String typeBoost, duree;
        Pattern patternMatchs; Matcher matcherMatchs;
        Pattern patternHeures; Matcher matcherHeures;


        ResultSet setActifs = Manager.getManager().sendRequestQuery(queryActiver,connection);
        try{
            while (setActifs.next()){
                boostActif.add(setActifs.getInt("id_Boost"));
            }

            ResultSet setBoost = Manager.getManager().sendRequestQuery(queryBoost,connection);
            while (setBoost.next()){
                id_Boost = setBoost.getInt("id_Boost");
                typeBoost = setBoost.getString("typeBoost");

                patternMatchs = Pattern.compile("(\\d+)\\s(Matchs)");
                matcherMatchs = patternMatchs.matcher(typeBoost);

                patternHeures = Pattern.compile("(\\d+)\\s(Heures)");
                matcherHeures = patternHeures.matcher(typeBoost);

                if (matcherMatchs.find()) {       //Boost XP en nbMatchs.
                    duree = matcherMatchs.group(1);
                    if (boostActif.contains(id_Boost)) queryAjout.add("UPDATE activer SET nbMatchsFin=nbMatchsFin+"+duree+" WHERE Pseudo LIKE '"+pseudo+"' AND id_Boost="+id_Boost+";");
                    else queryAjout.add("INSERT INTO activer (Pseudo,id_Boost,nbMatchsFin) VALUES ('"+pseudo+"',"+id_Boost+","+duree+");");
                }
                else if (matcherHeures.find()) {
                    duree = matcherHeures.group(1);
                    if (boostActif.contains(id_Boost)) queryAjout.add("UPDATE activer SET HeuresFin=HeuresFin+SEC_TO_TIME("+duree+"*3600) WHERE Pseudo LIKE '"+pseudo+"' AND id_Boost="+id_Boost+";");
                    else queryAjout.add("INSERT INTO activer (Pseudo,id_Boost,HeuresFin) VALUES ('"+pseudo+"',"+id_Boost+",SEC_TO_TIME("+duree+"*3600));");
                }
                s.add(new Doublet(id_Boost,1));
            }
            Manager.getManager().sendMultipleRequestUpdate(queryAjout,connection);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if (connection != null) connection.close();
            }catch (SQLException e){

            }
        }
        for(Doublet d : s){
            System.out.println(d.id+"   /   "+d.value);
        }
        return s;
    }
    public  ArrayList<Doublet> gererAjoutIcone(String pseudo, String id_Offre){
        ArrayList<Doublet> s = new ArrayList<>();

        String queryIcone = "SELECT id_IconeJoueur" +
                " FROM OffreIcone" +
                " WHERE id_Offre = "+id_Offre+";";
        ArrayList<String> queryInsert = new ArrayList<>();
        int id_Icone;
        ResultSet setIcone = Manager.getManager().sendRequestQuery(queryIcone,connection);
        try {
            while (setIcone.next()) {
                id_Icone = setIcone.getInt("id_IconeJoueur");
                queryInsert.add("INSERT INTO posséderIconeJoueur (Pseudo,id_IconeJoueur) VALUES ('" + pseudo + "'," + id_Icone + ");");
                s.add(new Doublet(id_Icone,1));
            }
            Manager.getManager().sendMultipleRequestUpdate(queryInsert,connection);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if (connection != null) connection.close();
            }catch (SQLException e){
                //ignorer
            }
        }
        return s;
    }

    public int doAchat(String pseudo, String mdp, String id_Offre, String money){



        String queryMdp = "SELECT mdpCompte " +
                "FROM CompteJoueur " +
                "WHERE Pseudo LIKE '"+pseudo+"';";
        ResultSet testMdp = Manager.getManager().sendRequestQuery(queryMdp,connection);
        try {
            testMdp.next();
            String mdpCompte = testMdp.getString("mdpCompte");
            if (!(mdpCompte.equals(mdp))){
                return RequestStatus.ACHAT_FAILED_MDP;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        String queryMoney = "SELECT "+money+
                " FROM CompteJoueur " +
                "WHERE Pseudo LIKE '"+pseudo+"';";
        String queryPrix = "SELECT prix"+money+", typeOffre" +
                " FROM Offre" +
                " WHERE id_Offre = "+id_Offre+" ;";


        ResultSet testMoney = Manager.getManager().sendRequestQuery(queryMoney,connection);

        try {
            testMoney.next();
            int moneyCompte = testMoney.getInt(money);
            ResultSet testPrix = Manager.getManager().sendRequestQuery(queryPrix,connection);
            testPrix.next();
            int prixOffre = testPrix.getInt("prix"+money);
            if (moneyCompte < prixOffre){
                return RequestStatus.ACHAT_FAILED_MONEY;
            }
            String typeOffre = testPrix.getString("typeOffre");
            switch (typeOffre){
                case    "Pack" : {
                    if (!(gererAchatPackRandomFullOpti(pseudo, id_Offre).isEmpty())) break;
                    else return -1;         //Erreur JeSaisPasTropQuoiMaisL'AjoutDesCartesAPlanté
                }
                case    "Map" : {
                    if (!(gererAjoutMap(pseudo,id_Offre).isEmpty())) break;
                    else return -1;
                }
                case    "Carton" : {
                    if (!(gererAjoutCartonCarte(pseudo,id_Offre).isEmpty())) break;
                    else return -1;
                }
                case    "Boost" : {
                    if (!(gererAjoutBoost(pseudo,id_Offre).isEmpty())) break;
                    else return -1;
                }
                case    "Icone" : {
                    if (!(gererAjoutIcone(pseudo, id_Offre).isEmpty())) break;
                    else return -1;
                }

            }
            String queryRendLArgentAuxAbonnes = "UPDATE CompteJoueur SET "+money+"="+(moneyCompte-prixOffre)+" WHERE Pseudo='"+pseudo+"';";
            Manager.getManager().sendRequestUpdate(queryRendLArgentAuxAbonnes,connection);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
        try {
            if(connection != null)
                connection.close();
        }catch (SQLException e){
                /* ignore */
        }
    }




        return RequestStatus.ACHAT_SUCCESS;
    }


}
