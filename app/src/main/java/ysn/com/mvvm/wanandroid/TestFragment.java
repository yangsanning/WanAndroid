package ysn.com.mvvm.wanandroid;

import android.os.Bundle;

import ysn.com.mvvm.base.BaseDataBingFragment;
import ysn.com.mvvm.wanandroid.databinding.FrgamentTestBinding;

/**
 * @Author yangsanning
 * @ClassName TestFragment
 * @Description 一句话概括作用
 * @Date 2020/5/25
 */
public class TestFragment extends BaseDataBingFragment<FrgamentTestBinding> {

    private static final String BUNDLE_TEST = "BUNDLE_TEST";

    public static TestFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_TEST, text);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TestFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frgament_test;
    }

    @Override
    protected void initView() {
        dataBinding.setText(getArguments().getString(BUNDLE_TEST));
    }
}
