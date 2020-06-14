package ysn.com.wanandroid.widget.adapter;

import androidx.databinding.BindingAdapter;

import ysn.com.view.autoellipsistextview.AutoEllipsisTexView;

/**
 * @Author yangsanning
 * @ClassName AppBindingAdapter
 * @Description 整个App 的 BindingAdapter
 * @Date 2020/6/14
 */
public class AppBindingAdapter {

    @BindingAdapter(value = {"ysn:text",})
    public static void bindAutoEllipsisTexView(AutoEllipsisTexView view, String text) {
        view.setText(text);
    }
}
