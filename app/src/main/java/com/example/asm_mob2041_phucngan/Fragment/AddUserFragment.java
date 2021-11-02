package com.example.asm_mob2041_phucngan.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_mob2041_phucngan.DAO.ThuThuDAO;
import com.example.asm_mob2041_phucngan.R;
import com.example.asm_mob2041_phucngan.entity.ThuThu;


public class AddUserFragment extends Fragment {
    EditText edUser,edHoten,edPass,edRePass;
    Button btnCancel,btnSave;
    ThuThuDAO dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            final View v = inflater.inflate(R.layout.fragment_add_user,container,false);
            edUser = v.findViewById(R.id.edUser);
            edPass = v.findViewById(R.id.edPass);
            edHoten = v.findViewById(R.id.edHoten);
            edRePass = v.findViewById(R.id.edRePass);
            btnCancel = v.findViewById(R.id.btnCanceladd);
            btnSave = v.findViewById(R.id.btnSaveadd);

            dao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUser.setText("");
                edHoten.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu = new ThuThu();
                thuThu.maTT = edUser.getText().toString();
                thuThu.hoTen = edHoten.getText().toString();
                thuThu.matKhau = edPass.getText().toString();
                if (validate()>0){
                    if (dao.insert(thuThu)>0){
                        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        edUser.setText("");
                        edHoten.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
    public int validate(){
        int check = 1;
        if (edUser.getText().length()==0||edHoten.getText().length()==0||edPass.getText().length()==0||edRePass.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            }
        }
        return check;
    }
}