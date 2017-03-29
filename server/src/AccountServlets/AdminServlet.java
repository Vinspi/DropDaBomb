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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        HttpSession session = req.getSession();

        Connection connection = null;
        PrintWriter out = resp.getWriter();

        resp.setCharacterEncoding("UTF-8");
        //resp.setContentType("application/json");



        //Récupérer le pseudo.
        String pseudo = (String) session.getAttribute("pseudo");
        /*
        int request = Integer.parseInt(req.getParameter("idRequest"));
        System.out.println("request = "+request);

        switch(request){
            case 0:
                int id_ensemble = Integer.parseInt(req.getParameter("id_ensemble"));
                System.out.println("là");
                adv.chooseEnsemble(id_ensemble);
                String json_CE = new Gson().toJson(adv.getCurrentEnsemble());
                System.out.println(json_CE);
                out.print(json_CE);
                break;
            case 1:
                int id_lootpack = Integer.parseInt(req.getParameter("id_lootpack"));
                adv.chooseLootPack(id_lootpack);
                String json_CLP = new Gson().toJson(adv.getCurrentLootPack());
                System.out.println(json_CLP);
                out.print(json_CLP);
                break;
            case 2:
                int id_pack = Integer.parseInt(req.getParameter("id_pack"));
                adv.choosePack(id_pack);
                String json_CP = new Gson().toJson(adv.getCurrentPack());
                System.out.println(json_CP);
                out.print(json_CP);
                break;

            case 7:
                int id_Carte = Integer.parseInt(req.getParameter("id_Carte"));
                adv.addCarteToEnsemble(id_Carte);
                session.setAttribute("current_ensemble",adv.getCurrentEnsemble());
                break;
            default:
                break;
        }
    */


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        HttpSession session = req.getSession();

        Connection connection = null;
        PrintWriter out = resp.getWriter();

        resp.setCharacterEncoding("UTF-8");
        //resp.setContentType("application/json");



        //Récupérer le pseudo.
        String pseudo = (String) session.getAttribute("pseudo");

        int request = Integer.parseInt(req.getParameter("idRequest"));
        System.out.println("idRequest = "+request);

        int id, j, qte; String json_CNE; float drop;

        switch(request){
            case -1:        //Reset de l'adv (refresh, etc)
                adv = new AdminPackView();
                break;
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
                System.out.println("Pack ici ?");
                adv.choosePack(id);
                String json_CP = new Gson().toJson(adv.getCurrentPack());
                System.out.println(json_CP);
                out.print(json_CP);
                break;
            case 3:
                String nomNwEns = req.getParameter("nomEnsemble");
                adv.createEnsemble(nomNwEns);
                String json_NE = new Gson().toJson(adv.getListEnsembles());
                System.out.println(json_NE);
                out.print(json_NE);
                break;

            case 4:
                String nomNwLP = req.getParameter("nomLootPack");
                adv.createLootPack(nomNwLP);
                String json_NLP = new Gson().toJson(adv.getListLootPacks());
                System.out.println(json_NLP);
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
                System.out.println(json_NP);
                out.print(json_NP);
                break;




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
                System.out.println(json_LC);
                out.print(json_LC);
                break;
            case 11:
                System.out.println(req.getParameter("id_carte"));
                id = Integer.parseInt(req.getParameter("id_carte"));
                adv.addCarteToEnsemble(id);
                json_CNE = new Gson().toJson(adv.getCurrentEnsemble());
                System.out.println(json_CNE);
                out.print(json_CNE);
                break;
            case 12:
                id = Integer.parseInt(req.getParameter("id_carte"));
                adv.removeCarteFromEnsemble(id);
                json_CNE = new Gson().toJson(adv.getCurrentEnsemble());
                out.print(json_CNE);
                break;





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
            case 14:
                id = Integer.parseInt(req.getParameter("id_Ensemble"));
                System.out.println(req.getParameter("dropRate"));
                drop = Float.parseFloat(req.getParameter("dropRate"));

                adv.addEnsembleToLootPack(id,drop);
                json_CNE = new Gson().toJson(adv.getCurrentLootPack());
                System.out.println(json_CNE);
                out.print(json_CNE);
                break;
            case 15:
                id = Integer.parseInt(req.getParameter("id_Ensemble"));
                adv.removeEnsembleFromLootPack(id);
                json_CNE = new Gson().toJson(adv.getCurrentLootPack());
                out.print(json_CNE);
                break;
            case 16:
                id = Integer.parseInt(req.getParameter("id_Ensemble"));
                System.out.println(req.getParameter("dropRate"));
                drop = Float.parseFloat(req.getParameter("dropRate"));
                adv.modifyDropRate(id,drop);
                json_CNE = new Gson().toJson(adv.getCurrentLootPack());
                out.print(json_CNE);
                break;





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
            case 18:
                id = Integer.parseInt(req.getParameter("id_LootPack"));
                System.out.println(req.getParameter("qte"));
                qte = Integer.parseInt(req.getParameter("qte"));

                adv.addLootPackToPack(id,qte);
                json_CNE = new Gson().toJson(adv.getCurrentPack());
                System.out.println(json_CNE);
                out.print(json_CNE);
                break;
            case 19:
                id = Integer.parseInt(req.getParameter("id_LootPack"));
                adv.removeLootPackFromPack(id);
                json_CNE = new Gson().toJson(adv.getCurrentPack());
                out.print(json_CNE);
                break;
            case 20:
                id = Integer.parseInt(req.getParameter("id_LootPack"));
                qte = Integer.parseInt(req.getParameter("qte"));
                adv.modifyQte(id,qte);
                json_CNE = new Gson().toJson(adv.getCurrentPack());
                out.print(json_CNE);
                break;
            case 21:
                adv.switchMisEnVente();
                json_CNE = new Gson().toJson(adv.getCurrentPack());
                out.print(json_CNE);
                break;




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
