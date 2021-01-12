package com.elirosdiana.pahlawannasional;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.elirosdiana.pahlawannasional.model.PahlawanNasional;

public class FormPahlawanActivity extends AppCompatActivity {

    Button btnSimpan;
    TextInputLayout tilNama, tilLahir, tilWafat, tilProfil;
    Spinner spnDaerah;
    final String[] asal = {PahlawanNasional.JAWA, PahlawanNasional.SUMATERA, PahlawanNasional.IRIANJAYA, PahlawanNasional.SULAWESI, PahlawanNasional.KALIMANTAN, PahlawanNasional.NTB, PahlawanNasional.NTT};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pahlawan);
        inisialisasiView();
    }

    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(view -> simpan());
        tilNama = findViewById(R.id.til_nama_pahlawan);
        tilLahir = findViewById(R.id.til_tahun_lahir);
        tilWafat = findViewById(R.id.til_tahun_wafat);
        tilProfil = findViewById(R.id.til_profile);
        spnDaerah = findViewById(R.id.spn_asal);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                asal
        );
        spnDaerah.setAdapter(adapter);
        spnDaerah.setSelection(0);
    }

    private void simpan() {
        if (isDataValid()) {
            PahlawanNasional tr = new PahlawanNasional();
            tr.setNamaPahlawan(tilNama.getEditText().getText().toString());
            tr.setLahir(tilLahir.getEditText().getText().toString());
            tr.setWafat(tilWafat.getEditText().getText().toString());
            tr.setProfil(tilProfil.getEditText().getText().toString());
            tr.setAsal(spnDaerah.getSelectedItem().toString());
            SharedPreferenceUtility.addPahlawan(this, tr);
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya setelah 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);


        }
    }

    private boolean isDataValid() {
        if (tilNama.getEditText().getText().toString().isEmpty() || tilLahir.getEditText().getText().toString().isEmpty() || tilWafat.getEditText().getText().toString().isEmpty()  || tilProfil.getEditText().getText().toString().isEmpty()|| spnDaerah.getSelectedItem().toString().isEmpty()
        ) {
            Toast.makeText(this, "Data tidak bileh ada yang kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}