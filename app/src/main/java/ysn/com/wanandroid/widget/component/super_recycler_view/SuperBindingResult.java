package ysn.com.wanandroid.widget.component.super_recycler_view;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import ysn.com.wanandroid.network.NetworkResultList;

/**
 * @Author yangsanning
 * @ClassName SuperBindingResult
 * @Description {@link SuperRecyclerView 的翻页数据管理}
 * @Date 2020/6/8
 */
public class SuperBindingResult extends BaseObservable {

    private State state;
    private NetworkResultList networkResultList;

    public SuperBindingResult() {
    }

    @Bindable
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        notifyChange();
    }

    @Bindable
    public NetworkResultList getNetworkResultList() {
        return networkResultList;
    }

    public SuperBindingResult setNetworkResultList(NetworkResultList networkResultList) {
        this.networkResultList = networkResultList;
        return this;
    }

    public enum State {
        /**
         * 刷新成功
         */
        REFRESH_SUCCESS,

        /**
         * 刷新失败
         */
        REFRESH_FAILURE,

        /**
         * 加载更多成功
         */
        LOAD_MORE_SUCCESS,

        /**
         * 加载更多失败
         */
        LOAD_MORE_FAILURE,
    }
}
