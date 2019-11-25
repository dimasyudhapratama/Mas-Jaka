package com.erait.mas_jaka;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.erait.mas_jaka.Fragment.FragmentAkun;
import com.erait.mas_jaka.Fragment.FragmentDashboardMasyarakat;
import com.erait.mas_jaka.Fragment.FragmentDashboardUKM;
import com.erait.mas_jaka.Fragment.FragmentNotifikasi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Pengaturan Action Bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.mas_jaka_logo_kecil);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);

//        getFragmentPage(new FragmentDashboardMasyarakat());
        getFragmentPage(new FragmentDashboardUKM());

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.navigation_home){
                    getFragmentPage(new FragmentDashboardMasyarakat());
                }else if(menuItem.getItemId() == R.id.navigation_notifications) {
                    getFragmentPage(new FragmentNotifikasi());
                }else  if(menuItem.getItemId() == R.id.navigation_account){
                    getFragmentPage(new FragmentAkun());
                }
                return true;
            }
        });
    }

    //Menampilkan halaman Fragment
    private void getFragmentPage(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.page_container,fragment);
        ft.commit();
    }

}
