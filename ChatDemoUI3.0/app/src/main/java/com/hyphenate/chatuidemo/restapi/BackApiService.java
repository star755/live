package com.hyphenate.chatuidemo.restapi;

import com.hyphenate.chatuidemo.restapi.model.LoginModule;
import com.hyphenate.chatuidemo.restapi.model.ResponseBModule;
import com.hyphenate.chatuidemo.restapi.model.UserModule;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BackApiService {

    @POST("Login")
    Call<ResponseBModule<UserModule>> login(@Body LoginModule login);



}
