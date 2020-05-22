package ysn.com.mvvm.lifecycle;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author yangsanning
 * @ClassName BaseViewModel
 * @Description 管理rxJava发出的请求
 * @Date 2020/5/22
 */
public abstract class BaseViewModel extends ViewModel {

    /**
     * 订阅者容器(rxJava 请求)
     */
    private CompositeDisposable compositeDisposable;
    /**
     * 用来通知 Activity／Fragment 是否显示等待Dialog
     */
    protected MutableLiveData<Boolean> loadingDialogLiveData = new MutableLiveData<>();

    /**
     * 添加 rxJava 请求进入容器
     */
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void observeLoadingDialog(LifecycleOwner owner, Observer<Boolean> observer) {
        loadingDialogLiveData.observe(owner, observer);
    }

    /**
     * ViewModel 销毁时, 清除所有订阅者
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            // 清除所有订阅者
            compositeDisposable.clear();
            compositeDisposable = null;
        }
        loadingDialogLiveData = null;
    }
}
