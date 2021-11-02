package com.example.asm_mob2041_phucngan.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asm_mob2041_phucngan.Adapter.LoaiSachAdapter;
import com.example.asm_mob2041_phucngan.Adapter.ThanhVienAdapter;
import com.example.asm_mob2041_phucngan.DAO.LoaiSachDAO;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.LoaiSach;
import com.example.asm_mob2041_phucngan.entity.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class LoaiSachFragment extends Fragment {
    ListView lv;
    EditText edMaLoaiSach,edTenLoaiSach;
    Dialog dialog;
    Button btnSave,btnCancel;
    FloatingActionButton fab;
    static LoaiSachDAO dao;
    LoaiSach item;
    ArrayList<LoaiSach> list;
    LoaiSachAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lv = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fab1);
        dao = new LoaiSachDAO(getContext());
        capnhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext(),0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getContext(),1);
                return false;
            }
        });
        return v;
    }
    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        edMaLoaiSach=dialog.findViewById(R.id.edMaLoaiSach);
        edTenLoaiSach=dialog.findViewById(R.id.edTenLoaiSach);

        btnCancel=dialog.findViewById(R.id.btnCancelLoaiSach);
        btnSave=dialog.findViewById(R.id.btnSaveLoaiSach);
        edMaLoaiSach.setEnabled(false);
        if (type!=0){
            edMaLoaiSach.setText(String.valueOf(item.maLoai));
            edTenLoaiSach.setText(item.tenLoai);

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
                item = new LoaiSach();
                item.tenLoai=edTenLoaiSach.getText().toString();
                if (validate()>0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.maLoai = Integer.parseInt(edMaLoaiSach.getText().toString());
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
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edTenLoaiSach.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}