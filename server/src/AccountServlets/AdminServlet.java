package AccountServlets;

import View.*;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by deutsch on 10/03/17.
 */
public class AdminServlet extends HttpServlet {

    AdminPackView adv = new AdminPackView();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        HttpSession session = req.getSession();

        Connection connection = null;
        PrintWriter out = resp.getWriter();

        resp.setCharacterEncoding("UTF-8");
        //resp.setContentType("application/json");



        //Récupérer la variable de session "pseudo".
        String pseudo = (String) session.getAttribute("pseudo");

        int request = Integer.parseInt(req.getParameter("idRequest"));
        System.out.println("idRequest = "+request);

        int id, j, qte; String json_CNE; float drop;

        switch(request){

            case -1:        //Reset de l'AdminView (refresh de page, ...)
                adv = new AdminPackView();
                break;

            //____________________________________________________//
            //Changement d'Ensemble/LootPack/Pack courant :
            case 0:
                id = Integer.parseInt(req.getParameter("id_ensemble"));
                System.out.println("là");
                adv.chooseEnsemble(id);
                String json_CE = new Gson().toJson(adv.getCurrentEnsemble());
                System.out.println(json_CE);
                out.print(json_CE);
                break;
            case 1:
                id = Integer.parseInt(req.getParameter("id_lootpack"));
                adv.chooseLootPack(id);
                String json_CLP = new Gson().toJson(adv.getCurrentLootPack());
                System.out.println(json_CLP);
                out.print(json_CLP);
                break;
            case 2:
                id = Integer.parseInt(req.getParameter("id_pack"));
                adv.choosePack(id);
                String json_CP = new Gson().toJson(adv.getCurrentPack());
                System.out.println(json_CP);
                out.print(json_CP);
                break;

            //____________________________________________________//
            //Création d'un Ensemble/LootPack/Pack :

            case 3:
                String nomNwEns = req.getParameter("nomEnsemble");
                adv.createEnsemble(nomNwEns);
                String json_NE = new Gson().toJson(adv.getListEnsembles());
                out.print(json_NE);
                break;
            case 4:
                String nomNwLP = req.getParameter("nomLootPack");
                adv.createLootPack(nomNwLP);
                String json_NLP = new Gson().toJson(adv.getListLootPacks());
                out.print(json_NLP);
                break;

            case 5:
                String nomNwP = req.getParameter("nomPack");
                String description = req.getParameter("description");
                String image = req.getParameter("image");
                int prixIG = Integer.parseInt(req.getParameter("prixIG"));
                int prixIRL = Integer.parseInt(req.getParameter("prixIRL"));
                adv.createPack(nomNwP,description,image,prixIG,prixIRL);
                String json_NP = new Gson().toJson(adv.getListPacks());
                out.print(json_NP);
                break;

            //____________________________________________________//
            //Récupération des cartes ajoutables à l'ensemble courant

            case 10:
                ArrayList<MiniatureCarte> listCartesAjoutables = new ArrayList<>();

                for(MiniatureCarte m : adv.getListCards()){
                    j = 0;
                    for(int i = 0; i < adv.getCurrentEnsemble().getCartes().size(); i++){
                      if (m.getId() == adv.getCurrentEnsemble().getCartes().get(i).getId()) j = 1;
                    }
                    if(j == 0) listCartesAjoutables.add(m);
                }

                String json_LC = new Gson().toJson(listCartesAjoutables);
                out.print(json_LC);
                break;

            //Ajout d'une carte à l'ensemble courant.
            case 11:
                System.out.println(req.getParameter("id_carte"));
                id = Integer.parseInt(req.getParameter("id_carte"));
                adv.addCarteToEnsemble(id);
                json_CNE = new Gson().toJson(adv.getCurrentEnsemble());
                out.print(json_CNE);
                break;

            //Suppression d'une carte de l'ensemble courant.
            case 12:
                id = Integer.parseInt(req.getParameter("id_carte"));
                adv.removeCarteFromEnsemble(id);
                json_CNE = new Gson().toJson(adv.getCurrentEnsemble());
                out.print(json_CNE);
                break;


            //____________________________________________________//
            //Récupération des Ensembles ajoutables au LootPack courant

            case 13:
                ArrayList<Ensemble> listEnsembleAjoutable = new ArrayList<>();

                for(Ensemble e : adv.getListEnsembles()){
                    j = 0;
                    for(int i = 0; i < adv.getCurrentLootPack().getEnsembles().size();i++){
                        if (e.getId() == adv.getCurrentLootPack().getEnsembles().get(i).getId()) j = 1;
                    }
                    if (j == 0) listEnsembleAjoutable.add(e);
                }
                String json_LE = new Gson().toJson(listEnsembleAjoutable);
                out.print(json_LE);
                break;

            //Ajout d'un Ensemble au LootPack courant
            case 14:
                id = Integer.parseInt(req.getParameter("id_Ensemble"));
                drop = Float.parseFloat(req.getParameter("dropRate"));
                adv.addEnsembleToLootPack(id,drop);
                json_CNE = new Gson().toJson(adv.getCurrentLootPack());
                out.print(json_CNE);
                break;

            //Suppression d'un ensemble du LootPack courant.
            case 15:
                id = Integer.parseInt(req.getParameter("id_Ensemble"));
                adv.removeEnsembleFromLootPack(id);
                json_CNE = new Gson().toJson(adv.getCurrentLootPack());
                out.print(json_CNE);
                break;

            //Modification du dropRate d'un ensemble du LootPack.
            case 16:
                id = Integer.parseInt(req.getParameter("id_Ensemble"));
                drop = Float.parseFloat(req.getParameter("dropRate"));
                adv.modifyDropRate(id,drop);
                json_CNE = new Gson().toJson(adv.getCurrentLootPack());
                out.print(json_CNE);
                break;

            //____________________________________________________//


            //Récupération des LootPack ajoutables au Pack courant.
            case 17:
                ArrayList<LootPack> listLootPacksAjoutable = new ArrayList<>();

                for(LootPack lp : adv.getListLootPacks()){
                    j = 0;
                    for(int i = 0; i < adv.getCurrentPack().getLootPacks().size();i++){
                        if (lp.getId() == adv.getCurrentPack().getLootPacks().get(i).getId()) j = 1;
                    }
                    if (j == 0) listLootPacksAjoutable.add(lp);
                }
                String json_LLP = new Gson().toJson(listLootPacksAjoutable);
                out.print(json_LLP);
                break;

            //Ajout d'un LootPack au Pack courant.
            case 18:
                id = Integer.parseInt(req.getParameter("id_LootPack"));
                qte = Integer.parseInt(req.getParameter("qte"));
                adv.addLootPackToPack(id,qte);
                json_CNE = new Gson().toJson(adv.getCurrentPack());
                out.print(json_CNE);
                break;

            //Suppression d'un LootPack du Pack courant.
            case 19:
                id = Integer.parseInt(req.getParameter("id_LootPack"));
                adv.removeLootPackFromPack(id);
                json_CNE = new Gson().toJson(adv.getCurrentPack());
                out.print(json_CNE);
                break;

            //Modification de la qte d'un LootPack dans le Pack courant.
            case 20:
                id = Integer.parseInt(req.getParameter("id_LootPack"));
                qte = Integer.parseInt(req.getParameter("qte"));
                adv.modifyQte(id,qte);
                json_CNE = new Gson().toJson(adv.getCurrentPack());
                out.print(json_CNE);
                break;
            //Changement de l'attribut miseVente (-> met en vente le pack s'il ne l'était pas, l'enlève de la vente sinon)
            case 21:
                adv.switchMisEnVente();
                json_CNE = new Gson().toJson(adv.getCurrentPack());
                out.print(json_CNE);
                break;



            //Récupération des listes des Ensembles, LootPacks et Packs pour l'affichage.
            case 42:
                String listEns = new Gson().toJson(adv.getListEnsembles());
                out.print(listEns);
                break;
            case 43:
                String listLP = new Gson().toJson(adv.getListLootPacks());
                out.print(listLP);
                break;
            case 44:
                String listP = new Gson().toJson(adv.getListPacks());
                out.print(listP);
                break;

        }



    }
}
