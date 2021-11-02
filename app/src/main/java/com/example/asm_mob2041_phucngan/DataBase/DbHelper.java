package com.example.asm_mob2041_phucngan.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "PNLIB";
    static final int dbVersion = 1;

    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuThu = "create table ThuThu (" +
                "maTT TEXT PRIMARY KEY, "+
                "hoTen TEXT NOT NULL, "+
                "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThuThu);

        String createTableThanhVien="create table ThanhVien (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT NOT NULL," +
                " namSinh TEXT NOT NULL)";
        db.execSQL(createTableThanhVien);

        String createTableLoaiSach ="create table LoaiSach(" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSach);

        String createTableSach = "create table Sach (" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSach TEXT NOT NULL, " +
                "giaThue INTEGER NOT NULL, " +
                "maLoai INTEGER NOT NULL )";
        db.execSQL(createTableSach);

        String createTablePhieuMuon = "create table PhieuMuon (" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maTT TEXT NOT NULL , " +
                "maTV INTEGER NOT NULL , " +
                "maSach INTEGER NOT NULL ," +
                "tienThue INTEGER NOT NULL," +
                "ngay DATE NOT NULL," +
                "traSach INTEGER NOT NULL )";
        db.execSQL(createTablePhieuMuon);
        db.execSQL(Data_SQLite.INSERT_PHIEU_MUON);
        db.execSQL(Data_SQLite.INSER_THANH_VIEN);
        db.execSQL(Data_SQLite.INSERT_THU_THU);
        db.execSQL(Data_SQLite.INSERT_LOAI_SACH);
        db.execSQL(Data_SQLite.INSERT_SACH);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableLoaiThuThu= "drop table if exists ThuThu";
        db.execSQL(dropTableLoaiThuThu);
        String dropTableThanhVien= "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach= "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach= "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon= "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
