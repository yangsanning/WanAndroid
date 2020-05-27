package ysn.com.mvvm.wanandroid.app;

import android.app.Application;

import com.lazy.library.logging.Builder;
import com.lazy.library.logging.Logcat;

import ysn.com.mvvm.utils.AppInitUtils;
import ysn.com.mvvm.wanandroid.BuildConfig;

/**
 * @Author yangsanning
 * @ClassName MyApplication
 * @Description 一句话概括作用
 * @Date 2020/5/22
 */
public class MyApplication extends Application {

    public static final String LOG_GLOBAL_TAG = "WanAndroid";

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initialize();
    }

    private void initialize() {
        initLogCat();
        AppInitUtils.inject(instance);

        // 刷新风格设置
        MyAppRefreshLayoutStyle.init();
    }

    private void initLogCat() {
        Builder builder = Logcat.newBuilder();
        builder.topLevelTag(LOG_GLOBAL_TAG);
        if (BuildConfig.DEBUG) {
            builder.logCatLogLevel(Logcat.SHOW_ALL_LOG);
        } else {
            builder.logCatLogLevel(Logcat.SHOW_INFO_LOG | Logcat.SHOW_WARN_LOG | Logcat.SHOW_ERROR_LOG);
        }
        Logcat.initialize(this, builder.build());
    }

    public static MyApplication get() {
        return instance;
    }
}
