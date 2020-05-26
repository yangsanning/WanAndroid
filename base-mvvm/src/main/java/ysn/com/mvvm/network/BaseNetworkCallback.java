package ysn.com.mvvm.network;

import io.reactivex.functions.Consumer;
import ysn.com.mvvm.base.R;
import ysn.com.mvvm.utils.ResUtils;

/**
 * @Author yangsanning
 * @ClassName NetworkCallback
 * @Description 网络返回的回调接口，可以在此对异常数据进行处理
 * 注意：异常默认抛出错误信息为网络错误
 * @Date 2020/5/22
 */
public abstract class BaseNetworkCallback<T> {

    Consumer<T> success;
    Consumer<Throwable> error;

    public BaseNetworkCallback() {
        success = this::onSuccess;
        error = e -> {
            if (e instanceof ApiException) {
                ApiException apiException = (ApiException) e;
                onFailure(apiException.resultCode, apiException.getMessage());
            } else {
                // 异常处理为网络错误
                onFailure(0, ResUtils.getString(R.string.network_exception));
                e.printStackTrace();
            }
        };
    }

    public abstract void onSuccess(T data);

    public abstract void onFailure(int code, String msg);
}

