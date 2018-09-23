package com.easemob.livedemo.net.service;

import com.easemob.livedemo.net.bean.UserModule;
import com.easemob.livedemo.net.response.BaseResponse;
import com.easemob.livedemo.net.response.LoginModule;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface DemoUser {
    @POST("Login")
    Observable<BaseResponse<UserModule>> login(@Body LoginModule login);
}
