package com.vnshine.phoenix.cooking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vnshine.phoenix.cooking.Model.Dish;
import com.vnshine.phoenix.cooking.Activity.Main2Activity;
import com.vnshine.phoenix.cooking.R;

import java.util.ArrayList;

/**
 * Created by phoenix on 13/10/17.
 */

public class AutoPagerAdapter extends PagerAdapter {

    private ArrayList<Dish> dishes;
    private LayoutInflater inflater;
    private Context context;

    public AutoPagerAdapter(Context context, ArrayList<Dish> dishes) {
        this.dishes = dishes;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dishes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final View imageLayout = inflater.inflate(R.layout.sliding_image, container, false);
        assert imageLayout != null;
        ImageView imageView = imageLayout.findViewById(R.id.image_auto);
        Picasso.with(context).load(dishes.get(position).getDishImageLink())
                .placeholder(R.drawable.mn_home)
                .resize(800,600)
                .into(imageView);
        container.addView(imageLayout,0);
        imageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(imageLayout.getContext(), Main2Activity.class);
                intent.putExtra("dishName", dishes.get(position).getName());
                intent.putExtra("dishID", dishes.get(position).getId());
                intent.putExtra("dishLink", dishes.get(position).getDishImageLink());
                intent.putExtra("dishInstruction",dishes.get(position).getInstruction());
                imageLayout.getContext().startActivity(intent);
            }
        });
        return imageLayout;
    }
}
