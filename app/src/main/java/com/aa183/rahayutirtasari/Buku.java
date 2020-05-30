package com.aa183.rahayutirtasari;


public class Buku {

    private int idBuku;
    private String judul;
    private String penulis;
    private String gambar;
    private String penerbit;
    private String tahunRilis;
    private String ringkasan;

    public Buku(int idBuku, String judul, String penulis, String gambar, String penerbit, String tahunRilis, String ringkasan) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.gambar = gambar;
        this.penerbit = penerbit;
        this.tahunRilis = tahunRilis;
        this.ringkasan = ringkasan;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getTahunRilis() {
        return tahunRilis;
    }

    public void setTahunRilis(String tahunRilis) {
        this.tahunRilis = tahunRilis;
    }

    public String getRingkasan() {
        return ringkasan;
    }

    public void setRingkasan(String ringkasan) {
        this.ringkasan = ringkasan;
    }
}
