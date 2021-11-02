package com.example.asm_mob2041_phucngan.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.asm_mob2041_phucngan.Adapter.PhieuMuonAdapter;
import com.example.asm_mob2041_phucngan.Adapter.SachSpinnerAdapter;
import com.example.asm_mob2041_phucngan.Adapter.ThanhVienSpinnerAdapter;
import com.example.asm_mob2041_phucngan.DAO.PhieuMuonDAO;
import com.example.asm_mob2041_phucngan.DAO.SachDAO;
import com.example.asm_mob2041_phucngan.DAO.ThanhVienDAO;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.PhieuMuon;
import com.example.asm_mob2041_phucngan.entity.Sach;
import com.example.asm_mob2041_phucngan.entity.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

public class PhieuMuonFragment extends Fragment {
    ListView lv;
    ArrayList<PhieuMuon> list;
    FloatingActionButton fab1;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV,spSach;
    TextView tvNgay,tvTienThue;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    Button btnSavePM,btnCancelPM;
    PhieuMuon item;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    CheckBox chkTraSach;
    Sach sach;
    int maSach,tienThue;
    int posTV,posSach;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lv = v.findViewById(R.id.lvPPM);
        fab1=v.findViewById(R.id.fab1);
        dao = new PhieuMuonDAO(getActivity());
        capnhatLv();
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }
    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM=dialog.findViewById(R.id.edMaPM);
        spTV=dialog.findViewById(R.id.spnTV);
        spSach=dialog.findViewById(R.id.spnSach);
        tvNgay=dialog.findViewById(R.id.tvNgayThue);
        tvTienThue=dialog.findViewById(R.id.tvTienThue);
        chkTraSach=dialog.findViewById(R.id.chkTraSach);
        btnCancelPM=dialog.findViewById(R.id.btnCancelPM);
        btnSavePM=dialog.findViewById(R.id.btnSavePM);
        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context,listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).maTV;
                Toast.makeText(context, "Chọn "+listThanhVien.get(position).hoTen, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context,listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).maSach;
                tienThue=listSach.get(position).giaThue;

                Toast.makeText(context, "Chọn "+listSach.get(position).tenSach, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //kiểm tra type insert hay update
        edMaPM.setEnabled(false);
        if (type!=0){
            edMaPM.setText(String.valueOf(item.maPM));
            for (int i = 0;i<listThanhVien.size();i++){
                if (item.maTV == (listThanhVien.get(i).maTV)){
                    posTV = i;
                }
                spTV.setSelection(posTV);
            }
            for (int i = 0;i<listSach.size();i++){
                if (item.maSach == (listSach.get(i).maSach)){
                    posSach = i;
                }
                spSach.setSelection(posSach);

            }
            tvNgay.setText("Ngày Thuê: "+sdf.format(item.ngay));
            //Log.e("///////////////","============="+item.tienThue);
            tvTienThue.setText("Tiền Thuê: "+item.tienThue);
            if (item.traSach == 1){
                chkTraSach.setChecked(true);
            }else {
                chkTraSach.setChecked(false);
            }
        }
        btnCancelPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSavePM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.maSach = maSach;
                item.maTV=maThanhVien;
                Date dt = new Date();
                String date = sdf.format(dt);
                item.ngay=dt;
                item.tienThue=tienThue;
                if (chkTraSach.isChecked()){
                    item.traSach = 1;
                }else{
                    item.traSach = 0;
                }
                if (validate()>0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.maPM = Integer.parseInt(edMaPM.getText().toString());
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    capnhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("DELETE");
        builder.setMessage("Bạn có muốn xóa ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capnhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
    void capnhatLv(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
//        if (edTenLoaiSach.getText().length()==0){
//            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//            check=-1;
//        }
        return check;
    }
}