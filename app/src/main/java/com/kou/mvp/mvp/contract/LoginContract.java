package com.kou.mvp.mvp.contract;

import com.kou.mvp.base.BaseModel;
import com.kou.mvp.base.BasePresenter;
import com.kou.mvp.base.BaseView;
import com.kou.mvp.bean.LoginResponBen;

import java.util.Map;

import rx.Observable;

/**
 * Created by sq on 2018/6/23
 */
public interface LoginContract {
	interface LoginView extends BaseView {
		void showDialog();

		void onSucceed(LoginResponBen data);

		void onFail(String err);

		void hideDialog();
	}

	interface LoginModel extends BaseModel {
		Observable<LoginResponBen> getToken(Map<String, Object> map);
	}

	abstract  class LoginPresent extends BasePresenter<LoginView,LoginModel> {
		public abstract void getToken(Map<String,Object> map);
	}

}
