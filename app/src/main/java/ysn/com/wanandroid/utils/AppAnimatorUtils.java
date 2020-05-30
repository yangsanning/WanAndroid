package ysn.com.wanandroid.utils;

import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

import ysn.com.mvvm.utils.AnimatorUtils;

/**
 * @Author yangsanning
 * @ClassName AppAnimatorUtils
 * @Description 应用内动画工具类
 * @Date 2020/5/29
 */
public class AppAnimatorUtils {

    /**
     * 显示首页 AppBar
     */
    public static void showMainAppBar(CoordinatorLayout.Behavior behavior, int appBarHeight) {
        if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
            AnimatorUtils.ofFloat((animation, value) -> {
                appBarLayoutBehavior.setTopAndBottomOffset((int) value);
            }, 500, AnimatorUtils.ACCELERATE_INTERPOLATOR, -appBarHeight, 0);
        }
    }

    /**
     * 显示首页底部按钮
     */
    public static void showMainBottomMenu(View view) {
        float translationY = view.getTranslationY();
        AnimatorUtils.translationY(view, 200, AnimatorUtils.FAST_OUT_SLOW_IN_INTERPOLATOR, translationY, 0);
    }
}
