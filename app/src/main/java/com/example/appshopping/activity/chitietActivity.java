package com.example.appshopping.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.appshopping.R;
import com.example.appshopping.model.sanphammoi;
import com.example.appshopping.model.giohang;
import com.example.appshopping.utils.untils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

public class chitietActivity extends AppCompatActivity {
    TextView tensp, giasp, mota;
    Button btnthem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;
    NotificationBadge badge;

    // Use class-level sanphammoi variable
    sanphammoi sanphammoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);

        // Initialize views
        initView();

        // Set up toolbar
        ActionToolBar();

        // Populate data
        initData();
        initControl();
    }

    private void initControl() {
        btnthem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                themgiohang();
            }

            private void themgiohang() {
                if (sanphammoi == null) {
                    // Log or handle the error appropriately
                    return;
                }

                if(untils.manggiohang.size()>0){
                    boolean flag=false;
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    for(int i=0;i<untils.manggiohang.size();i++){
                        if(untils.manggiohang.get(i).getIdsp()==sanphammoi.getId()){
                            untils.manggiohang.get(i).setSoluong(soluong+untils.manggiohang.get(i).getSoluong());
                            long gia =Long.parseLong(sanphammoi.getGiasp())*untils.manggiohang.get(i).getSoluong();
                            untils.manggiohang.get(i).setGiasp(gia);
                            flag=true;
                        }
                    }
                    if(!flag){
                        long gia=Long.parseLong(sanphammoi.getGiasp())*soluong;
                        giohang giohang=new giohang();
                        giohang.setGiasp(gia);
                        giohang.setSoluong(soluong);
                        giohang.setIdsp(sanphammoi.getId());
                        giohang.setTensp(sanphammoi.getTensp());
                        giohang.setHinhsp(sanphammoi.getHinhanh());
                        untils.manggiohang.add(giohang);
                    }
                } else {
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    long gia=Long.parseLong(sanphammoi.getGiasp())*soluong;
                    giohang giohang=new giohang();
                    giohang.setGiasp(gia);
                    giohang.setSoluong(soluong);
                    giohang.setIdsp(sanphammoi.getId());
                    giohang.setTensp(sanphammoi.getTensp());
                    giohang.setHinhsp(sanphammoi.getHinhanh());
                    untils.manggiohang.add(giohang);
                }
                badge.setText(String.valueOf(untils.manggiohang.size()));
            }
        });
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        // Correctly retrieve the sanphammoi object from the Intent
        sanphammoi = (sanphammoi) getIntent().getSerializableExtra("chitiet");

        if (sanphammoi != null) {
            tensp.setText(sanphammoi.getTensp());
            mota.setText(sanphammoi.getMota());
            Glide.with(getApplicationContext()).load(sanphammoi.getHinhanh()).into(imghinhanh);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            giasp.setText("Giá: " + decimalFormat.format(Double.parseDouble(sanphammoi.getGiasp())) + "vnd");
            Integer[] so = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, so);
            spinner.setAdapter(adapterspin);
        } else {
            // Handle the error: sanphammoi is null
        }
    }

    private void initView() {
        tensp = findViewById(R.id.txtten);
        giasp = findViewById(R.id.txtgiasp);
        mota = findViewById(R.id.txtmota);
        btnthem = findViewById(R.id.btnthemvaogiohang);
        spinner = findViewById(R.id.spinner);
        imghinhanh = findViewById(R.id.imgchitiet);
        toolbar = findViewById(R.id.toobar);
        badge = findViewById(R.id.menu_sl);
        if(untils.manggiohang!=null){
            badge.setText(String.valueOf(untils.manggiohang.size()));
        }
    }
}
