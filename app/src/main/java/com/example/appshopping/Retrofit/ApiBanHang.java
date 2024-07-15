package com.example.appshopping.Retrofit;

import com.example.appshopping.model.loaispModel;
import com.example.appshopping.model.sanphammoiModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiBanHang {
    @GET("getloaisp.php")
    Observable<loaispModel> getloaisp();

    @GET("getspmoi.php")
    Observable<sanphammoiModel> getspmoi();
}
