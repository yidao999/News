package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.mvp_cardtop.model.TopModel;
import com.example.myapplication.mvp.mvp_news.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: 小川
 * Date: 2019/5/17
 * Description:
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<TopModel.ResultBeanX.ResultBean.ListBean> topList = new ArrayList<>();
    private OnItemClickListener listener;

    public NewsAdapter(Context context) {
        mContext = context;
    }

    public void addAllNewsData(List<TopModel.ResultBeanX.ResultBean.ListBean> topList) {
        this.topList.addAll(topList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, viewGroup, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(topList.get(position).getTitle());
        Glide.with(mContext).load(topList.get(position).getPic()).into(holder.newsPhoto);
        // holder.title.setBackgroundColor(Color.argb(20, 0, 0, 0));
        String content = topList.get(position).getContent();
        holder.newsDesc.setText(topList.get(position).getContent().substring(17, topList.get(position).getContent().length()) + "");
        holder.newsTvtime.setText(topList.get(position).getTime() + "");
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override

    public int getItemCount() {
        return topList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_photo)
        ImageView newsPhoto;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.news_desc)
        TextView newsDesc;
        @BindView(R.id.news_tvtime)
        TextView newsTvtime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClick(v, (int) v.getTag());
        }
    }

    public void clearNewData() {
        topList.clear();
    }
}
