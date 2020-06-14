package ysn.com.wanandroid.widget.decoration;

import androidx.annotation.Nullable;

import ysn.com.mvvm.utils.ResUtils;
import ysn.com.recyclerview.BaseItemDecoration;
import ysn.com.recyclerview.RvItemDecoration;
import ysn.com.recyclerview.RvItemDecorationBuilder;
import ysn.com.wanandroid.R;

/**
 * @Author yangsanning
 * @ClassName DefaultItemDecoration
 * @Description 分割线
 * @Date 2020/5/27
 */
public class ProjectItemDecoration extends BaseItemDecoration {

    private float width, halfWidth;
    private int color;

    public ProjectItemDecoration() {
        super();
        this.color = ResUtils.getColor(R.color.app_background);
        this.width = ResUtils.getDimension(R.dimen.px_25);
        this.halfWidth = width / 2;
    }

    @Nullable
    @Override
    public RvItemDecoration getRvItemDecoration(int itemPosition) {
        RvItemDecorationBuilder builder = new RvItemDecorationBuilder();
        if (itemPosition < 2) {
            builder.buildTop(color, width);
        }
        if ((itemPosition + 1) % 2 == 0) {
            builder.buildLeft(color, halfWidth)
                    .buildRight(color, width);
        } else {
            builder.buildLeft(color, width)
                    .buildRight(color, halfWidth);
        }
        return builder.buildBottom(color, width).finish();
    }
}