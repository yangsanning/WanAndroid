package ysn.com.wanandroid.widget.hepler;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.prefs.Preferences;

import ysn.com.wanandroid.app.MyApplication;
import ysn.com.wanandroid.model.bean.User;
import ysn.com.wanandroid.model.db.RoomDatabaseHelper;
import ysn.com.mvvm.widget.hepler.BasePreferenceHelper;

/**
 * @Author yangsanning
 * @ClassName PreferenceHelper
 * @Description 一句话概括作用
 * @Date 2020/5/30
 */
public class PreferenceHelper extends BasePreferenceHelper {

    private static final String FILE_NAME = "general";
    private static final String KEY_USER_ID = "KEY_USER_ID";

    private static PreferenceHelper instance;
    private SharedPreferences sharedPreferences;
    private String userId;

    public static PreferenceHelper get() {
        if (instance == null) {
            synchronized (Preferences.class) {
                if (instance == null) {
                    instance = new PreferenceHelper();
                }
            }
        }
        return instance;
    }

    private PreferenceHelper() {
        sharedPreferences = MyApplication.get().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    protected SharedPreferences getSharePreferences() {
        return sharedPreferences;
    }

    public void login(User user) {
        RoomDatabaseHelper.get(MyApplication.get()).getUserDao().insert(user);
        setUserId(user.id);
    }

    public void loginOut() {
        setUserId("");
    }

    public String getUserId() {
        if (TextUtils.isEmpty(userId)) {
            userId = (String) getValue(KEY_USER_ID, "");
        }
        return userId;
    }

    public boolean isLoginOut() {
        return getUserId().isEmpty();
    }

    public void setUserId(String userId) {
        this.userId = userId;
        setValue(KEY_USER_ID, userId);
    }
}
