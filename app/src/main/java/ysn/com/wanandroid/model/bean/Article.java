package ysn.com.wanandroid.model.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Author yangsanning
 * @ClassName Article
 * @Description 一句话概括作用
 * @Date 2020/5/25
 */
@Entity(tableName = "Article")
public class Article {

    /**
     * apkLink :
     * audit : 1
     * author : 谷歌开发者
     * canEdit : false
     * chapterId : 415
     * chapterName : 谷歌开发者
     * collect : false
     * courseId : 13
     * desc :
     * descMd :
     * envelopePic :
     * fresh : false
     * id : 13516
     * link : https://mp.weixin.qq.com/s/wzSi7UrFK6iQV2MA2OCwew
     * niceDate : 2020-05-21 00:00
     * niceShareDate : 2020-05-21 19:45
     * origin :
     * prefix :
     * projectLink :
     * publishTime : 1589990400000
     * selfVisible : 0
     * shareDate : 1590061558000
     * shareUser :
     * superChapterId : 408
     * superChapterName : 公众号
     * tags : [{"name":"公众号","url":"/wxarticle/list/415/1"}]
     * title : 协程中的取消和异常 | 核心概念介绍
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "apkLink")
    public String apkLink;
    @ColumnInfo(name = "audit")
    public int audit;
    @ColumnInfo(name = "author")
    public String author;
    @ColumnInfo(name = "canEdit")
    public boolean canEdit;
    @ColumnInfo(name = "chapterId")
    public int chapterId;
    @ColumnInfo(name = "chapterName")
    public String chapterName;
    @ColumnInfo(name = "collect")
    public boolean collect;
    @ColumnInfo(name = "courseId")
    public int courseId;
    @ColumnInfo(name = "desc")
    public String desc;
    @ColumnInfo(name = "descMd")
    public String descMd;
    @ColumnInfo(name = "envelopePic")
    public String envelopePic;
    @ColumnInfo(name = "fresh")
    public boolean fresh;
    @ColumnInfo(name = "link")
    public String link;
    @ColumnInfo(name = "niceDate")
    public String niceDate;
    @ColumnInfo(name = "niceShareDate")
    public String niceShareDate;
    @ColumnInfo(name = "origin")
    public String origin;
    @ColumnInfo(name = "prefix")
    public String prefix;
    @ColumnInfo(name = "projectLink")
    public String projectLink;
    @ColumnInfo(name = "publishTime")
    public long publishTime;
    @ColumnInfo(name = "selfVisible")
    public int selfVisible;
    @ColumnInfo(name = "shareDate")
    public long shareDate;
    @ColumnInfo(name = "shareUser")
    public String shareUser;
    @ColumnInfo(name = "superChapterId")
    public int superChapterId;
    @ColumnInfo(name = "superChapterName")
    public String superChapterName;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "type")
    public int type;
    @ColumnInfo(name = "userId")
    public int userId;
    @ColumnInfo(name = "visible")
    public int visible;
    @ColumnInfo(name = "zan")
    public int zan;
}
