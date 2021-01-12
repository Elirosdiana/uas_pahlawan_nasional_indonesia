package com.elirosdiana.pahlawannasional;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.elirosdiana.pahlawannasional.model.PahlawanNasional;

public class SharedPreferenceUtility {

    private static final String PREF_FILE = "com.elirosdiana.pahlawannasional.DATA";
    // PREF_FILE -> Nama file utk penyimpanan,
    // biasanya menyertakan app.id agar tidak bentrok dgn app lain
    private static final String TRANS_KEY = "TRANS"; // KEY utk daftar transaksi
    private static final String USER_NAME_KEY = "USERNAME"; // KEY untuk nama user

    private static SharedPreferences getSharedPref(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                PREF_FILE, Context.MODE_PRIVATE);
        return sharedPref;
    }

    /*
        Ambil data username dari Shared Preference
     */
    public static String getUserName(Context ctx) {
        return getSharedPref(ctx).getString(USER_NAME_KEY, "NO NAME");
    }

    /*
        Simpan data username ke Shared Preference
     */
    public static void saveUserName(Context ctx, String userName) {
        Log.d("SH PREF", "Change user name to" + userName);
        getSharedPref(ctx).edit().putString(USER_NAME_KEY, userName).apply();
    }

    /*
        Ambil data transaksi dari Shared Preference
        Perhatikan bahwa data disimpan dalam format JSON String
     */
    public static List<PahlawanNasional> getAllPahlawan(Context ctx) {
        String jsonString = getSharedPref(ctx).getString(TRANS_KEY, null);
        List<PahlawanNasional> trs = new ArrayList<PahlawanNasional>();
        if (jsonString != null) {
            Log.d("SH PREF", "json string is:" + jsonString);
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    trs.add(PahlawanNasional.fromJSONObject(obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(trs, (transaksi, t1) -> {
            return transaksi.getId().compareTo(t1.getId());
        }); // urutkan transaksi berdasarkan id
        return trs;
    }

    /*
        Simpan data transaksi ke Shared Preference
        Perhatikan bahwa data disimpan dalam format JSON String
     */
    private static void saveAllPahlawan(Context ctx, List<PahlawanNasional> trs) {
        List<JSONObject> jsonTrs = new ArrayList<JSONObject>();
        JSONArray jsonArr = new JSONArray();
        for (PahlawanNasional tr : trs) {
            jsonArr.put(tr.toJSONObject());
        }
        getSharedPref(ctx).edit().putString(TRANS_KEY, jsonArr.toString()).apply();
    }

    /*
        Tambah data transaksi baru ke Shared Preference
     */
    public static void addPahlawan(Context ctx, PahlawanNasional tr) {
        List<PahlawanNasional> trs = getAllPahlawan(ctx);
        trs.add(tr);
        saveAllPahlawan(ctx, trs);
    }

    public static void editPahlawan(Context ctx, PahlawanNasional tr) {
        List<PahlawanNasional> trs = getAllPahlawan(ctx);
        for (PahlawanNasional tre : trs) {
            if (tr.getId().equals(tre.getId())) {
                trs.remove(tre);
                trs.add(tr);
            }
        }
        saveAllPahlawan(ctx, trs);
    }

    public static void deletePahlawan(Context ctx, String id) {
        List<PahlawanNasional> trs = getAllPahlawan(ctx);
        for (PahlawanNasional tr : trs) {
            if (tr.getId().equals(id)) {
                trs.remove(tr);
            }
        }
        saveAllPahlawan(ctx, trs);
    }

}
