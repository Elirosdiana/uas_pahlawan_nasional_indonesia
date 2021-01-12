package com.elirosdiana.pahlawannasional.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;


public class PahlawanNasional {
    public static final String JAWA = "JAWA";
    public static final String SUMATERA = "SUMATERA";
    public static final String IRIANJAYA = "IRIANJAYA";
    public static final String KALIMANTAN = "KALIMANTAN";
    public static final String SULAWESI = "SULAWESI";
    public static final String NTB = "NTB";
    public static final String NTT = "NTT";

    private String id;
    private String nama_pahlawan;
    private String lahir;
    private String wafat;
    private String profil;
    private String asal;

    public PahlawanNasional() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaPahlawan() {
        return nama_pahlawan;
    }

    public void setNamaPahlawan(String nama_pahlawan) {
        this.nama_pahlawan = nama_pahlawan;
    }

    public String getLahir() {
        return lahir;
    }

    public void setLahir(String lahir) {
        this.lahir = lahir;
    }

    public String getWafat() {
        return wafat;
    }

    public void setWafat(String wafat) {
        this.wafat = wafat;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public static PahlawanNasional fromJSONObject(JSONObject obj) {
        PahlawanNasional tr = new PahlawanNasional();
        try {
            tr.setId(obj.getString("id"));
            tr.setNamaPahlawan(obj.getString("nama_pahlawan"));
            tr.setLahir(obj.getString("lahir"));
            tr.setWafat(obj.getString("wafat"));
            tr.setProfil(obj.getString("profil"));
            tr.setAsal(obj.getString("asal"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id", this.id);
            jsonObj.put("nama_pahlawan", this.nama_pahlawan);
            jsonObj.put("lahir", this.lahir);
            jsonObj.put("wafat", this.wafat);
            jsonObj.put("profil", this.profil);
            jsonObj.put("asal", this.asal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
