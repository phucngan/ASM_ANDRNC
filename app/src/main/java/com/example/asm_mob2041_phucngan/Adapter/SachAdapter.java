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
import com.example.asm_mob2041_phucngan.Fragment.SachFragment;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.LoaiSach;
import com.example.asm_mob2041_phucngan.entity.Sach;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    SachFragment fragment;
    private ArrayList<Sach> lists;
    TextView tvMaSach,tvTenSach,tvGiaThue,tvMaLoai;
    ImageView imgDel;
    public SachAdapter(@NonNull Context context, SachFragment fragment,ArrayList<Sach> lists) {
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
            v = inflater.inflate(R.layout.sach_item,null);
        }
        final Sach item =lists.get(position);
        if (item != null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.maLoai));
            tvMaSach=v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã Sách: "+item.maSach);
            tvTenSach=v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: "+item.tenSach);
            tvGiaThue=v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá Thuê: "+item.giaThue);
            tvMaLoai=v.findViewById(R.id.tvMaLoai);
            tvMaLoai.setText("Loại Sách: "+loaiSach.tenLoai);
            imgDel=v.findViewById(R.id.imgDelSach);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maSach));
            }
        });
        return v;

    }
}
