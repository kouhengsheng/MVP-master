package com.kou.mvp.api;

import com.kou.mvp.bean.AdsBean;
import com.kou.mvp.bean.BookBean;
import com.kou.mvp.bean.HotBean;
import com.kou.mvp.bean.IpBean;
import com.kou.mvp.bean.News;
import com.kou.mvp.bean.Translation;
import com.kou.mvp.bean.Translation1;
import com.kou.mvp.bean.repos;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kouhengsheng on 2018/8/21.
 */
public interface Api {
	//	@Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
	@GET("course_api/wares/hot")
	Call<HotBean> getHotGoods(@Query("curPage") int curPage, @Query("pageSize") int pageSize);


	@GET("api/android/customer")
		//Get请求地址
	Call<AdsBean> getAds(@Query("type") int type);//广告,定义参数type的当前是第几页 1,2,3 ...

	@GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
	Call<Translation> getCall();//金山词霸的接口

	//采用@Post表示Post方法进行请求（传入部分url地址）
	//采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
	//需要配合@Field使用
	@POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
	@FormUrlEncoded
	Call<Translation1> getWord(@Field("i") String targetSentence);

	//这个接口可能请求不到数据
	@Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
	@GET("world/world")
	Call<News> getNews(@Query("num") String num, @Query("page") String page);

	//这个接口可能请求不到数据
	@Headers({"apikey:81bf9da930c7f9825a3c3383f1d8d766", "Content-Type:application/json"})
	@GET("{type}/{type}")
	Call<News> getNewsTiyu(@Path("type") String type, @Query("num") String num, @Query("page") String page);

	@GET("getIpInfo.php")
	Call<IpBean> getIpInfo(@Query("ip") String ip);

	@GET("users/{user}/repos")
	Call<repos> listRepos(@Path("user") String user);

	@GET("v2/book/1220562")
	Observable<BookBean> getBook();

}
