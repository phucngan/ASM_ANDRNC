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

import com.example.asm_mob2041_phucngan.Adapter.ThanhVienAdapter;
import com.example.asm_mob2041_phucngan.DAO.ThanhVienDAO;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ThanhVienFragment extends Fragment {
    ListView lv;
    ArrayList<ThanhVien> list;
    FloatingActionButton fab1;
    Dialog dialog;
    EditText edMaTV,edTenTV,edNamSinh;
    Button btnSave,btnCancel;
    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lv=v.findViewById(R.id.lvThanhVien);
        fab1=v.findViewById(R.id.fab1);
        dao = new ThanhVienDAO(getActivity());
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
    protected void openDialog(final Context context,final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanh_vien_dialog);
        edMaTV=dialog.findViewById(R.id.edMaTV);
        edTenTV=dialog.findViewById(R.id.edTenTV);
        edNamSinh=dialog.findViewById(R.id.edNamSinh);
        btnCancel=dialog.findViewById(R.id.btnCancelDialog);
        btnSave=dialog.findViewById(R.id.btnSaveDialog);
        edMaTV.setEnabled(false);
        if (type!=0){
            edMaTV.setText(String.valueOf(item.maTV));
            edTenTV.setText(item.hoTen);
            edNamSinh.setText(item.namSinh);
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
                item = new ThanhVien();
                item.hoTen=edTenTV.getText().toString();
                item.namSinh=edNamSinh.getText().toString();
                if (validate()>0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.maTV = Integer.parseInt(edMaTV.getText().toString());
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
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edTenTV.getText().length()==0 || edNamSinh.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }

}