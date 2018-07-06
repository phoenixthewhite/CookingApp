package com.vnshine.phoenix.cooking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vnshine.phoenix.cooking.Model.Dish;
import com.vnshine.phoenix.cooking.Activity.Main2Activity;
import com.vnshine.phoenix.cooking.R;
import com.vnshine.phoenix.cooking.Ultity.AdMod;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by phoenix on 03/10/17.
 */

public class    GridViewAdapter extends ArrayAdapter<Dish> {
    Context context;
    LayoutInflater inflater;
    List<Dish> dishes;

    public GridViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Dish> objects) {
        super(context, resource, objects);
        this.context = context;
        inflater = LayoutInflater.from(context);
        dishes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gv, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.dishName);
            holder.imageView = convertView.findViewById(R.id.dishImage);
            holder.instruction = convertView.findViewById(R.id.dishInstruction);
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();

        final Dish dish = dishes.get(position);
        holder.textView.setText(dish.getName());
//        int identifier = context.getResources().getIdentifier("@string/" +
//                ("mon" + String.valueOf(dish.getId())), "string", context.getPackageName());
//        String s = String.valueOf(Html.fromHtml(context.getResources().getString(identifier)))
//                .substring(0,50);
        String s = dish.getInstruction().substring(0,80);
        s = s.replaceAll("\\n","");
        holder.instruction.setText(s);
        Picasso.with(context)
                .load(dish.getDishImageLink())
                .placeholder(R.drawable.mn_home)
                .resize(110,100)
                .into(holder.imageView);
        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(finalConvertView.getContext(), Main2Activity.class);
                info.putExtra("dishName", dish.getName());
                info.putExtra("dishID", dish.getId());
                info.putExtra("dishLink", dish.getDishImageLink());
                info.putExtra("dishInstruction",dish.getInstruction());
//                System.out.println("\n*****************"+ dish.getName() + "\n*************\n");
                finalConvertView.getContext().startActivity(info);
            }
        });
        if(position==dishes.size()-1){
            AdMod adMod=new AdMod(context);
        }

//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
//        convertView.startAnimation(animation);
//        Animation animation = AnimationUtils.makeOutAnimation(context,true);
        Animation animation;
        animation = AnimationUtils.makeInAnimation(context,false);
        convertView.startAnimation(animation);
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        TextView instruction;
        CircleImageView imageView;
    }
}
