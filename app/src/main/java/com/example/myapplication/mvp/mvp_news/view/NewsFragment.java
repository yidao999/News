package com.example.myapplication.mvp.mvp_news.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NewsAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.mvp.mvp_cardtop.model.TopModel;
import com.example.myapplication.mvp.mvp_cardtop.view.CardTopDetailActivity;
import com.example.myapplication.mvp.mvp_news.model.NewsModel;
import com.example.myapplication.mvp.mvp_news.presenter.NewsPresenter;
import com.example.myapplication.utils.DividerItemDecoration;
import com.melnykov.fab.FloatingActionButton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: 小川
 * Date: 2019/5/17
 * Description:
 */
public class NewsFragment extends BaseFragment implements NewsView {

    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private NewsPresenter newsPresenter = new NewsPresenter(this);
    private Unbinder bind;
    private String strtype;
    private NewsAdapter adapter;
    private int count;
    private List<TopModel.ResultBeanX.ResultBean.ListBean> topList = new ArrayList<>();

    public static NewsFragment newInstance(String title) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", title);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void getNewsSuccess(TopModel topModel) {
        if (topModel.getCode().equals("10000")) {
            topList.clear();
            topList.addAll(topModel.getResult().getResult().getList());
            adapter.addAllNewsData(topList);
        }
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void getNewsFailure(Throwable e) {
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    protected View iniView() {
        view = LayoutInflater.from(context).inflate(R.layout.fragment_news, null, false);
        return view;
    }

    @Override
    protected void iniData() {
        bind = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            strtype = bundle.getString("msg");
        }

        RecyclerView recyclerView = pullLoadMoreRecyclerView.getRecyclerView();
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setVerticalScrollBarEnabled(true);
        fab.attachToRecyclerView(recyclerView);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setRefreshing(true);
        pullLoadMoreRecyclerView.setFooterViewText("loading");
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        adapter = new NewsAdapter(context);
        pullLoadMoreRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, CardTopDetailActivity.class);
                if (topList != null && topList.size() != 0) {
                    intent.putExtra("top_title", topList.get(position).getTitle());
                    intent.putExtra("top_weburl", topList.get(position).getWeburl() + "");
                    intent.putExtra("top_pic", topList.get(position).getPic() + "");
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            newsPresenter.loadNewsData(strtype, "10", 0, "747b4744b764f5b6dc5021e72ff9ea4c");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @OnClick(R.id.fab)
    void onclick() {
        pullLoadMoreRecyclerView.scrollToTop();
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {

        @Override
        public void onRefresh() {
            setRefresh();
            newsPresenter.loadNewsData(strtype, "10", 0, "747b4744b764f5b6dc5021e72ff9ea4c");
        }

        @Override
        public void onLoadMore() {
            count += 10;
            newsPresenter.loadNewsData(strtype, "10", count, "747b4744b764f5b6dc5021e72ff9ea4c");
        }
    }

    private void setRefresh() {
        adapter.clearNewData();
        count = 0;
    }

    @Override
    public void onDestroy() {
        if (newsPresenter != null) {
            newsPresenter.detachView();
        }
        super.onDestroy();
    }
}
