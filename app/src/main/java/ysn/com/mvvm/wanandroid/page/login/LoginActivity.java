package ysn.com.mvvm.wanandroid.page.login;

import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import ysn.com.mvvm.base.BaseActivity;
import ysn.com.mvvm.utils.ResUtils;
import ysn.com.mvvm.utils.ToastUtils;
import ysn.com.mvvm.wanandroid.R;
import ysn.com.mvvm.wanandroid.databinding.ActivityLoginBinding;
import ysn.com.mvvm.wanandroid.utils.PageUtils;
import ysn.com.mvvm.wanandroid.widget.component.SimpleTextWatcher;

/**
 * @Author yangsanning
 * @ClassName LoginActivity
 * @Description 登录页面
 * @Date 2020/5/22
 */
public class LoginActivity extends BaseActivity<LoginViewModel, ActivityLoginBinding> {

    public TextWatcher userNameTextWatcher = new SimpleTextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            dataBinding.loginUserNameLayout.setErrorEnabled(false);
        }
    };

    public TextWatcher pswTextWatcher = new SimpleTextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            dataBinding.loginPasswordLayout.setErrorEnabled(false);
        }
    };

    @Override
    protected LoginViewModel getViewModel() {
        return new LoginViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        dataBinding.setActivity(this);
        dataBinding.loginPasswordLayout.setEndIconDrawable(R.drawable.selector_pwd);

        viewModel.getUserResultLiveData().observe(this, userStateData -> {
            switch (userStateData.getState()) {
                case SUCCESS:
                    PageUtils.startMainActivity(LoginActivity.this);
                    finish();
                    break;
                case FAILURE:
                    ToastUtils.showNormalToast(userStateData.getMsg());
                    break;
                default:
            }
        });
    }

    public void login(View view) {
        String userName = dataBinding.loginUserName.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            dataBinding.loginUserNameLayout.setError(ResUtils.getString(R.string.text_user_name_not_null));
            return;
        }
        String password = dataBinding.loginPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            dataBinding.loginPasswordLayout.setError(ResUtils.getString(R.string.text_pwd_not_null));
            return;
        }
        viewModel.login(userName, password);
    }
}
