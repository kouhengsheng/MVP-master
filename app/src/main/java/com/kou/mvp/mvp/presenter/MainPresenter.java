package com.kou.mvp.mvp.presenter;


import com.kou.mvp.mvp.contract.MainContract;
import com.kou.mvp.mvp.model.MainModel;

/**
 * Created by Nicholas on 2016/10/30.
 */

public class MainPresenter extends MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }

}
