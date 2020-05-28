package ysn.com.mvvm.utils;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

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

    public static void translationY(final View view, float start, float end) {
        final ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        view.setVisibility(View.VISIBLE);
        animator.addUpdateListener(valueAnimator -> view.setTranslationY((Float) animator.getAnimatedValue()));
        animator.setDuration(200);
        animator.setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR);
        animator.start();
    }

    public static void ofFloat(OnUpdateListener onUpdateListener, long duration, float... values) {
        final ValueAnimator animator = ValueAnimator.ofFloat(values);
        animator.addUpdateListener(valueAnimator -> {
            float value = (Float) animator.getAnimatedValue();
            onUpdateListener.onAnimationUpdate(animator, value);
        });
        animator.setDuration(duration);
        animator.setInterpolator(ACCELERATE_INTERPOLATOR);
        animator.start();
    }

    public interface OnUpdateListener {

        void onAnimationUpdate(ValueAnimator animation, float value);
    }
}
