package ysn.com.wanandroid.page.login;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.disposables.Disposable;
import ysn.com.mvvm.lifecycle.BaseViewModel;
import ysn.com.mvvm.lifecycle.ResultLiveData;
import ysn.com.mvvm.network.BaseNetworkCallback;
import ysn.com.mvvm.utils.ResUtils;
import ysn.com.wanandroid.R;
import ysn.com.wanandroid.model.bean.User;
import ysn.com.wanandroid.network.request.LoginNetworkRequest;

/**
 * @Author yangsanning
 * @ClassName LoginViewModel
 * @Description 登录ViewModel
 * @Date 2020/5/22
 */
public class LoginViewModel extends BaseViewModel {

    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    /**
     * 当数据请求成功回调
     */
    private ResultLiveData<User> userResultLiveData = new ResultLiveData<>();

    public void login() {
        String userName = this.userName.getValue();
        if (TextUtils.isEmpty(userName)) {
            toastLiveData.setValue(ResUtils.getString(R.string.text_user_name_not_null));
            return;
        }
        String password = this.password.getValue();
        if (TextUtils.isEmpty(password)) {
            toastLiveData.setValue(ResUtils.getString(R.string.text_pwd_not_null));
            return;
        }

        loadingDialogLiveData.setValue(true);
        Disposable login = LoginNetworkRequest.get().login(userName, password,
                new BaseNetworkCallback<User>() {
                    @Override
                    public void onSuccess(User data) {
                        loadingDialogLiveData.setValue(false);
                        userResultLiveData.postSuccess(data);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        loadingDialogLiveData.setValue(false);
                        toastLiveData.setValue(msg);
                    }
                });
        addDisposable(login);
    }

    public ResultLiveData<User> getUserResultLiveData() {
        return userResultLiveData;
    }
}
