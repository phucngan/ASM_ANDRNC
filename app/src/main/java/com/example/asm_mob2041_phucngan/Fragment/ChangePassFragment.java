package com.example.asm_mob2041_phucngan.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
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


public class ChangePassFragment extends Fragment {
    EditText edPassOld,edPassNew,edRePassnew;
    Button btnCancel,btnSave;
    ThuThuDAO dao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_pass,container,false);
        edPassOld = v.findViewById(R.id.edPassOld);
        edPassNew = v.findViewById(R.id.edPassNew);
        edRePassnew = v.findViewById(R.id.edRePassNew);
        btnCancel = v.findViewById(R.id.btnCancel);
        btnSave = v.findViewById(R.id.btnSave);

        dao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPassNew.setText("");
                edRePassnew.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pre = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pre.getString("USERNAME","");
                if (validate() >0){
                    ThuThu thuThu = dao.getID(user);
                    thuThu.matKhau = edPassNew.getText().toString();
                    if (dao.updatePass(thuThu)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPassNew.setText("");
                        edRePassnew.setText("");
                    }else{
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
    public int validate(){
        int check = 1;
        if (edPassOld.getText().length()==0||edPassNew.getText().length()==0||edRePassnew.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            SharedPreferences pre = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String passOld = pre.getString("PASSWORD","");
            String passnew = edPassNew.getText().toString();
            String rePassNew = edRePassnew.getText().toString();
            if (!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!passnew.equals(rePassNew)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}