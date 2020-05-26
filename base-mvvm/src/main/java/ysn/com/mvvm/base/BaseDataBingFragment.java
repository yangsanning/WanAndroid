package ysn.com.mvvm.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import ysn.com.mvvm.utils.ResUtils;
import ysn.com.mvvm.utils.ToastUtils;

/**
 * @Author yangsanning
 * @ClassName BaseDataBingFragment
 * @Description ViewDataBinding 基类(无ViewModel)
 * @Date 2020/5/22
 */
public abstract class BaseDataBingFragment<DataBinding extends ViewDataBinding> extends Fragment {

    protected DataBinding dataBinding;
    protected BaseDataBingActivity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mActivity = (BaseDataBingActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = initDataBinding(inflater, getLayoutId(), container);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    /**
     * 获取布局资源 id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化 DataBinding
     */
    protected DataBinding initDataBinding(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, layoutId, container, false);
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dataBinding != null) {
            dataBinding.unbind();
        }
    }

    /**
     * 显示加载弹窗
     */
    protected void showLoadingDialog() {
        mActivity.showLoadingDialog();
    }

    /**
     * 隐藏加载弹窗
     */
    protected void dismissLoadingDialog() {
        mActivity.dismissLoadingDialog();
    }

    public void showMessage(@StringRes int resId) {
        showMessage(ResUtils.getString(resId));
    }

    public void showMessage(@NonNull String msg) {
        ToastUtils.showNormalToast(msg);
    }

    public void showDelayedMessage(@StringRes int resId) {
        showDelayedMessage(ResUtils.getString(resId));
    }

    public void showDelayedMessage(@NonNull String msg) {
        ToastUtils.showDelayedToast(msg);
    }

    protected void finishActivity() {
        finishActivity(mActivity);
    }

    protected void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
        }
    }
}
