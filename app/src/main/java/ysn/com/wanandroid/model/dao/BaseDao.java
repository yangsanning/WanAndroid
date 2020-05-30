package ysn.com.wanandroid.model.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

/**
 * @Author yangsanning
 * @ClassName BaseDao
 * @Description Dao基类 插入和删除
 * @Date 2020/5/30
 */
public interface BaseDao<T>  {

    /**
     * 插入数据（强制替换）
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T... ts);

    /**
     * 插入数据（强制替换）
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<T> tList);

    /**
     * 删除
     */
    @Delete()
    void delete(T... ts);

    /**
     * 删除
     */
    @Delete()
    void delete(List<T> tList);
}
