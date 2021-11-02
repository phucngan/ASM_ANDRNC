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

import com.example.asm_mob2041_phucngan.Fragment.ThanhVienFragment;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    TextView tvMaTV,tvTenTV,tvNamSinh;
    private ArrayList<ThanhVien> lists;
    ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context=context;
        this.lists=lists;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanh_vien_item,null);
        }
        final ThanhVien item = lists.get(position);
        if (item!=null){
            tvMaTV=v.findViewById(R.id.tvIdTV);
            tvMaTV.setText("Mã Thành Viên: "+item.maTV);
            tvTenTV=v.findViewById(R.id.tvNameTV);
            tvTenTV.setText("Tên Thành Viên: "+item.hoTen);
            tvNamSinh=v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm Sinh: "+item.namSinh);
            imgDel=v.findViewById(R.id.imgDel);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maTV));
            }
        });
        return v;
    }
}
