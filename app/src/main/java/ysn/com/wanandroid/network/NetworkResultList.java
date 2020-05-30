package ysn.com.wanandroid.network;

import java.util.List;

/**
 * @Author yangsanning
 * @ClassName NetworkResultList
 * @Description 服务器返回的列表数据
 * @Date 2020/5/25
 */
public class NetworkResultList<T> {

    /**
     * curPage : 2
     * datas : []
     * offset : 20
     * over : false
     * pageCount : 427
     * size : 20
     * total : 8539
     */

    public int curPage;
    public int offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;
    public List<T> datas;
}
