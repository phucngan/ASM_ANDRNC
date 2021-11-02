package com.example.asm_mob2041_phucngan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asm_mob2041_phucngan.DAO.LoaiSachDAO;
import com.example.asm_mob2041_phucngan.Fragment.LoaiSachFragment;
import com.example.asm_mob2041_phucngan.Fragment.SachFragment;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.LoaiSach;
import com.example.asm_mob2041_phucngan.entity.Sach;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> lists;
    TextView tvMaLoaiSach,tvTenLoaiSach;
    ImageView imgDel;
    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists=lists;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item,null);
        }
        final LoaiSach item =lists.get(position);
        if (item != null){
            //LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            //LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.maLoai));
            tvMaLoaiSach=v.findViewById(R.id.tvMaLoaiSach);
            tvMaLoaiSach.setText("Mã Sách: "+item.maLoai);
            tvTenLoaiSach=v.findViewById(R.id.tvTenLoaiSach);
            tvTenLoaiSach.setText("Tên Sách: "+item.tenLoai);
            imgDel=v.findViewById(R.id.imgDelSach);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maLoai));
            }
        });
        return v;

    }
}
