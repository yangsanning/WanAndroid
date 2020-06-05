package ysn.com.mvvm.widget.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author yangsanning
 * @ClassName BaseViewHolder
 * @Description 结合 DataBinding 的 RecyclerView Holder
 * @Date 2020/5/26
 */
public final class BaseViewHolder extends RecyclerView.ViewHolder {

    public ItemViewManager itemViewManager;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(@NonNull ViewGroup parent, @NonNull ItemViewManager itemViewManager) {
        super(itemViewManager.getItemView(parent));
        this.itemViewManager = itemViewManager;
    }
}
