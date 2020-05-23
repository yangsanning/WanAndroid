package ysn.com.mvvm.wanandroid.network;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import ysn.com.mvvm.wanandroid.bean.User;

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
}
