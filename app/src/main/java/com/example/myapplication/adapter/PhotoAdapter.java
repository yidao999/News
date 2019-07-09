package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.mvp_photo.model.PhotoModel;
import com.example.myapplication.mvp.mvp_photo.view.PhotoDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: 小川
 * Date: 2019/5/20
 * Description:
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private Context mContext;
    private List<PhotoModel.DataBean> photoList = new ArrayList<>();

    public PhotoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void clearPhotoData() {
        this.photoList.clear();
    }

    public void addAllPhotoData(List<PhotoModel.DataBean> photoList) {
        this.photoList.addAll(photoList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(mContext)
                .load(photoList.get(i).getImage_url())
                .into(viewHolder.iv_photo);
        viewHolder.iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoDetailsActivity.class);
                intent.putExtra("img_url", photoList.get(i).getImage_url());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photo)
        ImageView iv_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
