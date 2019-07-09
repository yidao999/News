package com.example.myapplication.retrofit;


import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapplication.retrofit.ApiService.BASEURL_CARDTOP;
import static com.example.myapplication.retrofit.ApiService.BASEURL_PHOTO;
import static com.example.myapplication.retrofit.ApiService.BASEURL_VIDEO;

/**
 * Created by QZS
 */
public class ApiClient {
    public static Retrofit mRetrofit_CardTop;
    public static Retrofit mRetrofit_Video;
    public static Retrofit mRetrofit_Photo;

    public static Retrofit retrofit(int hostType) {
        if (hostType == HostType.Card_Top) {
            if (mRetrofit_CardTop == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                OkHttpClient okHttpClient = builder.build();
                mRetrofit_CardTop = new Retrofit.Builder()
                        .baseUrl(getHost(hostType))
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
            return mRetrofit_CardTop;
        } else if (hostType == HostType.BASE_VIDEO) {
            if (mRetrofit_Video == null) {
                OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
                mRetrofit_Video = new Retrofit.Builder()
                        .baseUrl(getHost(hostType))
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
            return mRetrofit_Video;
        } else if (hostType == HostType.GIRL_PHOTO) {
            if (mRetrofit_Photo == null) {
                OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
                mRetrofit_Photo = new Retrofit.Builder()
                        .baseUrl(getHost(HostType.GIRL_PHOTO))
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
            return mRetrofit_Photo;
        }
        return null;
    }

    private static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.Card_Top:
                host = BASEURL_CARDTOP;
                break;

            case HostType.BASE_VIDEO:
                host = BASEURL_VIDEO;
                break;

            case HostType.GIRL_PHOTO:
                host = BASEURL_PHOTO;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }

}
