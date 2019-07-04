package com.example.sample.sampleprojectapplication.views;

import com.example.sample.sampleprojectapplication.model.UserModel;

import java.util.List;

/**
 * Created by azimmemon on 02/07/19.
 */

public interface IUsersView {

    void onResponseSuccess(List<UserModel> usersList);

    void onResponseFailure(String message);

    void showProgress();

    void hideProgress();

}
