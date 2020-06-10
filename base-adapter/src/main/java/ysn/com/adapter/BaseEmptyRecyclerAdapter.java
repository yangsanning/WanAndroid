package ysn.com.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import java.util.List;

/**
 * @Author yangsanning
 * @ClassName BaseEmptyRecyclerView
 * @Description 空布局 Adapter
 * @Date 2020/6/6
 */
public class BaseEmptyRecyclerAdapter extends BaseMultiRecyclerAdapter {

    protected EmptyData emptyData;

    @Override
    public int getItemCount() {
        if (isEmpty() && emptyData != null) {
            datas.add(emptyData);
            return 1;
        }
        return super.getItemCount();
    }

    @Override
    public List<Object> getDatas() {
        removeEmptyData();
        return super.getDatas();
    }

    @Override
    public void addData(List<Object> data) {
        removeEmptyData();
        super.addData(data);
    }

    @Override
    public void addData(Object data) {
        removeEmptyData();
        super.addData(data);
    }

    private void removeEmptyData() {
        if (isEmptyData()) {
            datas.remove(0);
        }
    }

    public boolean isEmptyData() {
        return datas.size() == 1 && datas.get(0) instanceof EmptyData;
    }

    public void setEmptyData(EmptyData emptyData) {
        this.emptyData = emptyData;
        notifyDataSetChanged();
    }

    /**
     * 为数据源注册为空时的 ItemView 资源管理器 {@link ItemViewManager}
     * 通过{@link MultiItemTypeManager}管理不同的 {@link ItemViewManager}
     *  @param <Data>          数据
     * @param <DataBinding>   ViewHolder
     * @param itemViewManager ItemView 的资源管理器
     * @return
     */
    public <Data, DataBinding extends ViewDataBinding> BaseEmptyRecyclerAdapter registerEmpty(
            @NonNull ItemViewManager<Data, DataBinding> itemViewManager) {
        multiItemTypeManager.register(EmptyData.class, itemViewManager);
        return this;
    }

    public static class EmptyData {

    }
}
