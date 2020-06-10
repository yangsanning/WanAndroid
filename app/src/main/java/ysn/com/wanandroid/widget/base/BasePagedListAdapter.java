package ysn.com.wanandroid.widget.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import ysn.com.adapter.BaseViewHolder;
import ysn.com.wanandroid.R;

/**
 * @Author yangsanning
 * @ClassName BasePagedListAdapter
 * @Description paging 的 adapter
 * @Date 2020/6/8
 */
public class BasePagedListAdapter<Data, DataBinding extends ViewDataBinding> extends PagedListAdapter<Data, BaseViewHolder> {

    private static int ITEM_TYPE_FOOTER = 2020;

    private int layoutId;
    protected int variableId;

    public BasePagedListAdapter(@NonNull DiffUtil.ItemCallback<Data> diffCallback, @LayoutRes int layoutId, int variableId) {
        super(diffCallback);
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooter(position)) {
            return ITEM_TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    private boolean isFooter(int position) {
        return position == getItemCount() - 1;
    }

    @Override
    public int getItemCount() {
        if (getCurrentList() == null) {
            return super.getItemCount();
        }
        return super.getItemCount() + 1;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_TYPE_FOOTER) {
            return new BaseViewHolder(inflater.inflate(R.layout.item_paged_list_footer, parent, false));
        }
        DataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        return new BaseViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (isFooter(position)) {
            return;
        }
        DataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        final Data itemData = getItem(position);
        binding.setVariable(variableId, itemData);
        onBindViewHolder(itemData, binding, position);
        // 迫使数据立即绑定
        binding.executePendingBindings();
    }

    /**
     * 绑定数据
     */
    protected void onBindViewHolder(Data data, DataBinding binding, int position) {
    }
}
