package ysn.com.wanandroid.widget.component.super_recycler_view;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ysn.com.mvvm.widget.adapter.BaseEmptyRecyclerAdapter;
import ysn.com.mvvm.widget.adapter.ItemViewManager;

/**
 * @Author yangsanning
 * @ClassName ISuperRecyclerView
 * @Description 一句话概括作用
 * @Date 2020/5/26
 */
public interface ISuperRecyclerView {

    SuperRecyclerView setLayoutManager(RecyclerView.LayoutManager layoutManager);

    SuperRecyclerView addItemDecoration(RecyclerView.ItemDecoration decor);

    SuperRecyclerView setAdapter(BaseEmptyRecyclerAdapter adapter);

    <Data, DataBinding extends ViewDataBinding> SuperRecyclerView registerEmpty(ItemViewManager<Data, DataBinding> itemViewManager);

    SuperRecyclerView register(Class<?> cls, ItemViewManager itemViewManager);

    SuperRecyclerView setEnableRefresh(boolean enabled);

    SuperRecyclerView setEnableAutoLoadMore(boolean enabled);

    SuperRecyclerView setOnRefreshListener(SuperRecyclerView.OnRefreshListener refreshListener);

    SuperRecyclerView setOnLoadListener(SuperRecyclerView.OnLoadMoreListener loadMoreListener);

    SuperRecyclerView showLoading();

    SuperRecyclerView showError();

    SuperRecyclerView showEmpty();

    SuperRecyclerView refreshFailure();

    SuperRecyclerView refreshSuccess(int pages);

    SuperRecyclerView refreshSuccess(@NonNull List data);

    SuperRecyclerView refreshSuccess(@NonNull List data, int pages);

    SuperRecyclerView refreshSuccess(@NonNull List data, int pageNum, int pages);

    SuperRecyclerView loadMoreFailure();

    SuperRecyclerView loadMoreSuccess(int pageNum, int pages);

    SuperRecyclerView loadMoreSuccess(List data, int pageNum, int pages);

    SuperRecyclerView autoRefresh();
}