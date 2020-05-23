package ysn.com.mvvm.utils;

import android.app.Application;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.Utils;

/**
 * @Author yangsanning
 * @ClassName AppUtilsHelper
 * @Description 一句话概括作用
 * @Date 2020/5/22
 */
public class AppInitUtils {

    public static void inject(@NonNull Application application) {
        ResUtils.inject(application);
        ToastUtils.inject(application);
        Utils.init(application);
    }
}