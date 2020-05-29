package ysn.com.mvvm.wanandroid;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import ysn.com.mvvm.base.BaseDataBingActivity;
import ysn.com.mvvm.wanandroid.databinding.ActivityMainBinding;
import ysn.com.mvvm.wanandroid.utils.AppAnimatorUtils;
import ysn.com.mvvm.wanandroid.widget.adapter.MainPageAdapter;

public class MainActivity extends BaseDataBingActivity<ActivityMainBinding> {

    private int appBarHeight;
    private CoordinatorLayout.LayoutParams appBarLayoutParams;

    private long exitTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        initViewPager();
        initBottomMenuLayout();
    }

    private void initViewPager() {
        // 禁止滑动
        dataBinding.viewPager.setUserInputEnabled(false);
        dataBinding.viewPager.setAdapter(new MainPageAdapter(this));
        dataBinding.viewPager.setOffscreenPageLimit(dataBinding.viewPager.getAdapter().getItemCount());
    }

    private void initBottomMenuLayout() {
        dataBinding.appBarLayout.measure(0, 0);
        appBarHeight = dataBinding.appBarLayout.getMeasuredHeight();
        appBarLayoutParams = (CoordinatorLayout.LayoutParams) dataBinding.appBarLayout.getLayoutParams();
        dataBinding.bottomMenuLayout.setOnMenuItemSelectedListener((menuItemView, previousPosition, currentPosition) -> {
            if (currentPosition == 0) {
                setAppLayoutHeight(appBarHeight);
            } else {
                setAppLayoutHeight(0);
            }
        });
        dataBinding.bottomMenuLayout.setViewPager2(dataBinding.viewPager);
    }

    private void setAppLayoutHeight(int height) {
        appBarLayoutParams.height = height;
        dataBinding.appBarLayout.setLayoutParams(appBarLayoutParams);
    }

    @Override
    public void onBackPressed() {
        // 判断按钮是否隐藏
        if (dataBinding.bottomMenuLayout.getTranslationY() > 0) {
            if (dataBinding.bottomMenuLayout.getCurrentPosition() == 0) {
                AppAnimatorUtils.showMainAppBar(appBarLayoutParams.getBehavior(), appBarHeight);
            }
            AppAnimatorUtils.showMainBottomMenu(dataBinding.bottomMenuLayout);
        } else if ((System.currentTimeMillis() - exitTime) > 2000) {
            showMessage("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }
}