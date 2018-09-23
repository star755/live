package com.easemob.livedemo.net.service;

import com.easemob.livedemo.net.bean.UserModule;
import com.easemob.livedemo.net.response.BaseResponse;
import com.easemob.livedemo.net.response.LoginModule;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface DemoUserList {
    @GET("User")
    Observable<BaseResponse<List<UserModule>>> get(@Query("userType") int type);
    @GET("User")
    Observable<BaseResponse<List<UserModule>>> get(@Query("userType") int type ,@Query("userStatus") String status);
    @GET("User")
    Observable<BaseResponse<List<UserModule>>> getStatus(@Query("userStatus") int status);
    @GET("User")
    Observable<BaseResponse<List<UserModule>>> get(@Query("name") String userName);

}
