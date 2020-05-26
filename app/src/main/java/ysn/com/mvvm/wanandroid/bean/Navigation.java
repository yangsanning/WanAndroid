package ysn.com.mvvm.wanandroid.bean;

import androidx.annotation.DrawableRes;

/**
 * @Author yangsanning
 * @ClassName Navigation
 * @Description 一句话概括作用
 * @Date 2020/5/26
 */
public class Navigation {

    @DrawableRes
    public int drawableRes;

    public String text;

    public Navigation(int drawableRes, String text) {
        this.drawableRes = drawableRes;
        this.text = text;
    }
}