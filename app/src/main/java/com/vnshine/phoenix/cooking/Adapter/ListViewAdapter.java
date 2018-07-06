package com.vnshine.phoenix.cooking.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnshine.phoenix.cooking.Model.Dish;
import com.vnshine.phoenix.cooking.Model.LVModel;
import com.vnshine.phoenix.cooking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoenix on 04/10/17.
 */

public class ListViewAdapter extends ArrayAdapter<LVModel> {

    Context context;
    List<LVModel> lvModels;
    LayoutInflater inflater;


    ArrayList<Dish> dishes = new ArrayList<>();
    RVAdapter mRVAdapter;
    Dish dish = new Dish();
    public ListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<LVModel> objects) {
        super(context, resource, objects);
        this.context = context;
        inflater = LayoutInflater.from(context);
        lvModels = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_lv,parent,false);
            holder.imageDish = convertView.findViewById(R.id.imageType);
            holder.imageBackground = convertView.findViewById(R.id.backgroundType);
            holder.textView = convertView.findViewById(R.id.titleFood);
            holder.recyclerView = convertView.findViewById(R.id.recyclerView);
            convertView.setTag(holder);
        }else holder = (ViewHolder) convertView.getTag();
        LVModel model = lvModels.get(position);
        holder.imageBackground.setImageResource(model.getBackgroundType());
        holder.imageDish.setImageResource(model.getImageType());
        holder.imageDish.setColorFilter(Color.parseColor("#ffffff"));
        holder.textView.setText(model.getTitle());
        mRVAdapter = new RVAdapter(model.getDishes());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(convertView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView.setAdapter(mRVAdapter);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        return  convertView;

    }

    class ViewHolder{
        ImageView imageDish;
        ImageView imageBackground;
        TextView textView;
        RecyclerView recyclerView;
    }
}
