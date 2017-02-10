package com.example.work.dropdabomb;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by work on 02/02/17.
 */

public abstract class DDBView extends AppCompatActivity{

    protected DDBController controller;

    public DDBController getController() {
        return controller;
    }

    public void setController(DDBController controller) {
        this.controller = controller;
    }

    public abstract void handleUpdate();
}
