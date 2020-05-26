package ysn.com.mvvm.network;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author yangsanning
 * @ClassName BaseNetworkRequest
 * @Description 网络请求基础类
 * @Date 2020/5/22
 */
public class BaseNetworkRequest {

    /**
     * 根据返回的状态,抛出异常或将返回数据的固定格式中的data值剥取出来
     *
     * @param <T> 具体业务所需的数据类型
     */
    public static class NetworkResultFun<T> implements Function<BaseNetworkResult<T>, T> {

        @Override
        public T apply(BaseNetworkResult<T> tBaseNetworkResult) throws Exception {
            if (tBaseNetworkResult.isSuccess()) {
                return tBaseNetworkResult.getData();
            }
            throw new ApiException(tBaseNetworkResult.getErrorCode(), tBaseNetworkResult.getErrorMsg());
        }
    }

    /**
     * 切换线程,订阅信息
     *
     * @param <T>             泛型
     * @param o               被观察者
     * @param networkCallback 网络返回的回调
     * @return
     */
    protected <T> Disposable toSubscribe(Observable<T> o, BaseNetworkCallback<T> networkCallback) {
        Scheduler io = Schedulers.io();
        return o.subscribeOn(io)
                .subscribeOn(Schedulers.newThread())
                .unsubscribeOn(io)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(networkCallback.success, networkCallback.error);
    }
}
