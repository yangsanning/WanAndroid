package ysn.com.wanandroid.widget.diff;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ysn.com.wanandroid.model.bean.Article;

/**
 * @Author yangsanning
 * @ClassName ArticleDiff
 * @Description {@link ysn.com.wanandroid.widget.base.BasePagedListAdapter} 的 Diff
 * @Date 2020/6/9
 */
public class ArticleDiff extends DiffUtil.ItemCallback<Article> {

    @Override
    public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
        return oldItem.equals(newItem);
    }
}
