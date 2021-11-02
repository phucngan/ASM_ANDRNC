package com.example.asm_mob2041_phucngan.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_mob2041_phucngan.DataBase.DbHelper;
import com.example.asm_mob2041_phucngan.entity.LoaiSach;
import com.example.asm_mob2041_phucngan.entity.PhieuMuon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT",String.valueOf(obj.maTT));
        values.put("maTV",obj.maTV);
        values.put("maSach",String.valueOf(obj.maSach));
        values.put("tienThue",String.valueOf(obj.tienThue));
        values.put("traSach",obj.traSach);
        values.put("ngay", simpleDateFormat.format(obj.ngay));
        return db.insert("PhieuMuon",null,values);
    }
    public int update(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT",String.valueOf(obj.maTT));
        values.put("maTV",obj.maTV);
        values.put("maSach",String.valueOf(obj.maSach));
        values.put("tienThue",String.valueOf(obj.tienThue));
        values.put("traSach",obj.traSach);
        values.put("ngay", simpleDateFormat.format(obj.ngay));
        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(obj.maPM)});

    }
    public int delete(String id){
        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }

    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }
    public PhieuMuon getID(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }
    private List<PhieuMuon> getData(String sql,String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.maPM = Integer.parseInt(c.getString(c.getColumnIndex("maPM")));
            obj.maTT = c.getString(c.getColumnIndex("maTT"));
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            obj.tienThue = Integer.parseInt(c.getString(c.getColumnIndex("tienThue")));
            obj.traSach = Integer.parseInt(c.getString(c.getColumnIndex("traSach")));
            try {
                obj.ngay = simpleDateFormat.parse(c.getString(c.getColumnIndex("ngay")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(obj);
        }
        return list;
    }
}
