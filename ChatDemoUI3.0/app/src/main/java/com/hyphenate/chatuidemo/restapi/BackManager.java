package com.hyphenate.chatuidemo.restapi;

import com.hyphenate.chatuidemo.restapi.model.LoginModule;
import com.hyphenate.chatuidemo.restapi.model.ResponseBModule;
import com.hyphenate.chatuidemo.restapi.model.UserModule;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackManager {
    private BackApiService apiService;

    private static BackManager instance;

    private BackManager(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mapi.ttdeng.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        apiService = retrofit.create(BackApiService.class);

    }

    static class RequestInterceptor implements Interceptor {

        @Override public okhttp3.Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", "Bearer " )
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();
            okhttp3.Response response =  chain.proceed(request);
            return response;
        }
    }
    public static BackManager getInstance(){
        if (instance == null){
            instance = new BackManager();
        }
        return instance;
    }

    public UserModule doLogin(String name,String password) throws BackException
    {
        LoginModule login = new LoginModule();
        login.name = name;
        login.password = password;

        Call<ResponseBModule<UserModule>> responseCall;

        responseCall = apiService.login(login);
        ResponseBModule<UserModule> response = handleResponseCall(responseCall).body();

        UserModule userdata = response.data;
        return  userdata;
    }

    private <T> Response<T> handleResponseCall(Call<T> responseCall) throws BackException{
        try {
            Response<T> response = responseCall.execute();
            if(!response.isSuccessful()){
                throw new BackException(response.code(), response.message());
            }
            return response;
        } catch (IOException e) {
            throw new BackException(e.getMessage());
        }
    }
}
