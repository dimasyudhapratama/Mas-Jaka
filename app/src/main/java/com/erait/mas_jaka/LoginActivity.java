package com.erait.mas_jaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.erait.mas_jaka.Library.PasswordEncrypt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private Button login_btn;
    private TextView redirect_to_register;

    private String var_email, var_password;
    String id = ""; //Digunakan Untuk Menampung Id User

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        login_btn = (Button) findViewById(R.id.login_btn);
        redirect_to_register = (TextView) findViewById(R.id.login_redirect_to_option_register);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String var_email = email.getText().toString();
                String var_password = password.getText().toString();
                if(var_email.equals("")) {
                    Toast.makeText(LoginActivity.this, "Email Harus Diisi", Toast.LENGTH_SHORT).show();
                }else if(var_password.equals("")){
                    Toast.makeText(LoginActivity.this, "Password Harus Diisi", Toast.LENGTH_SHORT).show();
                }else {
                    //Encrypt Text
                    String password_encrypted = PasswordEncrypt.md5(var_password);
                    //Go To Method Auth
                    auth(var_email, password_encrypted);
                }
            }
        });

        redirect_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterOptionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void auth(String email, String password){
        id = "";
        db.collection("users")
                .whereEqualTo("email",email)
                .whereEqualTo("password",password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                id = document.getId();
                                Log.d("Info",id + "=>" + document.getData());
                            }
                            if(id.equals("")){
                                Log.d("Info Login", "Data Tidak Ditemukan");
                                Toast.makeText(LoginActivity.this, "Email/Password Salah", Toast.LENGTH_SHORT).show();
                            }else{
                                Log.d("Info Login", "Data Ditemukan");
                                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Log.w("Error","Error Getting Documents", task.getException());
                        }
                    }
                });


    }
}
