package com.erait.mas_jaka.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.erait.mas_jaka.Model.DaganganModel;
import com.erait.mas_jaka.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KerajinanPopulerAdapter extends RecyclerView.Adapter<KerajinanPopulerAdapter.ListDaganganViewHolder>{
    private Context context;
    private ArrayList<DaganganModel> daganganModelArrayList;

    public KerajinanPopulerAdapter(Context context, ArrayList<DaganganModel> daganganModelArrayList) {
        this.context = context;
        this.daganganModelArrayList = daganganModelArrayList;
    }

    @NonNull
    @Override
    public ListDaganganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_kerajinan,parent,false);
        return new ListDaganganViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDaganganViewHolder holder, int position) {
        DaganganModel model = daganganModelArrayList.get(position);

        Picasso.get().load(model.getDagangan_image()).into(holder.grid_kerajinan_gambar);
        holder.grid_kerajinan_nama.setText(model.getNama_dagangan());

        holder.cardview_kerajinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, )
            }
        });

    }

    @Override
    public int getItemCount() {
        return daganganModelArrayList.size();
    }

    public class ListDaganganViewHolder extends RecyclerView.ViewHolder{
        CardView cardview_kerajinan;
        ImageView grid_kerajinan_gambar;
        TextView grid_kerajinan_nama;
        public ListDaganganViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview_kerajinan = itemView.findViewById(R.id.cardview_kerajinan);
            grid_kerajinan_gambar = itemView.findViewById(R.id.grid_kerajinan_gambar);
            grid_kerajinan_nama = itemView.findViewById(R.id.grid_kerajinan_nama);
        }
    }
}
