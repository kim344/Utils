package com.kim344.utils.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<Model> getAnswers();

}
