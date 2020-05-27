package ysn.com.mvvm.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

/**
 * @Author yangsanning
 * @ClassName BindingUtils
 * @Description DataBinding 的工具类
 * @Date 2020/5/27
 */
public class BindingUtils {

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }
}