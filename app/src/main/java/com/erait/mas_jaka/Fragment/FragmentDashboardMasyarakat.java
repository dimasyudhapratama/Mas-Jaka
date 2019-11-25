package com.erait.mas_jaka.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erait.mas_jaka.Adapter.EventAdapter;
import com.erait.mas_jaka.Adapter.KerajinanPopulerAdapter;
import com.erait.mas_jaka.Adapter.LapakUKMGridAdapter;
import com.erait.mas_jaka.Adapter.ListDaganganAdapter;
import com.erait.mas_jaka.EventCampaignActivity;
import com.erait.mas_jaka.ListDaganganActivity;
import com.erait.mas_jaka.Model.DaganganModel;
import com.erait.mas_jaka.Model.EventModel;
import com.erait.mas_jaka.Model.LapakUkmModel;
import com.erait.mas_jaka.PembayaranBPJSActivity;
import com.erait.mas_jaka.PeringkatActivity;
import com.erait.mas_jaka.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FragmentDashboardMasyarakat extends Fragment {
    LinearLayout main_option_peringkat_ms, main_option_bayar_bpjs_ms, main_option_lapak_ms;
    TextView dashboard_masyarakat_more_event;
    RecyclerView rv_ukm, rv_kerajinan_masyarakat;
    ArrayList<LapakUkmModel> lapakUkmModelArrayList = new ArrayList<>();
    ArrayList<DaganganModel> daganganModelArrayList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard_masyarakat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        main_option_peringkat_ms = view.findViewById(R.id.main_option_peringkat_ms);
        main_option_bayar_bpjs_ms = view.findViewById(R.id.main_option_bayar_bpjs_ms);
        main_option_lapak_ms = view.findViewById(R.id.main_option_lapak_ms);
        dashboard_masyarakat_more_event = view.findViewById(R.id.dashboard_masyarakat_more_event);
        rv_ukm = view.findViewById(R.id.rv_ukm);
        rv_kerajinan_masyarakat = view.findViewById(R.id.rv_kerajinan_masyarakat);
        mContext = getActivity().getApplicationContext();


        //Ketika Option Peringkat Di-Klik
        main_option_peringkat_ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PeringkatActivity.class);
                startActivity(intent);
            }
        });
        //Ketika Option BPJS Di-Klik
        main_option_bayar_bpjs_ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PembayaranBPJSActivity.class);
                startActivity(intent);
            }
        });
        //Ketika Option Lapak Di-Klik
        //More Event
        dashboard_masyarakat_more_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), EventCampaignActivity.class);
                startActivity(intent);
            }
        });
        //Get Kerajinan Populer
        getKerajinanPopuler();
        //Get Data UKM
        getDataUKM();


    }
    private void getKerajinanPopuler(){
        db.collection("merchandise")
                .limit(5)
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
                            KerajinanPopulerAdapter adapter = new KerajinanPopulerAdapter(mContext,daganganModelArrayList);
                            rv_kerajinan_masyarakat.setAdapter(adapter);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
                            rv_kerajinan_masyarakat.setLayoutManager(mLayoutManager);


                        }else{
                            Log.w("Error","Error Getting Documents", task.getException());
                        }
                    }
                });
    }

    void getDataUKM(){
        db.collection("users")
                .whereEqualTo("userType","ukm")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                LapakUkmModel model = new LapakUkmModel();
                                model.setId_user_ukm(document.getId());
                                model.setNama_ukm(document.getData().get("nama_ukm").toString());
                                model.setImage(document.getData().get("image").toString());
                                lapakUkmModelArrayList.add(model);

                                Log.d("Info",document.getId() + "=>" + document.getData());
                            }
                            LapakUKMGridAdapter adapter = new LapakUKMGridAdapter(mContext,lapakUkmModelArrayList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
                            rv_ukm.setLayoutManager(mLayoutManager);
                            rv_ukm.setAdapter(adapter);

                        }else{
                            Log.w("Error","Error Getting Documents", task.getException());
                        }
                    }
                });
    }
}
