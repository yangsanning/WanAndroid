package ysn.com.wanandroid.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import ysn.com.wanandroid.model.bean.User;

/**
 * @Author yangsanning
 * @ClassName UserDao
 * @Description 一句话概括作用
 * @Date 2020/5/30
 */
@Dao
public interface UserDao extends BaseDao<User> {

    @Query("SELECT * FROM USER WHERE  ID = :id LIMIT 1")
    LiveData<User> getUser(String id);
}
