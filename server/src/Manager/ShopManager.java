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
    //Class destinée à gérer les opérations effectués dans et par la boutique (achat d'offres/récupération de données pour l'affichage).


    //Structures utiles pour la modélisation des packs et des différentes Offres.
    public class TripletEnsemble
    {
        public int id_Ensemble;
        public float dropRate;
        public ArrayList<Integer> Cartes = new ArrayList<Integer>();
        public ArrayList<String> img = new ArrayList<>();

        public TripletEnsemble(int id, float value, ArrayList<Integer> Cartes, ArrayList<String> img)
        {
            this.id_Ensemble = id;
            this.dropRate = value;
            this.Cartes = Cartes;
            this.img = img;
        }

    }
    public class Triplet{
        public int id;
        public int value;
        public String img;

        public Triplet(int id, int value, String img) {
            this.id = id;
            this.value = value;
            this.img = img;
        }

    }
    public class Doublet {
        public int id;
        public Object value;

        public Doublet(){
            this.id = 0;
            this.value = null;
        }

        public Doublet(int id, int value)
        {
            this.id = id;
            this.value = value;
        }
        public Doublet(int id, ArrayList<Doublet> value){

            this.id = id;
            this.value = value;
        }

        public Doublet(int id, String value) {
            this.id = id;
            this.value = value;
        }
    }

    public class ListLootPack{
        int id;
        int qte;
        ArrayList<TripletEnsemble> ensembles;

        public ListLootPack(int id, int qte, ArrayList<TripletEnsemble> Le){
            this.id = id;
            this.qte = qte;
            this.ensembles = Le;
        }


    }



    Connection connection = null;
    private List<PackView> listPackView = new LinkedList<>() ;
    private List<BoostView> listBoostView = new LinkedList<>();
    private List<SkinMapView> listSkinMapView = new LinkedList<>();
    private List<SkinCartonView> listSkinCartonView = new LinkedList<>();


    //Getter & Setter
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

    //______________________________________________________
    //Fonction permettant de récupérer les différentes offres selon leur type, et de les insérer dans la liste correspondante.

    public LinkedList<PackView> getAllPackOffers(){

        LinkedList<PackView> listItem = new LinkedList<>();



        String query = "SELECT id_Offre,nomPack,prixMonnaieIG, prixMonnaieIRL, id_Pack, descriptionPack, imageOffre, misEnVente FROM Offre" +
                " JOIN Pack USING(id_pack);";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                if(resultSet.getInt("misEnVente") == 1) listItem.add((new PackView(resultSet.getInt("id_Offre"),resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_Pack"),resultSet.getString("nomPack"),resultSet.getString("imageOffre"),resultSet.getString("descriptionPack"))));
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


        String query = "SELECT id_Offre,nomBoost,prixMonnaieIG, prixMonnaieIRL, id_Boost, imageOffre, descriptionBoost, misEnVente FROM Offre " +

                "JOIN OffreBoost USING (id_Offre)" +
                " JOIN Boost USING(id_Boost);";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                if(resultSet.getInt("misEnVente") == 1) listItem.add((new BoostView(resultSet.getInt("id_Offre"),resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_Boost"),resultSet.getString("nomBoost"),resultSet.getString("imageOffre"),resultSet.getString("descriptionBoost"))));
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

        String query =  "SELECT id_Offre,nomCarton,prixMonnaieIRL, prixMonnaieIG, id_SkinCartonCarte, imageOffre, descriptionCarton, misEnVente FROM Offre JOIN OffreCartonCarte USING (id_Offre)" +
                " JOIN SkinCartonCarte USING (id_SkinCartonCarte) WHERE id_SkinCartonCarte NOT IN (SELECT id_SkinCartonCarte FROM posséderSkinCartonCarte WHERE Pseudo LIKE '"+pseudo+"');";
        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                if(resultSet.getInt("misEnVente") == 1) listItem.add((new SkinCartonView(resultSet.getInt("id_Offre"),resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_SkinCartonCarte"),resultSet.getString("nomCarton"),resultSet.getString("imageOffre"),resultSet.getString("descriptionCarton"))));
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

        String query =  "SELECT id_Offre,nomMap,prixMonnaieIG, prixMonnaieIRL, id_SkinMap, imageOffre, descriptionMap, misEnVente FROM Offre JOIN OffreMap USING (id_Offre) JOIN SkinMap USING (id_SkinMap) WHERE id_SkinMap NOT IN (SELECT id_SkinMap FROM posséderSkinMap WHERE Pseudo LIKE '"+pseudo+"');";
        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                if(resultSet.getInt("misEnVente") == 1) listItem.add((new SkinMapView(resultSet.getInt("id_Offre"),resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_SkinMap"),resultSet.getString("nomMap"),resultSet.getString("imageOffre"),resultSet.getString("descriptionMap"))));
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
        String query = "SELECT id_Offre, nomIcone, prixMonnaieIRL, prixMonnaieIG, id_IconeJoueur, imageOffre, descriptionIcone, misEnVente FROM Offre JOIN OffreIcone USING (id_Offre)" +
                " JOIN IconeJoueur USING (id_IconeJoueur) WHERE id_IconeJoueur NOT IN (SELECT id_IconeJoueur FROM posséderIconeJoueur WHERE Pseudo LIKE '"+pseudo+"');";

        ResultSet resultSet = Manager.getManager().sendRequestQuery(query,connection);
        try {
            while (resultSet.next()){
                if(resultSet.getInt("misEnVente") == 1) listIcone.add((new IconeView(resultSet.getInt("id_Offre"),resultSet.getInt("prixMonnaieIRL"),resultSet.getInt("prixMonnaieIG"),resultSet.getInt("id_IconeJoueur"),resultSet.getString("nomIcone"),resultSet.getString("imageOffre"),resultSet.getString("descriptionIcone"))));
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

    //______________________________________________________
    //Fonction permettant d'effectuer l'achat d'une Offre selon son type. Appelées par doAchat.
    //Tout les fonctions de gestion d'achat retournent des ArrayList de Doublet (id, value) où id sont les id des achats et value la quantité. Le but étant de renvoyer cette AL pour faciliter un potentiel affichage.
    //L'achat d'un Pack fait exception puisqu'on renvoie en plus l'image des cartes obtenues lors de l'ouverture d'un pack.


    //Achète un Pack et l'ouvre : pioche des cartes et les ajoute au compte "pseudo". S'il possède déjà une carte X, alors la quantité de X dans son Deck0 est incrémenté par le nombre de cartes X obtenues à l'ouverture du Pack. Sinon, elles sont ajoutées à son Deck0.
    public ArrayList<Triplet> gererAchatPack(String pseudo, String id_Offre) {


        int x, i = -1, y = -1, a = -1, b = -1,tq = -1,oldtq = -1;
        float r;
        float c = -1, d = -1;
        float rand;


        String queryListLoot = "SELECT id_LootPack, qteCartePack, id_Ensemble, dropRatePack, id_Carte, imageCarte" +     //Récupère TOUT
                " FROM EnsembleCarte" +
                " JOIN LootPackEnsemble USING (id_Ensemble)" +
                " JOIN LootPackPack USING (id_LootPack)" +
                " JOIN Carte USING (id_Carte) "+
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
        ArrayList<Doublet> ListCartesObtenus = new ArrayList<>();       //Liste des (id_Carte,imageCarte). Arrangement de la liste en fin de fonction de façon à renvoyer une liste s.
        ArrayList<Doublet> ListCopiesCartes = new ArrayList<>();        //Trouver un autre moyen si besoin d'optimisation.
        ArrayList<Triplet> s = new ArrayList<>();                       //Résultat : liste de doublets (id_Carte,qteCarte).


        ArrayList<String> ListImgCartes = new ArrayList<>();
        ArrayList<Integer> ListCartes = new ArrayList<>();              //Liste de cartes composant un Ensemble.
        ArrayList<TripletEnsemble> tmpListSet = new ArrayList<>();
        ArrayList<ListLootPack> ListSet = new ArrayList<>();


        ResultSet setPack = Manager.getManager().sendRequestQuery(queryListLoot,connection);
        try{

            while (setPack.next()){
                if (i != setPack.getInt("id_LootPack")){
                    i = setPack.getInt("id_LootPack");
                    tq = setPack.getInt("qteCartePack");
                    a = setPack.getInt("id_Ensemble");
                    c = setPack.getFloat("dropRatePack");

                    ListLoot.add(new Doublet(i,tq));
                    if (!(tmpListSet.isEmpty())) {
                        tmpListSet.add(new TripletEnsemble(b,d,ListCartes,ListImgCartes));
                        ListSet.add(new ListLootPack(y,oldtq,tmpListSet));
                    }

                    tmpListSet = new ArrayList<>();
                    ListCartes = new ArrayList<>();
                    ListImgCartes = new ArrayList<>();
                    ListCartes.add(setPack.getInt("id_Carte"));
                    ListImgCartes.add(setPack.getString("imageCarte"));

                }
                else {
                    if (y != setPack.getInt("id_LootPack")) {
                        y = setPack.getInt("id_LootPack");
                        oldtq = setPack.getInt("qteCartePack");
                    }


                    if (a == setPack.getInt("id_Ensemble")){
                        if (b != setPack.getInt("id_Ensemble")){
                            b = setPack.getInt("id_Ensemble"); d = setPack.getFloat("dropRatePack");
                        }
                        ListCartes.add(setPack.getInt("id_Carte"));
                        ListImgCartes.add(setPack.getString("imageCarte"));
                    }
                    else {
                        a = setPack.getInt("id_Ensemble"); c = setPack.getFloat("dropRatePack") ;
                        tmpListSet.add(new TripletEnsemble(b,d,ListCartes,ListImgCartes));
                        ListCartes = new ArrayList<>();
                        ListImgCartes = new ArrayList<>();
                        ListCartes.add(setPack.getInt("id_Carte"));
                        ListImgCartes.add(setPack.getString("imageCarte"));
                    }

                }

            }
            tmpListSet.add(new TripletEnsemble(a,c,ListCartes,ListImgCartes));
            ListSet.add(new ListLootPack(i,tq,tmpListSet));



            ResultSet setCartesPossedees = Manager.getManager().sendRequestQuery(queryCartesPossedees,connection);
            while(setCartesPossedees.next()){
                ListCartesPossedees.add(setCartesPossedees.getInt("id_Carte"));
            }


            for(ListLootPack llp : ListSet){
              for(int q = 0; q < llp.qte;q++){
                    r = 0;
                    int id_Carte; String imageCarte;
                    int randCarte;
                    rand = (float) Math.random();
                    for(TripletEnsemble coupleEnsemble : llp.ensembles){
                        r += coupleEnsemble.dropRate;
                        if (rand < r) {
                            randCarte = (int) (Math.random() * coupleEnsemble.Cartes.size());
                            id_Carte = coupleEnsemble.Cartes.get(randCarte);
                            imageCarte = coupleEnsemble.img.get(randCarte);
                            ListCartesObtenus.add(new Doublet(id_Carte,imageCarte));
                            ListCopiesCartes.add(new Doublet(id_Carte,imageCarte));
                            break;
                        }
                    }
                }
            }

            int j;
            for(Doublet dab : ListCartesObtenus){
                x = 0;
                j = 0;
                while(j < ListCopiesCartes.size()){
                    if(dab.id == ListCopiesCartes.get(j).id && dab.value == ListCopiesCartes.get(j).value){
                        x++;
                        ListCopiesCartes.remove(j);
                        j--;
                    }
                    j++;
                }
                if(x > 0) s.add(new Triplet(dab.id,x,(String) dab.value));
            }

            //Insère dans la base de données.
            for(Triplet triplet : s){
                if (ListCartesPossedees.contains(triplet.id)) queryInsertions.add("UPDATE JoueurCarteDeck SET qteCarteDeck=qteCarteDeck+"+triplet.value+" WHERE (id_Deck LIKE '"+pseudo+"0' AND id_Carte ="+triplet.id+");");
                else queryInsertions.add("INSERT INTO JoueurCarteDeck (Pseudo,id_Deck,id_Carte,qteCarteDeck) VALUES ('"+pseudo+"','"+pseudo+"0',"+triplet.id+","+triplet.value+");");
            }
            Manager.getManager().sendMultipleRequestUpdate(queryInsertions,connection);


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
        return s;
    }


    //Achète les Boosts contenus dans l'Offre "id_Offre" et les active pour le compte "pseudo" : si le compte possède déjà une instance d'un de ces boosts active, il incrémente la durée de l'instance par la durée du boost acheté.
    //Sinon, ajoute un boost à la table activer.
    public ArrayList<Doublet> gererAchatBoost(String pseudo, String id_Offre){
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
                    s.add(new Doublet(id_Boost,Integer.parseInt(duree)));
                }
                else if (matcherHeures.find()) {
                    duree = matcherHeures.group(1);
                    if (boostActif.contains(id_Boost)) queryAjout.add("UPDATE activer SET HeuresFin=HeuresFin+SEC_TO_TIME("+duree+"*3600) WHERE Pseudo LIKE '"+pseudo+"' AND id_Boost="+id_Boost+";");
                    else queryAjout.add("INSERT INTO activer (Pseudo,id_Boost,HeuresFin) VALUES ('"+pseudo+"',"+id_Boost+",SEC_TO_TIME("+duree+"*3600));");

                    s.add(new Doublet(id_Boost,Integer.parseInt(duree)));
                }

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

        return s;
    }

    //Achète les SkinMap contenus dans l'Offre "id_Offre" et les ajoute au compte "pseudo" en les insérant dans la table posséderSkinMap.
    public ArrayList<Doublet> gererAchatMap(String pseudo, String id_Offre){


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

    //Achète les SkinCarton contenus dans l'Offre "id_Offre" et les ajoute au compte "pseudo" en les insérant dans la table posséderSkinCartonCarte.
    public ArrayList<Doublet> gererAchatCartonCarte(String pseudo, String id_Offre) {

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

    //Achète les Icones contenus dans l'Offre "id_Offre" et les ajoute au compte "pseudo" en les insérant dans la table posséderIconeJoueur.
    public  ArrayList<Doublet> gererAchatIcone(String pseudo, String id_Offre){
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



    //Fonction permettant l'achat de l'Offre "id_Offre" par le compte "pseudo" avec la monnaie "money" (money appartenant à {"monnaieIG","monnaieIRL"}.
    public Doublet doAchat(String pseudo, String id_Offre, String money){


        //Le pseudo est passé en paramètre via variable de session, donc l'existence du compte "pseudo" est vérifiée via la connection.

        //L'id sera 5 en cas de succès, -1 en cas d'échec.
        //La value sera une ArrayList de Doublet <id,quantité> (ou Triplet <id,quantité,image>, dans le cas de l'achat d'un Pack).

        Doublet s = new Doublet();




        String queryMoney = "SELECT "+money+
                " FROM CompteJoueur " +
                "WHERE Pseudo LIKE '"+pseudo+"';";
        String queryPrix = "SELECT prix"+money+", typeOffre" +
                " FROM Offre" +
                " WHERE id_Offre = "+id_Offre+" ;";

        ResultSet testMoney = Manager.getManager().sendRequestQuery(queryMoney,connection);

        try {

            //Vérifie si le compte "pseudo" possède assez de monnaie pour acheter l'offre, sinon renvoie une erreur ACHAT_FAILED_MONEY.
            testMoney.next();
            int moneyCompte = testMoney.getInt(money);
            ResultSet testPrix = Manager.getManager().sendRequestQuery(queryPrix,connection);
            testPrix.next();
            int prixOffre = testPrix.getInt("prix"+money);
            if (moneyCompte < prixOffre){
                return new Doublet(RequestStatus.ACHAT_FAILED_MONEY,new ArrayList<Doublet>());
            }

            //Vérifie le type de l'Offre pour un appel adapté :
            String typeOffre = testPrix.getString("typeOffre");
            switch (typeOffre){
                case    "Pack" : {
                    s.value = gererAchatPack(pseudo,id_Offre);
                    if (!(((ArrayList<Doublet>)s.value).isEmpty())){
                        s.id = RequestStatus.ACHAT_SUCCESS;
                        break;
                    }
                    else {
                        return new Doublet(-1,new ArrayList<Doublet>());
                    }
                }
                case    "Map" : {
                    s.value = gererAchatMap(pseudo,id_Offre);
                    if (!(((ArrayList<Doublet>)s.value).isEmpty())){
                        s.id = RequestStatus.ACHAT_SUCCESS;
                        break;
                    }
                    else return new Doublet(-1,new ArrayList<Doublet>());
                }
                case    "Carton" : {
                    s.value = gererAchatCartonCarte(pseudo,id_Offre);
                    if (!(((ArrayList<Doublet>)s.value).isEmpty())){
                        s.id = RequestStatus.ACHAT_SUCCESS;
                        break;
                    }
                    else return new Doublet(-1,new ArrayList<Doublet>());
                }
                case    "Boost" : {
                    s.value = gererAchatBoost(pseudo,id_Offre);
                    if (!(((ArrayList<Doublet>)s.value).isEmpty())){
                        s.id = RequestStatus.ACHAT_SUCCESS;
                        break;
                    }
                    else return new Doublet(-1,new ArrayList<Doublet>());
                }
                case    "Icone" : {
                    s.value = gererAchatIcone(pseudo,id_Offre);
                    if (!(((ArrayList<Doublet>)s.value).isEmpty())){
                        s.id = RequestStatus.ACHAT_SUCCESS;
                        break;
                    }
                    else return new Doublet(-1,new ArrayList<Doublet>());
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

        return s;
    }


}
