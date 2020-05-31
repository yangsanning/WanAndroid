package ysn.com.wanandroid.model.bean;

import java.util.List;

/**
 * @Author yangsanning
 * @ClassName Knowledge
 * @Description 一句话概括作用
 * @Date 2020/5/30
 */
public class Knowledge {

    /**
     * children : []
     * courseId : 13
     * id : 150
     * name : 开发环境
     * order : 1
     * parentChapterId : 0
     * userControlSetTop : false
     * visible : 1
     */

    public int courseId;
    public int id;
    public String name;
    public int order;
    public int parentChapterId;
    public boolean userControlSetTop;
    public int visible;
    public List<ArticleChannel> children;

    public static class ArticleChannel {

        public int id;
    }
}
