package com.example.asm_mob2041_phucngan.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_mob2041_phucngan.DataBase.DbHelper;
import com.example.asm_mob2041_phucngan.entity.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach",obj.tenSach);
        values.put("giaThue",String.valueOf(obj.giaThue));
        values.put("maLoai",obj.maLoai);
        return db.insert("Sach",null,values);
    }
    public int update(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach",obj.tenSach);
        values.put("giaThue",String.valueOf(obj.giaThue));
        values.put("maLoai",obj.maLoai);
        return db.update("Sach",values,"maSach=?",new String[]{String.valueOf(obj.maSach)});

    }
    public int delete(String id){
        return db.delete("Sach","maSach=?",new String[]{id});
    }
    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }
    public Sach getID(String id){
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }
    private List<Sach> getData(String sql,String...selectionArgs){
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Sach obj = new Sach();
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.tenSach = c.getString(c.getColumnIndex("tenSach"));
            obj.giaThue = Integer.parseInt(c.getString(c.getColumnIndex("giaThue")));
            obj.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            list.add(obj);
        }
        return list;
    }
}
