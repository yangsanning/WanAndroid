package ysn.com.mvvm.wanandroid.page.index;

import java.util.List;

import ysn.com.mvvm.lifecycle.BaseViewModel;
import ysn.com.mvvm.lifecycle.ResultLiveData;
import ysn.com.mvvm.network.BaseNetworkCallback;
import ysn.com.mvvm.wanandroid.bean.Article;
import ysn.com.mvvm.wanandroid.bean.Banner;
import ysn.com.mvvm.wanandroid.network.NetworkResultList;
import ysn.com.mvvm.wanandroid.network.request.IndexNetworkRequest;

/**
 * @Author yangsanning
 * @ClassName IndexViewModel
 * @Description 一句话概括作用
 * @Date 2020/5/25
 */
public class IndexViewModel extends BaseViewModel {

    private ResultLiveData<List<Banner>> bannerResultLiveData = new ResultLiveData<>();

    public void init(){
        getBanner();
        getArticleList(0);
    }

    /**
     * 获取首页banner
     */
    public void getBanner() {
        IndexNetworkRequest.get().getBanner(new BaseNetworkCallback<List<Banner>>() {
            @Override
            public void onSuccess(List<Banner> data) {
                bannerResultLiveData.postSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
            }
        });
    }

    /**
     * 获取首页文章列表
     */
    public void getArticleList(int page) {
        IndexNetworkRequest.get().getArticleList(page, new BaseNetworkCallback<NetworkResultList<Article>>() {
            @Override
            public void onSuccess(NetworkResultList<Article> data) {

            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }

    public ResultLiveData<List<Banner>> getBannerResultLiveData() {
        return bannerResultLiveData;
    }
}
