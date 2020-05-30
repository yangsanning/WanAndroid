package ysn.com.mvvm.wanandroid.page.splash;

import androidx.appcompat.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ysn.com.mvvm.lifecycle.BaseViewModel;
import ysn.com.mvvm.utils.ResUtils;
import ysn.com.mvvm.wanandroid.R;
import ysn.com.mvvm.wanandroid.constant.UShareConstant;
import ysn.com.mvvm.wanandroid.utils.PageUtils;
import ysn.com.mvvm.wanandroid.widget.hepler.PreferenceHelper;

/**
 * @Author yangsanning
 * @ClassName SplashViewModel
 * @Description 一句话概括作用
 * @Date 2020/5/30
 */
public class SplashViewModel extends BaseViewModel {

    public void checkPermissions(AppCompatActivity activity) {
        Disposable disposable = new RxPermissions(activity)
                .request(UShareConstant.PERMISSION_LIST)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(granted -> {
                    if (granted) {
                        nextPage(activity);
                    } else {
                        toastLiveData.setValue(ResUtils.getString(R.string.text_permission_denied));
                    }
                });
        addDisposable(disposable);
    }

    public void nextPage(AppCompatActivity activity) {
        Disposable disposable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(true);
            }
        }).delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(b -> {
                    if (PreferenceHelper.get().isLoginOut()) {
                        PageUtils.startLoginActivity(activity);
                    } else {
                        PageUtils.startMainActivity(activity);
                    }
                    activity.finish();
                });
        addDisposable(disposable);
    }
}
