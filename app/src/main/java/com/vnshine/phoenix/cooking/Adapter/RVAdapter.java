package com.vnshine.phoenix.cooking.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.vnshine.phoenix.cooking.Model.Dish;
import com.vnshine.phoenix.cooking.Activity.Main2Activity;
import com.vnshine.phoenix.cooking.R;

import java.util.ArrayList;

/**
 * Created by phoenix on 03/10/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVHolder> {
    ArrayList<Dish> dishes;

    public RVAdapter(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv,parent,false);

        RVHolder holder = new RVHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RVHolder holder, final int position) {
        final String name;
        final String imageLink;
        final int id;
        final String instruction;
        final Dish dish = dishes.get(position);
        holder.textView.setText(dish.getName());
        Picasso.with(holder.imageView.getContext())
                .load(dish.getDishImageLink())
                .placeholder(R.drawable.mn_home)
                .resize(60,60)
                .into(holder.imageView);
        name = dish.getName();
        id = dish.getId();
        imageLink = dish.getDishImageLink();
        instruction = dish.getInstruction();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(dishes.get(position).getName());
                Intent info = new Intent(holder.itemView.getContext(),Main2Activity.class);
                info.putExtra("dishName", name);
                info.putExtra("dishID", id);
                info.putExtra("dishInstruction",instruction);
                info.putExtra("dishLink", imageLink);
                holder.itemView.getContext().startActivity(info);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
}
