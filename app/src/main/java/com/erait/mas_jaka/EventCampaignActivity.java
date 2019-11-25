package com.erait.mas_jaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.erait.mas_jaka.Adapter.EventAdapter;
import com.erait.mas_jaka.Model.EventModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EventCampaignActivity extends AppCompatActivity {
    RecyclerView rv_event_campaign;
    ArrayList<EventModel> eventModelArrayList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EventAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_campaign);

        rv_event_campaign = findViewById(R.id.rv_event_campaign);

        getEventData();
    }

    private void getEventData(){
        db.collection("events")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                EventModel eventModel = new EventModel();
                                eventModel.setEvent_id(document.getId());
                                eventModel.setEvent_name(document.getData().get("nama_event").toString());
                                eventModel.setEvent_desc(document.getData().get("deskripsi").toString());
                                eventModel.setEvent_image(document.getData().get("event_image").toString());
                                eventModel.setEvent_date(document.getData().get("tanggal").toString());
                                eventModelArrayList.add(eventModel);
                                Log.d("Info",document.getId() + "=>" + document.getData().get("first_name"));
                            }
                            adapter = new EventAdapter(EventCampaignActivity.this,eventModelArrayList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
                            rv_event_campaign.setLayoutManager(mLayoutManager);
                            rv_event_campaign.setAdapter(adapter);

                        }else{
                            Log.w("Error","Error Getting Documents", task.getException());
                        }
                    }
                });
    }
}
