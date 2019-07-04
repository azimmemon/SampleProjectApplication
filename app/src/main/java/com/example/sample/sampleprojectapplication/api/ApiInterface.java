package com.example.sample.sampleprojectapplication.api;

import com.example.sample.sampleprojectapplication.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by azimmemon on 02/07/19.
 */

public interface ApiInterface {

    @GET("/users")
    Call<List<UserModel>> getUsersList(@Query("since") String params);
}
