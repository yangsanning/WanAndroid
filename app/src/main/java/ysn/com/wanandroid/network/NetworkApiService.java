package ysn.com.wanandroid.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.model.bean.Banner;
import ysn.com.wanandroid.model.bean.Knowledge;
import ysn.com.wanandroid.model.bean.User;

/**
 * @Author yangsanning
 * @ClassName NetworkApiService
 * @Description ApiService
 * @Date 2020/5/22
 */
public interface NetworkApiService {

    /**
     * 正式环境
     */
    String API_HOME = "https://www.wanandroid.com";

    /**
     * 登陆
     *
     * @param phone    手机号
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("/user/login")
    Observable<NetworkResult<User>> login(@Field("username") String phone, @Field("password") String password);

    /**
     * 获取知识体系列表
     *
     * @return 知识体系列表
     */
    @GET("/tree/json")
    Observable<NetworkResult<List<Knowledge>>> getKnowledgeList();

    /**
     * 获取首页文章列表
     *
     * @param page 页码
     * @return 相应页面文章列表
     */
    @GET("/article/list/{page}/json")
    Observable<NetworkResult<NetworkResultList<Article>>> getKnowledgeArticleList(@Path("page") int page, @Query("cid") int cid);

    /**
     * 获取首页banner
     *
     * @return 首页banner
     */
    @GET("/banner/json")
    Observable<NetworkResult<List<Banner>>> getBanner();

    /**
     * 获取首页文章列表
     *
     * @param page 页码
     * @return 相应页面文章列表
     */
    @GET("/article/list/{page}/json")
    Observable<NetworkResult<NetworkResultList<Article>>> getArticleList(@Path("page") int page);
}
