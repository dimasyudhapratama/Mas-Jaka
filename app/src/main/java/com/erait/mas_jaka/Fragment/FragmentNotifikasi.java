package com.erait.mas_jaka.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erait.mas_jaka.Adapter.LapakUKMGridAdapter;
import com.erait.mas_jaka.Adapter.NotifikasiAdapter;
import com.erait.mas_jaka.Model.LapakUkmModel;
import com.erait.mas_jaka.Model.NotifikasiModel;
import com.erait.mas_jaka.R;

import java.util.ArrayList;

public class FragmentNotifikasi extends Fragment {
    RecyclerView rv_notifikasi;
    ArrayList<NotifikasiModel> notifikasiModelArrayList = new ArrayList<>();
    ArrayList<LapakUkmModel> lapakUkmModelArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifikasi,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_notifikasi = view.findViewById(R.id.rv_notifikasi);

        NotifikasiModel model1 = new NotifikasiModel("Point Masuk","10-08-2019","11.30",30);
        NotifikasiModel model2 = new NotifikasiModel("Pembelian","09-08-2019","10:08",100);
        NotifikasiModel model3 = new NotifikasiModel("Pembayaran BPJS","10-08-2019","11.30",30);
        NotifikasiModel model4 = new NotifikasiModel("Pembelian","09-08-2019","10:08",100);
        NotifikasiModel model5 = new NotifikasiModel("Pembayaran BPJS","10-08-2019","11.30",30);
        NotifikasiModel model6 = new NotifikasiModel("Pembelian","09-08-2019","10:08",100);
        NotifikasiModel model7 = new NotifikasiModel("Pembayaran BPJS","10-08-2019","11.30",30);
        NotifikasiModel model8 = new NotifikasiModel("Pembelian","09-08-2019","10:08",100);
        NotifikasiModel model9 = new NotifikasiModel("Pembelian","09-08-2019","10:08",100);
        NotifikasiModel model10 = new NotifikasiModel("Pembelian","09-08-2019","10:08",100);

        notifikasiModelArrayList.add(model1);
        notifikasiModelArrayList.add(model2);
        notifikasiModelArrayList.add(model3);
        notifikasiModelArrayList.add(model4);
        notifikasiModelArrayList.add(model5);
        notifikasiModelArrayList.add(model6);
        notifikasiModelArrayList.add(model7);
        notifikasiModelArrayList.add(model8);
        notifikasiModelArrayList.add(model9);
        notifikasiModelArrayList.add(model10);
        NotifikasiAdapter adapter = new NotifikasiAdapter(getActivity().getApplicationContext(),notifikasiModelArrayList);
        rv_notifikasi.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rv_notifikasi.setAdapter(adapter);

    }
}
