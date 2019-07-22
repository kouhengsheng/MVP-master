package com.kou.mvp.mvp.contract;


import com.kou.mvp.base.BaseModel;
import com.kou.mvp.base.BasePresenter;
import com.kou.mvp.base.BaseView;

/**
 * Created by sq on 2018/6/23
 */

public interface MainContract {

    interface View extends BaseView {

    }

    interface Model extends BaseModel {
//        Observable<Gank> getGank();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
//        public abstract void getGank();
    }
}
