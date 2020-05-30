package ysn.com.wanandroid.utils;

import android.app.Activity;
import android.content.Intent;

import ysn.com.wanandroid.MainActivity;
import ysn.com.wanandroid.page.login.LoginActivity;

/**
 * @Author yangsanning
 * @ClassName PageUtils
 * @Description 页面跳转
 * @Date 2020/5/23
 */
public class PageUtils {

    public static void startLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    public static void startMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
