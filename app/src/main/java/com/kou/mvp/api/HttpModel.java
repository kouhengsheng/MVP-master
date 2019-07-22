package com.kou.mvp.api;

import com.kou.mvp.bean.IpBean;
import com.kou.mvp.bean.repos;
import com.kou.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kouhengsheng on 2018/12/3.
 */
public class HttpModel {
	public static void getIpInfo() {
		Api api = Network.getInstance().getApi(ApiService.baseUrl3);
		Call<IpBean> call = api.getIpInfo("8.8.8.8");
		call.enqueue(new Callback<IpBean>() {
			@Override
			public void onResponse(Call<IpBean> call, Response<IpBean> response) {
				if (response != null) {
					LogUtil.e("eee", response.body().toString());
				} else {
					LogUtil.e("eee", "response=null");
				}
			}

			@Override
			public void onFailure(Call<IpBean> call, Throwable t) {
				LogUtil.e("eee", t.toString());
			}
		});
	}

	public static void getGithub() {
		Api api = Network.getInstance().getApi(ApiService.baseUrl4);
		Call<repos> octocat = api.listRepos("octocat");
		octocat.enqueue(new Callback<repos>() {
			@Override
			public void onResponse(Call<repos> call, Response<repos> response) {
				LogUtil.e("eee", response.body().toString());
			}

			@Override
			public void onFailure(Call<repos> call, Throwable t) {
				LogUtil.e("eee", t.toString());
			}
		});

	}
}
