package ysn.com.wanandroid.widget.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lazy.library.logging.Logcat;

import ysn.com.wanandroid.TestFragment;
import ysn.com.wanandroid.page.index.IndexFragment;

/**
 * @Author yangsanning
 * @ClassName MainPageAdapter
 * @Description 主页 Adapter
 * @Date 2020/5/25
 */
public class MainPageAdapter extends FragmentStateAdapter {

    public MainPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return IndexFragment.newInstance();
            case 1:
                return TestFragment.newInstance("体系");
            case 2:
                return TestFragment.newInstance("项目");
            default:
                return TestFragment.newInstance("我的");
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
