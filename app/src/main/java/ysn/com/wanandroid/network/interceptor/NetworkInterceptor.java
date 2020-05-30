package ysn.com.wanandroid.network.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Author yangsanning
 * @ClassName NetworkInterceptor
 * @Description 网络拦截器
 * @Date 2020/5/22
 */
public class NetworkInterceptor implements Interceptor {

    @Override
    @NonNull
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder()
                .header("deviceType", "0")
                .header("Authorization", "")
                .build());
    }
}