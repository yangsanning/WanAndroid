package ysn.com.wanandroid.page.knowledge;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ysn.com.mvvm.lifecycle.BaseViewModel;
import ysn.com.wanandroid.app.MyApplication;
import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.model.dao.ArticleDao;
import ysn.com.wanandroid.model.db.RoomDatabaseHelper;

/**
 * @Author yangsanning
 * @ClassName KnowledgeViewModel
 * @Description 一句话概括作用
 * @Date 2020/6/8
 */
public class KnowledgeViewModel extends BaseViewModel {

    private ArticleDao articleDao;
    private PagedList.Config pageConfig;
    public ObservableInt total = new ObservableInt();

    {
        articleDao = RoomDatabaseHelper.get(MyApplication.get()).getArticleDao();
        pageConfig = new PagedList.Config.Builder()
                // 首次加载的数据量
                .setInitialLoadSizeHint(20)
                // 分页加载的数量
                .setPageSize(20)
                // 是否启用占位符（本地数据比较合适，因为远程数据是未知的）
                .setEnablePlaceholders(false)
                // 距离底部还有多少条数据时开始预加载
                .setPrefetchDistance(5)
                .build();
    }

    public LiveData<PagedList<Article>> getData() {
        Disposable disposable = Observable.create((ObservableOnSubscribe<Integer>) emitter ->
                emitter.onNext(articleDao.getTotal()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> total.set(integer));
        addDisposable(disposable);
        return new LivePagedListBuilder<>(articleDao.getData(), pageConfig).build();
    }

    public LiveData<PagedList<Article>> getData(int superChapterId) {
        Disposable disposable = Observable.create((ObservableOnSubscribe<Integer>) emitter ->
                emitter.onNext(articleDao.getTotalBySuperChapterId(superChapterId)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> total.set(integer));
        addDisposable(disposable);
        return new LivePagedListBuilder<>(articleDao.getDataBySuperChapterId(superChapterId), pageConfig).build();
    }
}
