package com.example.work.dropdabomb;

/**
 * Created by work on 02/02/17.
 */

public abstract class DDBModel {

    protected DDBView view;

    public void setView(DDBView v){this.view = v;}

    public DDBView getView(){return this.view;}

    public abstract void handleHttpAnswer(String output);

}
