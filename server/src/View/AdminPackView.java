package View;

import Manager.Manager;
import Manager.PackManager;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;

/**
 * Created by deutsch on 09/03/17.
 */
public class AdminPackView {

    static PackManager packManager = new PackManager();

    private Pack currentPack = new Pack();
    private LootPack currentLootPack = new LootPack();
    private Ensemble currentEnsemble = new Ensemble();


    private ArrayList<MiniatureCarte> listCards = new ArrayList<>();
    private ArrayList<Ensemble> listEnsembles = new ArrayList<>();
    private ArrayList<LootPack> listLootPacks = new ArrayList<>();
    private ArrayList<Pack> listPacks = new ArrayList<>();



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

    public AdminPackView(){

        packManager.getEvthgAlrdCreated();
        packManager.getAllCards();

        setCurrentEnsemble(packManager.getCurrentEnsemble());
        setCurrentLootPack(packManager.getCurrentLootPack());
        setCurrentPack(packManager.getCurrentPack());

        setListCards(packManager.getListCards());
        setListEnsembles(packManager.getListEnsembles());
        setListLootPacks(packManager.getListLootPacks());
        setListPacks(packManager.getListPacks());

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
    public int chooseEnsemble(int idEns){
        for(Ensemble e : listEnsembles){
            if (e.getId() == idEns) {
                currentEnsemble = e;
                return 0;
            }
        }
        return 1;
    }
    //Ajout d'une carte dans le currentEnsemble
    public void addCarteToEnsemble(int id_Carte) {
        packManager.setCurrentEnsemble(getCurrentEnsemble());
        packManager.addCarteToEnsemble(id_Carte);
        setCurrentEnsemble(packManager.getCurrentEnsemble());

    }
    public void removeCarteFromEnsemble(int id_Carte){
        packManager.setCurrentEnsemble(getCurrentEnsemble());
        packManager.removeCarteFromEnsemble(id_Carte);
        setCurrentEnsemble(packManager.getCurrentEnsemble());
    }

    public void addEnsembleToLootPack(int id_Ensemble,float drop){
        packManager.setCurrentLootPack(getCurrentLootPack());
        packManager.addEnsembleToLootPack(id_Ensemble,drop);
        setCurrentLootPack(packManager.getCurrentLootPack());
    }
    public void removeEnsembleFromLootPack(int id_Ensemble){
        packManager.setCurrentLootPack(getCurrentLootPack());
        packManager.removeEnsembleFromLootPack(id_Ensemble);
        setCurrentLootPack(packManager.getCurrentLootPack());
    }
    public void modifyDropRate(int id_Ensemble, float drop){
        packManager.setCurrentLootPack(getCurrentLootPack());
        packManager.modifyDropRate(id_Ensemble,drop);
        setCurrentLootPack(packManager.getCurrentLootPack());
    }

    public void addLootPackToPack(int id_LootPack, int qte){
        packManager.setCurrentPack(getCurrentPack());
        packManager.addLootPackToPack(id_LootPack,qte);
        setCurrentPack(packManager.getCurrentPack());

    }
    public void removeLootPackFromPack(int id_LootPack){
        packManager.setCurrentPack(getCurrentPack());
        packManager.removeLootPackFromPack(id_LootPack);
        setCurrentPack(packManager.getCurrentPack());
    }

    public void switchMisEnVente(){
        packManager.setCurrentPack(getCurrentPack());
        packManager.switchMiseEnVente();
        setCurrentPack(packManager.getCurrentPack());


    }


    public void createEnsemble(String nom){
        //packManager.setListEnsembles(getListEnsembles());
        packManager.createEnsemble(nom);
        setListEnsembles(packManager.getListEnsembles());
        setCurrentEnsemble(packManager.getCurrentEnsemble());
    }
    public void createLootPack(String nom){
        packManager.createLootPack(nom);
        setListLootPacks(packManager.getListLootPacks());
        setCurrentLootPack(packManager.getCurrentLootPack());

    }

    public void createPack(String nom){

    }
}
