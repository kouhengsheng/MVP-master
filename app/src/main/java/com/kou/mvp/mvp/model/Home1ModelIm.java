package com.kou.mvp.mvp.model;

import com.kou.mvp.api.ApiEngine;
import com.kou.mvp.bean.HotBean;
import com.kou.mvp.bean.Slider;
import com.kou.mvp.mvp.contract.Home1Contract;
import com.kou.mvp.rx.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Created by kouhengsheng on 2018/7/3.
 */
public class Home1ModelIm implements Home1Contract.home1model {
	@Override
	public Observable<List<Slider>> getHome1SliderData(int i) {
		return ApiEngine.getInstance().getApiService()
				.getSliderHome1Data(i)
				.compose(RxSchedulers.<List<Slider>>switchThread())
				;
	}

	@Override
	public Observable<HotBean> getRecyclerView(int curPage, int pageSise) {
		return ApiEngine.getInstance().getApiService()
				.getHotRecyclerView(curPage, pageSise)
				.compose(RxSchedulers.<HotBean>switchThread())
				;
	}

}
