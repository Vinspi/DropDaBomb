package Manager;

/**
 * Created by vinspi on 26/01/17.
 */


public class RequestStatus {
    public static final int CREATE_ACCOUNT_FAILED_PSEUDO = 0;
    public static final int CREATE_ACCOUNT_FAILED_EMAIL = 1;
    public static final int CREATE_ACCOUNT_SUCCES = 2;
    public static final int ACHAT_FAILED_MDP = 3;
    public static final int ACHAT_FAILED_MONEY = 4;
    public static final int ACHAT_SUCCESS = 5;
    public static final String URL_BDD = "jdbc:mysql://192.168.43.138:3306/DropDaBomb";
    public static final String BDD_USER = "test";
    public static final String BDD_PASSWORD = "test";

    public static final int AUTH_FAILED = 6;
    public static final int AUTH_SUCCES = 7;
    public static final int SWAP_FAILED = 8;
    public static final int SWAP_SUCCESS = 9;
    public static final int UPDATE_MDP_SUCCESS = 10;
    public static final int UPDATE_EMAIL_SUCCESS = 11;
    public static final int UPDATE_MAP_SUCCESS = 12;
    public static final int UPDATE_CARTON_SUCCESS = 13;
    public static final int UPDATE_MDP_FAILED = 14;
    public static final int UPDATE_ICON_SUCCESS = 15;
    public static final int UPDATE_ICON_FAILED = 16;
    public static final int UPDATE_EMAIL_FAILED = 17;
    public static final int UPDATE_MAP_FAILED = 18;
    public static final int UPDATE_CARTON_FAILED = 19;
    public static final int UPDATE_ICON = 20;
    public static final int UPDATE_MDP = 21;
    public static final int UPDATE_EMAIL = 22;
    public static final int UPDATE_CARTON = 23;
    public static final int ACHAT_MONNAIE_SUCCESS = 24;
    public static final int ACHAT_MONNAIE_FAILED = 25;
    public static final int ACHAT_MONNAIE = 26;
    public static final int UPDATE_MAP = 27;

}
