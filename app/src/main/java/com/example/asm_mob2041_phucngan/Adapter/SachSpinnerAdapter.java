package com.example.asm_mob2041_phucngan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.Sach;

import java.util.ArrayList;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> lists;
    TextView tvMaLoaiSach,tvTenLoaiSach;
    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> lists ) {
        super(context, 0,lists);
        this.context = context;
        this.lists=lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_spinner_item,null);
        }
        final  Sach item = lists.get(position);
        if (item != null){
            tvMaLoaiSach = v.findViewById(R.id.tvMaSachSp);
            tvMaLoaiSach.setText(item.maSach+". ");
            tvTenLoaiSach = v.findViewById(R.id.tvTenSachSp);
            tvTenLoaiSach.setText(item.tenSach);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_spinner_item,null);
        }
        final Sach item = lists.get(position);
        if (item !=null){
            tvMaLoaiSach = v.findViewById(R.id.tvMaSachSp);
            tvMaLoaiSach.setText(item.maSach+". ");
            tvTenLoaiSach = v.findViewById(R.id.tvTenSachSp);
            tvTenLoaiSach.setText(item.tenSach);
        }
        return v;
    }
}
