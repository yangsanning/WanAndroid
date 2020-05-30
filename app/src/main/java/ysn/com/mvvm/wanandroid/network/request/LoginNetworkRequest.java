package ysn.com.mvvm.wanandroid.network.request;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import ysn.com.mvvm.network.BaseNetworkCallback;
import ysn.com.mvvm.wanandroid.model.bean.User;
import ysn.com.mvvm.network.BaseNetworkRequest;
import ysn.com.mvvm.wanandroid.network.NetworkClient;

/**
 * @Author yangsanning
 * @ClassName LoginNetworkRequest
 * @Description 登录事件绑定
 * @Date 2020/5/22
 */
public class LoginNetworkRequest extends BaseNetworkRequest {

    private static LoginNetworkRequest instance;

    public static LoginNetworkRequest get() {
        if (instance == null) {
            synchronized (LoginNetworkRequest.class) {
                if (instance == null) {
                    instance = new LoginNetworkRequest();
                }
            }
        }
        return instance;
    }

    /**
     * 登录
     */
    public Disposable login(String username, String password, BaseNetworkCallback<User> networkCallback) {
        Observable<User> observable = NetworkClient.get().mService
                .login(username, password)
                .map(new NetworkResultFun<>());
        return toSubscribe(observable, networkCallback);
    }
}
