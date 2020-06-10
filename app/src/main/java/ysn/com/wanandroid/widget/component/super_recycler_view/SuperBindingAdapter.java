package ysn.com.wanandroid.widget.component.super_recycler_view;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;
import java.util.Set;

import ysn.com.adapter.BaseEmptyRecyclerAdapter;
import ysn.com.adapter.ItemViewManager;
import ysn.com.wanandroid.network.NetworkResultList;

/**
 * @Author yangsanning
 * @ClassName SuperBindingAdapter
 * @Description {@link SuperRecyclerView} 的绑定事件
 * @Date 2020/6/8
 */
public class SuperBindingAdapter {

    @BindingAdapter(value = {"super:bindingResult"})
    public static void setSuperBindingResult(SuperRecyclerView view, SuperBindingResult superBindingResult) {
        if (superBindingResult == null) {
            return;
        }
        NetworkResultList result = superBindingResult.getNetworkResultList();
        SuperBindingResult.State state = superBindingResult.getState();
        if (state == null || result == null) {
            return;
        }
        switch (state) {
            case REFRESH_SUCCESS:
                view.refreshSuccess(result.datas, result.curPage, result.pageCount);
                break;
            case REFRESH_FAILURE:
                view.refreshFailure();
                break;
            case LOAD_MORE_SUCCESS:
                view.loadMoreSuccess(result.datas, result.curPage, result.pageCount);
                break;
            case LOAD_MORE_FAILURE:
                view.loadMoreFailure();
                break;
            default:
                break;
        }
    }

    @BindingAdapter(value = {"super:itemViewManagerMap"})
    public static void setAdapter(SuperRecyclerView view, Map<Class, ItemViewManager> itemViewManagerMap) {
        BaseEmptyRecyclerAdapter adapter = getOrCreateAdapter(view);
        Set<Map.Entry<Class, ItemViewManager>> entries = itemViewManagerMap.entrySet();
        for (Map.Entry<Class, ItemViewManager> entry : entries) {
            adapter.register(entry.getKey(), entry.getValue());
        }
    }

    private static BaseEmptyRecyclerAdapter getOrCreateAdapter(SuperRecyclerView view) {
        if (view.getAdapter() != null) {
            return view.getAdapter();
        } else {
            final BaseEmptyRecyclerAdapter adapter = new BaseEmptyRecyclerAdapter();
            view.setAdapter(adapter);
            return adapter;
        }
    }

    @BindingAdapter(value = {"super:onRefresh"})
    public static void setOnRefreshListener(SuperRecyclerView view, SuperRecyclerView.OnRefreshListener listener) {
        view.setOnRefreshListener(listener);
    }

    @BindingAdapter(value = {"super:onRefresh", "super:enableRefresh"})
    public static void setOnRefreshListener(SuperRecyclerView view, SuperRecyclerView.OnRefreshListener listener,
                                            boolean enableRefresh) {
        view.setOnRefreshListener(listener);
        view.setEnableRefresh(enableRefresh);
    }

    @BindingAdapter("super:onLoadMore")
    public static void setOnLoadMoreListener(SuperRecyclerView view, SuperRecyclerView.OnLoadMoreListener listener) {
        view.setOnLoadListener(listener);
    }

    @BindingAdapter("super:itemDecoration")
    public static void addItemDecoration(SuperRecyclerView view, RecyclerView.ItemDecoration itemDecoration) {
        view.addItemDecoration(itemDecoration);
    }
}
