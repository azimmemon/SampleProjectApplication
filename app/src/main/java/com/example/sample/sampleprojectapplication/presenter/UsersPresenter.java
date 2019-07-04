package com.example.sample.sampleprojectapplication.presenter;

import com.example.sample.sampleprojectapplication.model.UserModel;
import com.example.sample.sampleprojectapplication.views.IInteractor;
import com.example.sample.sampleprojectapplication.views.IUsersView;
import com.example.sample.sampleprojectapplication.views.Interactor;

import java.util.List;

/**
 * Created by azimmemon on 02/07/19.
 */

public class UsersPresenter implements IPresenter, IInteractor.OnFinishedListener {

    private IUsersView iUsersView;
    private Interactor interactor;

    public UsersPresenter(IUsersView iUsersView){
        this.iUsersView = iUsersView;
        interactor = new Interactor(iUsersView);
    }

    @Override
    public void onFinished(List<UserModel> movieArrayList) {
        iUsersView.onResponseSuccess(movieArrayList);
        if (iUsersView!= null){
            iUsersView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        iUsersView.onResponseFailure(t.getMessage());
        if (iUsersView!= null){
            iUsersView.hideProgress();
        }

    }

    @Override
    public void requestDataFromServer() {
        if (iUsersView != null) {
            iUsersView.showProgress();
        }
        interactor.getUsersList(this);
    }

    @Override
    public void onDestroy() {
        iUsersView = null;
    }

}
