package ysn.com.wanandroid.model.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

import ysn.com.wanandroid.model.bean.Article;

/**
 * @Author yangsanning
 * @ClassName ArticleDao
 * @Description 一句话概括作用
 * @Date 2020/5/31
 */
@Dao
public interface ArticleDao extends BaseDao<Article> {

    @Query("SELECT COUNT(*) FROM ARTICLE")
    int getTotal();

    @Query("SELECT * FROM Article ORDER BY PUBLISHTIME DESC")
    DataSource.Factory<Integer, Article> getData();

    @Query("SELECT COUNT(*) FROM ARTICLE WhERE CHAPTERID=:chapterId")
    int getTotalByChapterId(int chapterId);

    @Query("SELECT * FROM Article WhERE CHAPTERID=:chapterId ORDER BY PUBLISHTIME DESC")
    DataSource.Factory<Integer, Article> getDataByChapterId(int chapterId);

    @Query("SELECT COUNT(*) FROM ARTICLE WhERE SUPERCHAPTERID=:superChapterId")
    int getTotalBySuperChapterId(int superChapterId);

    @Query("SELECT * FROM Article WhERE SUPERCHAPTERID=:superChapterId ORDER BY PUBLISHTIME DESC")
    DataSource.Factory<Integer, Article> getDataBySuperChapterId(int superChapterId);
}
