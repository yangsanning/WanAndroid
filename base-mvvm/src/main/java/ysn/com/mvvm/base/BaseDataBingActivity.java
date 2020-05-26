package ysn.com.mvvm.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import ysn.com.mvvm.utils.ResUtils;
import ysn.com.mvvm.utils.ToastUtils;
import ysn.com.mvvm.widget.dialog.LoadingDialog;
import ysn.com.mvvm.utils.ActivityUtils;

/**
 * @Author yangsanning
 * @ClassName BaseDataBingActivity
 * @Description ViewDataBinding 基类(无ViewModel)
 * @Date 2020/5/22
 */
public abstract class BaseDataBingActivity<DataBinding extends ViewDataBinding> extends AppCompatActivity {

    protected DataBinding dataBinding;
    protected LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.getInstance().addActivity(this);
        int layoutId = getLayoutId();
        setContentView(layoutId);

        dataBinding = initDataBinding(layoutId);
        initView();
    }

    /**
     * 获取布局资源 id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化 DataBinding
     */
    protected DataBinding initDataBinding(@LayoutRes int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId);
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataBinding != null) {
            dataBinding.unbind();
        }
        ActivityUtils.getInstance().removeActivity(this);
    }

    /**
     * 显示加载弹窗
     */
    protected void showLoadingDialog() {
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            loadingDialog = new LoadingDialog(this);
            loadingDialog.show();
        }
    }

    /**
     * 隐藏加载弹窗
     */
    protected void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    public void showMessage(@NonNull String msg) {
        ToastUtils.showNormalToast(msg);
    }

    public void showMessage(@StringRes int resId) {
        ToastUtils.showNormalToast(ResUtils.getString(resId));
    }

    public void showDelayedMessage(@StringRes int resId) {
        showDelayedMessage(ResUtils.getString(resId));
    }

    public void showDelayedMessage(@NonNull String msg) {
        ToastUtils.showDelayedToast(msg);
    }
}
