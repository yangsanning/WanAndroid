package ysn.com.mvvm.wanandroid.network;

import okhttp3.OkHttpClient;
import ysn.com.mvvm.network.BaseNetworkClient;
import ysn.com.mvvm.wanandroid.network.interceptor.NetworkInterceptor;

/**
 * @Author yangsanning
 * @ClassName NetworkClient
 * @Description 网络Client
 * @Date 2020/5/22
 */
public class NetworkClient extends BaseNetworkClient {

    private static NetworkClient instance = null;
    public final NetworkApiService mService;

    public static NetworkClient get() {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }

    private NetworkClient() {
        mService = getService(NetworkApiService.API_HOME, NetworkApiService.class);
    }

    @Override
    protected OkHttpClient.Builder getOkHttpClientBuilder() {
        return super.getOkHttpClientBuilder()
                .addInterceptor(new NetworkInterceptor());
    }
}
