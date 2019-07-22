package com.kou.mvp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kou.utils.ToUtil;

/**
 * Created by kouhengsheng on 2018/11/15.
 */
public class TimeBroadcast extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action != null && action.equals(Intent.ACTION_TIME_TICK)) {
			Bundle extras = intent.getExtras();
			Log.e("eeee", extras.toString());
			ToUtil.show(context,extras.toString());
		}
	}
}
