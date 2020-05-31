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

}
