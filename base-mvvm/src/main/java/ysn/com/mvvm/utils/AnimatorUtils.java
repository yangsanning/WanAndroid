package ysn.com.mvvm.utils;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

/**
 * @Author yangsanning
 * @ClassName AnimatorUtils
 * @Description 动画工具类
 * @Date 2020/5/28
 */
public class AnimatorUtils {

    public static final LinearOutSlowInInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();
    public static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();

    /**
     * 改变Y轴坐标
     *
     * @param view         需要改变的控件
     * @param duration     动画持续时间
     * @param interpolator 插值器
     * @param values       变化值
     */
    public static void translationY(final View view, long duration, Interpolator interpolator, float... values) {
        ofFloat((animation, value) -> view.setTranslationY(value), duration, interpolator, values);
    }

    /**
     * ofFloat
     *
     * @param onUpdateListener Update 监听
     * @param duration         动画持续时间
     * @param interpolator     插值器
     * @param values           变化值
     */
    public static void ofFloat(OnUpdateListener onUpdateListener, long duration, Interpolator interpolator, float... values) {
        final ValueAnimator animator = ValueAnimator.ofFloat(values);
        animator.addUpdateListener(valueAnimator -> {
            float value = (Float) animator.getAnimatedValue();
            onUpdateListener.onAnimationUpdate(animator, value);
        });
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        animator.start();
    }

    public interface OnUpdateListener {

        void onAnimationUpdate(ValueAnimator animation, float value);
    }
}
