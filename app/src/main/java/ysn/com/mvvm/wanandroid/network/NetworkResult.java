package ysn.com.mvvm.wanandroid.network;

import ysn.com.mvvm.network.BaseNetworkResult;

/**
 * @Author yangsanning
 * @ClassName NetworkResult
 * @Description 服务器返回的数据
 * @Date 2020/5/23
 */
public class NetworkResult<T> extends BaseNetworkResult<T> {

    public String errorMsg;
    public int errorCode;
    public T data;

    @Override
    public boolean isSuccess() {
        return getErrorCode() >= NetworkCode.CODE_SUCCESS_CRITICAL;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public T getData() {
        return data;
    }
}
