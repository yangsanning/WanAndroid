package ysn.com.mvvm.widget.adapter;

/**
 * @Author yangsanning
 * @ClassName OnItemLongClickListener
 * @Description RecyclerView Item 长按事件
 * @Date 2020/5/26
 */
public interface OnItemLongClickListener<Data> {

    /**
     * Item 长按事件
     *
     * @param data     item的数据
     * @param position item的下标
     */
    boolean onItemLongClick(Data data, int position);
}