package com.example.appshopping.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appshopping.R;
import com.example.appshopping.Retrofit.ApiBanHang;
import com.example.appshopping.Retrofit.RetrofitClient;
import com.example.appshopping.adapter.loaispAdapter;
import com.example.appshopping.adapter.sanphammoiadt;
import com.example.appshopping.model.loaisp;
import com.example.appshopping.model.sanphammoi;
import com.example.appshopping.model.sanphammoiModel;
import com.example.appshopping.utils.untils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.internal.Util;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
Toolbar Toolbarmhc;
    ViewFlipper ViewFlippermhc;
    NavigationView NavigationViewmhc;
    ListView ListViewmhc;
    RecyclerView recycleviewmhc;
    DrawerLayout DrawerLayoutmhc;
    loaispAdapter loaispAdapter;
    List<loaisp> mangloaisp;
CompositeDisposable compositeDisposable=new CompositeDisposable();
ApiBanHang apiBanHang;
List<sanphammoi> mangspmoi;
sanphammoiadt sanphammoiadt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanHang= RetrofitClient.getInstance(untils.BASE_URL).create(ApiBanHang.class);
        anhxa();
        Actionbar();
        ActionViewFlippe();
        getloaisp();
        getspmoi();
        if(isconected(this)){
            Toast.makeText(getApplicationContext(),"kn",Toast.LENGTH_LONG).show();
        }else {
Toast.makeText(getApplicationContext(),"no iternet",Toast.LENGTH_LONG).show();
        }
    }

    private void getspmoi() {
        compositeDisposable.add(apiBanHang.getspmoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanphammoiModel -> {
                            if (sanphammoiModel.isSuccess()){
                                mangspmoi = sanphammoiModel.getResult();
                                // Log mảng sản phẩm mới
                                Log.d("MainActivity", "Mảng sản phẩm mới: " + mangspmoi.toString());

                                // Tiếp tục xử lý khác nếu cần
                                sanphammoiadt = new sanphammoiadt(getApplicationContext(),mangspmoi);
                                recycleviewmhc.setAdapter(sanphammoiadt);
                            }
                        },
                        throwable -> {
                            // Log lỗi nếu có
                            Log.d("MainActivity", "Error fetching new products", throwable);
                        }
                ));
    }

    private void getloaisp() {
        compositeDisposable.add(apiBanHang.getloaisp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaispModel -> {
                            if (loaispModel.isSuccess()){
                              mangloaisp=loaispModel.getResult();
                                //khoitaoadapter

                                loaispAdapter = new loaispAdapter(getApplicationContext(),mangloaisp);
                                ListViewmhc.setAdapter(loaispAdapter);
                            }
                        }
                ));
    }

    private void ActionViewFlippe(){
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
for (int i = 0; i < mangquangcao.size();i++){
    ImageView imageView = new ImageView(getApplicationContext());
    Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    ViewFlippermhc.addView(imageView);

}
ViewFlippermhc.setFlipInterval(3000);
        ViewFlippermhc.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        ViewFlippermhc.setInAnimation(slide_in);
        ViewFlippermhc.setInAnimation(slide_out);
    }
    private void Actionbar(){
       setSupportActionBar(Toolbarmhc);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       Toolbarmhc.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
       Toolbarmhc.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DrawerLayoutmhc.openDrawer((GravityCompat.START));
           }
       });
    }
    private void anhxa(){
        Toolbarmhc = findViewById(R.id.Toolbarmhc);
        ViewFlippermhc = findViewById(R.id.ViewFlippermhc);
        ListViewmhc = findViewById(R.id.ListViewmhc);
        NavigationViewmhc = findViewById(R.id.NavigationViewmhc);
        recycleviewmhc = findViewById(R.id.recycleviewmhc);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        recycleviewmhc.setLayoutManager(layoutManager);
        recycleviewmhc.setHasFixedSize(true);
        DrawerLayoutmhc = findViewById((R.id.DrawerLayoutmhc));
        mangloaisp = new ArrayList<>();
        mangspmoi = new ArrayList<>();
    }
    private boolean isconected (Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService((Context.CONNECTIVITY_SERVICE));
        NetworkInfo WIFI =connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((WIFI != null && WIFI.isConnected()) || (mobile != null && mobile.isConnected())){
    return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}