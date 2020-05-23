package ysn.com.mvvm.wanandroid.network.request;

import rx.Observable;
import rx.Subscriber;
import ysn.com.mvvm.wanandroid.bean.User;
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
    public void login(String username, String password, Subscriber<User> subscribers) {
        Observable<User> observable = NetworkClient.get().mService
                .login(username, password)
                .map(new NetworkResultFun<>());
        toSubscribe(observable, subscribers);
    }
}
