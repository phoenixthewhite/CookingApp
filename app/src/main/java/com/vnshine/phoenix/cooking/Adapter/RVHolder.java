package com.vnshine.phoenix.cooking.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnshine.phoenix.cooking.R;

/**
 * Created by phoenix on 03/10/17.
 */

public class RVHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public ImageView imageView;
    public RVHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.itemRVDishName);
        imageView = itemView.findViewById(R.id.itemRVDishImage);
    }
}
