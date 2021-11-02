package com.example.asm_mob2041_phucngan.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asm_mob2041_phucngan.DAO.LoaiSachDAO;
import com.example.asm_mob2041_phucngan.DAO.SachDAO;
import com.example.asm_mob2041_phucngan.DAO.ThanhVienDAO;
import com.example.asm_mob2041_phucngan.Fragment.PhieuMuonFragment;
import com.example.asm_mob2041_phucngan.Fragment.SachFragment;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.LoaiSach;
import com.example.asm_mob2041_phucngan.entity.PhieuMuon;
import com.example.asm_mob2041_phucngan.entity.Sach;
import com.example.asm_mob2041_phucngan.entity.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM, tvTenSach, tvNgayThue, tvTienThue,tvTenTV,tvTraSach;
    ImageView imgDel;
    ThanhVienDAO thanhVienDAO;
    SachDAO sachDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.phieu_muon_item, null);
        }
        final PhieuMuon item = lists.get(position);
        if (item != null) {
            tvMaPM=v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã Phiếu Mượn: "+item.maPM);
            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.maSach));
            tvTenSach=v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: "+sach.tenSach);
            tvNgayThue=v.findViewById(R.id.tvNgayThue);
            //Log.i("/////////////","///////////===========>"+item.ngay);
            tvNgayThue.setText("Ngày Thuê: "+sdf.format(item.ngay));
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.maTV));
            tvTenTV=v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Loại Sách: "+thanhVien.hoTen);
            tvTienThue=v.findViewById(R.id.tvGiaThue);
            tvTienThue.setText("Giá Thuê: "+item.tienThue);
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if (item.traSach ==1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã Trả Sách");
            }else{
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa Trả Sách");
            }
            imgDel=v.findViewById(R.id.imgDelPM);
        }
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(String.valueOf(item.maPM));
                }
            });
            return v;


    }
}
