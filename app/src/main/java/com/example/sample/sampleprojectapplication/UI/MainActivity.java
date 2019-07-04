package com.example.sample.sampleprojectapplication.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sample.sampleprojectapplication.R;
import com.example.sample.sampleprojectapplication.adapters.UsersListAdapter;
import com.example.sample.sampleprojectapplication.model.UserModel;
import com.example.sample.sampleprojectapplication.views.IUsersView;
import com.example.sample.sampleprojectapplication.presenter.UsersPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IUsersView, TextWatcher{


    @BindView(R.id.search_edit_text)
    EditText mSearchEt;

    @BindView(R.id.users_list)
    RecyclerView mUsersList;

    @BindView(R.id.circular_progress_bar)
    ProgressBar mProgressBar;

    private UsersListAdapter mAdapter;
    private UsersPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mSearchEt.addTextChangedListener(this);
        //call presenter to get users list:
        mPresenter = new UsersPresenter(this);
        mPresenter.requestDataFromServer();


    }

    // response success
    @Override
    public void onResponseSuccess(List<UserModel> usersList) {
        mAdapter = new UsersListAdapter(MainActivity.this, usersList);
        mUsersList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mUsersList.setAdapter(mAdapter);
    }

    // response failure
    @Override
    public void onResponseFailure(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mAdapter.filterData(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
