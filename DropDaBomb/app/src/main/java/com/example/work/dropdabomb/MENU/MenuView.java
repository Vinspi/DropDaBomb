package com.example.work.dropdabomb.MENU;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.work.dropdabomb.Const;
import com.example.work.dropdabomb.DDBView;
import com.example.work.dropdabomb.R;

import java.util.Date;

import javax.xml.datatype.Duration;


public class MenuView extends DDBView {



    private int SCREEN = Const.LAYOUT_MENU;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private ViewFlipper viewFlipper;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };



    //USEFUL METHODS


    public void flipToViewID(int id){
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(id)));
    }

    public static void onPostExecute(String output){
        Log.d("STATE", output);
    }

    public void makeToast(String text, int g, int d){
        Toast t = new Toast(getApplicationContext());
        Toast toast = Toast.makeText(getApplicationContext(), text, d);
        toast.setGravity(g, 0, 0);
        toast.show();
    }

    // traiter l'affichage après reception de réponse serveur
    public void handleChangeBehavior(String output){
        switch (Integer.parseInt(output)){
            case Const.AUTH_FAILED:
                makeToast("Erreur authentification", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;
            case Const.AUTH_SUCCES:
                makeToast("CONNECTÉ", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;

            case Const.CREATE_ACCOUNT_FAILED_EMAIL:
                findViewById(R.id.form_creation_email).setBackgroundColor(Color.RED);
                makeToast("ERREUR EMAIL", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;

            case Const.CREATE_ACCOUNT_FAILED_PSEUDO:
                findViewById(R.id.form_creation_login).setBackgroundColor(Color.RED);
                makeToast("ERREUR PSEUDO", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;

            case Const.CREATE_ACCOUNT_FAILED_MDP:
                findViewById(R.id.form_creation_confirm_paswd).setBackgroundColor(Color.RED);
                findViewById(R.id.form_creation_paswd).setBackgroundColor(Color.RED);
                makeToast("MOTS DE PASSE DIFFERENTS", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;

            case Const.CREATE_ACCOUNT_SUCCES:
                makeToast("CREATION RÉUSSIE ; CONNEXION", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;

            case Const.ACHAT_FAILED_MDP:
                makeToast("Erreur : dentifiez vous", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;
            case Const.ACHAT_FAILED_MONEY:
                makeToast("Erreur : Pas assez d'argent", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;
            case Const.ACHAT_SUCCESS:
                makeToast("Achat confirmé", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;

            case Const.SWAP_FAILED:
                makeToast("Erreur : deck inchangé", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;
            case Const.SWAP_SUCCES:
                makeToast("Deck changé", Gravity.BOTTOM, Toast.LENGTH_LONG);
                break;
        }
    }



    public void submit_creation_form(){

            String login = ((EditText) findViewById(R.id.form_creation_login)).getText().toString();
            String paswd = ((EditText) findViewById(R.id.form_creation_paswd)).getText().toString();
            String paswdconfirm = ((EditText) findViewById(R.id.form_creation_confirm_paswd)).getText().toString();;
            String email = ((EditText) findViewById(R.id.form_creation_email)).getText().toString();;

            int month_birth = ((DatePicker) findViewById(R.id.form_creation_age)).getMonth();
            int year_birth = ((DatePicker) findViewById(R.id.form_creation_age)).getYear();
            int date_birth = ((DatePicker) findViewById(R.id.form_creation_age)).getDayOfMonth();

            ((MenuController) controller).control_submit_creation_form(login, paswd, paswdconfirm, email, date_birth, month_birth, year_birth);
    }

    public void submit_connexion_form(){
            String login = ((EditText) findViewById(R.id.form_connexion_login)).getText().toString();
            String paswd = ((EditText) findViewById(R.id.form_connexion_paswd)).getText().toString();

            ((MenuController) controller).control_submit_connexion_form(login, paswd);
    }





    //OVERRIDED METHODS


    @Override
    public void onBackPressed() {
        if(SCREEN == Const.LAYOUT_MENU_CONNEXION || SCREEN == Const.LAYOUT_MENU_INSCRIPTION) {
            SCREEN = Const.LAYOUT_MENU;
            Log.d("STATE", "BACK ON MENU CO/INSC PRESSED");
            flipToViewID(R.id.layout_menu);
        }
    }


    private final View.OnClickListener btn_menu_listenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_login:
                    SCREEN = Const.LAYOUT_MENU_CONNEXION;
                    flipToViewID(R.id.layout_menu_signin);
                    break;
                case R.id.btn_signin:
                    SCREEN = Const.LAYOUT_MENU_INSCRIPTION;
                    flipToViewID(Const.LAYOUT_MENU_INSCRIPTION);
                    break;
                case R.id.form_connexion_submit:
                    submit_connexion_form();
                    break;
                case R.id.form_creation_submit:
                    submit_creation_form();
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MenuModel menuModel = new MenuModel();
        MenuController menuController = new MenuController();

        setController(menuController);
        menuController.setModel(menuModel);
        menuModel.setView(this);

        setContentView(R.layout.layout_menu_flip);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content);
        mContentView = findViewById(R.id.fullscreen_content);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);

        final Animation inAnim = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        final Animation outAnim = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        viewFlipper.setInAnimation(inAnim);
        viewFlipper.setOutAnimation(outAnim);

        findViewById(R.id.btn_login).setOnClickListener(btn_menu_listenner);
        findViewById(R.id.btn_signin).setOnClickListener(btn_menu_listenner);
        findViewById(R.id.form_connexion_submit).setOnClickListener(btn_menu_listenner);
        findViewById(R.id.form_creation_submit).setOnClickListener(btn_menu_listenner);

        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) { delayedHide(3000);  }
                    }
                });
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void handleUpdate() {

    }
}
