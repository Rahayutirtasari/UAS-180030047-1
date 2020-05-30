package com.aa183.rahayutirtasari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgBuku;
    private TextView tvJudul, tvPenulis, tvPenerbit, tvTahunRilis, tvRingkasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgBuku = findViewById(R.id.iv_buku);
        tvJudul = findViewById(R.id.tv_judul);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvPenerbit = findViewById(R.id.tv_penerbit);
        tvTahunRilis = findViewById(R.id.tv_tahun_rilis);
        tvRingkasan = findViewById(R.id.tv_ringkasan);

        Intent terimaData = getIntent();
        tvJudul.setText(terimaData.getStringExtra("JUDUL"));
        tvPenulis.setText(terimaData.getStringExtra("PENULIS"));
        tvPenerbit.setText(terimaData.getStringExtra("PENERBIT"));
        tvTahunRilis.setText(terimaData.getStringExtra("TAHUN_RILIS"));
        tvRingkasan.setText(terimaData.getStringExtra("RINGKASAN"));
        String imgLocation = terimaData.getStringExtra("GAMBAR");

        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgBuku.setImageBitmap(bitmap);
            imgBuku.setContentDescription(imgLocation);
        }catch (FileNotFoundException er) {
            er.printStackTrace();
            Toast.makeText(this, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }
}
