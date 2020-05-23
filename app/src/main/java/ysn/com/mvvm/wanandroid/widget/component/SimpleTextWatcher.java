package ysn.com.mvvm.wanandroid.widget.component;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @Author yangsanning
 * @ClassName SimpleTextWatcher
 * @Description 一句话概括作用
 * @Date 2020/5/22
 */
public abstract class SimpleTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
