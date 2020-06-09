package ysn.com.wanandroid.page.index;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableMap;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import ysn.com.mvvm.base.BaseLazyFragment;
import ysn.com.mvvm.utils.ImageUtils;
import ysn.com.mvvm.utils.ResUtils;
import ysn.com.mvvm.widget.adapter.BaseRecyclerAdapter;
import ysn.com.mvvm.widget.adapter.ItemViewManager;
import ysn.com.wanandroid.BR;
import ysn.com.wanandroid.R;
import ysn.com.wanandroid.databinding.FragmentIndexBinding;
import ysn.com.wanandroid.databinding.ItemArticleBinding;
import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.model.bean.Banner;
import ysn.com.wanandroid.model.bean.Navigation;
import ysn.com.wanandroid.widget.decoration.DefaultItemDecoration;

/**
 * @Author yangsanning
 * @ClassName IndexFragment
 * @Description 一句话概括作用
 * @Date 2020/5/25
 */
public class IndexFragment extends BaseLazyFragment<IndexViewModel, FragmentIndexBinding> {

    public boolean articleEnableRefresh;
    public RecyclerView.ItemDecoration articleItemDecoration;
    public ObservableMap<Class, ItemViewManager> articleItemViewManagerMap = new ObservableArrayMap<>();

    private BannerViewPager<Banner, IndexBannerViewHolder> bannerView;

    public static IndexFragment newInstance() {
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected IndexViewModel getViewModel() {
        return ViewModelProviders.of(this).get(IndexViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView() {
        dataBinding.setFragment(this);
        dataBinding.setVm(viewModel);

        bannerView = dataBinding.bannerView;
        bannerView.setAdapter(new IndexBannerAdapter()).create();

        initNavigation();
        initArticle();
    }

    private void initNavigation() {
        int[] icons = ResUtils.getResourcesIdArray(R.array.navigation_icon);
        String[] texts = ResUtils.getStringArray(R.array.navigation_text);
        List<Navigation> navigationList = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            navigationList.add(new Navigation(icons[i], texts[i]));
        }
        dataBinding.navigationRecyclerView.setLayoutManager(new GridLayoutManager(activityCast(), 4));
        dataBinding.navigationRecyclerView.setAdapter(new BaseRecyclerAdapter<>(navigationList, R.layout.item_index_navigation, BR.navigation));
    }

    private void initArticle() {
        articleItemDecoration = new DefaultItemDecoration().addTopDecoration(0, ResUtils.getDimension(R.dimen.app_space_small));
        articleItemViewManagerMap.put(Article.class, new ItemViewManager<Article, ItemArticleBinding>
                (R.layout.item_article, (binding, article) -> {
                    binding.setArticle(article);
                    binding.getRoot().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("功能开发中");
                        }
                    });
                }));
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

    public static class IndexBannerAdapter extends BaseBannerAdapter<Banner, IndexBannerViewHolder> {

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