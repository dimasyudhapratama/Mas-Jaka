package com.erait.mas_jaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterMasyarakat extends AppCompatActivity {
    private EditText reg_masy_nama_lengkap, reg_masy_email, reg_masy_tgl_lahir, reg_masy_no_telp, reg_masy_password, reg_masy_repassword;
    private RadioGroup radio_group_masy_JK;
    private RadioButton radio_button_masy_JK;
    private Button btn_register_masy;
    private TextView masy_redirect_login;

    private String nama_lengkap,email, jk, tgl_lahir,no_telp,password,repassword, password_encrypted;
    int SelectedJK;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_masyarakat);
        getSupportActionBar().hide();
        //Initialization
        reg_masy_nama_lengkap = findViewById(R.id.reg_masy_nama_lengkap);
        reg_masy_email = findViewById(R.id.reg_masy_email);
        radio_group_masy_JK = findViewById(R.id.radio_masy_JK);
        reg_masy_tgl_lahir = findViewById(R.id.reg_masy_tgl_lahir);
        reg_masy_no_telp = findViewById(R.id.reg_masy_no_telp);
        reg_masy_password = findViewById(R.id.reg_masy_password);
        reg_masy_repassword = findViewById(R.id.reg_masy_repassword);
        btn_register_masy = findViewById(R.id.btn_register_masy);
        masy_redirect_login = findViewById(R.id.masy_redirect_login);

        //Ketika Button Diklik
        btn_register_masy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama_lengkap = reg_masy_nama_lengkap.getText().toString();
                email = reg_masy_email.getText().toString();
                SelectedJK = radio_group_masy_JK.getCheckedRadioButtonId();
                tgl_lahir = reg_masy_tgl_lahir.getText().toString();
                no_telp = reg_masy_no_telp.getText().toString();
                password = reg_masy_password.getText().toString();
                repassword = reg_masy_repassword.getText().toString();

                if(nama_lengkap.equals("") || email.equals("") || tgl_lahir.equals("") || no_telp.equals("") || password.equals("") || repassword.equals("")){
                    Toast.makeText(RegisterMasyarakat.this, "Silahkan Lengkapi Data", Toast.LENGTH_SHORT).show();
                }else{
                    //Mencari Hasil Radio Button Jenis Kelamin
                    radio_button_masy_JK = (RadioButton) findViewById(SelectedJK);
                    jk = radio_button_masy_JK.getText().toString();

                    if(password.equals(repassword) ){
                        //Encrypt Password MD5
                        password_encrypted = PasswordEncrypt.md5(password);
                        Log.d("Password Encrypted : ", password_encrypted);

                        //Insert Ke Collection 'Users'
                        Map<String, Object> user = new HashMap<>();
                        user.put("nama_lengkap", nama_lengkap);
                        user.put("email", email);
                        user.put("jk", jk);
                        user.put("tgl_lahir", email); //Tgl Lahir Belum Bener
                        user.put("no_telp", no_telp);
                        user.put("password", password_encrypted);
                        user.put("userType","masyarakat");
                        user.put("XP",0);
                        user.put("Points",0);

                        addUserToCollection(user);

                        //Redirect Ke Halaman Sign In
                        Toast.makeText(RegisterMasyarakat.this, "Registrasi Berhasil\nSilahkan Login Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterMasyarakat.this,LoginActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(RegisterMasyarakat.this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        masy_redirect_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterMasyarakat.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void addUserToCollection(Map<String, Object> data){
        db.collection("users")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Info", "Sukses Disimpan : "+documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Info","Gagal Disimpan");
                    }
                });
    }
}
