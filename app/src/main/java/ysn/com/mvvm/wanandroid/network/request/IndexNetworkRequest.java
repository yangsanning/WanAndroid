package ysn.com.mvvm.wanandroid.network.request;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import ysn.com.mvvm.network.BaseNetworkRequest;
import ysn.com.mvvm.wanandroid.bean.Article;
import ysn.com.mvvm.wanandroid.bean.Banner;
import ysn.com.mvvm.wanandroid.network.NetworkClient;
import ysn.com.mvvm.wanandroid.network.NetworkResultList;

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
    public void getBanner(Subscriber<List<Banner>> subscribers) {
        Observable<List<Banner>> observable = NetworkClient.get().mService
                .getBanner()
                .map(new NetworkResultFun<>());
        toSubscribe(observable, subscribers);
    }

    /**
     * 获取首页文章列表
     */
    public void getArticleList(int page, Subscriber<NetworkResultList<Article>> subscribers) {
        Observable<NetworkResultList<Article>> observable = NetworkClient.get().mService
                .getArticleList(page)
                .map(new NetworkResultFun<>());
        toSubscribe(observable, subscribers);
    }
}