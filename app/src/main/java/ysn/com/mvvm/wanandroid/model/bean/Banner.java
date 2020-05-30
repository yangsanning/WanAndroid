package ysn.com.mvvm.wanandroid.model.bean;

/**
 * @Author yangsanning
 * @ClassName Banner
 * @Description 一句话概括作用
 * @Date 2020/5/25
 */
public class Banner {

    /**
     * desc : 享学~
     * id : 29
     * imagePath : https://www.wanandroid.com/blogimgs/b0f02a44-46f4-421b-a51a-ba0097ada981.png
     * isVisible : 1
     * order : 0
     * title : 可能是目前最全的《Android面试题及解析》（379页）
     * type : 0
     * url : https://mp.weixin.qq.com/s/am5UNP3qY2uM16BnT6wrjg
     */

    private String desc;
    private int id;
    private String imagePath;
    private int isVisible;
    private int order;
    private String title;
    private int type;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
