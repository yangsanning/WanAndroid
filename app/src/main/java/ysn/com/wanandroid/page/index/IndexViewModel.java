package ysn.com.wanandroid.page.index;

import java.util.List;

import io.reactivex.disposables.Disposable;
import ysn.com.mvvm.lifecycle.BaseViewModel;
import ysn.com.mvvm.lifecycle.ResultLiveData;
import ysn.com.mvvm.network.BaseNetworkCallback;
import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.model.bean.Banner;
import ysn.com.wanandroid.network.NetworkResultList;
import ysn.com.wanandroid.network.request.IndexNetworkRequest;
import ysn.com.mvvm.widget.recycler_view.ISuperRecyclerView;

/**
 * @Author yangsanning
 * @ClassName IndexViewModel
 * @Description 一句话概括作用
 * @Date 2020/5/25
 */
public class IndexViewModel extends BaseViewModel {

    private ResultLiveData<List<Banner>> bannerResultLiveData = new ResultLiveData<>();
    private ISuperRecyclerView superRecyclerView;

    public void init(ISuperRecyclerView superRecyclerView) {
        this.superRecyclerView = superRecyclerView;
        getBanner();
        getArticleList(0);
    }

    /**
     * 获取首页banner
     */
    public void getBanner() {
        Disposable banner = IndexNetworkRequest.get().getBanner(new BaseNetworkCallback<List<Banner>>() {
            @Override
            public void onSuccess(List<Banner> data) {
                bannerResultLiveData.postSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
            }
        });
        addDisposable(banner);
    }

    /**
     * 获取首页文章列表
     */
    public void getArticleList(int page) {
        Disposable articleList = IndexNetworkRequest.get().getArticleList(page,
                new BaseNetworkCallback<NetworkResultList<Article>>() {
                    @Override
                    public void onSuccess(NetworkResultList<Article> data) {
                        if (page == 0) {
                            superRecyclerView.refreshSuccess(data.datas, data.curPage, data.pageCount);
                        } else {
                            superRecyclerView.loadMoreSuccess(data.datas, data.curPage, data.pageCount);
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        if (page == 0) {
                            superRecyclerView.refreshFailure();
                        } else {
                            superRecyclerView.loadMoreFailure();
                        }
                    }
                });
        addDisposable(articleList);
    }

    public ResultLiveData<List<Banner>> getBannerResultLiveData() {
        return bannerResultLiveData;
    }
}
