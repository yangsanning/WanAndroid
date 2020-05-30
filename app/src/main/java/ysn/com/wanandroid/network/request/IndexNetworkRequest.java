package ysn.com.wanandroid.network.request;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import ysn.com.mvvm.network.BaseNetworkCallback;
import ysn.com.mvvm.network.BaseNetworkRequest;
import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.model.bean.Banner;
import ysn.com.wanandroid.network.NetworkClient;
import ysn.com.wanandroid.network.NetworkResultList;

/**
 * @Author yangsanning
 * @ClassName IndexNetworkRequest
 * @Description 首页相关请求
 * @Date 2020/5/25
 */
public class IndexNetworkRequest extends BaseNetworkRequest {

    private static IndexNetworkRequest instance;

    public static IndexNetworkRequest get() {
        if (instance == null) {
            synchronized (IndexNetworkRequest.class) {
                if (instance == null) {
                    instance = new IndexNetworkRequest();
                }
            }
        }
        return instance;
    }

    /**
     * 获取首页banner
     */
    public Disposable getBanner(BaseNetworkCallback<List<Banner>> networkCallback) {
        Observable<List<Banner>> observable = NetworkClient.get().mService
                .getBanner()
                .map(new NetworkResultFun<>());
      return  toSubscribe(observable, networkCallback);
    }

    /**
     * 获取首页文章列表
     */
    public Disposable getArticleList(int page, BaseNetworkCallback<NetworkResultList<Article>> networkCallback) {
        Observable<NetworkResultList<Article>> observable = NetworkClient.get().mService
                .getArticleList(page)
                .map(new NetworkResultFun<>());
        return toSubscribe(observable, networkCallback);
    }
}