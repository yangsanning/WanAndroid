package ysn.com.mvvm.network;

/**
 * @Author yangsanning
 * @ClassName ApiException
 * @Description 异常数据实体类
 * @Date 2020/5/22
 */
public class ApiException extends RuntimeException {

    public int resultCode;

    public ApiException(int resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }
}