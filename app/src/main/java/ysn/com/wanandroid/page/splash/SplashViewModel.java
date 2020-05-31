package ysn.com.wanandroid.page.splash;

import androidx.appcompat.app.AppCompatActivity;

import com.lazy.library.logging.Logcat;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ysn.com.mvvm.lifecycle.BaseViewModel;
import ysn.com.mvvm.network.BaseNetworkCallback;
import ysn.com.mvvm.utils.ResUtils;
import ysn.com.wanandroid.R;
import ysn.com.wanandroid.constant.ArticleChannelConstant;
import ysn.com.wanandroid.constant.UShareConstant;
import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.model.bean.Knowledge;
import ysn.com.wanandroid.model.dao.ArticleDao;
import ysn.com.wanandroid.model.db.RoomDatabaseHelper;
import ysn.com.wanandroid.network.NetworkResultList;
import ysn.com.wanandroid.network.request.SplashNetworkRequest;
import ysn.com.wanandroid.utils.PageUtils;
import ysn.com.wanandroid.widget.hepler.PreferenceHelper;

/**
 * @Author yangsanning
 * @ClassName SplashViewModel
 * @Description 一句话概括作用
 * @Date 2020/5/30
 */
public class SplashViewModel extends BaseViewModel {

    private int count;

    public void checkPermissions(AppCompatActivity activity) {
        Disposable disposable = new RxPermissions(activity)
                .request(UShareConstant.PERMISSION_LIST)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(granted -> {
                    if (granted) {
                        getKnowledgeList(activity);
                    } else {
                        toastLiveData.setValue(ResUtils.getString(R.string.text_permission_denied));
                    }
                });
        addDisposable(disposable);
    }

    public void getKnowledgeList(AppCompatActivity activity) {
        Disposable disposable = SplashNetworkRequest.get().getKnowledgeList(new BaseNetworkCallback<List<Knowledge>>() {
            @Override
            public void onSuccess(List<Knowledge> data) {
                List<Knowledge.ArticleChannel> articleChannelList = new ArrayList<>();
                for (Knowledge knowledge : data) {
                    if (ArticleChannelConstant.CUSTOM_VIEW_ID == knowledge.id
                            || ArticleChannelConstant.NEW_TECHNOLOGY_ID == knowledge.id) {
                        articleChannelList.addAll(knowledge.children);
                    }
                }
                if (articleChannelList.isEmpty()) {
                    nextPage(activity);
                } else {
                    getKnowledgeArticleList(activity, articleChannelList);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                nextPage(activity);
            }
        });
        addDisposable(disposable);
    }

    public void getKnowledgeArticleList(AppCompatActivity activity, List<Knowledge.ArticleChannel> articleChannelList) {
        this.count = 0;
        // 因为获取2页，所以 *2
        int total = (articleChannelList.size()) * 2;
        ArticleDao articleDao = RoomDatabaseHelper.get(activity).getArticleDao();
        Disposable disposable = SplashNetworkRequest.get().getKnowledgeArticleList(articleDao, articleChannelList,
                new BaseNetworkCallback<NetworkResultList<Article>>() {
                    @Override
                    public void onSuccess(NetworkResultList<Article> data) {
                        count++;
                        if (count == total) {
                            nextPage(activity);
                        }
                        Logcat.d("test", "count: " + count + " total: " + total);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        count++;
                        if (count == total) {
                            nextPage(activity);
                        }
                        Logcat.d("test", "count: " + count + " total: " + total);
                    }
                });
        addDisposable(disposable);
    }

    public void nextPage(AppCompatActivity activity) {
        if (PreferenceHelper.get().isLoginOut()) {
            PageUtils.startLoginActivity(activity);
        } else {
            PageUtils.startMainActivity(activity);
        }
        activity.finish();
    }
}
