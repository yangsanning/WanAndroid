package ysn.com.wanandroid.model.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.prefs.Preferences;

import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.model.bean.User;
import ysn.com.wanandroid.model.dao.ArticleDao;
import ysn.com.wanandroid.model.dao.UserDao;

/**
 * @Author yangsanning
 * @ClassName RoomDatabaseHelper
 * @Description 数据库
 * @Date 2020/5/30
 */
@Database(entities = {User.class, Article.class},
        version = 1, exportSchema = false)
@TypeConverters({RoomConversionFactory.class})
public abstract class RoomDatabaseHelper extends RoomDatabase {

    private static final String DATABASE_NAME = "Ysn_WanAndroid";

    private static RoomDatabaseHelper instance;

    public static RoomDatabaseHelper get(Context context) {
        if (instance == null) {
            synchronized (Preferences.class) {
                if (instance == null) {
                    instance = getDefault(context);
                }
            }
        }
        return instance;
    }

    public static RoomDatabaseHelper getDefault(Context context) {
        return databaseBuilder(context, RoomDatabaseHelper.class, DATABASE_NAME);
    }

    public static void destroy() {
        instance = null;
    }

    /**
     * 为持久数据库创建一个RoomDatabase.Builder。
     */
    private static <T extends RoomDatabase> T databaseBuilder(@NonNull Context context,
                                                              @NonNull Class<T> klass, @NonNull String tableName) {
        return Room.databaseBuilder(context.getApplicationContext(), klass, tableName)
//                .allowMainThreadQueries()
                .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                .build();
    }

    /**
     * 为内存数据库创建一个RoomDatabase.Builder。存储在内存数据库中的信息在进程终止时消失。
     */
    private static <T extends RoomDatabase> T inMemoryDatabaseBuilder(@NonNull Context context, @NonNull Class<T> klass) {
        return Room.inMemoryDatabaseBuilder(context.getApplicationContext(), klass)
                .allowMainThreadQueries()
                .build();
    }

    public abstract UserDao getUserDao();

    public abstract ArticleDao getArticleDao();
}
