package com.example.asm_mob2041_phucngan.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asm_mob2041_phucngan.Adapter.LoaiSachSpinnerAdapter;
import com.example.asm_mob2041_phucngan.Adapter.SachAdapter;
import com.example.asm_mob2041_phucngan.DAO.LoaiSachDAO;
import com.example.asm_mob2041_phucngan.DAO.SachDAO;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.LoaiSach;
import com.example.asm_mob2041_phucngan.entity.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SachFragment extends Fragment {
    ListView lv;
    ArrayList<Sach> list;
    Dialog dialog;
    FloatingActionButton fab;
    EditText edMaSach,edTenSach,edGiaThue;
    Spinner spinner;
    Button btnSave,btnCancel;
    static SachDAO dao;
    SachAdapter adapter;
    Sach item;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach,position;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_sach, container, false);
        lv = v.findViewById(R.id.lvSach);
        fab = v.findViewById(R.id.fab1);
        dao = new SachDAO(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
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
    protected void openDialog(final Context context,final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edMaSach=dialog.findViewById(R.id.edMaSach);
        edTenSach=dialog.findViewById(R.id.edTenSach);
        edGiaThue=dialog.findViewById(R.id.edGiaThue);
        spinner=dialog.findViewById(R.id.spnLoaiSach);
        btnSave=dialog.findViewById(R.id.btnSaveSach);
        btnCancel=dialog.findViewById(R.id.btnCancelSach);
        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        spinnerAdapter = new LoaiSachSpinnerAdapter(context,listLoaiSach);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).maLoai;
                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).tenLoai, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaSach.setEnabled(false);
        if (type !=0){
            edMaSach.setText(String.valueOf(item.maSach));
            edTenSach.setText(String.valueOf(item.tenSach));
            edGiaThue.setText(String.valueOf(item.giaThue));
            for (int i=0;i<listLoaiSach.size();i++){
                if (item.maSach==listLoaiSach.get(i).maLoai){
                    position = i;
                }
                //Log.i("demo","posSach"+position);
                spinner.setSelection(position);
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Sach();
                item.tenSach=edTenSach.getText().toString();
                item.giaThue=Integer.parseInt(edGiaThue.getText().toString());
                item.maLoai=maLoaiSach;
                if (validate()>0){
                    if (type == 0){
                        if (dao.insert(item)>0){
                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maSach=Integer.parseInt(edMaSach.getText().toString());
                        if (dao.update(item)>0){
                            Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("DELETE");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
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
    void capNhatLv(){
        list = (ArrayList<Sach>) dao.getAll();
        adapter=new SachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edTenSach.getText().length()==0||edGiaThue.getText().length()==0){
            Toast.makeText(getContext(), "Bạn Phải Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}




