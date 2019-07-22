package com.kou.mvp.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by sq on 2018/6/23
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P mPresenter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(createBaseLayout(),null);
		ButterKnife.bind(this,view);
		if (onCreatePresenter() != null) {
			mPresenter = onCreatePresenter();
		}
		return view;
	}


	@Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    protected abstract P onCreatePresenter();
	protected abstract int createBaseLayout();
}
