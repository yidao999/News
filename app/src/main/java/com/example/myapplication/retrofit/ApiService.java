package com.example.myapplication.retrofit;


import com.example.myapplication.mvp.mvp_cardtop.model.TopModel;
import com.example.myapplication.mvp.mvp_news.model.NewsModel;
import com.example.myapplication.mvp.mvp_photo.model.PhotoModel;
import com.example.myapplication.mvp.mvp_video.model.VideoModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by QZS
 */

public interface ApiService {

    //baseUrl
    String BASEURL_CARDTOP = "https://way.jd.com/jisuapi/";

    String BASEURL_VIDEO = "http://api.m.mtime.cn/PageSubArea/";

    String BASEURL_PHOTO = "http://image.baidu.com/channel/";

    //加载天气
//    @GET("weather")
//    Observable<WeatherModel> loadDataByRetrofitRxjava(@Query("citypinyin") String cityId);

//    @FormUrlEncoded
//    @POST("user/login")
//    Observable<WeatherModel> getlogin(@Field("oper_name") String page, @Field("oper_pwds") String rows);

//            https://way.jd.com/jisuapi/get?channel=%E5%A4%B4%E6%9D%A1&num=10&start=0&appkey=747b4744b764f5b6dc5021e72ff9ea4c
    @GET("get")
    Observable<TopModel> LoadTopData_RxJava(@Query("channel") String type, @Query("num") String num, @Query("start") int startposition, @Query("appkey") String key);

//    @GET("get")
//    Observable<NewsModel> LoadNewData_RxJava(@Query("channel") String type, @Query("num") String num, @Query("start") int startposition, @Query("appkey") String key);


//    http://api.m.mtime.cn/PageSubArea/TrailerList.api
    @GET("TrailerList.api")
    Observable<VideoModel> loadVideoData_RxJava();
//    http://image.baidu.com/channel/listjson?pn=0&rn=20&tag1=%E7%BE%8E%E5%A5%B3&tag2=%E6%80%A7%E6%84%9F

    @GET("listjson")
    Observable<PhotoModel> loadPhotoData_RxJava(@Query("pn") int page, @Query("rn") int number, @Query("tag1") String type1,@Query("tag2") String type2);

}
