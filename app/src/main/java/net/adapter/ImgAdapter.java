package net.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import net.basicmodel.R;
import net.interFace.OnItemClickListener;
import net.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;

import model.HotDataBean;

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ViewHolder> {

    HotDataBean data;
    Context context;
    Activity activity;
    private OnItemClickListener onItemClickListener;

    public ImgAdapter(HotDataBean data, Context context, Activity activity) {
        this.data = data;
        this.context = context;
        this.activity = activity;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_img, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.height = ScreenUtils.getScreenSize(activity)[0] / 4;
        params.width = ScreenUtils.getScreenSize(activity)[1];
        holder.imageView.setLayoutParams(params);
        Glide.with(context).load(data.get(position).getImg_thumb_url()).into(holder.imageView);
        holder.name.setText(data.get(position).getImg_title());
        holder.like.setText(data.get(position).getImg_like() + "likes");
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
        Log.v("----------", "??????=" + data.size());
        return data.size();
    }

    public HotDataBean getData(){
        return data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        TextView like;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.img_name);
            like = itemView.findViewById(R.id.img_like);
        }
    }
}
