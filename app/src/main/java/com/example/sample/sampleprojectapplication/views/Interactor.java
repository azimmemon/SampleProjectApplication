package com.example.sample.sampleprojectapplication.views;

import android.content.Context;

import com.example.sample.sampleprojectapplication.Retrofit.RetrofitClient;
import com.example.sample.sampleprojectapplication.api.ApiInterface;
import com.example.sample.sampleprojectapplication.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by azimmemon on 03/07/19.
 */

public class Interactor implements IInteractor {

    DatabaseOpenHelper databaseOpenHelper;

    public Interactor(IUsersView view){
        databaseOpenHelper = new DatabaseOpenHelper((Context) view);
    }

    @Override
    public void getUsersList(final OnFinishedListener listener) {

        if (databaseOpenHelper.getUsersData().size() > 0){
            listener.onFinished(databaseOpenHelper.getUsersData());

        }else {

            ApiInterface apiInterface = RetrofitClient.getRetrofitClient().getApiInterface();
            Call<List<UserModel>> usersModel = apiInterface.getUsersList("10");
            usersModel.enqueue(new Callback<List<UserModel>>() {
                @Override
                public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                    listener.onFinished(response.body());
                    databaseOpenHelper.insertUsersData(response.body());

                }

                @Override
                public void onFailure(Call<List<UserModel>> call, Throwable t) {
                    listener.onFailure(t);

                }
            });

        }
    }


}
