package ysn.com.mvvm.wanandroid.page.index;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import ysn.com.mvvm.base.BaseLazyFragment;
import ysn.com.mvvm.utils.ImageUtils;
import ysn.com.mvvm.utils.ResUtils;
import ysn.com.mvvm.wanandroid.BR;
import ysn.com.mvvm.wanandroid.R;
import ysn.com.mvvm.wanandroid.bean.Article;
import ysn.com.mvvm.wanandroid.bean.Banner;
import ysn.com.mvvm.wanandroid.bean.Navigation;
import ysn.com.mvvm.wanandroid.databinding.FragmentIndexBinding;
import ysn.com.mvvm.wanandroid.databinding.ItemIndexArticleBinding;
import ysn.com.mvvm.wanandroid.databinding.ItemIndexNavigationBinding;
import ysn.com.mvvm.wanandroid.widget.decoration.DefaultItemDecoration;
import ysn.com.mvvm.widget.adapter.BaseRecyclerAdapter;
import ysn.com.mvvm.widget.adapter.OnItemClickListener;

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
        return ViewModelProviders.of(this).get(IndexViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView() {
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
        NavigationAdapter navigationAdapter = new NavigationAdapter(navigationList);
        navigationAdapter.setOnItemClickListener(new OnItemClickListener<Navigation>() {
            @Override
            public void onItemClick(Navigation navigation, int position) {
                // todo: 跳转相应页面
            }
        });
        dataBinding.navigationRecyclerView.setLayoutManager(new GridLayoutManager(activityCast(), 4));
        dataBinding.navigationRecyclerView.setAdapter(navigationAdapter);

    }

    private void initArticle() {
        dataBinding.articleSuperRecyclerView
                .setOnRefreshListener(() -> viewModel.getArticleList(0))
                .setEnableRefresh(false)
                .setOnLoadListener(pageNum -> viewModel.getArticleList(pageNum))
                .addItemDecoration(new DefaultItemDecoration()
                        .addTopDecoration(0, ResUtils.getDimension(R.dimen.app_space_small)))
                .setAdapter(new ArticleAdapter());
    }

    @Override
    protected void lazyLoad() {
        viewModel.getBannerResultLiveData().observe(this, bannerResult ->
                bannerView.refreshData(bannerResult.getData()));

        viewModel.init(dataBinding.articleSuperRecyclerView.getSuperRecyclerView());
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

    private static class NavigationAdapter extends BaseRecyclerAdapter<Navigation, ItemIndexNavigationBinding> {

        public NavigationAdapter(List<Navigation> data) {
            super(data, R.layout.item_index_navigation, BR.navigation);
        }
    }

    private static class ArticleAdapter extends BaseRecyclerAdapter<Article, ItemIndexArticleBinding> {

        public ArticleAdapter() {
            super(R.layout.item_index_article, BR.article);
        }

        @Override
        protected void onBindViewHolder(Article article, ItemIndexArticleBinding binding, int position) {
            super.onBindViewHolder(article, binding, position);
        }
    }
}