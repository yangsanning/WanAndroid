package ysn.com.mvvm.wanandroid.page.login;

import ysn.com.mvvm.lifecycle.BaseViewModel;
import ysn.com.mvvm.lifecycle.ResultLiveData;
import ysn.com.mvvm.network.BaseNetworkCallback;
import ysn.com.mvvm.wanandroid.bean.User;
import ysn.com.mvvm.wanandroid.network.request.LoginNetworkRequest;

/**
 * @Author yangsanning
 * @ClassName LoginViewModel
 * @Description 登录ViewModel
 * @Date 2020/5/22
 */
public class LoginViewModel extends BaseViewModel {

    /**
     * 当数据请求成功回调
     */
    private ResultLiveData<User> userResultLiveData = new ResultLiveData<>();

    public void login(String userName, String password) {
        loadingDialogLiveData.setValue(true);
        LoginNetworkRequest.get().login(userName, password, new BaseNetworkCallback<User>() {
            @Override
            public void onSuccess(User data) {
                loadingDialogLiveData.setValue(false);
                userResultLiveData.postSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                loadingDialogLiveData.setValue(false);
                userResultLiveData.postFailure(code, msg);
            }
        });
    }

    public ResultLiveData<User> getUserResultLiveData() {
        return userResultLiveData;
    }
}
