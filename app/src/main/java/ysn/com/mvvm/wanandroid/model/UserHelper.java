package ysn.com.mvvm.wanandroid.model;

import androidx.lifecycle.LiveData;

import java.util.prefs.Preferences;

import ysn.com.mvvm.wanandroid.app.MyApplication;
import ysn.com.mvvm.wanandroid.model.bean.User;
import ysn.com.mvvm.wanandroid.model.dao.UserDao;
import ysn.com.mvvm.wanandroid.model.db.RoomDatabaseHelper;
import ysn.com.mvvm.wanandroid.widget.hepler.PreferenceHelper;

/**
 * @Author yangsanning
 * @ClassName 再进一层封装UserDao
 * @Description
 * @Date 2020/5/30
 */
public class UserHelper {

    private static UserHelper instance;
    private LiveData<User> userLiveData;

    public static UserHelper get() {
        if (instance == null) {
            synchronized (Preferences.class) {
                if (instance == null) {
                    instance = new UserHelper();
                }
            }
        }
        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    public LiveData<User> getUserLiveData() {
        if (userLiveData == null || !userLiveData.getValue().token.equals(PreferenceHelper.get().getUserId())) {
            userLiveData = getUserDao().getUser(PreferenceHelper.get().getUserId());
        }
        return userLiveData;
    }

    private UserDao getUserDao() {
        return RoomDatabaseHelper.getDefault(MyApplication.get()).getUserDao();
    }
}
