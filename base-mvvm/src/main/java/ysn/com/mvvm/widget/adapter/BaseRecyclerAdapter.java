package ysn.com.mvvm.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangsanning
 * @ClassName BaseRecyclerAdapter
 * @Description 结合 DataBinding 的 RecyclerView Adapter
 * @Date 2020/5/26
 */
public class BaseRecyclerAdapter<Data, DataBinding extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Data> data;
    private int layoutId;
    protected Context context;
    protected int variableId;
    protected OnItemClickListener<Data> onItemClickListener;
    protected OnItemLongClickListener<Data> onItemLongClickListener;

    public BaseRecyclerAdapter(@LayoutRes int layoutId, int variableId) {
        this.layoutId = layoutId;
        this.variableId = variableId;
        data = new ArrayList<>();
    }

    public BaseRecyclerAdapter(List<Data> data, @LayoutRes int layoutId, int variableId) {
        this.data = data == null ? new ArrayList<Data>() : data;
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        DataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        return new BaseViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        DataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        final Data itemData = data.get(position);
        binding.setVariable(variableId, itemData);
        onBindViewHolder(itemData, binding, position);
        // 迫使数据立即绑定
        binding.executePendingBindings();

        // 设置点击事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(itemData, position);
                }
            });
        }

        // 设置长按事件
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onItemLongClickListener.onItemLongClick(itemData, position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 绑定数据
     */
    protected void onBindViewHolder(Data data, DataBinding binding, int position) {
    }

    public List<Data> getData() {
        return data;
    }

    /**
     * 设置新数据
     */
    public void setNewData(List<Data> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(Data data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     */
    public void addData(List<Data> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 设置Item 点击事件
     */
    public void setOnItemClickListener(OnItemClickListener<Data> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置Item 长按事件
     */
    public void setOnItemLongClickListener(OnItemLongClickListener<Data> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
