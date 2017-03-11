package View;

import Manager.PackManager;

import java.util.ArrayList;

/**
 * Created by deutsch on 09/03/17.
 */
public class AdminPackView {

    PackManager packManager = new PackManager();

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

        this.currentEnsemble = packManager.getCurrentEnsemble();
        System.out.println(this.currentEnsemble);
        this.currentLootPack = packManager.getCurrentLootPack();
        this.currentPack = packManager.getCurrentPack();

        this.listCards = packManager.getListCards();
        this.listEnsembles = packManager.getListEnsembles();
        this.listLootPacks = packManager.getListLootPacks();
        this.listPacks = packManager.getListPacks();

    }

    public void choosePack(int idPack){
        for(Pack p : listPacks){
            if (p.getId() == idPack) {
                currentPack = p;
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
    //Ajout d'une carte dans le currentEnsemble
    public void addCarteToEnsemble(int id_Carte){
        for(MiniatureCarte m : listCards){
            if(m.getId() == id_Carte) getCurrentEnsemble().getCartes().add(m);
            break;
        }
    }
}
