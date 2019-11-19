package com.erait.mas_jaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterOptionActivity extends AppCompatActivity {
    private Button option_masyarakat_umum, option_ukm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_option);
        getSupportActionBar().hide();
        option_masyarakat_umum = findViewById(R.id.option_masyarakat_umum);
        option_ukm = findViewById(R.id.option_ukm);

        option_masyarakat_umum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterOptionActivity.this, RegisterMasyarakat.class);
                startActivity(intent);
            }
        });
        option_ukm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterOptionActivity.this, RegisterUKM.class);
                startActivity(intent);
            }
        });

    }
}
