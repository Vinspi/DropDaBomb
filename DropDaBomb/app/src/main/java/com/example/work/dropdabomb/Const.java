package com.example.work.dropdabomb;

/**
 * Created by work on 03/02/17.
 */

public class Const {


    // REQUEST STATUS

    public static final int CREATE_ACCOUNT_FAILED_PSEUDO = 0;
    public static final int CREATE_ACCOUNT_FAILED_MDP = 10;
    public static final int CREATE_ACCOUNT_FAILED_EMAIL = 1;
    public static final int CREATE_ACCOUNT_SUCCES = 2;
    public static final int ACHAT_FAILED_MDP = 3;
    public static final int ACHAT_FAILED_MONEY = 4;
    public static final int ACHAT_SUCCESS = 5;
    public static final int AUTH_FAILED = 6;
    public static final int AUTH_SUCCES = 7;
    public static final int SWAP_FAILED = 8;
    public static final int SWAP_SUCCES = 9;


    // LAYOUTS

    public static final int LAYOUT_MENU = 1;
    public static final int LAYOUT_MENU_CONNEXION = 2;
    public static final int LAYOUT_MENU_INSCRIPTION = 3;


    // URLs

    public final static String URL = "http://192.168.43.7:8080/";
    public final static String URL_CREATE_ACCOUNT = URL + "CreateAccountAndroid";
    public final static String URL_CONNEXION = URL + "Auth";
    public final static String URL_INVENTORY = URL + "Inventory";
    public final static String URL_SHOP = URL + "Shop";
    public final static String URL_SHOPACHAT = URL + "ShopAchat";
    public final static String URL_DECKSWAP = URL + "DeckSwap";


    public final static String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

}
