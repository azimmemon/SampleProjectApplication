package com.example.sample.sampleprojectapplication.Retrofit;

import com.example.sample.sampleprojectapplication.Utils.Constants;
import com.example.sample.sampleprojectapplication.api.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by azimmemon on 03/07/19.
 */

public class RetrofitClient {

    private static RetrofitClient mRetrofitClient = null;
    private ApiInterface mApiInterface;


    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiInterface = retrofit.create(ApiInterface.class);
    }


    public static RetrofitClient getRetrofitClient(){
        if (mRetrofitClient == null){
            mRetrofitClient = new RetrofitClient();
        }

        return mRetrofitClient;
    }

    public ApiInterface getApiInterface(){
        return mApiInterface;
    }
}
