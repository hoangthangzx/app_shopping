package com.example.appshopping.adapter;

import android.content.Context;
import android.text.style.IconMarginSpan;
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

import java.text.DecimalFormat;
import java.util.List;

public class dienthoaiAdapter extends RecyclerView.Adapter<dienthoaiAdapter.MyViewHolder> {
    Context context;
    List<sanphammoi> array;

    public dienthoaiAdapter(Context context, List<sanphammoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dienthoai, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
sanphammoi sanphammoi = array.get(position);
        holder.tensp.setText(sanphammoi.getTensp());
        holder.mota.setText(sanphammoi.getMota());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá: "+decimalFormat.format(Double.parseDouble(sanphammoi.getGiasp()))+ "vnd");
        Glide.with(context).load(sanphammoi.getHinhanh()).into(holder.hinhanh);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tensp, giasp, mota;
        ImageView hinhanh;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tensp= itemView.findViewById(R.id.itemdt_ten);
            giasp= itemView.findViewById(R.id.itemdt_gia);
            mota= itemView.findViewById(R.id.itemdt_mota);
            hinhanh = itemView.findViewById(R.id.itemdt_image);
        }
    }
}
