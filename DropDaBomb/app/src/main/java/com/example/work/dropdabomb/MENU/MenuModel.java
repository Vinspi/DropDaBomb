package com.example.work.dropdabomb.MENU;

import android.util.Log;

import com.example.work.dropdabomb.Const;
import com.example.work.dropdabomb.DDBModel;

import java.lang.reflect.Field;

/**
 * Created by work on 02/02/17.
 */

public class MenuModel extends DDBModel {

    public String create_url_creation_compte(String login, String paswd, String email, String dob){
        String URL = Const.URL_CREATE_ACCOUNT + "?pseudo="+login;
        URL += "&password="+paswd;
        URL += "&email="+email;
        URL += "&dob="+dob;

        Log.d("STATE", "URL SENDING = " + URL + "\n");

        return URL;
    }

    public String create_url_connexion_compte(String login, String paswd){
        String URL = Const.URL_CONNEXION + "?pseudo="+login;
        URL += "&password="+paswd;

        Log.d("STATE", "URL SENDING = " + URL + "\n");

        return URL;
    }


    public void model_submit_connexion_form(String login, String paswd){
       new MenuHttpRequest(this).execute(create_url_connexion_compte(login, paswd));
    }

    public void model_submit_creation_form(String login, String paswd, String email, String dob){
       new MenuHttpRequest(this).execute(create_url_creation_compte(login, paswd, email, dob));
    }

    @Override
    public void handleHttpAnswer(String output) {
        System.out.println("[REPONSE SEVEUR] = " + output);
        ((MenuView) getView()).handleChangeBehavior(output);
    }
}
