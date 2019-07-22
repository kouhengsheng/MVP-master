package com.kou.mvp.api;

import com.kou.mvp.bean.HotBean;
import com.kou.mvp.bean.LoginResponBen;
import com.kou.mvp.bean.Slider;
import com.kou.mvp.bean.Translation;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Nicholas on 2016/10/30.
 */

public interface ApiService {
	//	http://112.124.22.238:8081/course_api/banner/query?type=1
	String baseUrl  = "http://112.124.22.238:8081/";
	String baseUrl2 = "http://fanyi.youdao.com/";//有道词典的接口
	String baseUrl3 = "http://ip.taobao.com/service/";//淘宝查询ip地址的接口
	String baseUrl4 = "https://api.github.com/";//github的接口
	String baseUrl5 = "https://api.douban.com/";//github的接口

	//	 String baseUrl = "http://apis.baidu.com/showapi_open_bus/";
	//	 http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?apikey=83ec99fff780989a5376a1bc595ed5ff&page=0
	//	 apikey:83ec99fff780989a5376a1bc595ed5ff/http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?page=1

	//**************************************************
	/*
	 * 模拟网络请求返回list集合
	 * http://7xij5m.com1.z0.glb.clouddn.com/spRecommend_new.txt
	 * */
	/*String baseurl = "http://7xij5m.com1.z0.glb.clouddn.com/";
	String spRecommendURL_NEW = baseUrl + "spRecommend_new.txt";
	@GET("spRecommend_new.txt")
	Observable<List<GoodsInfo.ResultBean.GoodlistBean>>getHome1Data();*/
	//**************************************************


	//**************************************************
	/*
	 * 登陆模的baseurl跟请求参数
	 * */
	String BASEONEURL = "http://106.14.115.37:89/v1/";

	/*
	 * 登陆成功返回的respon,用于获取Token
	 * */
	@Headers({
			"Authorization: Basic Y2xpZW50XzI6MTIzNDU2",
			"Content-Type: application/json"
	})
	@POST("oauth/token")
	Observable<LoginResponBen> getStringToken(@QueryMap Map<String, Object> map);
	//**************************************************


	//**************************************************
    /*  String BASE_URL="http://gank.io/";
    @GET("api/data/Android/10/{page}")
    Observable<Gank> getGank(@Path("page") String page);*/
	//**************************************************



/*	//**************************************************
	@Headers("apikey:83ec99fff780989a5376a1bc595ed5ff")
	@GET("showapi_joke/joke_text")
	Observable<JokeEntity> getJoke(@Query("page") int page);
	//***************************************************/


	/*//**************************************************
	@GET("showapi_joke/joke_text")
	Call<JokeEntity> callJoke(@Header("apikey") String apikey, @Query("page") int page);
	//***************************************************/


	@GET("course_api/banner/query")
	Observable<List<Slider>> getSliderHome1Data(@Query("type") int type);

	/*
	 * http://112.124.22.238:8081/course_api/wares/hot?curPage=1&pageSize=10;
	 */

	@GET("course_api/wares/hot")
	Observable<HotBean> getHotRecyclerView(@Query("curPage") int curPage, @Query("pageSize") int pageSize);

	@GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
	Call<Translation> getCall();//金山词霸的接口
}
