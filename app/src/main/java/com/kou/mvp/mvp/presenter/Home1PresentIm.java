package com.kou.mvp.mvp.presenter;

import com.kou.mvp.bean.HotBean;
import com.kou.mvp.bean.Slider;
import com.kou.mvp.mvp.contract.Home1Contract;
import com.kou.mvp.mvp.model.Home1ModelIm;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by kouhengsheng on 2018/7/3.
 */
public class Home1PresentIm extends Home1Contract.Home1Present{

	public Home1PresentIm(Home1Contract.home1View home1) {
		mView=home1;
		mModel=new Home1ModelIm();
	}

	@Override
	public void getHome1SliderData(int i) {
		Subscription subscribe = mModel.getHome1SliderData(i)
				.subscribe(new Subscriber<List<Slider>>() {

					@Override
					public void onStart() {
						super.onStart();
						mView.showLoading();
					}

					@Override
					public void onCompleted() {
						mView.hideDialog();
					}

					@Override
					public void onError(Throwable e) {
						mView.showError(e.toString());
						onCompleted();
					}

					@Override
					public void onNext(List<Slider> sliders) {
						mView.onSuceeed(sliders);
						mView.hideDialog();
					}
				});
		addSubscribe(subscribe);
	}

	@Override
	public void getHome1RecyclerView(int curPage, int pageSise) {
		Subscription subscribeList = mModel.getRecyclerView(curPage, pageSise)
				.subscribe(new Subscriber<HotBean>() {

					@Override
					public void onStart() {
						super.onStart();
						mView.showLoading();
					}


					@Override
					public void onCompleted() {
						mView.hideDialog();

					}

					@Override
					public void onError(Throwable e) {
						mView.showError(e.toString());
						onCompleted();
					}

					@Override
					public void onNext(HotBean lists) {
						mView.onSuceedList(lists);
						mView.hideDialog();
					}
				});
		addSubscribe(subscribeList);
	}
}
