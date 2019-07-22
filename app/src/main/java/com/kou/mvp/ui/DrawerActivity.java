package com.kou.mvp.ui;

import android.app.Activity;
import android.os.Bundle;

import me.yaowei.mvp.R;


/**
 * Created by kouhengsheng on 2018/12/24.
 */
public class DrawerActivity extends Activity {
	/**
	 * 自己手写的侧滑菜单，详见下方链接
	 * https://www.jianshu.com/p/c5d0a6f0b789
	 *
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer);

	}

}
