package com.kou.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kou.mvp.base.BaseActivity;
import com.kou.mvp.bean.FragmentBean;
import com.kou.mvp.mvp.contract.MainContract;
import com.kou.mvp.mvp.presenter.MainPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import me.yaowei.mvp.R;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
	@BindView(R.id.drawer_button)
	Button mDrawerButton;
	@BindView(R.id.btn_filter)
	Button mBtnFilter;
	@BindView(R.id.btn_select_city)
	Button mBtnSelectCity;

	@Override
	protected int createBaseLayout() {
		return R.layout.activity_main;
	}

	//	@BindView(R.id.realtabcontent_mainactivity)
	//	FrameLayout     mRealtabcontentMainactivity;
	//	@BindView(R.id.tabcontent)
	//	FrameLayout     mTabcontent;
	//	@BindView(R.id.coframgentTabhost_mainactivity)
	//	FragmentTabHost mCoframgentTabhostMainactivity;
	private ArrayList<FragmentBean> mFragmentList;


	@OnClick(R.id.drawer_button)
	public void click_drawer_button() {
		Intent intent = new Intent(this, DrawerActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//		initTabHost();
	}

	private View setIndicatorView(FragmentBean tab) {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.tab_indicatorshangcheng, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.icon_tabindicator);
		TextView textView = (TextView) view.findViewById(R.id.txt_indicatorindicator);
		imageView.setImageResource(tab.getDrawable());
		textView.setText(tab.getName());
		return view;
	}


	@Override
	protected MainPresenter onCreatePresenter() {
		return new MainPresenter(this);
	}


	@OnClick(R.id.btn_filter)
	public void click_btn_filter() {
		Intent intent = new Intent(MainActivity.this, FilterActivity.class);
		startActivity(intent);

	}

	@OnClick(R.id.btn_select_city)//点击选择城市的时候
	public void click_btn_select_city() {
		Intent intent = new Intent(MainActivity.this, CityPickerActivity.class);
		startActivity(intent);
	}
	//	private void initTabHost() {
	//		mFragmentList = new ArrayList<>();
	//		mCoframgentTabhostMainactivity.setup(MainActivity.this, getSupportFragmentManager(), R.id.realtabcontent_mainactivity);
	//		FragmentBean test1 = new FragmentBean(R.mipmap.ic_launcher, "1", home1.class);
	//		FragmentBean test2 = new FragmentBean(R.mipmap.ic_launcher, "2", home2.class);
	//		FragmentBean test3 = new FragmentBean(R.mipmap.ic_launcher, "3", home3.class);
	//		mFragmentList.add(test1);
	//		mFragmentList.add(test2);
	//		mFragmentList.add(test3);
	//		if (mFragmentList.size() > 0) {
	//			for (FragmentBean tab : mFragmentList) {
	//				TabHost.TabSpec tabSpec = mCoframgentTabhostMainactivity.newTabSpec(tab.getName());
	//				tabSpec.setIndicator((setIndicatorView(tab)));
	//				mCoframgentTabhostMainactivity.addTab(tabSpec, tab.getFragment(), null);
	//			}
	//			mCoframgentTabhostMainactivity.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
	//			mCoframgentTabhostMainactivity.setCurrentTab(0);
	//			mCoframgentTabhostMainactivity.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
	//				@Override
	//				public void onTabChanged(String tabId) {
	//
	//				}
	//			});
	//		}
	//
	//	}


}
