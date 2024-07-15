package com.example.appshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appshopping.R;
import com.example.appshopping.model.loaisp;

import java.util.List;

public class loaispAdapter extends BaseAdapter {
    List<loaisp> array;
    Context context;

    public loaispAdapter(Context context, List<loaisp> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position); // Return the actual item
    }

    @Override
    public long getItemId(int position) {
        return position; // Return the actual item ID
    }

    public static class ViewHolder {
        TextView textensp;
        ImageView imghinhanh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_sanpham, null);
            viewHolder.textensp = view.findViewById(R.id.item_tensp);
            viewHolder.imghinhanh = view.findViewById(R.id.item_image);
            view.setTag(viewHolder); // Set the tag here
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        loaisp item = array.get(i);
        viewHolder.textensp.setText(item.getTensanpham());
        Glide.with(context).load(item.getHinhanh()).into(viewHolder.imghinhanh);

        return view;
    }
}
