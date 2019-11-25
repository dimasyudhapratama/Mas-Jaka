package com.erait.mas_jaka.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erait.mas_jaka.DetailEventActivity;
import com.erait.mas_jaka.Model.EventModel;
import com.erait.mas_jaka.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{
    private Context context;
    private ArrayList<EventModel> eventModelArrayList;

    public EventAdapter(Context context, ArrayList<EventModel> eventModelArrayList) {
        this.context = context;
        this.eventModelArrayList = eventModelArrayList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_content_event,parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel model = eventModelArrayList.get(position);
        Picasso.get().load(model.getEvent_image()).into(holder.rv_event_image);
        holder.rv_event_name.setText(model.getEvent_name());
        holder.rv_event_tanggal.setText(model.getEvent_date());

        holder.rv_btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailEventActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventModelArrayList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{
        ImageView rv_event_image ;
        TextView rv_event_name, rv_event_tanggal;
        TextView rv_btn_detail;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_event_image = itemView.findViewById(R.id.rv_event_image);
            rv_event_name = itemView.findViewById(R.id.rv_event_name);
            rv_event_tanggal = itemView.findViewById(R.id.rv_event_tanggal);
            rv_btn_detail = itemView.findViewById(R.id.rv_btn_detail);
        }
    }
}
