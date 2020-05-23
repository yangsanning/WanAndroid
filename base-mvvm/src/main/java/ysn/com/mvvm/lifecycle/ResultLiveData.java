package ysn.com.mvvm.lifecycle;

import androidx.lifecycle.MutableLiveData;

/**
 * @Author yangsanning
 * @ClassName ResultLiveData
 * @Description 网络请求结果的 LiveData
 * @Date 2020/5/22
 */
public class ResultLiveData<T> extends MutableLiveData<Result<T>> {

    /**
     * 加载失败
     */
    public void postFailure(int code, String msg) {
        postValue(new Result<T>().failure(code, msg));
    }

    /**
     * 加载成功
     */
    public void postSuccess(T data) {
        postValue(new Result<T>().success(data));
    }
}