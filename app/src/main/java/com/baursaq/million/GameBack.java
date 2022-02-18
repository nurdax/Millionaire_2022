package com.baursaq.million;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameBack {

    int timerbe = 100;
    int timeraf = 100;
    static Activity activity;
    ArrayList<Questions> onaysuraq = new ArrayList<>();
    ArrayList<Questions> ortasuraq = new ArrayList<>();
    ArrayList<Questions> qyunsuraq = new ArrayList<>();
    Button a;
    Button b;
    Button c;
    Button d;

    public static int gecensure = 0;
    public static Timer sureakisi = new Timer();
    ImageButton cekilbuttonu;
    static String kazanilantutar;
    TextView sorucontainer;
    Questions soru;
    ImageButton yariyariya;
    ImageButton telefonla;
    ImageButton advid;
    ImageButton seyircisor;
    static String seyircijokercevap;
    TextView[] parabutton;
    int index;
    int soruadet = 0;
    int kacincisorudayim = 0;
    boolean ciftsiksiljoker = true;
    boolean seyircijoker = true;
    boolean ciftcevapjoker = true;
    boolean telefonjoker = true;
    public static MediaPlayer mediaPlayer;
    static String cekilmeparasi = "0";
    static int cikisdurumu = 0;
    private final String TAG = "MainActivity";
    TextView level;

    public ImageButton getCekilbuttonu() {
        return cekilbuttonu;
    }

    public void setCekilbuttonu(ImageButton cekilbuttonu) {
        this.cekilbuttonu = cekilbuttonu;
    }

    public ImageButton getSeyircisor() {
        return seyircisor;
    }

    public void setSeyircisor(ImageButton seyircisor) {
        this.seyircisor = seyircisor;
    }

    public ImageButton getTelefonla() {
        return telefonla;
    }

    public void setTelefonla(ImageButton telefonla) {
        this.telefonla = telefonla;
    }

    public ImageButton getAdvid() {
        return advid;
    }
    public void setAdvid(ImageButton advid) {
        this.advid = advid;
    }

    static String telefoncevabi;

    public ImageButton getYariyariya() {
        return yariyariya;
    }

    public void setYariyariya(ImageButton yariyariya) {
        this.yariyariya = yariyariya;
    }

    public TextView[] getParabutton() {
        return parabutton;
    }

    public void setParabutton(TextView[] parabutton) {
        this.parabutton = parabutton;
    }

    public int getSoruadet() {
        return soruadet;
    }

    public void setSoruadet(int soruadet) {
        this.soruadet = soruadet;
    }

    public GameBack(final Activity activity, Button a, Button b, Button c, Button d, TextView sorucontainer) {
        this.activity = activity;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.sorucontainer = sorucontainer;
        qyunsuraq = zorsorugetir();
        onaysuraq = kolaysorugetir();
        ortasuraq = ortasorugetir();
        mediaPlayer = MediaPlayer.create(activity, R.raw.oyunbaslangic);
        SharedPreferences sharedPreferences=activity.getSharedPreferences("ses", Context.MODE_PRIVATE);

       // level.setText("" + parabutton);
        if(sharedPreferences.getString("ses","").equals("ok")) {
            mediaPlayer.start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(activity, R.raw.soruekranigenel);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    mediaPlayer.setVolume(0.3f, 0.3f);
                }
            }, 25);
        }
//

    }

    public void oyunOyna() {


        getCekilbuttonu().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oyundaCekil();
            }

        });


        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                gecensure++;
            }
        };
        sureakisi = new Timer();
        sureakisi.scheduleAtFixedRate(t, 1000, 1000);
        sorusor(kacincisorudayim, new cevap() {
            @Override
            public void cevapDogru() {

            }

            @Override
            public void cevapYanlis() {
                oyunbitir();
            }
        });


    }

    public ArrayList<Questions> kolaysorugetir() {
        ArrayList<Questions> kolaysorular = new ArrayList<>();
        XmlPullParserFactory pullParserFactory;

        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream in_s = activity.getApplicationContext().getAssets().open("easyquestions.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);
            kolaysorular = soruparse(parser);
            for (Questions questions : kolaysorular) {


            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(kolaysorular); //soruları karma
        return kolaysorular;
    }

    public ArrayList<Questions> ortasorugetir() {
        ArrayList<Questions> ortasorular = new ArrayList<>();
        XmlPullParserFactory pullParserFactory;

        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream in_s = activity.getApplicationContext().getAssets().open("normalquestions.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);
            ortasorular = soruparse(parser);
            for (Questions questions : ortasorular) {


            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.shuffle(ortasorular);
        return ortasorular;
    }

    public ArrayList<Questions> zorsorugetir() {
        ArrayList<Questions> zorsorular = new ArrayList<>();
        XmlPullParserFactory pullParserFactory;

        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream in_s = activity.getApplicationContext().getAssets().open("hardquestions.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);
            zorsorular = soruparse(parser);
            for (Questions questions : zorsorular) {


            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(zorsorular);
        return zorsorular;
    }

    void sorusor(int sorusirasi, final cevap cevap) {
        index = sorusirasi;
        boolean sonuc = false;
        if (index < 5) soru = onaysuraq.get(index);
        else if (index > 3 && index < 10) soru = ortasuraq.get(index - 5);
        else soru = qyunsuraq.get(index - 10);
        Map<String, String> cevaplar = new HashMap<>();
        cevaplar = soru.getCevaplar();
        ciftejokerHakki(getYariyariya(), soru.getDogrucevap());
        adVideo(getAdvid(), soru.getDogrucevap());
        telefonJokerString(soru.getDogrucevap(), getTelefonla());
        seyirciyesorJoker(getSeyircisor(), soru.getDogrucevap());
        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        a.setText("A:  " + cevaplar.get("1"));
        b.setText("B:  " + cevaplar.get("2"));
        c.setText("C:  " + cevaplar.get("3"));
        d.setText("D:  " + cevaplar.get("4"));
        a.setEnabled(true);
        b.setEnabled(true);
        c.setEnabled(true);
        d.setEnabled(true);
        kazanilantutar = setKazanilanPara(index);
        a.setBackground(activity.getDrawable(R.drawable.sorunormalcevap));
        a.setTextColor(Color.WHITE);
        b.setBackground(activity.getDrawable(R.drawable.sorunormalcevap));
        b.setTextColor(Color.WHITE);
        c.setBackground(activity.getDrawable(R.drawable.sorunormalcevap));
        c.setTextColor(Color.WHITE);
        d.setBackground(activity.getDrawable(R.drawable.sorunormalcevap));
        d.setTextColor(Color.WHITE);
        sorucontainer.setText(soru.getSorulansoru());
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SesCalIkiSaniye(R.raw.sikisaretleme);
                DogruButtonHerzamanYesil(soru.getDogrucevap());
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                d.setEnabled(false);
                a.setBackground(activity.getDrawable(R.drawable.soruaktifsari));
                a.setTextColor(Color.BLACK);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (a.getTag().equals(soru.getDogrucevap())) {
                            SesCalIkiSaniye(R.raw.dogrucevap);
                            a.setBackground(activity.getDrawable(R.drawable.sorudogrucevap));
                            a.setTextColor(Color.BLACK);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cevap.cevapDogru();
                                    if (index < (getSoruadet() - 1)) {
                                        index++;
                                        sorusor(index, new cevap() {
                                            @Override
                                            public void cevapDogru() {

                                            }

                                            @Override
                                            public void cevapYanlis() {
                                                oyunbitir();
                                            }
                                        });
                                    } else {
                                        oyunbitirfinal();
                                    }
                                }
                            }, timeraf);
                        } else {
                            SesCal(R.raw.yanliscevap);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cevap.cevapYanlis();

                                }
                            }, timeraf);
                            a.setBackground(activity.getDrawable(R.drawable.yanliscevap));
                            a.setTextColor(Color.BLACK);
                        }
                    }
                }, timerbe);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SesCalIkiSaniye(R.raw.sikisaretleme);

                DogruButtonHerzamanYesil(soru.getDogrucevap());
                b.setEnabled(false);
                a.setEnabled(false);
                c.setEnabled(false);
                d.setEnabled(false);
                b.setBackground(activity.getDrawable(R.drawable.soruaktifsari));
                b.setTextColor(Color.BLACK);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (b.getTag().equals(soru.getDogrucevap())) {
                            SesCalIkiSaniye(R.raw.dogrucevap);
                            b.setBackground(activity.getDrawable(R.drawable.sorudogrucevap));
                            b.setTextColor(Color.WHITE);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cevap.cevapDogru();
                                    if (index < (getSoruadet() - 1)) {
                                        index++;
                                        sorusor(index, new cevap() {
                                            @Override
                                            public void cevapDogru() {

                                            }

                                            @Override
                                            public void cevapYanlis() {
                                                oyunbitir();
                                            }
                                        });
                                    } else {
                                        oyunbitirfinal();
                                    }
                                }
                            }, timeraf);
                        } else {
                            SesCal(R.raw.yanliscevap);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cevap.cevapYanlis();
                                }
                            }, timeraf);
                            b.setBackground(activity.getDrawable(R.drawable.yanliscevap));
                            b.setTextColor(Color.WHITE);
                        }
                    }
                }, timerbe);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SesCalIkiSaniye(R.raw.sikisaretleme);

                DogruButtonHerzamanYesil(soru.getDogrucevap());
                c.setEnabled(false);
                b.setEnabled(false);
                a.setEnabled(false);
                d.setEnabled(false);
                c.setBackground(activity.getDrawable(R.drawable.soruaktifsari));
                c.setTextColor(Color.BLACK);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (c.getTag().equals(soru.getDogrucevap())) {
                            SesCalIkiSaniye(R.raw.dogrucevap);
                            c.setBackground(activity.getDrawable(R.drawable.sorudogrucevap));
                            c.setTextColor(Color.WHITE);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cevap.cevapDogru();
                                    if (index < (getSoruadet() - 1)) {
                                        index++;
                                        sorusor(index, new cevap() {
                                            @Override
                                            public void cevapDogru() {

                                            }

                                            @Override
                                            public void cevapYanlis() {
                                                oyunbitir();
                                            }
                                        });
                                    } else {
                                        oyunbitirfinal();
                                    }
                                }
                            }, timeraf);
                        } else {
                            SesCal(R.raw.yanliscevap);


                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cevap.cevapYanlis();
                                }
                            }, timeraf);
                            c.setBackground(activity.getDrawable(R.drawable.yanliscevap));
                            c.setTextColor(Color.WHITE);
                        }
                    }
                }, timerbe);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SesCalIkiSaniye(R.raw.sikisaretleme);
                DogruButtonHerzamanYesil(soru.getDogrucevap());
                d.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                a.setEnabled(false);
                d.setBackground(activity.getDrawable(R.drawable.soruaktifsari));
                d.setTextColor(Color.BLACK);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (d.getTag().equals(soru.getDogrucevap())) {
                            SesCalIkiSaniye(R.raw.dogrucevap);
                            d.setBackground(activity.getDrawable(R.drawable.sorudogrucevap));
                            d.setTextColor(Color.WHITE);
                            final Handler handler = new Handler();
                            cevap.cevapDogru();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if (index < (getSoruadet() - 1)) {
                                        index++;
                                        sorusor(index, new cevap() {
                                            @Override
                                            public void cevapDogru() {

                                            }

                                            @Override
                                            public void cevapYanlis() {
                                                oyunbitir();
                                            }
                                        });
                                    } else {
                                        oyunbitirfinal();
                                    }
                                }
                            }, timeraf);
                        } else {
                            SesCal(R.raw.yanliscevap);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cevap.cevapYanlis();
                                }
                            }, timeraf);
                            d.setBackground(activity.getDrawable(R.drawable.yanliscevap));
                            d.setTextColor(Color.WHITE);
                        }
                    }
                }, timerbe);
            }
        });


    }

    private void adVideo(final ImageButton advid, String dogrucevap) {

        advid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ciftsiksiljoker) {
                        ciftcevapjoker = false;
                        advid.setEnabled(false);
                        advid.setBackgroundTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.backoff)));;
                        ArrayList<Button> buttonlar = new ArrayList<Button>();
                        buttonlar.add(a);
                        buttonlar.add(b);
                        buttonlar.add(c);
                        buttonlar.add(d);
                        ArrayList<Button> silinebilirbuttonlar = new ArrayList<Button>();
                        for (int i = 0; i < buttonlar.size(); i++) {
                            if (!buttonlar.get(i).getTag().equals(dogrucevap)) {
                                silinebilirbuttonlar.add(buttonlar.get(i));
                            }
                        }
                        Collections.shuffle(silinebilirbuttonlar);
                        silinebilirbuttonlar.get(0).setVisibility(View.INVISIBLE);;
                        silinebilirbuttonlar.get(1).setVisibility(View.INVISIBLE);
                        silinebilirbuttonlar.get(2).setVisibility(View.INVISIBLE);
                    }
                }
            });
        }



    private ArrayList<Questions> soruparse(XmlPullParser parser) throws XmlPullParserException, IOException {
        String text = "";
        ArrayList<Questions> sorular = new ArrayList<>();
        int eventType = parser.getEventType();
        Questions soru = null;
        Map<String, String> cevaplar = new HashMap<>();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagname = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagname.equalsIgnoreCase("suraqtar")) {
                        soru = new Questions();
                    }
                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (tagname.equalsIgnoreCase("suraqtar")) {
                        soru.setCevaplar(cevaplar);
                        cevaplar = new HashMap<>();
                        sorular.add(soru);
                    } else if (tagname.equalsIgnoreCase("suraq")) {
                        soru.setSorulansoru(text);
                    } else if (tagname.equalsIgnoreCase("a")) {
                        cevaplar.put("1", text);
                    } else if (tagname.equalsIgnoreCase("b")) {
                        cevaplar.put("2", text);
                    } else if (tagname.equalsIgnoreCase("c")) {
                        cevaplar.put("3", text);
                    } else if (tagname.equalsIgnoreCase("d")) {
                        cevaplar.put("4", text);
                    } else if (tagname.equalsIgnoreCase("zhauap")) {
                        soru.setDogrucevap(text);
                    }
                    break;

                default:
                    break;
            }
            eventType = parser.next();
        }
        return sorular;
    }

    public void setLevel(TextView level) {
        this.level = level;
        
    }

    public interface cevap {
        void cevapDogru();

        void cevapYanlis();
    }

    public String setKazanilanPara(int QQ) {
        String kazanilantutarsoru = "0/0";
        for (int i = 0; i < getParabutton().length; i++) {



            //20-150
            if (i == QQ) {
                //getParabutton()[i].setBackground(activity.getDrawable(R.drawable.resactiv));
                getParabutton()[i].setTextColor(ColorStateList.valueOf(activity.getResources().getColor(R.color.currentq)));
            } else if ((i == 4 || i == 9 || i == 14) && i != QQ) {
                //getParabutton()[i].setTextColor(ColorStateList.valueOf(activity.getResources().getColor(R.color.colorAccent)));
            } else {
                getParabutton()[i].setTextColor(ColorStateList.valueOf(activity.getResources().getColor(R.color.white)));
            }

        }
        switch (QQ) {
            case 0:
                kazanilantutarsoru = "0";
                cekilmeparasi = "0";
                break;
            case 1:
                kazanilantutarsoru = "0";
                cekilmeparasi = "0";
                level.setText(activity.getResources().getString(R.string.p2500));
                break;
            case 2:
                kazanilantutarsoru = "0";
                cekilmeparasi = "0";
                level.setText(activity.getResources().getString(R.string.p5000));
                break;
            case 3:
                kazanilantutarsoru = "0";
                cekilmeparasi = "0";
                level.setText(activity.getResources().getString(R.string.p10000));
                break;
            case 4:
                cekilmeparasi = "0";
                kazanilantutarsoru = "20 000";
                level.setText(activity.getResources().getString(R.string.p20000));
                break;
            case 5:
                cekilmeparasi = "20 000";
                kazanilantutarsoru = "40 000";
                level.setText(activity.getResources().getString(R.string.p40000));
                break;
            case 6:
                cekilmeparasi = "20 000";
                kazanilantutarsoru = "60 000";
                level.setText(activity.getResources().getString(R.string.p60000));
                break;
            case 7:
                cekilmeparasi = "20 000";
                kazanilantutarsoru = "90 000";
                level.setText(activity.getResources().getString(R.string.p90000));
                break;
            case 8:
                cekilmeparasi = "20 000";
                kazanilantutarsoru = "150 000";
                level.setText(activity.getResources().getString(R.string.p150000));
                break;
            case 9:
                cekilmeparasi = "20 000";
                kazanilantutarsoru = "300 000";
                level.setText(activity.getResources().getString(R.string.p300000));
                break;
            case 10:
                cekilmeparasi = "20 000";
                kazanilantutarsoru = "600 000";
                level.setText(activity.getResources().getString(R.string.p600000));
                break;
            case 11:
                cekilmeparasi = "300 000";
                kazanilantutarsoru = "1 200 000";
                level.setText(activity.getResources().getString(R.string.p1200000));
                break;
            case 12:
                cekilmeparasi = "300 000";
                kazanilantutarsoru = "2 500 000";
                level.setText(activity.getResources().getString(R.string.p2500000));
                break;
            case 13:
                cekilmeparasi = "300 000";
                kazanilantutarsoru = "5 000 000";
                level.setText(activity.getResources().getString(R.string.p5000000));
                break;
            case 14:
                cekilmeparasi = "300 000";
                kazanilantutarsoru = "10 000 000";
                level.setText(activity.getResources().getString(R.string.p10000000));
                break;


        }

        return kazanilantutarsoru;
    }


    public static void oyunbitir() {
        FragmentManager fm = activity.getFragmentManager();
//        mediaPlayer.stop();
        OyunSonu dialogFragment = new OyunSonu();
        try{dialogFragment.show(fm, "Sample Fragment");
        }
        catch (Exception e){

        }

    }


    public static void oyunbitirfinal() {
        FragmentManager fm = activity.getFragmentManager();
//        mediaPlayer.stop();
        OyunSonuFinal dialogFragment = new OyunSonuFinal();
        dialogFragment.show(fm, "Sample Fragment");
    }


    public void DogruButtonHerzamanYesil(final String dogruccevap) {
        final Button dogrucevapbuttonu;

        if (a.getTag().equals(dogruccevap)) {
            dogrucevapbuttonu = a;
        } else if (b.getTag().equals(dogruccevap)) {
            dogrucevapbuttonu = b;
        } else if (c.getTag().equals(dogruccevap)) {
            dogrucevapbuttonu = c;
        } else {
            dogrucevapbuttonu = d;
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dogrucevapbuttonu.setBackground(activity.getDrawable(R.drawable.sorudogrucevap));
            }
        }, timerbe);

    }

    public void ciftejokerHakki(final ImageButton cifte, final String dogrucevap) {

        cifte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ciftsiksiljoker) {
                    ciftcevapjoker = false;
                    cifte.setEnabled(false);
                    cifte.setBackgroundTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.backoff)));;
                    ArrayList<Button> buttonlar = new ArrayList<Button>();
                    buttonlar.add(a);
                    buttonlar.add(b);
                    buttonlar.add(c);
                    buttonlar.add(d);
                    ArrayList<Button> silinebilirbuttonlar = new ArrayList<Button>();
                    for (int i = 0; i < buttonlar.size(); i++) {
                        if (!buttonlar.get(i).getTag().equals(dogrucevap)) {
                            silinebilirbuttonlar.add(buttonlar.get(i));
                        }
                    }
                    Collections.shuffle(silinebilirbuttonlar);
                    silinebilirbuttonlar.get(0).setVisibility(View.INVISIBLE);;
                    silinebilirbuttonlar.get(1).setVisibility(View.INVISIBLE);
                }
            }
        });

    }


    public static class SeyirciJoker extends DialogFragment {
        ImageView ia, ib, ic, id;
        ImageView dogrusik;
        Button dismissss;
        int total = 10;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.zaldankomek, container, false);
            gorselata(rootView);

            ArrayList<ImageView> buttonlar = new ArrayList<ImageView>();
            buttonlar.add(ia);
            buttonlar.add(ib);
            buttonlar.add(ic);
            buttonlar.add(id);
            ArrayList<ImageView> xd = new ArrayList<ImageView>();
            for (int i = 0; i < buttonlar.size(); i++) {
                if (buttonlar.get(i).getTag().equals(seyircijokercevap)) {
                    dogrusik = buttonlar.get(i);
                    Random r = new Random();
                    int Low = 4;
                    int High = 8;
                    int Result = r.nextInt(High - Low) + Low;
                    total = total - Result;
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                            dogrusik.getLayoutParams();
                    params.weight = (float) Result;
                    dogrusik.setLayoutParams(params);
                } else {
                    xd.add(buttonlar.get(i));
                }
            }
            int[] gg = seyirciFonk(total, 3);
            for (int i = 0; i < xd.size(); i++) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                        xd.get(i).getLayoutParams();
                params.weight = (float) gg[i];
                xd.get(i).setLayoutParams(params);
            }

            dismissss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDialog().dismiss();
                }
            });

            return rootView;
        }

        void gorselata(View view) {
            dismissss = (Button) view.findViewById(R.id.dismissss);
            ia = (ImageView) view.findViewById(R.id.ia);
            ib = (ImageView) view.findViewById(R.id.ib);
            ic = (ImageView) view.findViewById(R.id.ic);
            id = (ImageView) view.findViewById(R.id.id);
        }

    }

    public static class TelefonJoker extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.phone, container, false);
            Button tamam = (Button) rootView.findViewById(R.id.tamam);
            TextView telefoncevap = (TextView) rootView.findViewById(R.id.telefoncevap);
            telefoncevap.setText(telefoncevabi);
            tamam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDialog().dismiss();
                }
            });
            return rootView;
        }
    }

    public void oyundaCekil() {
        FragmentManager fm = activity.getFragmentManager();
        OyundanCekil dialogFragment = new OyundanCekil();
        dialogFragment.show(fm, "Sample Fragment");
    }



    public static class OyundanCekil extends DialogFragment {

        int dialogWidth = 300;
        int dialogHeight = 300;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.takemoney, container, false);
            getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
            Button cekilok = (Button) rootView.findViewById(R.id.cekilyes);
            Button cekilno = (Button) rootView.findViewById(R.id.cekilno);
            cekilok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cikisdurumu = 1;
                    if (mediaPlayer.isPlaying())
                        mediaPlayer.stop();
                    try {
                        sureakisi.cancel();
                    } catch (Exception e) {
                    }
                    oyunbitir();
                }
            });

            cekilno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            return rootView;
        }
    }

    public void telefonJokerString(final String cevap, final ImageButton jokerbutton) {
        jokerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String konusma = "....";
                String appendsik;
                if (cevap.equals("1")) appendsik = "A";
                else if (cevap.equals("2")) appendsik = "B";
                else if (cevap.equals("3")) appendsik = "C";
                else appendsik = "D";
                Random random = new Random();
                int i = random.nextInt(5);
                switch (i) {

                    case 0:
                        konusma = "Сәлем, " + appendsik + " жауабын таңдап көр";
                        break;

                    case 1:
                        konusma = "Түшш, дәл қазір интернеттен іздедім, жауабы " + appendsik;
                        break;

                    case 2:
                        konusma = "Сенімді емеспін, бірақ жауабы " + appendsik + " сияқты.";
                        break;

                    case 3:
                        konusma = "Менің ойымша, жауабы " + appendsik + " сияқты";
                        break;

                    case 4:
                        konusma = "Сәлем досым, дұрыс жауабы " + appendsik + ", сәттілік!";
                        break;
                }
                telefoncevabi = konusma;
                FragmentManager fm = activity.getFragmentManager();
                TelefonJoker dialogFragment = new TelefonJoker();
                dialogFragment.show(fm, "Joker");
                jokerbutton.setEnabled(false);
                jokerbutton.setBackgroundTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.backoff)));
            }
        });

    }

    public static int[] seyirciFonk(int number, int number_of_parts) {
        Random r = new Random();
        HashSet<Integer> uniqueInts = new HashSet<Integer>();
        uniqueInts.add(0);
        uniqueInts.add(number);
        int array_size = number_of_parts + 1;
        while (uniqueInts.size() < array_size) {
            uniqueInts.add(1 + r.nextInt(number - 1));
        }
        Integer[] dividers = uniqueInts.toArray(new Integer[array_size]);
        Arrays.sort(dividers);
        int[] results = new int[number_of_parts];
        for (int i = 1, j = 0; i < dividers.length; ++i, ++j) {
            results[j] = dividers[i] - dividers[j];
        }
        return results;
    }

    public void seyirciyesorJoker(final ImageButton seyircisor, String cevap) {
        seyircijokercevap = cevap;
        seyircisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = activity.getFragmentManager();
                SeyirciJoker dialogFragment = new SeyirciJoker();
                dialogFragment.show(fm, "Joker");
                seyircisor.setEnabled(false);
                seyircisor.setBackgroundTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.backoff)));
            }
        });
    }

    public static class OyunSonu extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.end_of_game, container, false);
            TextView para = (TextView) rootView.findViewById(R.id.kazandiginpara);
            String paraver = "";
            if (cikisdurumu == 1)
                paraver = kazanilantutar;
            else if (cikisdurumu == 0)
                paraver = cekilmeparasi;
            para.setText(paraver + " " + getActivity().getResources().getString(R.string.currency));

            mediaPlayer.stop();
            sureakisi.cancel();
            Win win = new Win(paraver, gecensure);
            TextView aciklama = (TextView) rootView.findViewById(R.id.aciklama);
            aciklama.setText(getActivity().getResources().getString(R.string.game_over));
            this.setCancelable(false);
            /*SQLiteSkor sqLiteSkor = new SQLiteSkor(activity);
            sqLiteSkor.addScore(win);*/
            Button anamenu = (Button) rootView.findViewById(R.id.anasayfabutton);
            anamenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                }
            });

            return rootView;
        }
    }


    public static class OyunSonuFinal extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.end_of_game, container, false);
            TextView para = (TextView) rootView.findViewById(R.id.kazandiginpara);
            String paraver = "10 000 000";
            para.setText(paraver + " " + getActivity().getResources().getString(R.string.currency));
            mediaPlayer.stop();
            sureakisi.cancel();
            Win win = new Win(paraver, gecensure);
            TextView aciklama = (TextView) rootView.findViewById(R.id.aciklama);
            aciklama.setText(getActivity().getResources().getString(R.string.game_over_final));
            this.setCancelable(false);
            /*SQLiteSkor sqLiteSkor = new SQLiteSkor(activity);
            sqLiteSkor.addScore(win);*/
            Button anamenu = (Button) rootView.findViewById(R.id.anasayfabutton);
            anamenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                }
            });

            return rootView;
        }
    }

    public void SesCal(int i) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("ses", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("ses", "").equals("ok")) {
            final MediaPlayer mp = MediaPlayer.create(activity, i);
            mp.start();
        }
    }

    public void SesCalIkiSaniye(int i) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("ses", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("ses", "").equals("ok")) {
            final MediaPlayer mp = MediaPlayer.create(activity, i);
            mp.start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mp.stop();
                }
            }, 2200);
        }
    }
}
