package com.q.mahasiswa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class Adapter extends RecyclerView.Adapter<  Adapter.ViewHolder> {

    private ArrayList<Model> models;
    private Context context;
    private FirebaseDataListener listener;

    public Adapter(ArrayList<Model> model, Context context) {
        this.models = model;
        this.context = context;
        listener = (MainActivity)context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final Adapter.ViewHolder holder, final int position) {
        holder.nim.setText("NIM : "+models.get(position).getNim());
        holder.nama.setText("Nama : "+models.get(position).getNama());
        holder.prodi.setText("Prodi : "+models.get(position).getProdi());
        holder.matkul.setText("Mata Kuliah : "+models.get(position).getMatkul());
        holder.nilaiAwal.setText("Nilai Awal : "+models.get(position).getNilaiAwal());
        holder.nilaiSP.setText("Nilai SP : "+models.get(position).getNilaiSP());
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popup = new PopupMenu(context, holder.more);
                popup.inflate(R.menu.menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                context.startActivity(UpdateActivity.getActIntent((Activity) context).putExtra("data", models.get(position)));
                                return true;
                            case R.id.delete:
                                listener.onDeleteData(models.get(position), position);
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nim, nama, prodi, matkul, nilaiAwal, nilaiSP;
        ImageView more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nim = itemView.findViewById(R.id.tvNim);
            nama = itemView.findViewById(R.id.tvNama);
            prodi = itemView.findViewById(R.id.tvProdi);
            matkul = itemView.findViewById(R.id.tvMatkul);
            nilaiAwal = itemView.findViewById(R.id.tvNA);
            nilaiSP = itemView.findViewById(R.id.tvNSP);
            more = itemView.findViewById(R.id.more);

        }
    }

    public interface FirebaseDataListener {
        void onDeleteData(Model model, int position);
    }
}
