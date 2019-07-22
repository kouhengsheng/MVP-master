package com.kou.mvp.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.kou.mvp.TimeBroadcast;
import com.kou.mvp.api.Api;
import com.kou.mvp.api.ApiEngine;
import com.kou.mvp.api.ApiService;
import com.kou.mvp.api.Network;
import com.kou.mvp.bean.AdsBean;
import com.kou.mvp.bean.BookBean;
import com.kou.mvp.bean.HotBean;
import com.kou.mvp.bean.IpBean;
import com.kou.mvp.bean.News;
import com.kou.mvp.bean.Translation;
import com.kou.mvp.bean.Translation1;
import com.kou.utils.LogUtil;

import io.reactivex.disposables.Disposable;
import me.yaowei.mvp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sq on 2018/6/23
 * 测试retrofit的activity
 */
public class TestRetrofitActivity extends AppCompatActivity {

	private TimeBroadcast mTimeBroadcast;
	private TextView      mtv;
	private Disposable    mDisposable;
	private Dialog        loading_dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testlayout);
		//new DrawerBuilder().withActivity(this).build();
		mtv = findViewById(R.id.tv);

		loading_dialog = new AlertDialog.Builder(this).setMessage("loading...").create();
		//getDataFromNet();
		GetRequest();

		//getAds();//获取广告的接口

		//getYouDao();//获取有道词典的接口

		//getNews();//调用百度接口获取新闻

		//getNewsTiyu();

		//youDao();

		//获取ip地址信息
		//HttpModel.getIpInfo();
		//getIpInfo2();

		//HttpModel.getGithub();
		//		getDataFromNet();

		//		getBook();
	}

	//获取豆瓣书籍
	private void getBook() {
		RxHttpUtils.createApi(Api.class)
				.getBook()
				.compose(Transformer.<BookBean>switchSchedulers(loading_dialog))
				.subscribe(new CommonObserver<BookBean>() {
					@Override
					protected void onError(String errorMsg) {
						mtv.setText(errorMsg);
					}

					@Override
					protected void onSuccess(BookBean bookBean) {
						mtv.setText(bookBean.toString());
					}
				});

	}

	private void getIpInfo2() {
		Api api = Network.getInstance().getApi(ApiService.baseUrl3);
		Call<IpBean> ipInfo = api.getIpInfo("8.8.8.8");
		ipInfo.enqueue(new Callback<IpBean>() {
			@Override
			public void onResponse(Call<IpBean> call, Response<IpBean> response) {
				mtv.setText(response.body().toString());
				LogUtil.e("eee", response.body().toString());
			}

			@Override
			public void onFailure(Call<IpBean> call, Throwable t) {
				mtv.setText(t.toString());
				LogUtil.e("eee", t.toString());
			}
		});
	}

	private void youDao() {
		Api api = Network.getInstance().getApi(ApiService.baseUrl2);
		Call<Translation1> call = api.getWord("I love you");
		call.enqueue(new Callback<Translation1>() {
			@Override
			public void onResponse(Call<Translation1> call, Response<Translation1> response) {
				mtv.setText(response.body().toString());
				//LogUtil.e("eee",response.body().toString());

			}

			@Override
			public void onFailure(Call<Translation1> call, Throwable t) {
				mtv.setText(t.toString());
				//				LogUtil.e("eee",t.toString());
			}
		});
	}

	private void getNewsTiyu() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://apis.baidu.com/txapi/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		Api api = retrofit.create(Api.class);
		Call<News> tiyu = api.getNewsTiyu("tiyu", "1", "10");
		tiyu.enqueue(new Callback<News>() {
			@Override
			public void onResponse(Call<News> call, Response<News> response) {
				mtv.setText(response.body().toString());

			}

			@Override
			public void onFailure(Call<News> call, Throwable t) {
				mtv.setText(t.toString());
			}
		});
	}

	private void getNews() {
		//创建retrofit对象
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://apis.baidu.com/txapi/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		//实例化我们的mApi对象
		Api api = retrofit.create(Api.class);
		//调用我们的响应的方法
		Call<News> news = api.getNews("10", "1");
		news.enqueue(new Callback<News>() {
			@Override
			public void onResponse(Call<News> call, Response<News> response) {
				mtv.setText(response.body().toString());
				LogUtil.e("eee", response.body().toString());
			}

			@Override
			public void onFailure(Call<News> call, Throwable t) {
				mtv.setText(t.toString());
				LogUtil.e("eee", t.toString());
			}
		});
	}

	private void getYouDao() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://fanyi.youdao.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		Api api = retrofit.create(Api.class);
		Call<Translation1> call = api.getWord("I love you");
		call.enqueue(new Callback<Translation1>() {
			@Override
			public void onResponse(Call<Translation1> call, Response<Translation1> response) {
				mtv.setText(response.body().toString());
				LogUtil.e("eee", response.body().toString());
			}

			@Override
			public void onFailure(Call<Translation1> call, Throwable t) {
				mtv.setText(t.toString());
				LogUtil.e("eee", t.toString());
			}
		});
	}

	private void getAds() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://www.wuhaojun.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		Api api = retrofit.create(Api.class);
		Call<AdsBean> news = api.getAds(1);//获取第一页的数据
		news.enqueue(new Callback<AdsBean>() {
			@Override
			public void onResponse(Call<AdsBean> call, Response<AdsBean> response) {
				mtv.setText(response.body().toString());
				//mtv.setText(response.body().toString());
				LogUtil.e("eee", response.body().toString());
			}

			@Override
			public void onFailure(Call<AdsBean> call, Throwable t) {
				mtv.setText(t.toString());
			}
		});
	}

	private void GetRequest() {
		ApiService apiService = ApiEngine.getInstance().getApiService();
		Call<Translation> call = apiService.getCall();
		call.enqueue(new Callback<Translation>() {
			@Override
			public void onResponse(Call<Translation> call, Response<Translation> response) {
				mtv.setText(response.body().toString());
				LogUtil.e("eee", response.body().toString());
			}

			@Override
			public void onFailure(Call<Translation> call, Throwable t) {
				mtv.setText(t.toString());
				LogUtil.e("eee", t.toString());
			}
		});
		//		sliderHome1Data.map()
		//		Retrofit retrofit = new Retrofit.Builder()
		//				.baseUrl("http://fy.iciba.com/")
		//				.addConverterFactory(GsonConverterFactory.create())
		//				.build();
		//		Api api = retrofit.create(Api.class);
		//
		//		Call<Translation> call = api.getCall();
		//
		//		call.enqueue(new Callback<Translation>() {
		//			@Override
		//			public void onResponse(Call<Translation> call, Response<Translation> response) {
		//				response.body().show();
		//				mtv.setText(response.body().toString());
		//			}
		//
		//			@Override
		//			public void onFailure(Call<Translation> call, Throwable t) {
		//				LogUtil.e("eee", t.toString());
		//				mtv.setText(t.toString());
		//			}
		//		});
	}

	private void getDataFromNet() {
		//		Log.e("eee", "onResponse: ");
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl("http://112.124.22.238:8081/")
				.build();
		Api api = retrofit.create(Api.class);

		Call<HotBean> hotGoods = api.getHotGoods(1, 10);
		hotGoods.enqueue(new Callback<HotBean>() {
			@Override
			public void onResponse(Call<HotBean> call, Response<HotBean> response) {
				//				Log.e("eee", "onResponse: " + response.toString());
				LogUtil.e("eee", response.body().toString());
				//				Logger.e("eee","你好啊");
				//				com.orhanobut.logger.Logger.w("no thread info and only 1 method");

				mtv.setText(response.body().toString());
				Toast.makeText(TestRetrofitActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(Call<HotBean> call, Throwable t) {
				LogUtil.e("eee", t.toString());
				mtv.setText(t.toString());
				//				Log.e("eee", "eeeeeeee: " + toString());
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//		unregisterReceiver(mTimeBroadcast);
	}
}
