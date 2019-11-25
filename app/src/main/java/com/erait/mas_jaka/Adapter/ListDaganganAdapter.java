package com.erait.mas_jaka.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erait.mas_jaka.Model.DaganganModel;
import com.erait.mas_jaka.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListDaganganAdapter extends RecyclerView.Adapter<ListDaganganAdapter.ListDaganganViewHolder>{
    private Context context;
    private ArrayList<DaganganModel> daganganModelArrayList;

    public ListDaganganAdapter(Context context, ArrayList<DaganganModel> daganganModelArrayList) {
        this.context = context;
        this.daganganModelArrayList = daganganModelArrayList;
    }

    @NonNull
    @Override
    public ListDaganganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_content_dagangan,parent,false);
        return new ListDaganganViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDaganganViewHolder holder, int position) {
        DaganganModel model = daganganModelArrayList.get(position);

        Picasso.get().load(model.getDagangan_image()).into(holder.rv_gambar_dagangan);
        holder.rv_nama_dagangan.setText(model.getNama_dagangan());
        holder.rv_stok_dagangan.setText("Stok :"+model.getStok());

    }

    @Override
    public int getItemCount() {
        return daganganModelArrayList.size();
    }

    public class ListDaganganViewHolder extends RecyclerView.ViewHolder{
        ImageView rv_gambar_dagangan;
        TextView rv_nama_dagangan, rv_harga_dagangan, rv_stok_dagangan;
        public ListDaganganViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_gambar_dagangan = itemView.findViewById(R.id.rv_gambar_dagangan);
            rv_nama_dagangan = itemView.findViewById(R.id.rv_nama_dagangan);
            rv_harga_dagangan = itemView.findViewById(R.id.rv_harga_dagangan);
            rv_stok_dagangan = itemView.findViewById(R.id.rv_stok_dagangan);
        }
    }
}
