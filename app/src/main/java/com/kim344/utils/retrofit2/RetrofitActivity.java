package com.kim344.utils.retrofit2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kim344.utils.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    RetrofitInit retrofit;
    Service service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        setRetrofit();

        service.getAnswers().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.e("동주",response.body().getQuota_max()+"");
                Log.e("동주",response.body().getQuota_remaining()+"");
                Log.e("동주",response.body().isHas_more()+"");
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });
    }

    public void setRetrofit() {
        retrofit = new RetrofitInit().getInstance();
        service = retrofit.getService();
    }
}
