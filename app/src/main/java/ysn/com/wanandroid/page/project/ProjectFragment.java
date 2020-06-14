package ysn.com.wanandroid.page.project;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableMap;
import androidx.lifecycle.ViewModelProviders;

import ysn.com.adapter.ItemViewManager;
import ysn.com.mvvm.base.BaseLazyFragment;
import ysn.com.wanandroid.R;
import ysn.com.wanandroid.databinding.FragmentProjectBinding;
import ysn.com.wanandroid.databinding.ItemProjectBinding;
import ysn.com.wanandroid.model.bean.Article;

/**
 * @Author yangsanning
 * @ClassName ProjectFragment
 * @Description 一句话概括作用
 * @Date 2020/6/12
 */
public class ProjectFragment extends BaseLazyFragment<ProjectModel, FragmentProjectBinding> {

    public ObservableMap<Class, ItemViewManager> projectItemViewManagerMap = new ObservableArrayMap<>();

    public static ProjectFragment newInstance() {
        Bundle args = new Bundle();
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected ProjectModel getViewModel() {
        return ViewModelProviders.of(this).get(ProjectModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {
        dataBinding.setFragment(this);
        dataBinding.setVm(viewModel);

        projectItemViewManagerMap.put(Article.class, new ItemViewManager<Article, ItemProjectBinding>
                (R.layout.item_project, (binding, article) -> {
                    binding.setArticle(article);
                    binding.getRoot().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("功能开发中");
                        }
                    });
                }));
    }

    @Override
    protected void lazyLoad() {
        viewModel.getProjectList(0);
    }
}
