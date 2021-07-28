package net.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import net.basicmodel.R;
import net.interFace.OnItemClickListener;
import net.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

import model.HotDataBean;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {

    ArrayList<String> data;
    ArrayList<String> nameData;
    Context context;
    Activity activity;
    private OnItemClickListener onItemClickListener;

    public TypeAdapter(ArrayList<String> data,ArrayList<String> nameData, Context context, Activity activity) {
        this.data = data;
        this.nameData = nameData;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_type, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.root.getLayoutParams();
        params.width = ScreenUtils.getScreenSize(activity)[1] / 3;
        params.height = ScreenUtils.getScreenSize(activity)[1] / 3;
        holder.root.setLayoutParams(params);
        holder.name.setText(nameData.get(position));
        Glide.with(context).load(data.get(position)).into(holder.imageView);
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

    public ArrayList<String> getData(){
        return data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        RelativeLayout root;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.type_img);
            name = itemView.findViewById(R.id.type_name);
            root = itemView.findViewById(R.id.type_root);
        }
    }
}
