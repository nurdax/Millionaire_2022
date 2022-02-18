package com.baursaq.million;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;



public class Menu extends AppCompatActivity {
    MediaPlayer mp;
    GameBack gameBack;
    ToggleButton toggleButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        toggleButton = (ToggleButton) findViewById(R.id.toggle);
        getSupportActionBar().hide();
        mp = MediaPlayer.create(this, R.raw.anaekran);
        sharedPreferences = getSharedPreferences("ses", Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = sharedPreferences.edit();

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (toggleButton.isChecked()) {
                    toggleButton.setBackground(getResources().getDrawable(R.drawable.soundon));
                    edit.putString("ses", "ok");
                    mp.start();
                    edit.apply();
                } else {
                    toggleButton.setBackground(getResources().getDrawable(R.drawable.soundoff));
                    edit.putString("ses", "no");
                    mp.stop();
                    edit.commit();

                }
            }
        });


        if (sharedPreferences.getString("ses", "").equals("ok")) {
            toggleButton.setChecked(true);
            mp.start();
        } else
            toggleButton.setChecked(false);
    }



    public void yeniOyun(View view) {
        mp.stop();
        startActivity(new Intent(Menu.this, Game.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mp.isPlaying()) {
            mp.stop();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()) {
            mp.stop();
        }
    }

}