package ysn.com.wanandroid.model.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Objects;

/**
 * @Author yangsanning
 * @ClassName Article
 * @Description 一句话概括作用
 * @Date 2020/5/25
 */
@Entity()
public class Article {

    /**
     * apkLink :
     * audit : 1
     * author : SlamDunk007
     * canEdit : false
     * chapterId : 339
     * chapterName : K线图
     * collect : false
     * courseId : 13
     * desc : 使用Canvas进行股票行情图K线的绘制。 （1）股票行情图K线数据的集中处理 （2）股票行情图双缓冲绘制，具体绘制流程在子线程当中进行，然后将绘制好的bitmap交由onDraw当中的canvas直接进行绘制，打开GPU呈现模式，发现越复杂的绘制，对于GPU消耗有所下降 （3）主图和附图完全隔离，最终达到的效果是各种附图和主图能够进行随意组合。 （4）项目在业余时间，慢慢的优化和更新当中
     * descMd :
     * envelopePic : https://wanandroid.com/blogimgs/9e40bf04-9963-4ae7-b4e5-e89cd6f1c808.png
     * fresh : false
     * id : 13817
     * link : https://www.wanandroid.com/blog/show/2770
     * niceDate : 2020-06-09 00:23
     * niceShareDate : 2020-06-09 00:23
     * origin :
     * prefix :
     * projectLink : https://github.com/SlamDunk007/StockChart
     * publishTime : 1591633423000
     * selfVisible : 0
     * shareDate : 1591633423000
     * shareUser :
     * superChapterId : 294
     * superChapterName : 开源项目主Tab
     * tags : [{"name":"项目","url":"/project/list/1?cid=339"}]
     * title : 从0到1绘制股票行情图
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */

    @PrimaryKey
    public int id;

    public String apkLink;
    public int audit;
    public String author;
    public boolean canEdit;
    public int chapterId;
    public String chapterName;
    public boolean collect;
    public int courseId;
    public String desc;
    public String descMd;
    public String envelopePic;
    public boolean fresh;
    public String link;
    public String niceDate;
    public String niceShareDate;
    public String origin;
    public String prefix;
    public String projectLink;
    public long publishTime;
    public int selfVisible;
    public long shareDate;
    public String shareUser;
    public int superChapterId;
    public String superChapterName;
    public String title;
    public int type;
    public int userId;
    public int visible;
    public int zan;
    public List<TagsBean> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        Article article = (Article) o;
        return id == article.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class TagsBean {
        /**
         * name : 项目
         * url : /project/list/1?cid=339
         */

        public String name;
        public String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
