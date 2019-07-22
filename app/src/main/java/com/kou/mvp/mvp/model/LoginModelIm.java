package com.kou.mvp.mvp.model;

import com.kou.mvp.api.ApiEngine;
import com.kou.mvp.bean.LoginResponBen;
import com.kou.mvp.mvp.contract.LoginContract;

import java.util.Map;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by sq on 2018/6/23.
 */
public class LoginModelIm implements LoginContract.LoginModel{

	@Override
	public Observable<LoginResponBen> getToken(Map<String,Object> map) {

		return ApiEngine.getInstance().getApiService()
				.getStringToken(map)
				.subscribeOn(Schedulers.io())
				.observeOn(new Scheduler() {
					@Override
					public Worker createWorker() {
						return null;
					}
				});

	}


}

