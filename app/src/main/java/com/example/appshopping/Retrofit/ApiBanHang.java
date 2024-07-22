package com.example.appshopping.Retrofit;

import com.example.appshopping.model.loaispModel;
import com.example.appshopping.model.sanphammoiModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBanHang {
    @GET("getloaisp.php")
    Observable<loaispModel> getloaisp();

    @GET("getspmoi.php")
    Observable<sanphammoiModel> getspmoi();
    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<sanphammoiModel> getSanPham(
            @Field("page") int page,
            @Field("loai") int loai
    );
}
