package ysn.com.mvvm.wanandroid;

import ysn.com.mvvm.base.BaseDataBingActivity;
import ysn.com.mvvm.wanandroid.databinding.ActivityMainBinding;
import ysn.com.mvvm.wanandroid.widget.adapter.ViewPage2Adapter;

public class MainActivity extends BaseDataBingActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        initViewPager();
    }

    private void initViewPager() {
        ViewPage2Adapter viewPage2Adapter = new ViewPage2Adapter(this);
        viewPage2Adapter.addFragment(TestFragment.newInstance("首页"))
                .addFragment(TestFragment.newInstance("体系"))
                .addFragment(TestFragment.newInstance("项目"))
                .addFragment(TestFragment.newInstance("我的"));
        // 禁止滑动
        dataBinding.viewPager.setUserInputEnabled(false);
        dataBinding.viewPager.setAdapter(viewPage2Adapter);
        dataBinding.bottomMenuLayout.setViewPager2(dataBinding.viewPager);
    }
}