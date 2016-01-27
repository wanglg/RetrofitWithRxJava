package com.leowong.demo.retrofitwithrxjava;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * User: wanglg
 * Date: 2016-01-06
 * Time: 17:20
 * FIXME
 */
public class RetrofitManager {
    private Retrofit retrofit = null;
    private static RetrofitManager retrofitManager;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder().baseUrl(
                "https://api.github.com/").addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}
