package ysn.com.mvvm.network;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ysn.com.mvvm.network.interceptor.LogInterceptor;
import ysn.com.mvvm.network.interceptor.RewriteCacheControlInterceptor;

/**
 * @Author yangsanning
 * @ClassName BaseNetworkClient
 * @Description Retrofit
 * @Date 2020/5/22
 */
public class BaseNetworkClient {

    /**
     * 日志拦截器
     */
    private Interceptor logInterceptor;

    /**
     * 云端响应头拦截器
     */
    private Interceptor rewriteCacheControlInterceptor;

    protected <T> T getService(String baseUrl, final Class<T> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClientBuilder().build())
                // 支持直接格式化json返回Bean对象
                .addConverterFactory(GsonConverterFactory.create())
                // 支持RxJava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(service);
    }

    protected OkHttpClient.Builder getOkHttpClientBuilder() {
        Cache cache = new Cache(new File("", "HttpCache"), 1024 * 1024 * 10);
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(getRewriteCacheControlInterceptor())
                .addInterceptor(getLogInterceptor())
                .retryOnConnectionFailure(true)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);
    }

    protected Interceptor getRewriteCacheControlInterceptor() {
        if (rewriteCacheControlInterceptor == null) {
            rewriteCacheControlInterceptor = new RewriteCacheControlInterceptor();
        }
        return rewriteCacheControlInterceptor;
    }

    protected Interceptor getLogInterceptor() {
        if (logInterceptor == null) {
            logInterceptor = new LogInterceptor();
        }
        return logInterceptor;
    }
}
