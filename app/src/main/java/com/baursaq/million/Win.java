package com.baursaq.million;

import android.util.Log;

public class Win {
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Win(long id, String para, int sure, int puan) {

        this.id = id;
        this.para = para;
        this.sure = sure;
        this.puan = puan;
    }

    String para;
    int sure;
    int puan=0;

    void puanHesapla(String para,int sure){
        int kismi=0;
        switch (para) {
            case "0":
                kismi=0;
                break;
            case "1 000":
                kismi=10000;
                break;
            case "2 500":
                kismi=20000;
                break;
            case "5 000":
                kismi=30000;
                break;
            case "10 000":
                kismi=40000;
                break;
            case "20 000":
                kismi=50000;
                break;
            case "40 000":
                kismi=60000;
                break;
            case "60 000":
                kismi=70000;
                break;
            case "90 000":
                kismi=80000;
                break;
            case "150 000":
                kismi=90000;
                break;
            case "300 000":
                kismi=100000;
                break;
            case "600 000":
                kismi=110000;
                break;
            case "1 200 000":
                kismi=120000;
                break;
            case "2 500 000":
                kismi=130000;
                break;
            case "5 000 000":
                kismi=140000;
                break;
            case "10 000 000":
                kismi=210000;
                break;
        }

        int donecekpuan = kismi/sure;
        puan=donecekpuan;
        Log.e("Puan",""+puan);

    }

    public Win(String para, int sure) {
        this.para = para;
        this.sure = sure;
        puanHesapla(para,sure);
    }

    public int getPuan() {
        return puan;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public int getSure() {
        return sure;
    }

    public void setSure(int sure) {
        this.sure = sure;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }
}
