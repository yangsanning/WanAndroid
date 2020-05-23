package ysn.com.mvvm.network.interceptor;

import androidx.annotation.NonNull;

import com.lazy.library.logging.Logcat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import ysn.com.mvvm.base.BuildConfig;

/**
 * @Author yangsanning
 * @ClassName LogInterceptor
 * @Description 日志拦截器
 * @Date 2020/5/22
 */
public class LogInterceptor implements Interceptor {

    private static final String LOG_HTTP_REQUEST_TAG = "httpRequest";
    private static final String LOG_HTTP_RESPONSE_BODY_TAG = "httpResponseBody";

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (BuildConfig.DEBUG && !"get".equalsIgnoreCase(request.method())) {
            printRequestLog(request);

            MediaType mediaType = response.body().contentType();
            String string = response.body().string();

            Logcat.d().tag(LOG_HTTP_RESPONSE_BODY_TAG).msg(string).out();
            Logcat.json().tag(LOG_HTTP_RESPONSE_BODY_TAG).msg(string).out();

            // 调用了response.body().string()方法，response中的流会被关闭，所以需要创建出一个新的response
            return response.newBuilder().body(ResponseBody.create(mediaType, string)).build();
        } else {
            return response;
        }
    }

    private static void printRequestLog(Request request) throws IOException {
        RequestBody requestBody = request.body();
        StringBuilder bodyBuilder = new StringBuilder();
        if (requestBody != null) {
            if (requestBody instanceof MultipartBody) {
                MultipartBody multipartBody = (MultipartBody) requestBody;
                for (MultipartBody.Part part : multipartBody.parts()) {
                    Headers headers = part.headers();
                    int size = headers.size();
                    for (int i = 0; i < size; i++) {
                        bodyBuilder.append(headers.name(i));
                        bodyBuilder.append(headers.value(i));
                        if (i != size - 1) {
                            bodyBuilder.append("\r\n");
                        }
                    }
                }
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = StandardCharsets.UTF_8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(StandardCharsets.UTF_8);
                }
                bodyBuilder = new StringBuilder(buffer.readString(charset));
            }
        }
        Logcat.d().tag(LOG_HTTP_REQUEST_TAG).msg(request.url()).out();
        Logcat.d().tag(LOG_HTTP_REQUEST_TAG).msg(bodyBuilder.toString()).out();
    }
}
