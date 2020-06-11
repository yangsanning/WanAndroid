package ysn.com.wanandroid.widget.pop;

import android.app.Activity;
import android.view.View;

import ysn.com.mvvm.base.BasePopupWindow;
import ysn.com.wanandroid.R;
import ysn.com.wanandroid.constant.ArticleChannelConstant;
import ysn.com.wanandroid.databinding.PopKnowledgeMenuBinding;

/**
 * @Author yangsanning
 * @ClassName KnowledgeMenuPopWindow
 * @Description todo: 1.更改为recycler，2.更改为数据库获取并存储, 3. 改为二级列表
 * @Date 2020/6/10
 */
public class KnowledgeMenuPopWindow extends BasePopupWindow<PopKnowledgeMenuBinding> {

    private OnMenuItemClickListener onMenuItemClickListener;

    public KnowledgeMenuPopWindow(Activity mActivity) {
        super(mActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pop_knowledge_menu;
    }

    @Override
    protected View getTopView() {
        return dataBinding.topView;
    }

    @Override
    public int getAnimationStyle() {
        return R.style.TRM_ANIM_STYLE;
    }

    @Override
    protected void initView() {
        dataBinding.setPop(this);
    }

    public void onClick(int chapterId) {
        if (onMenuItemClickListener != null) {
            onMenuItemClickListener.onMenuItemClick(chapterId == ArticleChannelConstant.CUSTOM_VIEW_ID ? 126 : 54);
        }
        dismiss();
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    public interface OnMenuItemClickListener {

        void onMenuItemClick(int chapterId);
    }
}
