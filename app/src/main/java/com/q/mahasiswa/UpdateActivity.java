package com.q.mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {
    TextInputLayout nim, nama, prodi, matkul, nilaiAwal, nilaiSP;
    private DatabaseReference databaseReference;
    private Button button;
    List<Model> model = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nim = findViewById(R.id.nim1);
        nama = findViewById(R.id.nama1);
        prodi = findViewById(R.id.prodi1);
        matkul = findViewById(R.id.matkul1);
        nilaiAwal = findViewById(R.id.nilaiAwal1);
        nilaiSP = findViewById(R.id.nilaiSP1);

        button = findViewById(R.id.btn_submit);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        final Model modelGet = (Model) getIntent().getSerializableExtra("data");
        if (modelGet != null) {

            Objects.requireNonNull(nim.getEditText()).setText(modelGet.getNim());
            Objects.requireNonNull(nama.getEditText()).setText(modelGet.getNama());
            Objects.requireNonNull(prodi.getEditText()).setText(modelGet.getProdi());
            Objects.requireNonNull(matkul.getEditText()).setText(modelGet.getMatkul());
            Objects.requireNonNull(nilaiAwal.getEditText()).setText(modelGet.getNilaiAwal());
            Objects.requireNonNull(nilaiSP.getEditText()).setText(modelGet.getNilaiSP());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        modelGet.setNim(nim.getEditText().getText().toString());
                        modelGet.setNama(nama.getEditText().getText().toString());
                        modelGet.setProdi(prodi.getEditText().getText().toString());
                        modelGet.setMatkul(matkul.getEditText().getText().toString());
                        modelGet.setNilaiAwal(nilaiAwal.getEditText().getText().toString());
                        modelGet.setNilaiSP(nilaiSP.getEditText().getText().toString());
                    if (validate() == true) {
                        updateBarang(modelGet);
                    }


                }
            });
        }
    }


    private void updateBarang(Model model) {
        databaseReference.child("model") //akses parent index, ibaratnya seperti nama tabel
                .child(model.getKey()) //select barang berdasarkan key
                .setValue(model) //set value barang yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        finish();
                        Snackbar.make(findViewById(R.id.btn_submit), "Data berhasil diupdate", Snackbar.LENGTH_LONG).show();


                    }
                });
    }


    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, UpdateActivity.class);
    }

    private boolean validate() {
        //Nim
        String validateNim = Objects.requireNonNull(nim.getEditText()).getText().toString().trim();
        String validateNama = Objects.requireNonNull(nama.getEditText()).getText().toString().trim();
        String validateProdi = Objects.requireNonNull(prodi.getEditText()).getText().toString().trim();
        String validateMatkul = Objects.requireNonNull(matkul.getEditText()).getText().toString().trim();
        String validateNilaiAwal = Objects.requireNonNull(nilaiAwal.getEditText()).getText().toString().trim();
        String validateNilaiSP = Objects.requireNonNull(nilaiSP.getEditText()).getText().toString().trim();


        if (validateNim.isEmpty() && validateNama.isEmpty() && validateProdi.isEmpty() && validateMatkul.isEmpty() && validateNilaiAwal.isEmpty() && validateNilaiSP.isEmpty()) {
            nim.setError("Field Tidak Boleh Kosong");
            nama.setError("Field Tidak Boleh Kosong");
            prodi.setError("Field Tidak Boleh Kosong");
            matkul.setError("Field Tidak Boleh Kosong");
            nilaiAwal.setError("Field Tidak Boleh Kosong");
            nilaiSP.setError("Field Tidak Boleh Kosong");
            Log.d("fals" ,"false");
            return false;
        } else {
            nim.setError(null);
            nama.setError(null);
            prodi.setError(null);
            matkul.setError(null);
            nilaiAwal.setError(null);
            nilaiSP.setError(null);
            Log.d("fals2" ,"false");
            return true;
        }
    }
}
