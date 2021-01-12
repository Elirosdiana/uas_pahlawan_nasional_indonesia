package com.elirosdiana.pahlawannasional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.elirosdiana.pahlawannasional.model.PahlawanNasional;

import java.util.List;

public class DaftarPahlawanAdapter extends ArrayAdapter<PahlawanNasional> {
    Context context;

    public DaftarPahlawanAdapter(@NonNull Context context, @NonNull List<PahlawanNasional> objects) {
        super(context, R.layout.row_pahlawan, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txNama;
        TextView txLahir;
        TextView txWafat;
        TextView txProfil;
        TextView txAsal;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PahlawanNasional tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_pahlawan, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txNama = convertView.findViewById(R.id.row_nama_pahlawan);
            viewHolder.txLahir = convertView.findViewById(R.id.row_tahun_lahir);
            viewHolder.txWafat = convertView.findViewById(R.id.row_tahun_wafat);
            viewHolder.txProfil = convertView.findViewById(R.id.row_profil);
            viewHolder.txAsal = convertView.findViewById(R.id.row_daerah);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txNama.setText(tr.getNamaPahlawan());
        viewHolder.txLahir.setText(tr.getLahir());
        viewHolder.txWafat.setText(tr.getWafat());
        viewHolder.txProfil.setText(tr.getProfil());
        if (tr.getAsal().equals(PahlawanNasional.JAWA)) {
            viewHolder.txAsal.setText("JAWA");
        } else if (tr.getAsal().equals(PahlawanNasional.SUMATERA)) {
            viewHolder.txAsal.setText("SUMATERA");
        } else if (tr.getAsal().equals(PahlawanNasional.IRIANJAYA)) {
            viewHolder.txAsal.setText("IRIANJAYA");
        } else if (tr.getAsal().equals(PahlawanNasional.KALIMANTAN)) {
            viewHolder.txAsal.setText("KALIMANTAN");
        } else if (tr.getAsal().equals(PahlawanNasional.SULAWESI)) {
            viewHolder.txAsal.setText("SULAWESI");
        } else if (tr.getAsal().equals(PahlawanNasional.NTB)) {
            viewHolder.txAsal.setText("NTB");
        } else if (tr.getAsal().equals(PahlawanNasional.NTT)) {
            viewHolder.txAsal.setText("NTT");
        } else {
            viewHolder.txAsal.setText("INDONESIA");
        }
        return convertView;
    }
}