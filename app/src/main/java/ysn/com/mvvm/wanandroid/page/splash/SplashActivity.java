package ysn.com.mvvm.wanandroid.page.splash;

import android.view.KeyEvent;

import androidx.lifecycle.ViewModelProviders;

import ysn.com.mvvm.base.BaseActivity;
import ysn.com.mvvm.utils.ImageUtils;
import ysn.com.mvvm.utils.ResUtils;
import ysn.com.mvvm.wanandroid.R;
import ysn.com.mvvm.wanandroid.databinding.ActivitySplashBinding;

/**
 * @Author yangsanning
 * @ClassName SplashActivity
 * @Description 启动页
 * @Date 2020/5/30
 */
public class SplashActivity extends BaseActivity<SplashViewModel, ActivitySplashBinding> {

    @Override
    protected SplashViewModel getViewModel() {
        return ViewModelProviders.of(this).get(SplashViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        viewModel.checkPermissions(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 启动页禁止返回键退出。
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
