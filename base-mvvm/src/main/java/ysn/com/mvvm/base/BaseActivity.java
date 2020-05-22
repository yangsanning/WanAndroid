package ysn.com.mvvm.base;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import ysn.com.mvvm.lifecycle.BaseViewModel;

/**
 * @Author yangsanning
 * @ClassName BaseActivity
 * @Description ViewModel + ViewDataBinding 基类
 * @Date 2020/5/22
 */
public abstract class BaseActivity<ViewModel extends BaseViewModel, DataBinding extends ViewDataBinding>
        extends BaseDataBingActivity<DataBinding> {

    protected ViewModel viewModel;

    /**
     * 初始化 ViewModel
     */
    @Override
    protected DataBinding initDataBinding(int layoutId) {
        DataBinding dataBinding = super.initDataBinding(layoutId);
        viewModel = getViewModel();
        initObserve();
        return dataBinding;
    }

    /**
     * 初始化 ViewModel
     */
    protected abstract ViewModel getViewModel();

    /**
     * 监听当前ViewModel中 showDialog和error的值
     */
    private void initObserve() {
        if (viewModel == null) {
            return;
        }
        viewModel.observeLoadingDialog(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bean) {
                if (bean) {
                    showLoadingDialog();
                } else {
                    dismissLoadingDialog();
                }
            }
        });
    }
}
