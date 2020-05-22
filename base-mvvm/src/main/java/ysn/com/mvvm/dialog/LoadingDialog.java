package ysn.com.mvvm.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import ysn.com.mvvm.base.R;

/**
 * @Author yangsanning
 * @ClassName LoadingDialog
 * @Description 加载弹窗
 * @Date 2020/5/22
 */
public class LoadingDialog {

    private Dialog loadingDialog;
    private Runnable runnable;
    private Runnable delayRunnable;
    private View view;
    private Handler handler;
    private long timeOut = 30000;

    public LoadingDialog(Activity activity) {
        init(activity);
        initTask();
    }

    public void init(Activity context) {
        // 首先得到整个View
        view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        loadingDialog = new Dialog(context, R.style.LoadingDialogTheme);
        loadingDialog.getWindow().setDimAmount(0f);
        handler = new Handler();
        setCanceledOnTouchOutside(true);
    }

    /**
     * 显示dialog
     */
    public void show() {
        handler.postDelayed(delayRunnable, 50);
    }

    private void showLoading() {
        loadingDialog.show();
        handler.postDelayed(runnable, timeOut);
    }

    /**
     * 隐藏
     */
    public void dismiss() {
        long id = Thread.currentThread().getId();
        if (id == android.os.Process.myTid()) {
            loadingDialog.dismiss();
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.dismiss();
                }
            });
        }
        handler.removeCallbacks(runnable);
        handler.removeCallbacks(delayRunnable);
    }

    /**
     * 超时
     */
    private void timeoutCancel() {
        boolean showing = loadingDialog.isShowing();
        // todo:
//        ToastUtils.showNetworkError();
        if (showing) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 设置超时
     */
    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    private void initTask() {
        runnable = new Runnable() {
            @Override
            public void run() {
                timeoutCancel();
            }
        };
        delayRunnable = new Runnable() {
            @Override
            public void run() {
                showLoading();
            }
        };
    }

    /**
     * 设置返回键无效
     */
    public void setCanceledOnTouchOutside(boolean isClick) {
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        loadingDialog.setCancelable(isClick);
    }

    public void onDestroy() {
        loadingDialog.dismiss();
        handler.removeCallbacks(runnable);
    }

    public boolean isShowing() {
        if (loadingDialog != null) {
            return loadingDialog.isShowing();
        }
        return false;
    }
}
