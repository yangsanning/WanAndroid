package ysn.com.wanandroid.network.request;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import ysn.com.mvvm.network.BaseNetworkCallback;
import ysn.com.mvvm.network.BaseNetworkRequest;
import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.model.bean.Knowledge;
import ysn.com.wanandroid.model.dao.ArticleDao;
import ysn.com.wanandroid.network.NetworkClient;
import ysn.com.wanandroid.network.NetworkResultList;

/**
 * @Author yangsanning
 * @ClassName SplashNetworkRequest
 * @Description 启动页网络请求
 * @Date 2020/5/30
 */
public class SplashNetworkRequest extends BaseNetworkRequest {

    private static SplashNetworkRequest instance;

    public static SplashNetworkRequest get() {
        if (instance == null) {
            synchronized (SplashNetworkRequest.class) {
                if (instance == null) {
                    instance = new SplashNetworkRequest();
                }
            }
        }
        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    /**
     * 获取知识体系列表
     */
    public Disposable getKnowledgeList(BaseNetworkCallback<List<Knowledge>> networkCallback) {
        Observable<List<Knowledge>> observable = NetworkClient.get().mService
                .getKnowledgeList()
                .map(new NetworkResultFun<>());
        return toSubscribe(observable, networkCallback);
    }

    /**
     * 初始化文章列表
     */
    @SuppressLint("CheckResult")
    public Disposable getKnowledgeArticleList(ArticleDao articleDao, List<Knowledge.ArticleChannel> articleChannelList,
                                              BaseNetworkCallback<NetworkResultList<Article>> networkCallback) {
        List<Observable<NetworkResultList<Article>>> observableList = new ArrayList<>();
        for (Knowledge.ArticleChannel articleChannel : articleChannelList) {
            observableList.add(getKnowledgeArticleObservable(articleChannel.id));
        }
        Observable<NetworkResultList<Article>> observable = Observable.merge(observableList)
                .doOnNext(new Consumer<NetworkResultList<Article>>() {
                    @Override
                    public void accept(NetworkResultList<Article> data) throws Exception {
                        articleDao.insert(data.datas);
                    }
                });
        return toSubscribe(observable, networkCallback);
    }

    @SuppressLint("CheckResult")
    private Observable<NetworkResultList<Article>> getKnowledgeArticleObservable(int id) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // todo: 暂时获取两页
                emitter.onNext(1);
                emitter.onNext(2);
            }
        }).flatMap(new Function<Integer, ObservableSource<NetworkResultList<Article>>>() {
            @Override
            public ObservableSource<NetworkResultList<Article>> apply(Integer page) throws Exception {
                Observable<NetworkResultList<Article>> observable = NetworkClient.get().mService
                        .getKnowledgeArticleList(page, id)
                        .map(new NetworkResultFun<>());
                return observable;
            }
        });
    }
}
