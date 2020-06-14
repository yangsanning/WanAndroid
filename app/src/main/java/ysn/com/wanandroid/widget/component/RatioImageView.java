package ysn.com.wanandroid.widget.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.gcssloop.widget.RCImageView;

import ysn.com.wanandroid.R;

/**
 * @Author yangsanning
 * @ClassName RatioImageView
 * @Description 比例 image view
 * @Date 2020/6/13
 */
public class RatioImageView extends RCImageView {

    /**
     * 宽高比
     */
    private static final float DEFAULT_RATIO = 0.68f;

    private float ratio = DEFAULT_RATIO;
    private boolean isRestHeight;

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView);

        ratio = typedArray.getFloat(R.styleable.RatioImageView_riv_ratio, DEFAULT_RATIO);
        isRestHeight = typedArray.getBoolean(R.styleable.RatioImageView_riv_is_reset_height, false);
        typedArray.recycle();
    }

    public RatioImageView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (ratio != 0) {
            if (isRestHeight) {
                int width = MeasureSpec.getSize(widthMeasureSpec);
                float height = width / ratio;
                heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) height, MeasureSpec.EXACTLY);
            } else {
                int height = MeasureSpec.getSize(heightMeasureSpec);
                float width = height * ratio;
                widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) width, MeasureSpec.EXACTLY);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
        requestLayout();
    }
}
