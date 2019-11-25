package com.erait.mas_jaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.erait.mas_jaka.Adapter.EventAdapter;
import com.erait.mas_jaka.Adapter.ListDaganganAdapter;
import com.erait.mas_jaka.Model.DaganganModel;
import com.erait.mas_jaka.Model.EventModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListDaganganActivity extends AppCompatActivity {
    RecyclerView rv_list_dagangan;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<DaganganModel> daganganModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dagangan);
        //Pengaturan Action Bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.mas_jaka_logo_kecil);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        rv_list_dagangan = findViewById(R.id.rv_list_dagangan);
        getListDagangan();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_dagangan,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.list_dagangan_add){
            Toast.makeText(this, "Add Dagangan Coming Soon", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void getListDagangan(){
        db.collection("merchandise")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                DaganganModel model = new DaganganModel();
                                model.setId_dagangan(document.getId());
                                model.setNama_dagangan(document.getData().get("nama_dagangan").toString());
                                model.setHarga(document.getData().get("harga").toString());
                                model.setStok(document.getData().get("stok").toString());
                                model.setDagangan_image(document.getData().get("dagangan_image").toString());
                                model.setBagi_hasil(document.getData().get("bagi_hasil").toString());
                                model.setBagi_hasil(document.getData().get("est_point").toString());
                                daganganModelArrayList.add(model);
                                Log.d("Info",document.getId() + "=>" + document.getData());
                            }
                            ListDaganganAdapter adapter = new ListDaganganAdapter(ListDaganganActivity.this,daganganModelArrayList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
                            rv_list_dagangan.setLayoutManager(mLayoutManager);
                            rv_list_dagangan.setAdapter(adapter);

                        }else{
                            Log.w("Error","Error Getting Documents", task.getException());
                        }
                    }
                });
    }
}
