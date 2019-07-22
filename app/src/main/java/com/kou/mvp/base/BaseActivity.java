package com.kou.mvp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by sq on 2018/6/23
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		int baseLayout = createBaseLayout();
		if(baseLayout!=0){
			setContentView(createBaseLayout());
			ButterKnife.bind(this);
		}
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        if(getSupportActionBar()!=null){
			getSupportActionBar().hide();
		}
    }

	protected abstract int createBaseLayout();

	@Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    protected abstract P onCreatePresenter();
}
