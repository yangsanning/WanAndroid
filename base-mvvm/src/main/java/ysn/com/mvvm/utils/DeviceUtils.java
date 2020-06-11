package ysn.com.mvvm.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * @Author yangsanning
 * @ClassName DeviceUtils
 * @Description 一句话概括作用
 * @Date 2020/6/10
 */
public class DeviceUtils {

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

}
