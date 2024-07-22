package com.example.appshopping.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appshopping.R;
import com.example.appshopping.Retrofit.ApiBanHang;
import com.example.appshopping.Retrofit.RetrofitClient;
import com.example.appshopping.adapter.dienthoaiAdapter;
import com.example.appshopping.model.sanphammoi;
import com.example.appshopping.utils.untils;
import com.google.gson.annotations.Until;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class dienthoaiActivity extends AppCompatActivity {
Toolbar toolbar;
RecyclerView recyclerView;
CompositeDisposable compositeDisposable= new CompositeDisposable();
ApiBanHang apiBanHang;
int page=1;
int loai;
    dienthoaiAdapter dienthoaiAdapter;
    List<sanphammoi>sanphammoiList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dienthoai);
        apiBanHang = RetrofitClient.getInstance(untils.BASE_URL).create(ApiBanHang.class);
loai = getIntent().getIntExtra("loai",1);
        Anhxa();
        ActionToolBar();
getData();
    }

    private void getData() {
        compositeDisposable.add(apiBanHang.getSanPham(page,loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanphammoiModel -> {
if (sanphammoiModel.isSuccess()){
    sanphammoiList = sanphammoiModel.getResult();
    dienthoaiAdapter = new dienthoaiAdapter(getApplicationContext(), sanphammoiList);
    recyclerView.setAdapter(dienthoaiAdapter);
}
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"k kn server", Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toobar);
        recyclerView = findViewById(R.id.recycleview_dt);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        sanphammoiList = new ArrayList<>();
    }
}