package ysn.com.mvvm.network;

/**
 * @Author yangsanning
 * @ClassName BaseNetworkResult
 * @Description 服务器返回的数据的基类
 * @Date 2020/5/22
 */
public abstract class BaseNetworkResult<T> {

    public abstract boolean isSuccess();

    public abstract String getErrorMsg();

    public abstract int getErrorCode();

    public abstract T getData();
}