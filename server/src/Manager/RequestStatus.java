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
    public static final String URL_BDD = "jdbc:mysql://109.7.220.208:3306/DropDaBomb";
    public static final String BDD_USER = "vinspi";
    public static final String BDD_PASSWORD = "vinspi13";
    public static final int AUTH_FAILED = 6;
    public static final int AUTH_SUCCES = 7;
    public static final int SWAP_FAILED = 8;
    public static final int SWAP_SUCCES = 9;
}
