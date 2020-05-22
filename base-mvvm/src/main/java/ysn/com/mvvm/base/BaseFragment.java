package ysn.com.mvvm.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import ysn.com.mvvm.lifecycle.BaseViewModel;

/**
 * @Author yangsanning
 * @ClassName BaseFragment
 * @Description ViewModel + ViewDataBinding 基类
 * @Date 2020/5/22
 */
public abstract class BaseFragment<ViewModel extends BaseViewModel, DataBinding extends ViewDataBinding>
        extends BaseDataBingFragment<DataBinding> {

    protected ViewModel viewModel;

    /**
     * 初始化 ViewModel
     */
    @Override
    protected DataBinding initDataBinding(LayoutInflater inflater, int layoutId, ViewGroup container) {
        DataBinding dataBinding = super.initDataBinding(inflater, layoutId, container);
        viewModel = getViewModel();
        initObserve();
        return dataBinding;
    }

    /**
     * 初始化ViewModel
     */
    protected abstract ViewModel getViewModel();

    /**
     * 监听当前 ViewModel 中 showDialog和error的值
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
