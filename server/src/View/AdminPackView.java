package View;

import Manager.PackManager;

import java.util.ArrayList;

/**
 * Created by deutsch on 09/03/17.
 */
public class AdminPackView {

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
        PackManager packManager = new PackManager();
        packManager.getEvthgAlrdCreated();

        this.currentEnsemble = packManager.getCurrentEnsemble();
        this.currentLootPack = packManager.getCurrentLootPack();
        this.currentPack = packManager.getCurrentPack();

        this.listCards = packManager.getListCards();
        this.listEnsembles = packManager.getListEnsembles();
        this.listLootPacks = packManager.getListLootPacks();
        this.listPacks = packManager.getListPacks();
    }

}
