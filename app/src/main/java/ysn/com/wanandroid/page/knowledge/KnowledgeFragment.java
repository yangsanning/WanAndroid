package ysn.com.wanandroid.page.knowledge;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import ysn.com.mvvm.base.BaseLazyFragment;
import ysn.com.mvvm.utils.ResUtils;
import ysn.com.wanandroid.BR;
import ysn.com.wanandroid.R;
import ysn.com.wanandroid.databinding.FragmentKnowledgeBinding;
import ysn.com.wanandroid.databinding.ItemArticleBinding;
import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.widget.base.BasePagedListAdapter;
import ysn.com.wanandroid.widget.decoration.DefaultItemDecoration;
import ysn.com.wanandroid.widget.diff.ArticleDiff;
import ysn.com.wanandroid.widget.pop.KnowledgeMenuPopWindow;

/**
 * @Author yangsanning
 * @ClassName KnowledgeFragment
 * @Description 知识体系
 * @Date 2020/6/8
 */
public class KnowledgeFragment extends BaseLazyFragment<KnowledgeViewModel, FragmentKnowledgeBinding> {

    private KnowledgeMenuPopWindow knowledgeMenuPopWindow;
    private BasePagedListAdapter<Article, ItemArticleBinding> articleAdapter;

    public static KnowledgeFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeFragment fragment = new KnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected KnowledgeViewModel getViewModel() {
        return ViewModelProviders.of(this).get(KnowledgeViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        dataBinding.setFragment(this);
        dataBinding.setVm(viewModel);

        articleAdapter = new BasePagedListAdapter<>(new ArticleDiff(), R.layout.item_article, BR.article);
        dataBinding.recyclerview.setLayoutManager(new LinearLayoutManager(activityCast()));
        dataBinding.recyclerview.addItemDecoration(new DefaultItemDecoration());
        dataBinding.recyclerview.setAdapter(articleAdapter);
    }

    @Override
    protected void lazyLoad() {
        getData();
    }

    public void getData() {
        viewModel.getData().observe(this, articleList -> {
            articleAdapter.submitList(null);
            dataBinding.recyclerview.scrollToPosition(0);
            articleAdapter.submitList(articleList);
        });
    }

    public void getData(int chapterId) {
        viewModel.getData(chapterId).observe(this, articleList -> {
            articleAdapter.submitList(null);
            dataBinding.recyclerview.scrollToPosition(0);
            articleAdapter.submitList(articleList);
        });
    }

    public void showMenuPop(View view) {
        if (knowledgeMenuPopWindow == null) {
            knowledgeMenuPopWindow = new KnowledgeMenuPopWindow(activityCast());
            knowledgeMenuPopWindow.setOnMenuItemClickListener(this::getData);
        }
        if (!knowledgeMenuPopWindow.isShowing()) {
            knowledgeMenuPopWindow.showAsDropDown(view, 0, -(int) ResUtils.getDimension(R.dimen.px_12));
        }
    }
}
