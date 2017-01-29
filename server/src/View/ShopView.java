package View;

import View.BoostView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vinspi on 27/01/17.
 */
public class ShopView {
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

    public void addSkinCartonView(SkinCartonView skinCartonView){
        listSkinCartonView.add(skinCartonView);
    }

}
