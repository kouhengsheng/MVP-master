package com.kou.mvp.mvp.contract;

import com.kou.mvp.base.BaseModel;
import com.kou.mvp.base.BasePresenter;
import com.kou.mvp.base.BaseView;
import com.kou.mvp.bean.HotBean;
import com.kou.mvp.bean.Slider;

import java.util.List;

import rx.Observable;

/**
 * Created by kouhengsheng on 2018/7/3.
 */
public interface Home1Contract {
	interface home1View extends BaseView {
//		轮播
		void onSuceeed(List<Slider> sliders);
//		RecyclerView
		void onSuceedList(HotBean listbean);
		void showLoading();
		void showError(String msg);
		void hideDialog();
	}

	interface home1model extends BaseModel {
		Observable<List<Slider>> getHome1SliderData(int i);
		Observable<HotBean> getRecyclerView(int curPage,int pageSise);
	}

	abstract class Home1Present extends BasePresenter<home1View,home1model> {
		public abstract void getHome1SliderData(int i);
		public abstract void getHome1RecyclerView(int curPage,int pageSise);
	}
}
