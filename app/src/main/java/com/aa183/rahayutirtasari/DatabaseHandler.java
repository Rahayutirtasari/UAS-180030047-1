package com.aa183.rahayutirtasari;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 3;
    private final static String DATABASE_NAME = "db_buku";
    private final static String TABLE_BUKU = "t_buku";
    private final static String KEY_ID_BUKU = "ID_buku";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_PENULIS = "Penulis";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_PENERBIT = "Penerbit";
    private final static String KEY_TAHUN_RILIS = "Tahun_rilis";
    private final static String KEY_RINGKASAN = "Ringkasan";
    private Context context;


    public DatabaseHandler(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BUKU = "CREATE TABLE " + TABLE_BUKU
                + "(" + KEY_ID_BUKU + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_GAMBAR + " TEXT, "
                + KEY_PENULIS + " TEXT, " + KEY_PENERBIT + " TEXT, "
                + KEY_TAHUN_RILIS + " TEXT, " + KEY_RINGKASAN + " TEXT);";

        db.execSQL(CREATE_TABLE_BUKU);
        inisialisasiBukuAwal(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_BUKU;
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

    public void tambahBuku (Buku dataBuku) { // FUNGSI INI TIDAK ADA GUNANYA
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        cv.put(KEY_TAHUN_RILIS, dataBuku.getTahunRilis());
        cv.put(KEY_RINGKASAN, dataBuku.getRingkasan());
        Log.d("tambahBuku", "tambahBuku Gak Ada DB: " + dataBuku.getGambar());
        db.insert(TABLE_BUKU, null, cv);
        db.close();
    }


    public void tambahBuku (Buku dataBuku, SQLiteDatabase db) { //2
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        cv.put(KEY_TAHUN_RILIS, dataBuku.getTahunRilis());
        cv.put(KEY_RINGKASAN, dataBuku.getRingkasan());
        Log.d("tambahBuku", "tambahBuku Ada DB: " + dataBuku.getGambar());
        db.insert(TABLE_BUKU, null, cv);
    }

    public void editBuku (Buku dataBuku) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        cv.put(KEY_TAHUN_RILIS, dataBuku.getTahunRilis());
        cv.put(KEY_RINGKASAN, dataBuku.getRingkasan());

        db.update(TABLE_BUKU, cv, KEY_ID_BUKU + "=?", new String[] {String.valueOf(dataBuku.getIdBuku())});
        db.close();
    }

    public void hapusBuku(int idBuku) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_BUKU, KEY_ID_BUKU + "=?", new String[]{String.valueOf(idBuku)});
        db.close();
    }

    public ArrayList<Buku> getAllBuku() {
        ArrayList<Buku> dataBuku = new ArrayList<>();
        String querry = "SELECT * FROM " + TABLE_BUKU;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(querry, null);
        if (csr.moveToFirst()){
            do {
//                nt idBuku, String judul, String penulis, String gambar, String penerbit, String tahunRilis, String ringkasan
                Buku tempBuku = new Buku(
                        csr.getInt(0), //id
                        csr.getString(1), //judul
                        csr.getString(3), //penulis
                        csr.getString(2), //gambar
                        csr.getString(4), //penerbit
                        csr.getString(5), //tahunRilis
                        csr.getString(6) //ringkasan
                );
                dataBuku.add(tempBuku);
                Log.d("checkGambar", "getAllBuku: " + tempBuku.getGambar());
            } while (csr.moveToNext());
        }

        return dataBuku;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiBukuAwal(SQLiteDatabase db) {
        int idBuku = 0;

        //Menambahkan data buku ke 1

        Buku buku1 = new Buku(
                idBuku,
                "Promise",
                "Dwitasari",
                storeImageFile(R.drawable.buku1),
                "Loveable",
                "2016",
                "Rahman merupakan cowok yang berasal dari Jogja. Ia berwajah tampan namun berkepribadian sederhana dan lugu. Hal ini berkebalikan dengan sahabatnya yang bernama Aji. Aji, seorang cowok yang playboy namun setia kawan. Suatu hari, Aji ingin mengubah Rahman menjadi cowok yang dapat merasakan cinta dan berwawasan luas namun caranya salah.\n" +
                        "\n" +
                        "Aji memberi Rahman DVD porno namun ketahuan ayahnya sehingga Rahman belum sempat menontonnya. Ayahnya merasa nama baik sebagai cucu kyai tercoreng sehingga menjodohkan Rahman dengan anak sahabatnya.\n" +
                        "\n" +
                        "18 bulan kemudian, Rahman melanjutkan kuliah di Milan. Ia bersahabat dengan Moza setelah Ia kehilangan Aji yang telah pergi mewujudkan cita-citanya sebelum lulus sekolah. Ia tulus bersahabat dengan Moza, namun hal ini membuat Moza jatuh cinta kepadanya. Selain menuntut ilmu, tujuannya ke Milan adalah untuk mencari gadis pujaannya yang menghilang.\n" +
                        "Seiring berjalannya waktu, Rahman bertemu dengan Aji yang kini sudah berubah karena telah memiliki pasangan. Ia pun turut bahagia atas kebahagiaan Aji. Namun, Rahman masih menyimpan luka akibat cinta sejatinya yang hilang. Di sisi lain, Moza sadar bahwa Ia akan berjuang demi cintanya kepada Rahman.\n"
        );

        tambahBuku(buku1, db);
        idBuku++;

        //Data buku ke 2

        Buku buku2 = new Buku(
                idBuku,
                "Laskar Pelangi",
                "Andrea Hirata",
                storeImageFile(R.drawable.buku2),
                "Bentang Pustaka, Yogyakarta",
                "2005",
                "Laskar pelangi merupakan novel fiksi yang ditulis oleh Andrea Hirata. Novel tersebut mengisahkan tentang kehidupan 10 anak di Pulau Belitung, Provinsi Bangka Belitung yang saling bersahabat. Orang tua mereka memiliki profesi yang sama, yaitu penambang timah. Meskipun hidup di tengah kemiskinan, namun mereka tetap bersekolah untuk menimba ilmu.\n" +
                        "\n" +
                        "Keadaan sekolah mereka cukup memprihatinkan dan tidak layak huni. Hal ini mendapat teguran dari pemerintah untuk menutup sekolah tersebut karena jumlah muridnya yang sedikit, yaitu hanya 10 orang. Waktu itu, sekolah nyaris ditutup karena pada saat penerimaan peserta didik baru hanya ada 9 orang siswa. Lalu, harun datang sehingga sekolah tidak jadi ditutup.\n" +
                        "\n" +
                        "Ibu Muslimah merupakan sosok guru yang penyabar dalam mendidik para siswa. Meskipun beliau hanyalah lulusan SMP, namun beliau memiliki tekad yang kuat untuk mendedikasikan diri di dunia pendidikan.\n"
        );

        tambahBuku(buku2, db);
        idBuku++;

        //Data buku ke 3

        Buku buku3 = new Buku(
                idBuku,
                "Tentang Kamu",
                "Tere Liye",
                storeImageFile(R.drawable.buku3),
                "Republika Penerbit, Jakarta",
                "2016",
                "Tentang Kamu, merupakan novel karya Tere Liye. Novel tersebut menceritakan kisah hidup wanita dari keluarga miskin yang tinggal di Pulau Bungin, Kepulauan Sumbawa, Provinsi Nusa Tenggara Barat. Wanita itu adalah Sri Ningsih.\n" +
                        "\n" +
                        "Perjalanan dimulai dari Zaman yang datang ke tempat Sri Ningsih kecil. Zaman menceritakan masa kecil Sri Ningsih yang tinggal bersama ibu tirinya. Bapaknya melaut tidak pulang-pulang sehingga ibu tirinya menjadi galak dan sering memukuli Sri Ningsih. Suatu hari terjadi kebakaran yang membuat ibu tirinya meninggal. Ia dan adiknya tinggal di pondok pesantren di Surakarta.\n" +
                        "\n" +
                        "Sri Ningsih adalah wanita pekerja keras. Ia pernah menjadi pedagang kaki lima, membuka rental mobil hingga membuka pabrik sabun sendiri di Jakarta. Lalu, Ia memutuskan untuk ke London. Perjalanan hidupnya berakhir di Paris. Ia tinggal di panti jompo sebelum meninggal. Ia meninggalkkan surat wasiat yang unik sehingga jejak hidupnya dapat ditelusuri oleh Zaman.\n"
        );

        tambahBuku(buku3, db);
        idBuku++;

        //Data berita ke 4

        Buku buku4 = new Buku(
                idBuku,
                "Rindu",
                "Darwis Tere Liye",
                storeImageFile(R.drawable.buku4),
                " Republika",
                "2014",
                "Novel Karya Darwis Tere Liye ini mengisahkan tentang perjalanan panjang kerinduan yang membebani hati. Dimulai dari menempuh perjalanan dengan dosa yang banyak di masa lalu. Lalu, menempuh perjalanan yang penuh dengan kebencian karena kehilangan cintanya.\n" +
                        "\n" +
                        "Novel ini memiliki latar waktu pada masa Belanda menjajah Indonesia. Saat itu, warga pribumi akan diberi fasilitas ibadah haji oleh pemerintah Belanda bagi yang mempunyai cukup uang. Perjalanan dilakukan menggunakan kapal Blitar Holland, yang merupakan transportasi modern saat itu.\n" +
                        "\n" +
                        "Dikisahkan keluarga Daeng yang mengikuti perjalanan haji bersama dengan orang tua, kedua anaknya dan pembantunya. Mereka sangat gembira namun tidak mengetahui maksud terselubung ayahnya.\n" +
                        "\n" +
                        "Di sisi lain, ada seorang pelaut yang bernama Ambo Uleng. Ia menghabiskan hampir seluruh hjdupnya di atas laut. Ia juga menaiki kapal Blitar Holland namun tidak mempunyai tujuan hidup. Ia berkeinginan untuk pergi jauh dari Makassar.\n" +
                        "\n" +
                        "Ada seorang wanita keturunan Cina yang sering mengajari mengaji anak-anak di mushola kapal. Ia bernama Bunda Upe. Setiap malam, Ia menangisi dosa-dosanya yang telah berlalu.\n" +
                        "\n" +
                        "Ada lagi seorang ulama Makasasar bernama Gurutta Ahmad Karaeng. Beliau selalu shalat berjamaah dan suatu hari beliau ingin menyelenggarakan pengajian di kapal. Beliau juga sering menjawab pertanyaan dari orang-orang dengan baik. Namun, Ia juga memendam pertanyaan yang tak seorang pun dapat menjawabnya.\n"
        );

        tambahBuku(buku4, db);

    }
}
