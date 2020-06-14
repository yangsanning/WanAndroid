package ysn.com.wanandroid.page.project;

import io.reactivex.disposables.Disposable;
import ysn.com.mvvm.lifecycle.BaseViewModel;
import ysn.com.mvvm.network.BaseNetworkCallback;
import ysn.com.wanandroid.model.bean.Article;
import ysn.com.wanandroid.network.NetworkResultList;
import ysn.com.wanandroid.network.request.ProjectNetworkRequest;
import ysn.com.wanandroid.widget.component.super_recycler_view.SuperBindingResult;

/**
 * @Author yangsanning
 * @ClassName ProjectModel
 * @Description 一句话概括作用
 * @Date 2020/6/12
 */
public class ProjectModel extends BaseViewModel {

    public SuperBindingResult projectBindingResult = new SuperBindingResult();

    /**
     * 获取首页文章列表
     */
    public void getProjectList(int page) {
        Disposable projectList = ProjectNetworkRequest.get().getProjectList(page,
                new BaseNetworkCallback<NetworkResultList<Article>>() {
                    @Override
                    public void onSuccess(NetworkResultList<Article> data) {
                        projectBindingResult.setNetworkResultList(data).setState(page == 0 ?
                                SuperBindingResult.State.REFRESH_SUCCESS : SuperBindingResult.State.LOAD_MORE_SUCCESS);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        projectBindingResult.setState(page == 0 ?
                                SuperBindingResult.State.REFRESH_FAILURE : SuperBindingResult.State.LOAD_MORE_FAILURE);
                    }
                });
        addDisposable(projectList);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ProjectNetworkRequest.get().destory();
        projectBindingResult = null;
    }
}
