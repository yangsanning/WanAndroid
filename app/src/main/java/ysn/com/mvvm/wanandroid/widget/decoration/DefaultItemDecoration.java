package ysn.com.mvvm.wanandroid.widget.decoration;

import androidx.annotation.Nullable;

import java.util.LinkedHashMap;

import ysn.com.mvvm.utils.ResUtils;
import ysn.com.mvvm.wanandroid.R;
import ysn.com.recyclerview.BaseItemDecoration;
import ysn.com.recyclerview.RvItemDecoration;
import ysn.com.recyclerview.RvItemDecorationBuilder;

/**
 * @Author yangsanning
 * @ClassName DefaultItemDecoration
 * @Description 分割线
 * @Date 2020/5/27
 */
public class DefaultItemDecoration extends BaseItemDecoration {

    private float width;
    private int color;
    private LinkedHashMap<Integer, Float> topDecorationMap = new LinkedHashMap<>();

    public DefaultItemDecoration() {
        super();
        this.color = ResUtils.getColor(R.color.app_line_background);
        this.width = ResUtils.getDimension(R.dimen.px_1);
    }

    @Nullable
    @Override
    public RvItemDecoration getRvItemDecoration(int itemPosition) {
        RvItemDecorationBuilder builder = new RvItemDecorationBuilder();
        if (topDecorationMap.containsKey(itemPosition)) {
            builder.buildTop(true, color, topDecorationMap.get(itemPosition), 0, 0);
        }
        return builder.buildBottom(true, color, width, 0, 0).finish();
    }

    public DefaultItemDecoration addTopDecoration(int itemPosition, float width) {
        topDecorationMap.put(itemPosition, width);
        return this;
    }
}