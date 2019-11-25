package com.erait.mas_jaka.Fragment;

import android.content.Intent;
import android.os.Bundle;
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

import com.erait.mas_jaka.Adapter.LapakUKMGridAdapter;
import com.erait.mas_jaka.EventCampaignActivity;
import com.erait.mas_jaka.Model.LapakUkmModel;
import com.erait.mas_jaka.PeringkatActivity;
import com.erait.mas_jaka.R;

import java.util.ArrayList;

public class FragmentDashboardMasyarakat extends Fragment {
    LinearLayout main_option_peringkat_ms, main_option_bayar_bpjs_ms, main_option_lapak_ms;
    TextView dashboard_masyarakat_more_event;
    RecyclerView rv_ukm;
    ArrayList<LapakUkmModel> lapakUkmModelArrayList = new ArrayList<>();
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


        //Ketika Option Peringkat Di-Klik
        main_option_peringkat_ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PeringkatActivity.class);
                startActivity(intent);
            }
        });
        //Ketika Option BPJS Di-Klik
        //Ketika Option Lapak Di-Klik
        //More Event
        dashboard_masyarakat_more_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), EventCampaignActivity.class);
                startActivity(intent);
            }
        });

        LapakUkmModel model1 = new LapakUkmModel("UKM 1");
        LapakUkmModel model2 = new LapakUkmModel("UKM 1");
        LapakUkmModel model3 = new LapakUkmModel("UKM 1");
        LapakUkmModel model4 = new LapakUkmModel("UKM 1");
        LapakUkmModel model5 = new LapakUkmModel("UKM 1");
        LapakUkmModel model6 = new LapakUkmModel("UKM 1");

        lapakUkmModelArrayList.add(model1);
        lapakUkmModelArrayList.add(model2);
        lapakUkmModelArrayList.add(model3);
        lapakUkmModelArrayList.add(model4);
        lapakUkmModelArrayList.add(model5);
        lapakUkmModelArrayList.add(model6);
        LapakUKMGridAdapter adapter = new LapakUKMGridAdapter(getActivity().getApplicationContext(),lapakUkmModelArrayList);
        rv_ukm.setAdapter(adapter);



    }
}
