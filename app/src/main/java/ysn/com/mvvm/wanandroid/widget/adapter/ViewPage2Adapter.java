package ysn.com.mvvm.wanandroid.widget.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

import ysn.com.mvvm.base.BaseDataBingFragment;

/**
 * @Author yangsanning
 * @ClassName ViewPage2Adapter
 * @Description 一句话概括作用
 * @Date 2020/5/25
 */
public class ViewPage2Adapter extends FragmentStateAdapter {

    private ArrayList<BaseDataBingFragment> fragmentList = new ArrayList<>();

    public ViewPage2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public ViewPage2Adapter addFragment(BaseDataBingFragment fragment) {
        fragmentList.add(fragment);
        return this;
    }

    public void clear() {
        fragmentList.clear();
    }

    public BaseDataBingFragment getFragment(int position) {
        if (fragmentList.isEmpty() || position >= fragmentList.size()) {
            return null;
        }
        return fragmentList.get(position);
    }
}
