package ysn.com.mvvm.widget.recycler_view;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.reactivex.annotations.NonNull;
import ysn.com.mvvm.widget.adapter.BaseRecyclerAdapter;

/**
 * @Author yangsanning
 * @ClassName ISuperRecyclerView
 * @Description 一句话概括作用
 * @Date 2020/5/26
 */
public interface ISuperRecyclerView {

    SuperRecyclerView setLayoutManager(RecyclerView.LayoutManager layoutManager);

    SuperRecyclerView addItemDecoration(RecyclerView.ItemDecoration decor);

    SuperRecyclerView setAdapter(BaseRecyclerAdapter adapter);

    SuperRecyclerView setEnableRefresh(boolean enabled);

    SuperRecyclerView setOnRefreshListener(SuperRecyclerView.OnRefreshListener refreshListener);

    SuperRecyclerView setOnLoadListener(SuperRecyclerView.OnLoadMoreListener loadMoreListener);

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