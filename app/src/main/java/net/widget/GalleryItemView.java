package net.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import net.basicmodel.R;

/**
 * Copyright (C) 2021,2021/7/28, a Tencent company. All rights reserved.
 * <p>
 * User : v_xhangxie
 * <p>
 * Desc :
 */
public class GalleryItemView extends LinearLayout {

    ImageView img;

    public GalleryItemView(Context context) {
        super(context);
        initView(context);
    }

    public GalleryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GalleryItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private View initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_gallery, this, true);
        img = view.findViewById(R.id.gallery_item_img);
        return view;
    }

    public void setImg(Context context, String url) {
        Glide.with(context).load(url).into(img);
    }
}
