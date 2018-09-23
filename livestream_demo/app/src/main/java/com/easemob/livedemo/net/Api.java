package com.easemob.livedemo.net;

import com.easemob.livedemo.DemoApplication;
import com.easemob.livedemo.data.restapi.LiveException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit sInstance = null;

    private Api() {
    }

    private synchronized static Retrofit API() {
        if (sInstance == null) {
            int cacheSize = 1 * 1024 * 1024; // 1 MiB
            Cache cache = new Cache(DemoApplication.getInstance().getApplicationContext().getCacheDir(), cacheSize);
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                    .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                    .cache(cache);
            OkHttpClient client = builder.build();

            Retrofit R = new Retrofit.Builder()
                    .baseUrl("http://mapi.ttdeng.com/api/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())//加入jackjson解析
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            sInstance = R;
        }
        return sInstance;
    }

    public static <T> T create(Class<T> t){
        return API().create(t);
    }


}
