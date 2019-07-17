package com.kim344.utils.retrofit2;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.kim344.utils.retrofit2.Constant.URL_HOST;

public class RetrofitInit {

    private static RetrofitInit instance;

    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_HOST + "/")
            .client(client()).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build();

    public Service service = retrofit.create(Service.class);

    private OkHttpClient client() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor()).build();
        return okHttpClient;
    }

    private HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("Retrofit :", message + "");
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public RetrofitInit getInstance() {
        if (instance != null) { }
        else instance = new RetrofitInit();
        return instance;
    }

    public Service getService() {
        if (service != null) { }
        else service = retrofit.create(Service.class);
        return service;
    }
}
