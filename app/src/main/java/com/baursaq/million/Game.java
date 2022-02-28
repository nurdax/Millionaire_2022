package com.baursaq.million;

import static android.content.ContentValues.TAG;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

public class Game extends AppCompatActivity {
    GameBack gameBack;
    Button a, b, c, d;
    ImageButton witdraw, eluelu, phone,zaldankomek;
    TextView question, level;
    TextView[] btns = new TextView[15];

    private AdView adView;
    InterstitialAd fbInterstitialAd;
    static InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();
        knopka();

        //int_ad
        AudienceNetworkAds.initialize(this);
        interstitialAd = new InterstitialAd(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");




        //banner
        adView = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();


        ToggleButton menu = findViewById(R.id.menu);
        CardView cardd = findViewById(R.id.carrd);
        menu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (menu.isChecked()) {
                    menu.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.backoff)));
                    cardd.setVisibility(View.VISIBLE);
                } else {
                    menu.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.back)));
                    cardd.setVisibility(View.INVISIBLE);

                }
            }
        });

        try {
            gameBack = new GameBack(this, a, b, c, d, question);
            gameBack.setQotaqbas(btns);
            gameBack.setEluElu(eluelu);
            gameBack.setTelefon(phone);
            gameBack.setZaldanKomek(zaldankomek);
            gameBack.setShygu(witdraw);
            gameBack.setSoruadet(15);
            gameBack.oyunOyna();
            gameBack.setLevel(level);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    static InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
        @Override
        public void onInterstitialDisplayed(Ad ad) {
            Log.e(TAG, "Interstitial ad displayed.");

        }

        @Override
        public void onInterstitialDismissed(Ad ad) {
            // Interstitial dismissed callback
            Log.e(TAG, "Interstitial ad dismissed.");
        }

        @Override
        public void onError(Ad ad, AdError adError) {
            // Ad error callback
            Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
        }

        @Override
        public void onAdLoaded(Ad ad) {
            // Show the ad
            interstitialAd.show();
        }

        @Override
        public void onAdClicked(Ad ad) {
            // Ad clicked callback
            Log.d(TAG, "Interstitial ad clicked!");
        }

        @Override
        public void onLoggingImpression(Ad ad) {
            // Ad impression logged callback
            Log.d(TAG, "Interstitial ad impression logged!");
        }
    };



    void knopka() {
        a = (Button) findViewById(R.id.a);
        b = (Button) findViewById(R.id.b);
        c = (Button) findViewById(R.id.c);
        d = (Button) findViewById(R.id.d);
        zaldankomek = (ImageButton) findViewById(R.id.seyircisor);
        phone = (ImageButton) findViewById(R.id.telefonla);
        eluelu = (ImageButton) findViewById(R.id.yariyariya);
        question = (TextView) findViewById(R.id.sorucontainer);
        btns[0] = (TextView) findViewById(R.id.para1);
        btns[1] = (TextView) findViewById(R.id.para2);
        btns[2] = (TextView) findViewById(R.id.para3);
        btns[3] = (TextView) findViewById(R.id.para4);
        btns[4] = (TextView) findViewById(R.id.para5);
        btns[5] = (TextView) findViewById(R.id.para6);
        btns[6] = (TextView) findViewById(R.id.para7);
        btns[7] = (TextView) findViewById(R.id.para8);
        btns[8] = (TextView) findViewById(R.id.para9);
        btns[9] = (TextView) findViewById(R.id.para10);
        btns[10] = (TextView) findViewById(R.id.para11);
        btns[11] = (TextView) findViewById(R.id.para12);
        btns[12] = (TextView) findViewById(R.id.para13);
        btns[13] = (TextView) findViewById(R.id.para14);
        btns[14] = (TextView) findViewById(R.id.para15);
        witdraw = (ImageButton) findViewById(R.id.aqsha);

        level = findViewById(R.id.level);

    }

    @Override
    public void onBackPressed()  {
        Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
        gameBack.oynnanShygu();
    }



    @Override
    protected void onPause() {
        super.onPause();
        if (gameBack.mediaPlayer.isPlaying())
            gameBack.mediaPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (gameBack.mediaPlayer.isPlaying())
            gameBack.mediaPlayer.stop();
        try {
            gameBack.sureakisi.cancel();
        } catch (Exception e) {
            Log.e("Timer", e.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adView != null) {
            adView.destroy();
        }
        if (gameBack.mediaPlayer.isPlaying())
            gameBack.mediaPlayer.stop();
        try {
            gameBack.sureakisi.cancel();
        } catch (Exception e) {
            Log.e("Timer", e.toString());
        }

    }



}
