package ysn.com.mvvm.wanandroid;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

import ysn.com.mvvm.base.BaseDataBingActivity;
import ysn.com.mvvm.utils.AnimatorUtils;
import ysn.com.mvvm.wanandroid.databinding.ActivityMainBinding;
import ysn.com.mvvm.wanandroid.page.index.IndexFragment;
import ysn.com.mvvm.wanandroid.widget.adapter.ViewPage2Adapter;

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
        ViewPage2Adapter viewPage2Adapter = new ViewPage2Adapter(this);
        viewPage2Adapter.addFragment(IndexFragment.newInstance())
                .addFragment(TestFragment.newInstance("体系"))
                .addFragment(TestFragment.newInstance("项目"))
                .addFragment(TestFragment.newInstance("我的"));
        dataBinding.viewPager.setOffscreenPageLimit(viewPage2Adapter.getItemCount());
        // 禁止滑动
        dataBinding.viewPager.setUserInputEnabled(false);
        dataBinding.viewPager.setAdapter(viewPage2Adapter);
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
                CoordinatorLayout.Behavior behavior = appBarLayoutParams.getBehavior();
                if (behavior instanceof AppBarLayout.Behavior) {
                    AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
                    AnimatorUtils.ofFloat((animation, value) -> {
                        appBarLayoutBehavior.setTopAndBottomOffset((int) value);
                    }, 500, -appBarHeight, 0);
                }
            }
            float translationY = dataBinding.bottomMenuLayout.getTranslationY();
            AnimatorUtils.translationY(dataBinding.bottomMenuLayout, translationY, 0);
        } else if ((System.currentTimeMillis() - exitTime) > 2000) {
            showMessage("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }
}