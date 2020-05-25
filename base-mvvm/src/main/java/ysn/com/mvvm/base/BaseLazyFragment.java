package ysn.com.mvvm.base;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleObserver;

import ysn.com.mvvm.lifecycle.BaseViewModel;

/**
 * @Author yangsanning
 * @ClassName BaseLazyFragment
 * @Description 懒加载Fragment
 * @Date 2020/5/25
 */
public abstract class BaseLazyFragment<ViewModel extends BaseViewModel, DataBinding extends ViewDataBinding>
        extends BaseFragment<ViewModel, DataBinding> implements LifecycleObserver {

    private boolean visibleToUser;

    @Override
    public void onResume() {
        super.onResume();
        if (!visibleToUser) {
            visibleToUser = true;
            lazyLoad();
        }
    }

    /**
     * 懒加载，只有在Fragment第一次创建且第一次对用户可见
     */
    protected abstract void lazyLoad();
}
