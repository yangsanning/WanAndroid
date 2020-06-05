package ysn.com.mvvm.widget.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangsanning
 * @ClassName BaseMultiRecyclerAdapter
 * @Description 结合 DataBinding 的多类型 RecyclerView Adapter
 * @Date 2020/6/5
 */
public class BaseMultiRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Object> datas;
    protected MultiItemTypeManager multiItemTypeManager;

    public BaseMultiRecyclerAdapter() {
        this(null);
    }

    public BaseMultiRecyclerAdapter(List<Object> datas) {
        this.datas = datas == null ? new ArrayList<>() : datas;
        multiItemTypeManager = new MultiItemTypeManager();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new BaseViewHolder(parent, multiItemTypeManager.getItemViewManager(getData(position)));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        final Object data = getData(position);
        // 回调出去给用户使用
        holder.itemViewManager.onBindViewHolder(binding, data);
        // 迫使数据立即绑定
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type = multiItemTypeManager.getItemType(getData(position));
        if (type < 0) {
            throw new RuntimeException("没有找到 " + getData(position).getClass() + " 对应的 ItemViewManager");
        }
        return type;
    }

    private Object getData(int position) {
        return datas.get(position);
    }

    public List<Object> getDatas() {
        return datas;
    }

    /**
     * 设置新数据
     */
    public void setNewDatas(List<Object> data) {
        this.datas.clear();
        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     */
    public void addData(Object data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     */
    public void addData(List<Object> data) {
        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 为数据源注册 ItemView 的资源管理器 {@link ItemViewManager}
     * 通过{@link MultiItemTypeManager}管理不同的 {@link ItemViewManager}
     *
     * @param cls             数据源class
     * @param itemViewManager ItemView 的资源管理器
     * @param <Data>          数据
     * @param <DataBinding>   ViewHolder
     */
    public <Data, DataBinding extends ViewDataBinding> void register(@NonNull Class<Data> cls,
                                                                     @NonNull ItemViewManager<Data, DataBinding> itemViewManager) {
        multiItemTypeManager.register(cls, itemViewManager);
    }
}