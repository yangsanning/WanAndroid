package ysn.com.mvvm.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import ysn.com.mvvm.base.R;

/**
 * @Author yangsanning
 * @ClassName ToastUtils
 * @Description 一句话概括作用
 * @Date 2020/5/22
 */
public class ToastUtils {

    @SuppressLint("StaticFieldLeak")
    private static Application application = null;

    private static long ThreadID = android.os.Process.myTid();
    private static Handler handler;
    private static Toast toast;

    public static void inject(@NonNull Application application) {
        ToastUtils.application = application;
    }

    /**
     * 普通的吐司提示
     *
     * @param message 吐司内容
     */
    public static void showNormalToast(String message) {
        long id = Thread.currentThread().getId();
        if (ThreadID == id) {
            makeToast(message);
        } else {
            if (handler == null) {
                handler = new Handler();
            }
            handler.post(() -> makeToast(message));
        }
    }

    /**
     * 延迟的吐司提示
     *
     * @param message 吐司内容
     */
    public static void showDelayedToast(String message) {
        long id = Thread.currentThread().getId();
        if (ThreadID == id) {
            makeToast(message);
        } else {
            if (handler == null) {
                handler = new Handler();
            }
            handler.postDelayed(() -> makeToast(message), 300);
        }
    }

    public static void showNetworkError() {
        showNormalToast("请检查网络是否正常");
    }

    private static void makeToast(String msg) {
        cancel();
        View view = View.inflate(application, R.layout.view_toast, null);
        TextView toastView = view.findViewById(R.id.toast_view_text);
        toast = new Toast(application);
        toast.setGravity(Gravity.CENTER, 1, 1);
        toast.setDuration(Toast.LENGTH_LONG);
        toastView.setText(msg);
        toast.setView(view);
        toast.show();
    }

    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
