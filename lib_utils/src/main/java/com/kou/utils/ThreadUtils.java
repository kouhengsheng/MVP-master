package com.kou.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程工具类
 * Created by 寇恒升 on 2017/2/27.
 */
public class ThreadUtils {
	private static Handler  sHandler  = new Handler(Looper.getMainLooper());
	private static Executor sExecutor = Executors.newSingleThreadExecutor();

	public static void runOnSubThread(Runnable runnable) {
		sExecutor.execute(runnable);
	}

	public static void runOnUIThread(Runnable runnable) {
		sHandler.post(runnable);
	}
}
