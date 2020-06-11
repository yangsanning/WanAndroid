package ysn.com.mvvm.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import ysn.com.mvvm.utils.DeviceUtils;
import ysn.com.mvvm.utils.ResUtils;
import ysn.com.mvvm.utils.UiUtils;

/**
 * @Author yangsanning
 * @ClassName BasePopupWindow
 * @Description 一句话概括作用
 * @Date 2020/6/10
 */
public abstract class BasePopupWindow<DataBinding extends ViewDataBinding> extends PopupWindow implements PopupWindow.OnDismissListener {

    protected View mRootView;
    protected Activity mActivity;
    protected DataBinding dataBinding;

    protected OnPopDismissListener onPopDismissListener;

    public BasePopupWindow(Activity mActivity) {
        super(mActivity);
        this.mActivity = mActivity;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = inflater.inflate(getLayoutId(), null);
        this.setContentView(mRootView);

        initBase();
        initView();
        initListener();
    }

    @Override
    public void setContentView(View contentView) {
        super.setContentView(contentView);
        dataBinding = DataBindingUtil.bind(contentView);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {
        // 单击弹出窗以外处 关闭弹出窗
        mRootView.setOnTouchListener((v, event) -> {
            if (getTopView() != null) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getY() < getTopView().getTop()
                            || event.getY() > getTopView().getBottom()
                            || event.getX() < getTopView().getLeft()
                            || event.getX() > getTopView().getRight()) {
                        dismiss();
                    }
                }
            }
            return true;
        });

        this.setOnDismissListener(this);
    }

    private void initBase() {
        // 设置动画效果
        this.setAnimationStyle(getAnimationStyle());
        this.setWidth(getWidth());
        this.setHeight(getHeight());
        // 设置可触
        this.setFocusable(isFocusable());
        this.setBackgroundDrawable(getBackground());
    }

    protected abstract int getLayoutId();

    protected abstract View getTopView();

    protected abstract void initView();

    @Override
    public int getAnimationStyle() {
        return R.style.BasePopupWindowAnimation;
    }

    @Override
    public Drawable getBackground() {
        return new ColorDrawable(ResUtils.getColor(R.color.app_transparent));
    }

    @Override
    public int getHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override
    public void onDismiss() {
        UiUtils.setPopWindowBackgroundAlpha(mActivity, 1f);
        if (onPopDismissListener != null) {
            onPopDismissListener.onPopDismiss();
        }
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        showAtLocation(parent, gravity, x, y, 0.4f);
    }

    public void showAtLocation(View parent, int gravity, int x, int y, float bgAlpha) {
        if (mActivity == null || mActivity.isDestroyed()) {
            return;
        }
        UiUtils.setPopWindowBackgroundAlpha(mActivity, bgAlpha);
        super.showAtLocation(parent, gravity, x, y);
    }

    /**
     * 默认显示在 anchorView 下方，当屏幕不足以显示弹窗时，显示才会显示在上方
     *
     * @param anchorView 目标控件
     */
    public void showAutoLocation(View anchorView) {
        showAtLocation(anchorView, Gravity.CENTER, Boolean.TRUE);
    }

    /**
     * 始终显示在下方
     */
    public void showAlwaysBelow(View anchor, int hGravity) {
        showAtLocation(anchor, hGravity, Boolean.FALSE);
    }

    /**
     * @param anchorView 目标控件
     * @param hGravity   方向
     * @param isAuto     true 时智能调节, false 时始终显示在下方
     */
    public void showAtLocation(View anchorView, int hGravity, boolean isAuto) {
        mRootView.measure(0, 0);
        int height = mRootView.getMeasuredHeight();
        int width = mRootView.getMeasuredWidth();

        setWidth(width);
        setHeight(height);

        int[] outLocation = {0, 0};
        anchorView.getLocationInWindow(outLocation);

        int anchorMeasuredHeight = anchorView.getMeasuredHeight();
        int anchorMeasuredWidth = anchorView.getMeasuredWidth();

        int screenHeight = DeviceUtils.getScreenHeight(mActivity);
        int xOffSet;
        switch (hGravity) {
            case Gravity.START:
            case Gravity.LEFT:
                xOffSet = outLocation[0];
                break;
            case Gravity.END:
            case Gravity.RIGHT:
                xOffSet = outLocation[0] + anchorMeasuredWidth - width;
                break;
            case Gravity.CENTER:
            default:
                xOffSet = outLocation[0] + (anchorMeasuredWidth - width) / 2;
        }
        int yOffSet;
        if (isAuto) {
            boolean isDown = outLocation[1] + anchorMeasuredHeight + height < screenHeight;
            yOffSet = isDown ? (outLocation[1] + anchorMeasuredHeight) : (outLocation[1] - height);
        } else {
            yOffSet = outLocation[1] + anchorMeasuredHeight;
        }
        super.showAtLocation((View) anchorView.getParent(), Gravity.TOP | Gravity.LEFT, xOffSet, yOffSet);
    }

    public void setOnPopDismissListener(OnPopDismissListener onPopDismissListener) {
        this.onPopDismissListener = onPopDismissListener;
    }

    public interface OnPopDismissListener {

        void onPopDismiss();
    }
}

