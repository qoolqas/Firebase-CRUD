package com.q.mahasiswa;

import android.text.Editable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Model implements Serializable {

    private String nim;
    private String nama;
    private String prodi;
    private String matkul;
    private String nilaiAwal;
    private String nilaiSP;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Model() {
    }

    public Model(String nim1, String nama1, String prodi1, String matkul1, String nilaiAwal1, String nilaiSP1) {
        nim = nim1;
        nama = nama1;
        prodi = prodi1;
        matkul = matkul1;
        nilaiAwal = nilaiAwal1;
        nilaiSP = nilaiSP1;

    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getNilaiAwal() {
        return nilaiAwal;
    }

    public void setNilaiAwal(String nilaiAwal) {
        this.nilaiAwal = nilaiAwal;
    }

    public String getNilaiSP() {
        return nilaiSP;
    }

    public void setNilaiSP(String nilaiSP) {
        this.nilaiSP = nilaiSP;
    }
}
