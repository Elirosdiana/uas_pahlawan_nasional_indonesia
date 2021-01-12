package com.elirosdiana.pahlawannasional;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.elirosdiana.pahlawannasional.model.PahlawanNasional;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnTambahPahlawan;
    TextView txNoData;
    ListView lvDaftarPahlawan;
    DaftarPahlawanAdapter adapter;
    List<PahlawanNasional> daftarPahlawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inisialisasiView();
        loadDataWisata();
        setupListview();
    }

    private void inisialisasiView() {
        btnTambahPahlawan = findViewById(R.id.fabAddPahlawan);
        btnTambahPahlawan.setOnClickListener(view -> bukaFormTambahPahlawan());
        lvDaftarPahlawan = findViewById(R.id.lv_pahlawan);
        txNoData = findViewById(R.id.tx_nodata);
    }

    private void setupListview() {
        adapter = new DaftarPahlawanAdapter(this, daftarPahlawan);
        lvDaftarPahlawan.setAdapter(adapter);
    }

    private void loadDataWisata() {
        daftarPahlawan = SharedPreferenceUtility.getAllPahlawan(this);
        if (daftarPahlawan.size() > 0) {
            txNoData.setVisibility(View.GONE);
        } else {
            txNoData.setVisibility(View.VISIBLE);
        }

    }

    private void refreshListView() {
        adapter.clear();
        loadDataWisata();
        adapter.addAll(daftarPahlawan);
    }

    private void bukaFormTambahPahlawan() {
        Intent intent = new Intent(this, FormPahlawanActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}