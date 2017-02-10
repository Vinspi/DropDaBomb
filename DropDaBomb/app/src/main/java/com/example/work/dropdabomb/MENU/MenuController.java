package com.example.work.dropdabomb.MENU;

import android.util.Log;

import com.example.work.dropdabomb.Const;
import com.example.work.dropdabomb.DDBController;
import com.example.work.dropdabomb.DDBModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by work on 02/02/17.
 */

public class MenuController extends DDBController {


    public boolean validate(final String hex) {

        Pattern pattern = Pattern.compile(Const.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();

    }

    public void control_submit_connexion_form(String login, String paswd){
        Log.d("STATE", "Compte : " + login + " " + paswd + "\n\n");

        ((MenuModel) getModel()).model_submit_connexion_form(login, paswd);
    }

    public void control_submit_creation_form(String login, String paswd, String paswdconfirm, String email, int dobD, int dobM, int dobY){
            String dob = dobD + "/" + dobM + "/" + dobY;

        if(!paswd.equals(paswdconfirm)) {
            ((MenuModel) getModel()).handleHttpAnswer(Const.CREATE_ACCOUNT_FAILED_MDP + "");
        }
        else if(!validate(email)){
            ((MenuModel) getModel()).handleHttpAnswer(Const.CREATE_ACCOUNT_FAILED_EMAIL + "");
        }
        else {
            ((MenuModel) getModel()).model_submit_creation_form(login, paswd, email, dob);
        }
    }

}
