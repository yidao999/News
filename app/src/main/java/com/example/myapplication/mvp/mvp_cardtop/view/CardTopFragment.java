package com.example.myapplication.mvp.mvp_cardtop.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CardRecyclerViewAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.mvp.mvp_cardtop.model.TopModel;
import com.example.myapplication.mvp.mvp_cardtop.presenter.CardTopPresenter;
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
 * Date: 2019/5/14
 * Description:
 */
public class CardTopFragment extends BaseFragment implements CardTopView {

    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Unbinder bind;
    private RecyclerView mRecyclerView;
    private List<TopModel.ResultBeanX.ResultBean.ListBean> dataList = new ArrayList<>();
    private CardTopPresenter cardTopPresenter = new CardTopPresenter(this);
    private int mCount = 0;
    private CardRecyclerViewAdapter adapter;

    public static CardTopFragment newInstance(String title) {
        CardTopFragment fragment = new CardTopFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View iniView() {
        View view = View.inflate(context, R.layout.fragment_cardtop, null);
        return view;
    }

    @Override
    protected void iniData() {
        bind = ButterKnife.bind(this, view);

        //初始recyclerView
        initRecyclerView();


    }

    private void initRecyclerView() {
        mRecyclerView = pullLoadMoreRecyclerView.getRecyclerView();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        fab.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setVerticalScrollBarEnabled(true);
        pullLoadMoreRecyclerView.setRefreshing(true);
        pullLoadMoreRecyclerView.setFooterViewText("loading");
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        adapter = new CardRecyclerViewAdapter(context);
        pullLoadMoreRecyclerView.setAdapter(adapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            cardTopPresenter.loadTopData("头条", "10", 0, "747b4744b764f5b6dc5021e72ff9ea4c");
        }
    }

    @Override
    public void getCardTopSuccess(TopModel topModel) {
        if (topModel.getCode().equals("10000")) {
            dataList.clear();
            dataList.addAll(topModel.getResult().getResult().getList());
            adapter.addAllData(dataList);
            pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        }
    }

    @Override
    public void getCardTopFailure(Throwable e) {
        Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_SHORT).show();
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {

        @Override
        public void onRefresh() {
            setRefresh();
            cardTopPresenter.loadTopData("头条", "10", 0, "747b4744b764f5b6dc5021e72ff9ea4c");
        }

        @Override
        public void onLoadMore() {
            mCount += 10;
            cardTopPresenter.loadTopData("头条", "10", mCount, "747b4744b764f5b6dc5021e72ff9ea4c");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cardTopPresenter.detachView();
    }

    @OnClick(R.id.fab)
    void onclick() {
        pullLoadMoreRecyclerView.scrollToTop();

    }

    public void setRefresh() {
        adapter.clearData();
        mCount = 0;
    }
}
