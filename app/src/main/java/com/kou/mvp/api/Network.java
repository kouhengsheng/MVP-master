package com.kou.mvp.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kouhengsheng on 2018/12/3.
 */
public class Network {
	private static volatile Network mInstance;
	private                 Api     mApi;

	private Network() {
	}

	public static Network getInstance() {
		if (mInstance == null) {
			synchronized (Network.class) {
				if (mInstance == null) {
					mInstance = new Network();
				}
			}
		}
		return mInstance;
	}

	/**
	 * 使用默认的baseUrl
	 */
	public Api getApi() {
		if (mApi == null) {
			synchronized (Network.class) {
				if (mApi == null) {
					Retrofit retrofit = new Retrofit.Builder()
							.baseUrl(ApiService.baseUrl)
							.addConverterFactory(GsonConverterFactory.create())
							.build();
					mApi = retrofit.create(Api.class);
				}
			}
		}
		return mApi;
	}

	/**
	 * 自定义baseUrl
	 */
	public Api getApi(String baseUrl) {
		if (mApi == null) {
			synchronized (Network.class) {
				if (mApi == null) {
					Retrofit retrofit = new Retrofit.Builder()
							.baseUrl(baseUrl)
							.addConverterFactory(GsonConverterFactory.create())
							.build();
					mApi = retrofit.create(Api.class);
				}
			}
		}
		return mApi;
	}


}
