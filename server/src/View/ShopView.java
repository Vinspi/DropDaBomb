package View;

import View.BoostView;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import sun.awt.image.ImageWatched;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import Manager.*;

/**
 * Created by vinspi on 27/01/17.
 */
public class ShopView {

    private ShopManager shopManager = new ShopManager();

    private List<PackView> listPackView = new LinkedList<>();
    private List<BoostView> listBoostView = new LinkedList<>();
    private List<SkinMapView> listSkinMapView = new LinkedList<>();
    private List<SkinCartonView> listSkinCartonView = new LinkedList<>();
    private List<IconeView> listIconeView = new LinkedList<>();

    public ShopView(String pseudo){
        getAllOffers(pseudo);
    }

    public void addPackView(PackView packView){
        listPackView.add(packView);
    }

    public void addBoostView(BoostView boostView){
        listBoostView.add(boostView);
    }

    public void addSkinMapView(SkinMapView skinMapView){
        listSkinMapView.add(skinMapView);
    }

    public void addSkinCartonView(SkinCartonView skinCartonView){
        listSkinCartonView.add(skinCartonView);
    }

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
    public List<IconeView> getListIconeView() {
        return listIconeView;
    }
    public void setListIconeView(List<IconeView> listIconeView) {
        this.listIconeView = listIconeView;
    }

    public void getAllOffers(String pseudo){
        setListPackView(shopManager.getAllPackOffers());
        setListBoostView(shopManager.getAllBoostOffers());
        setListSkinCartonView(shopManager.getAllCartonOffers(pseudo));
        setListSkinMapView(shopManager.getAllMapOffers(pseudo));
        setListIconeView(shopManager.getAllIconeOffers(pseudo));
    }


}
