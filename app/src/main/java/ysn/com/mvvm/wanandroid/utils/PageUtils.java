package ysn.com.mvvm.wanandroid.utils;

import android.app.Activity;
import android.content.Intent;

import ysn.com.mvvm.wanandroid.MainActivity;

/**
 * @Author yangsanning
 * @ClassName PageUtils
 * @Description 页面跳转
 * @Date 2020/5/23
 */
public class PageUtils {

    public static void startMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
