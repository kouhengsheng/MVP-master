package com.kou.mvp.mvp.presenter;

import com.kou.mvp.bean.LoginResponBen;
import com.kou.mvp.mvp.contract.LoginContract;
import com.kou.mvp.mvp.model.LoginModelIm;

import java.util.Map;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by sq on 2018/6/24.
 */
public class LoginPresenterIm extends LoginContract.LoginPresent {

	public LoginPresenterIm(LoginContract.LoginView loginView) {
		mView=loginView;
		mModel=new LoginModelIm();
	}

	@Override
	public void getToken(Map<String,Object> map) {
		Subscription subscribe = mModel.getToken(map)
			.subscribe(new Subscriber<LoginResponBen>() {
				@Override
				public void onStart() {
					super.onStart();
					mView.showDialog();
				}

				@Override
				public void onCompleted() {
					mView.hideDialog();
				}

				@Override
				public void onError(Throwable e) {
					mView.onFail(e.getMessage());
					onCompleted();
				}

				@Override
				public void onNext(LoginResponBen loginResponBen) {
					mView.onSucceed(loginResponBen);
				}
		});
		addSubscribe(subscribe);
	}
}
