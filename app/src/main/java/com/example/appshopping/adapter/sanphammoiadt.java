package com.example.appshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appshopping.R;
import com.example.appshopping.model.sanphammoi;

import java.util.List;

public class sanphammoiadt extends RecyclerView.Adapter<sanphammoiadt.MyViewHolder> {
    Context context;
    List<sanphammoi> array;

    public sanphammoiadt(Context context, List<sanphammoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp_moi, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        sanphammoi sanphammoi = array.get(position);
        holder.txtten.setText(sanphammoi.getTensp());
        holder.txtgia.setText(sanphammoi.getGiasp());
        Glide.with(context).load(sanphammoi.getHinhanh()).into(holder.imghinhanh);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtgia, txtten;
        ImageView imghinhanh;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.itemsp_ten);
            txtgia = itemView.findViewById(R.id.itemsp_gia);
            imghinhanh = itemView.findViewById(R.id.itemsp_image);
        }
    }
}
