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

import com.erait.mas_jaka.Library.PasswordEncrypt;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterUKM extends AppCompatActivity {
    private EditText reg_ukm_nama_lengkap, reg_ukm_email, reg_ukm_nama_ukm, reg_ukm_tgl_lahir, reg_ukm_no_telp, reg_ukm_password, reg_ukm_repassword;
    private RadioGroup radio_group_ukm_JK;
    private RadioButton radio_button_ukm_JK;
    private Button btn_register_ukm;
    private TextView ukm_redirect_login;

    private String nama_lengkap,email, nama_ukm, jk, tgl_lahir,no_telp,password,repassword, password_encrypted;
    private int SelectedJK;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ukm);
        getSupportActionBar().hide();
        reg_ukm_nama_lengkap = findViewById(R.id.reg_ukm_nama_lengkap);
        reg_ukm_email = findViewById(R.id.reg_ukm_email);
        reg_ukm_nama_ukm = findViewById(R.id.reg_ukm_nama_ukm);
        radio_group_ukm_JK = findViewById(R.id.radio_ukm_JK);
        reg_ukm_tgl_lahir = findViewById(R.id.reg_ukm_tgl_lahir);
        reg_ukm_no_telp = findViewById(R.id.reg_ukm_no_telp);
        reg_ukm_password = findViewById(R.id.reg_ukm_password);
        reg_ukm_repassword = findViewById(R.id.reg_ukm_repassword);
        btn_register_ukm = findViewById(R.id.btn_register_ukm);
        ukm_redirect_login = findViewById(R.id.ukm_redirect_login);

        btn_register_ukm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama_lengkap = reg_ukm_nama_lengkap.getText().toString();
                email = reg_ukm_email.getText().toString();
                nama_ukm = reg_ukm_nama_ukm.getText().toString();
                SelectedJK = radio_group_ukm_JK.getCheckedRadioButtonId();
                tgl_lahir = reg_ukm_tgl_lahir.getText().toString();
                no_telp = reg_ukm_no_telp.getText().toString();
                password = reg_ukm_password.getText().toString();
                repassword = reg_ukm_repassword.getText().toString();

                if(nama_lengkap.equals("") || email.equals("") || tgl_lahir.equals("") || nama_ukm.equals("") || no_telp.equals("") || password.equals("") || repassword.equals("")){
                    Toast.makeText(RegisterUKM.this, "Silahkan Lengkapi Data", Toast.LENGTH_SHORT).show();
                }else{
                    //Mencari Hasil Radio Button Jenis Kelamin
                    radio_button_ukm_JK = (RadioButton) findViewById(SelectedJK);
                    jk = radio_button_ukm_JK.getText().toString();

                    if(password.equals(repassword) ){
                        //Encrypt Password MD5
                        password_encrypted = PasswordEncrypt.md5(password);
                        Log.d("Password Encrypted : ", password_encrypted);

                        //Insert Ke Collection 'Users'
                        Map<String, Object> user = new HashMap<>();
                        user.put("nama_lengkap", nama_lengkap);
                        user.put("email", email);
                        user.put("nama_ukm", nama_ukm);
                        user.put("jk", jk);
                        user.put("tgl_lahir", email); //Tgl Lahir Belum Bener
                        user.put("no_telp", no_telp);
                        user.put("password", password_encrypted);
                        user.put("userType","ukm");
                        user.put("XP",0);
                        user.put("Points",0);

                        addUserToCollection(user);

                        //Redirect Ke Halaman Sign In
                        Toast.makeText(RegisterUKM.this, "Registrasi Berhasil\nSilahkan Login Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterUKM.this,LoginActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(RegisterUKM.this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        ukm_redirect_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUKM.this,LoginActivity.class);
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
