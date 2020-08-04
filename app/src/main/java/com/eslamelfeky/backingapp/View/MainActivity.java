package com.eslamelfeky.backingapp.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.eslamelfeky.backingapp.R;

public class MainActivity extends AppCompatActivity implements Task,SendMessage {

    @Nullable
    @BindView(R.id.ly_sw_600_fragment)
    LinearLayout linearLayout;
    @BindView(R.id.recycler_view_fragment)
    FrameLayout frameLayout;
    boolean tabletSize ;
    SendMessage sendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fullScreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tabletSize = getResources().getBoolean(R.bool.isTablet);
      /* if(this.checkScreenWidth()<600){
           linearLayout.setVisibility(View.GONE);
       }*/

        if (getSupportFragmentManager().findFragmentByTag("HomeFragmentTag") == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recycler_view_fragment, new HomeFragment(), "HomeFragmentTag").addToBackStack(null)
                    .commit();
        }



    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void hideActionBar() {
        getSupportActionBar().hide();
    }

    @Override
    public int checkScreenWidth() {

        /*   DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Toast.makeText(this,""+displayMetrics.ydpi,Toast.LENGTH_LONG);*/
        //int height = displayMetrics.heightPixels;
        if(tabletSize){
            return 601;
        }else{
            return 0;
        }
    }

    @Override
    public void setFrameLayoutViability(boolean Viability) {
        if (Viability){
            frameLayout.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
        else {
            frameLayout.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackClick(boolean enable) {
        OnBackPressedCallback callback = new OnBackPressedCallback(enable /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

              if(frameLayout.getVisibility()==View.VISIBLE){
                onDestroy();
              }
              else{
                  setFrameLayoutViability(false);
                  linearLayout.setVisibility(View.GONE);
              }
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public void fullScreen() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE &&
                getSupportFragmentManager().findFragmentByTag("StepsDetailsFragment") == null) {
            if(checkScreenWidth()<600) {
              requestWindowFeature(Window.FEATURE_NO_TITLE);
               getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                        , WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void SendMessage(String message) {
        sendMessage=(SendMessage)getSupportFragmentManager().findFragmentByTag("StepsDetailsFragment");
        if(sendMessage!=null){
            sendMessage.SendMessage(message);
        }
    }
}



