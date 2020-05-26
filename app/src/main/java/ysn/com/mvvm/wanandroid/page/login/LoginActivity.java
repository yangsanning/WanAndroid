package ysn.com.mvvm.wanandroid.page.login;

import androidx.lifecycle.ViewModelProviders;

import ysn.com.mvvm.base.BaseActivity;
import ysn.com.mvvm.wanandroid.R;
import ysn.com.mvvm.wanandroid.databinding.ActivityLoginBinding;
import ysn.com.mvvm.wanandroid.utils.PageUtils;

/**
 * @Author yangsanning
 * @ClassName LoginActivity
 * @Description 登录页面
 * @Date 2020/5/22
 */
public class LoginActivity extends BaseActivity<LoginViewModel, ActivityLoginBinding> {

    @Override
    protected LoginViewModel getViewModel() {
        return ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        dataBinding.setVm(viewModel);
        dataBinding.loginPasswordLayout.setEndIconDrawable(R.drawable.selector_pwd);

        viewModel.getUserResultLiveData().observe(this, userStateData -> {
            PageUtils.startMainActivity(LoginActivity.this);
            finish();
        });
    }
}
