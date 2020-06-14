package ysn.com.wanandroid.network.request;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import ysn.com.mvvm.network.BaseNetworkCallback;
import ysn.com.mvvm.network.BaseNetworkRequest;
import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.network.NetworkClient;
import ysn.com.wanandroid.network.NetworkResultList;

/**
 * @Author yangsanning
 * @ClassName ProjectNetworkRequest
 * @Description 一句话概括作用
 * @Date 2020/6/13
 */
public class ProjectNetworkRequest extends BaseNetworkRequest {

    private static ProjectNetworkRequest instance;

    public static ProjectNetworkRequest get() {
        if (instance == null) {
            synchronized (ProjectNetworkRequest.class) {
                if (instance == null) {
                    instance = new ProjectNetworkRequest();
                }
            }
        }
        return instance;
    }

    public void destory() {
        instance = null;
    }

    /**
     * 获取项目列表
     */
    public Disposable getProjectList(int page, BaseNetworkCallback<NetworkResultList<Article>> networkCallback) {
        Observable<NetworkResultList<Article>> observable = NetworkClient.get().mService
                .getProjectList(page)
                .map(new NetworkResultFun<>());
        return toSubscribe(observable, networkCallback);
    }
}
