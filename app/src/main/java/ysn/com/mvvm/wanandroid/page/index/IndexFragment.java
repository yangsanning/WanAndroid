package ysn.com.mvvm.wanandroid.page.index;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

import ysn.com.mvvm.base.BaseLazyFragment;
import ysn.com.mvvm.utils.ImageUtils;
import ysn.com.mvvm.wanandroid.R;
import ysn.com.mvvm.wanandroid.bean.Banner;
import ysn.com.mvvm.wanandroid.databinding.FragmentIndexBinding;

/**
 * @Author yangsanning
 * @ClassName IndexFragment
 * @Description 一句话概括作用
 * @Date 2020/5/25
 */
public class IndexFragment extends BaseLazyFragment<IndexViewModel, FragmentIndexBinding> {

    private BannerViewPager<Banner, IndexBannerViewHolder> bannerView;

    public static IndexFragment newInstance() {
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected IndexViewModel getViewModel() {
        return new IndexViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView() {
        bannerView = dataBinding.bannerView;
        bannerView.setAdapter(new IndexBannerAdapter()).create();
    }

    @Override
    protected void lazyLoad() {
        viewModel.getBannerResultLiveData().observe(this, bannerResult ->
                bannerView.refreshData(bannerResult.getData()));

        viewModel.init();
    }

    @Override
    public void onResume() {
        super.onResume();
        bannerView.startLoop();
    }

    @Override
    public void onPause() {
        super.onPause();
        bannerView.stopLoop();
    }

    private static class IndexBannerAdapter extends BaseBannerAdapter<Banner, IndexBannerViewHolder> {

        @Override
        protected void onBind(IndexBannerViewHolder holder, Banner data, int position, int pageSize) {
            holder.bindData(data, position, pageSize);
        }

        @Override
        public IndexBannerViewHolder createViewHolder(View itemView, int viewType) {
            return new IndexBannerViewHolder(itemView);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_index_banner;
        }
    }

    private static class IndexBannerViewHolder extends BaseViewHolder<Banner> {

        public IndexBannerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(Banner data, int position, int pageSize) {
            ImageView imageView = findView(R.id.index_banner_item_image);
            ImageUtils.loadImage(imageView.getContext(), data.getImagePath(), imageView);
        }
    }
}