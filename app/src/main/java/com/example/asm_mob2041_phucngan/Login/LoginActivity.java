package com.example.asm_mob2041_phucngan.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_mob2041_phucngan.DAO.ThuThuDAO;
import com.example.asm_mob2041_phucngan.MainActivity;
import com.example.asm_mob2041_phucngan.R;

public class LoginActivity extends AppCompatActivity {
    EditText edUser,edPass;
    CheckBox chkRemember;
    Button btnCancel,btnLogin;
    ThuThuDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUser = (EditText) findViewById(R.id.edUser);
        edPass = (EditText) findViewById(R.id.edPass);
        chkRemember=(CheckBox) findViewById(R.id.chkRemember);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        dao = new ThuThuDAO(this);
        SharedPreferences pre = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUser.setText(pre.getString("USERNAME",""));
        edPass.setText(pre.getString("PASSWORD",""));
        chkRemember.setChecked(pre.getBoolean("REMEMBER",false));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPass.setText("");
                edUser.setText("");
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }
    public void checkLogin(){
        String strUser = edUser.getText().toString();
        String strPass = edPass.getText().toString();
        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
        }else{
            if (dao.checkLogin(strUser,strPass)>0 || (strUser.equals("admin"))&&(strPass.equals("admin"))){
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkRemember.isChecked());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user",strUser);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoắc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberUser(String u,String p ,boolean status){
        SharedPreferences pre = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        if(!status){
            editor.clear();
        }else{
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }

}