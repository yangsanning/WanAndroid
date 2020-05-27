package ysn.com.mvvm.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @Author yangsanning
 * @ClassName ImageUtils
 * @Description 图片Utils
 * @Date 2020/5/25
 */
public class ImageUtils {

    public static void loadImage(Context context, String url, ImageView imageView) {
        loadImage(context, 0, 0, url, imageView);
    }

    public static void loadImage(Context context, @DrawableRes int placeholder,
                                 String url, ImageView imageView) {
        loadImage(context, placeholder, 0, url, imageView);
    }

    public static void loadImage(Context context, @DrawableRes int placeholder,
                                 @DrawableRes int error, String url, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(url)
                    .apply(getCenterCropOptions(placeholder, error))
                    .into(imageView);
        } catch (Exception e) {
            //避免context被回收导致的异常
        }
    }

    @NonNull
    private static RequestOptions getCenterCropOptions(@DrawableRes int placeholder, @DrawableRes int error) {
        return new RequestOptions()
                // 加载成功之前占位图
                .placeholder(placeholder)
                // 加载错误之后的错误图
                .error(error)
                //                .override(imageView.getMeasuredWidth(), imageView.getMeasuredHeight())
                // 指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者高等于ImageView的宽或者高。）
                //                .fitCenter()
                // 指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的宽高都大于等于ImageView的宽度，然后截取中间的显示。）
                .centerCrop()
                // 跳过内存缓存
                //                .skipMemoryCache(true)
                // ALL: 缓存所有, NONE: 跳过内存缓存, DATA: 只缓存原来分辨率的图片, RESOURCE: 只缓存最终的图片
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }
}
