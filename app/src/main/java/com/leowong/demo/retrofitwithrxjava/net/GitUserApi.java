package com.leowong.demo.retrofitwithrxjava.net;

import com.leowong.demo.retrofitwithrxjava.models.Repository;
import com.leowong.demo.retrofitwithrxjava.models.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * User: wanglg
 * Date: 2015-12-31
 * Time: 18:03
 * FIXME
 */
public interface GitUserApi {
    @GET("users/{username}/repos")
    Observable<List<Repository>> publicRepositories(@Path("username") String username);
}
