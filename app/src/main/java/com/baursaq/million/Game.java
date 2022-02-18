package com.baursaq.million;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Game extends AppCompatActivity {
    GameBack gameBack;
    Button a, b, c, d;
    ImageButton witdraw;
    ImageButton eluelu;
    ImageButton phone;
    ImageButton advid;
    ImageButton zaldankomek;
    TextView question;
    TextView level;
    TextView[] btns = new TextView[15];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();
        knopka();

        ToggleButton menu = findViewById(R.id.menu);
        ScrollView scroll = findViewById(R.id.scroll);
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
            gameBack.setParabutton(btns);
            gameBack.setYariyariya(eluelu);
            gameBack.setTelefonla(phone);
            gameBack.setAdvid(advid);
            gameBack.setSeyircisor(zaldankomek);
            gameBack.setCekilbuttonu(witdraw);
            gameBack.setSoruadet(15);
            gameBack.oyunOyna();
            gameBack.setLevel(level);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    void knopka() {
        a = (Button) findViewById(R.id.a);
        b = (Button) findViewById(R.id.b);
        c = (Button) findViewById(R.id.c);
        d = (Button) findViewById(R.id.d);
        zaldankomek = (ImageButton) findViewById(R.id.seyircisor);
        phone = (ImageButton) findViewById(R.id.telefonla);
        advid = (ImageButton) findViewById(R.id.advid);
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
        //super.onBackPressed();
        gameBack.oyundaCekil();


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
        if (gameBack.mediaPlayer.isPlaying())
            gameBack.mediaPlayer.stop();
        try {
            gameBack.sureakisi.cancel();
        } catch (Exception e) {
            Log.e("Timer", e.toString());
        }

    }

}
