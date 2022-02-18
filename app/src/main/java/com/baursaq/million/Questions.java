package com.baursaq.million;

import java.util.Map;

public class Questions {
    Map<String,String> cevaplar;
    String dogrucevap;
    String sorulansoru;

    public Questions() {

    }

    public Map<String, String> getCevaplar() {

        return cevaplar;
    }

    public void setCevaplar(Map<String, String> cevaplar) {
        this.cevaplar = cevaplar;
    }

    public String getDogrucevap() {
        return dogrucevap;
    }

    public void setDogrucevap(String dogrucevap) {
        this.dogrucevap = dogrucevap;
    }

    public String getSorulansoru() {
        return sorulansoru;
    }

    public void setSorulansoru(String sorulansoru) {
        this.sorulansoru = sorulansoru;
    }

    public Questions(Map<String, String> cevaplar, String dogrucevap, String sorulansoru) {

        this.cevaplar = cevaplar;
        this.dogrucevap = dogrucevap;
        this.sorulansoru = sorulansoru;
    }
}
