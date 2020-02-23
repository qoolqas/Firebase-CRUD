package com.q.mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateActivity extends AppCompatActivity {
    TextInputLayout nim, nama, prodi, matkul, nilaiAwal, nilaiSP;
    private DatabaseReference databaseReference;
    private Button button;
    List<Model> model = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        prodi = findViewById(R.id.prodi);
        matkul = findViewById(R.id.matkul);
        nilaiAwal = findViewById(R.id.nilaiAwal);
        nilaiSP = findViewById(R.id.nilaiSP);

        button = findViewById(R.id.btn_submit);



        databaseReference = FirebaseDatabase.getInstance().getReference();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate())  {
                    submit(new Model(nim.getEditText().getText().toString(), nama.getEditText().getText().toString(), prodi.getEditText().getText().toString(),
                            matkul.getEditText().getText().toString(), nilaiAwal.getEditText().getText().toString(), nilaiSP.getEditText().getText().toString()));
                }
            }
        });
    }


    private void submit(Model model) {
        databaseReference.child("model").push().setValue(model).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private boolean validate() {
        //Nim
        String validateNim = Objects.requireNonNull(nim.getEditText()).getText().toString().trim();
        String validateNama = Objects.requireNonNull(nama.getEditText()).getText().toString().trim();
        String validateProdi = Objects.requireNonNull(prodi.getEditText()).getText().toString().trim();
        String validateMatkul = Objects.requireNonNull(matkul.getEditText()).getText().toString().trim();
        String validateNilaiAwal = Objects.requireNonNull(nilaiAwal.getEditText()).getText().toString().trim();
        String validateNilaiSP = Objects.requireNonNull(nilaiSP.getEditText()).getText().toString().trim();

        if (validateNim.isEmpty() && validateNama.isEmpty() && validateProdi.isEmpty()&&
                validateMatkul.isEmpty() && validateNilaiAwal.isEmpty() && validateNilaiSP.isEmpty()) {
            nim.setError("Field Tidak Boleh Kosong");
            nama.setError("Field Tidak Boleh Kosong");
            prodi.setError("Field Tidak Boleh Kosong");
            matkul.setError("Field Tidak Boleh Kosong");
            nilaiAwal.setError("Field Tidak Boleh Kosong");
            nilaiSP.setError("Field Tidak Boleh Kosong");
            return false;
        } else {
            nim.setError(null);
            nama.setError(null);
            prodi.setError(null);
            matkul.setError(null);
            nilaiAwal.setError(null);
            nilaiSP.setError(null);
            return true;
        }
    }
}
