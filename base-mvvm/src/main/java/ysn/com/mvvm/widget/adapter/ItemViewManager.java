package ysn.com.mvvm.widget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @Author yangsanning
 * @ClassName ItemViewManager
 * @Description ItemView 的资源管理器
 * @Date 2020/6/5
 */
public class ItemViewManager<Data, DataBinding extends ViewDataBinding> {

    @LayoutRes
    private int layoutId;
    private ItemViewBinder<Data, DataBinding> itemViewBinder;

    /**
     * 仅需要绑定 variableId , 不需要做过多处理时，调用此构造函数
     *
     * @param layoutId   布局文件资源id
     * @param variableId 要绑定变量的 BR id
     */
    public ItemViewManager(@LayoutRes int layoutId, int variableId) {
        this(layoutId, (dataBinding, data) -> dataBinding.setVariable(variableId, data));
    }

    /**
     * 此构造方法，会回调{@link ItemViewBinder#onBindViewHolder(Object, Object)} 方法，
     * 注意：调用此构造函数，需要自己手动绑定BR变量
     *
     * @param layoutId       布局文件资源id
     * @param itemViewBinder 数据绑定回调接口，在回调方法中自定义绑定逻辑
     */
    public ItemViewManager(@LayoutRes int layoutId, @NonNull ItemViewBinder<Data, DataBinding> itemViewBinder) {
        this.layoutId = layoutId;
        this.itemViewBinder = itemViewBinder;
    }

    protected int getLayoutId() {
        return layoutId;
    }

    /**
     * 获取 ItemView
     */
    protected View getItemView(@NonNull ViewGroup parent) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false).getRoot();
    }

    /**
     * 绑定数据到 {@link ItemViewBinder}
     *
     * @param dataBinding item 对应 dataBinding类
     * @param data        数据
     */
    protected void onBindViewHolder(DataBinding dataBinding, Data data) {
        itemViewBinder.onBindViewHolder(dataBinding, data);
    }

    /**
     * 数据绑定回调接口，在回调方法中自定义绑定逻辑
     */
    public interface ItemViewBinder<Data, DataBinding> {

        void onBindViewHolder(DataBinding dataBinding, Data data);
    }
}