package com.kou.mvp.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.kou.mvp.App;
import com.kou.mvp.base.BaseFragment;
import com.kou.mvp.bean.HotBean;
import com.kou.mvp.bean.Slider;
import com.kou.mvp.mvp.contract.Home1Contract;
import com.kou.mvp.mvp.presenter.Home1PresentIm;
import com.kou.mvp.ui.adapter.HotRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yaowei.mvp.R;

/**
 * Created by Administrator on 2017/9/12.
 */


public class home1 extends BaseFragment<Home1PresentIm> implements Home1Contract.home1View {
	@BindView(R.id.errorimage)
	ImageView mErrorimage;

	Unbinder unbinder;

	@BindView(R.id.recyclerview_home1)
	LRecyclerView mRecyclerviewHome1;

	@BindView(R.id.slider)
	SliderLayout mSlider;

	private ProgressDialog mProgressDialog;
	private String tag = "home1";

	//	分页加载数据的实现
	private static int                  mCurrentPage = 0;
	private static int                  mPageSize    = 10;
	private        int                  mTotalPage;
	private        HotRecyclerAdapter   mHotRecyclerAdapter;
	private        LRecyclerViewAdapter mLRecyclerViewAdapter;
	private        int                  mCurrentCounter = 0;


	@Override
	public void onResume() {
		super.onResume();
		mProgressDialog = new ProgressDialog(getActivity());
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setMessage("正在加载...");
		//mPresenter.getHome1SliderData(1);
		mPresenter.getHome1RecyclerView(mCurrentPage, mPageSize);
	}


	@Override
	protected Home1PresentIm onCreatePresenter() {
		return new Home1PresentIm(this);
	}

	@Override
	protected int createBaseLayout() {
		return R.layout.home1;
	}

	@Override
	public void showLoading() {
		mProgressDialog.show();
	}

	@Override
	public void showError(String msg) {
		Log.e(tag, "home1Slider" + msg.toString());
		Log.e(tag, "list" + msg);
		mProgressDialog.hide();
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
		mSlider.setVisibility(View.INVISIBLE);
		mErrorimage.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideDialog() {
		mProgressDialog.hide();
	}


	private void initSliderViewData(List<Slider> sliderss) {
		if (sliderss != null && sliderss.size() > 0) {
			for (Slider SliderData : sliderss) {
				TextSliderView textSliderView = new TextSliderView(App.getContext());
				textSliderView.image(SliderData.getImgUrl());
				textSliderView.description(SliderData.getName());
				textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
				if (mSlider != null) {
					mSlider.addSlider(textSliderView);
				}
			}
			mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
			mSlider.setCustomAnimation(new DescriptionAnimation());
			mSlider.setPresetTransformer(SliderLayout.Transformer.RotateUp);
			mSlider.setDuration(3000);
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		unbinder = ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void onSuceeed(List<Slider> sliderss) {
		initSliderViewData(sliderss);
	}

	@Override
	public void onSuceedList(HotBean listbean) {
		mCurrentPage = listbean.getCurrentPage();
		mTotalPage = listbean.getTotalPage();
		initRecyclerView(listbean);
	}

	private void initRecyclerView(HotBean listbean) {
		List<HotBean.ListBean> list = listbean.getList();
		mCurrentCounter = list.size();
		mHotRecyclerAdapter = new HotRecyclerAdapter(App.getContext());
		mHotRecyclerAdapter.addAll(list);
		mLRecyclerViewAdapter = new LRecyclerViewAdapter(mHotRecyclerAdapter);

		mRecyclerviewHome1.setAdapter(mLRecyclerViewAdapter);

		mRecyclerviewHome1.setHasFixedSize(true);
		mRecyclerviewHome1.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
		mRecyclerviewHome1.setLayoutManager(new LinearLayoutManager(App.getContext()));
		mRecyclerviewHome1.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				mCurrentCounter = 0;
				mHotRecyclerAdapter.clear();
				requestData();
			}
		});
	}

	private void requestData() {
		mRecyclerviewHome1.refreshComplete(10);
		mLRecyclerViewAdapter.notifyDataSetChanged();


	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}
}





