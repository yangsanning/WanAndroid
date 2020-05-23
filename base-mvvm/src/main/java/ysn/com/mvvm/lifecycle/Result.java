package ysn.com.mvvm.lifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Author yangsanning
 * @ClassName Result
 * @Description 网络请求返回结果（状态)
 * @Date 2020/5/22
 */
public class Result<T> {

    @NonNull
    private State state;

    @Nullable
    private T data;

    private int code;

    @Nullable
    private String msg;

    public Result() {
        this.state = State.NONE;
        this.data = null;
        this.msg = null;
        this.code = 0;
    }

    public Result<T> success(@NonNull T data) {
        this.state = State.SUCCESS;
        this.data = data;
        return this;
    }

    public Result<T> failure(int code, @NonNull String msg) {
        this.state = State.FAILURE;
        this.code = code;
        this.msg = msg;
        return this;
    }

    @NonNull
    public State getState() {
        return state;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    @Nullable
    public String getMsg() {
        return msg;
    }

    public enum State {
        /**
         * 加载中
         */
        NONE,

        /**
         * 成功
         */
        SUCCESS,

        /**
         * 失败
         */
        FAILURE,
    }
}