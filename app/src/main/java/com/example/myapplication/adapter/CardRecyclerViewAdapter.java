package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.mvp_cardtop.model.TopModel;
import com.example.myapplication.mvp.mvp_cardtop.view.CardTopDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: 小川
 * Date: 2019/5/14
 * Description:
 */
public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<TopModel.ResultBeanX.ResultBean.ListBean> dataList = new ArrayList<>();

    public void addAllData(List<TopModel.ResultBeanX.ResultBean.ListBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        dataList.clear();
    }

    public CardRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_title.setText(dataList.get(position).getTitle());
        Glide.with(mContext).load(dataList.get(position).getPic()).into(holder.iv_photo);
        holder.tv_title.setBackgroundColor(Color.argb(20, 0, 0, 0));

        holder.tv_desc.setText(dataList.get(position).getContent().substring(17, dataList.get(position).getContent().length()) + "");
        holder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CardTopDetailActivity.class);
                intent.putExtra("top_weburl", dataList.get(position).getWeburl() + "");
                intent.putExtra("top_title", dataList.get(position).getTitle());
                intent.putExtra("top_pic", dataList.get(position).getPic() + "");
                mContext.startActivity(intent);
            }
        });
        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_share(position);
            }
        });
    }

    private void fab_share(int position) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "娱乐TOP");
        intent.putExtra(Intent.EXTRA_TEXT, dataList.get(position).getWeburl() + "");
        mContext.startActivity(Intent.createChooser(intent, dataList.get(position).getTitle()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title, tv_desc;
        public ImageView iv_photo;
        public Button btn_share;
        @BindView(R.id.btn_more)
        Button btn_more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_card_photo);
            tv_desc = (TextView) itemView.findViewById(R.id.card_description);
            btn_share = (Button) itemView.findViewById(R.id.btn_share);

        }
    }
}
