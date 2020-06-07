package ysn.com.wanandroid.widget.component.super_recycler_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ysn.com.mvvm.widget.adapter.BaseEmptyRecyclerAdapter;
import ysn.com.mvvm.widget.adapter.ItemViewManager;
import ysn.com.wanandroid.BR;
import ysn.com.wanandroid.R;
import ysn.com.wanandroid.databinding.ViewSuperRecyclerViewBinding;

/**
 * @Author yangsanning
 * @ClassName SuperRecyclerView
 * @Description 一句话概括作用
 * @Date 2020/5/26
 */
public class SuperRecyclerView extends LinearLayout implements ISuperRecyclerView {

    private ViewSuperRecyclerViewBinding dataBinding;

    private BaseEmptyRecyclerAdapter adapter;

    private int pageNum, pages;
    protected boolean enableRefresh, enableLoadMore;
    protected OnRefreshListener refreshListener;
    private ItemViewManager loadingItemViewManager;
    private ItemViewManager emptyItemViewManager;
    private ItemViewManager errorItemViewManager;

    public SuperRecyclerView(Context context) {
        this(context, null);
    }

    public SuperRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.view_super_recycler_view, this, true);

        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        dataBinding.recyclerView.setHasFixedSize(true);

        loadingItemViewManager = new ItemViewManager<>(R.layout.layout_loading, BR.loading);
        emptyItemViewManager = new ItemViewManager<>(R.layout.layout_empty, this::onBindViewHolder);
        errorItemViewManager = new ItemViewManager<>(R.layout.layout_error, this::onBindViewHolder);
    }

    public void onBindViewHolder(ViewDataBinding viewDataBinding, BaseEmptyRecyclerAdapter.EmptyData emptyData) {
        viewDataBinding.getRoot().setOnClickListener(v -> {
            if (refreshListener != null) {
                showLoading();
                postDelayed(() -> refreshListener.onRefresh(), 500);
            }
        });
    }

    @Override
    public SuperRecyclerView setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        dataBinding.recyclerView.setLayoutManager(layoutManager);
        return this;
    }

    @Override
    public SuperRecyclerView addItemDecoration(RecyclerView.ItemDecoration decor) {
        dataBinding.recyclerView.addItemDecoration(decor);
        return this;
    }

    @Override
    public SuperRecyclerView setAdapter(BaseEmptyRecyclerAdapter adapter) {
        this.adapter = adapter;
        dataBinding.recyclerView.setAdapter(adapter);
        adapter.setEmptyData(new BaseEmptyRecyclerAdapter.EmptyData());
        showLoading();
        adapter.notifyDataSetChanged();
        return this;
    }

    @Override
    public <Data, DataBinding extends ViewDataBinding> SuperRecyclerView registerEmpty(
            @NonNull ItemViewManager<Data, DataBinding> itemViewManager) {
        adapter.registerEmpty(itemViewManager).setEmptyData(new BaseEmptyRecyclerAdapter.EmptyData());
        return this;
    }

    /**
     * 通过 itemTypeNameList 和 itemViewManagerList 两组集合对类型进行管理
     *
     * @param cls             数据源 class
     * @param itemViewManager ItemView 的资源管理器
     */
    @Override
    public SuperRecyclerView register(Class<?> cls, ItemViewManager itemViewManager) {
        adapter.register(cls, itemViewManager);
        return this;
    }

    @Override
    public SuperRecyclerView setEnableRefresh(boolean enabled) {
        dataBinding.refreshLayout.setEnableRefresh(enabled);
        enableRefresh = enabled;
        return this;
    }

    @Override
    public SuperRecyclerView setEnableAutoLoadMore(boolean enabled) {
        dataBinding.refreshLayout.setEnableAutoLoadMore(enabled);
        return this;
    }

    @Override
    public SuperRecyclerView setOnRefreshListener(OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
        if (refreshListener == null) {
            dataBinding.refreshLayout.setEnableRefresh(false);
            dataBinding.refreshLayout.setOnRefreshListener(null);
            enableRefresh = false;
        } else {
            dataBinding.refreshLayout.setEnableRefresh(true);
            dataBinding.refreshLayout.setOnRefreshListener(refreshLayout -> refreshListener.onRefresh());
            enableRefresh = true;
        }
        return this;
    }

    @Override
    public SuperRecyclerView setOnLoadListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener == null) {
            dataBinding.refreshLayout.setEnableLoadMore(false);
            dataBinding.refreshLayout.setOnLoadMoreListener(null);
            enableLoadMore = false;
        } else {
            dataBinding.refreshLayout.setEnableLoadMore(true);
            dataBinding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
                if (pageNum > pages) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    loadMoreListener.onLoadMore(pageNum);
                }
            });
            enableLoadMore = true;
        }
        return this;
    }

    @Override
    public SuperRecyclerView showLoading() {
        adapter.registerEmpty(loadingItemViewManager);
        adapter.notifyDataSetChanged();
        dataBinding.refreshLayout.setEnableRefresh(false);
        dataBinding.refreshLayout.setEnableLoadMore(false);
        return this;
    }

    @Override
    public SuperRecyclerView showError() {
        adapter.registerEmpty(errorItemViewManager);
        dataBinding.refreshLayout.setEnableRefresh(false);
        dataBinding.refreshLayout.setEnableLoadMore(false);
        return this;
    }

    @Override
    public SuperRecyclerView showEmpty() {
        adapter.registerEmpty(emptyItemViewManager);
        dataBinding.refreshLayout.setEnableRefresh(false);
        dataBinding.refreshLayout.setEnableLoadMore(false);
        return this;
    }

    @Override
    public SuperRecyclerView refreshFailure() {
        dataBinding.refreshLayout.finishRefresh(false);
        if (adapter.getDatas().isEmpty()) {
            showError();
        }
        return this;
    }

    @Override
    public SuperRecyclerView refreshSuccess(int pages) {
        this.pageNum = 2;
        this.pages = pages;
        refreshLoadingLayout();
        return this;
    }

    @Override
    public SuperRecyclerView refreshSuccess(List data) {
        return refreshSuccess(data, data.size());
    }

    @Override
    public SuperRecyclerView refreshSuccess(List data, int pages) {
        return refreshSuccess(data, 2, pages);
    }

    @Override
    public SuperRecyclerView refreshSuccess(List data, int pageNum, int pages) {
        adapter.setNewDatas(data);
        this.pageNum = pageNum;
        this.pages = pages;
        refreshLoadingLayout();
        return this;
    }

    @Override
    public SuperRecyclerView loadMoreFailure() {
        dataBinding.refreshLayout.finishLoadMore(false);
        return this;
    }

    @Override
    public SuperRecyclerView loadMoreSuccess(int pageNum, int pages) {
        this.pageNum = pageNum;
        this.pages = pages;
        dataBinding.refreshLayout.finishLoadMore(true);
        return this;
    }

    @Override
    public SuperRecyclerView loadMoreSuccess(List data, int pageNum, int pages) {
        adapter.addData(data);
        this.pageNum = pageNum;
        this.pages = pages;
        dataBinding.refreshLayout.finishLoadMore(true);
        return this;
    }

    @Override
    public SuperRecyclerView autoRefresh() {
        dataBinding.refreshLayout.autoRefresh();
        return this;
    }

    protected void refreshLoadingLayout() {
        dataBinding.refreshLayout.finishRefresh(true);
        if (adapter.getDatas().isEmpty()) {
            showEmpty();
        } else {
            adapter.registerEmpty(emptyItemViewManager);
            dataBinding.refreshLayout.setEnableRefresh(enableRefresh);
            dataBinding.refreshLayout.setEnableLoadMore(enableLoadMore);
            dataBinding.refreshLayout.setNoMoreData(false);
        }
        if (pageNum > pages) {
            dataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    public ISuperRecyclerView getSuperRecyclerView() {
        return this;
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int pageNum);
    }
}