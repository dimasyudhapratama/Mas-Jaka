package com.erait.mas_jaka.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erait.mas_jaka.Model.LapakUkmModel;
import com.erait.mas_jaka.R;

import java.util.ArrayList;

public class LapakUKMGridAdapter extends RecyclerView.Adapter<LapakUKMGridAdapter.GridUKMViewHolder>{
    private Context context;
    private ArrayList<LapakUkmModel> lapakUkmModelArrayList;

    public LapakUKMGridAdapter(Context context, ArrayList<LapakUkmModel> lapakUkmModelArrayList) {
        this.context = context;
        this.lapakUkmModelArrayList = lapakUkmModelArrayList;
    }

    @NonNull
    @Override
    public GridUKMViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_lapak_ukm,parent,false);
        return new GridUKMViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridUKMViewHolder holder, int position) {
        LapakUkmModel lapak = lapakUkmModelArrayList.get(position);
//        holder.grid_nama_ukm.setText(lapak.getNama_ukm());
    }

    @Override
    public int getItemCount() {
        return lapakUkmModelArrayList.size();
    }

    public class GridUKMViewHolder extends RecyclerView.ViewHolder{
        ImageView grid_logo_ukm;
        TextView grid_nama_ukm;
        public GridUKMViewHolder(@NonNull View itemView) {
            super(itemView);
            grid_logo_ukm = itemView.findViewById(R.id.grid_logo_ukm);
            grid_nama_ukm = itemView.findViewById(R.id.grid_nama_ukm);
        }
    }
}
