package com.example.sample.sampleprojectapplication.views;

import com.example.sample.sampleprojectapplication.model.UserModel;

import java.util.List;

/**
 * Created by azimmemon on 03/07/19.
 */

public interface IInteractor {

    interface OnFinishedListener {
        void onFinished(List<UserModel> movieArrayList);

        void onFailure(Throwable t);
    }

    void getUsersList(OnFinishedListener onFinishedListener);

}
