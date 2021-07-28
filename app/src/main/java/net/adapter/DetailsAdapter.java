package net.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import net.basicmodel.R;
import net.interFace.OnItemClickListener;
import net.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import model.HotDataBean;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    HotDataBean data;
    Context context;
    Activity activity;
    OnItemClickListener onItemClickListener;

    public DetailsAdapter(HotDataBean data, Context context,Activity activity) {
        this.data = data;
        this.context = context;
        this.activity = activity;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HotDataBean getData() {
        return data;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_details, parent, false);
        DetailsAdapter.ViewHolder holder = new DetailsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.height = ScreenUtils.getScreenSize(activity)[0] / 3;
        holder.imageView.setLayoutParams(params);
        Glide.with(context)
                .load(data.get(position).getImg_thumb_url())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(view, pos, "");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.details_img);
        }
    }
}
