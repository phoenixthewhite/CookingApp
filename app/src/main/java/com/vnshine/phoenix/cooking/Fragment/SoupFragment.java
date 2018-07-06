package com.vnshine.phoenix.cooking.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.vnshine.phoenix.cooking.Adapter.GridViewAdapter;
import com.vnshine.phoenix.cooking.DataBase.DatabaseHelper;
import com.vnshine.phoenix.cooking.Model.Dish;
import com.vnshine.phoenix.cooking.R;

import java.util.ArrayList;

/**
 * Created by phoenix on 03/10/17.
 */

public class SoupFragment extends Fragment {
    String [] names = new String[]{
            "GGG", "GGG", "GGG", "GGG", "GGG", "GGG", "GGG", "GGG", "GGG", "GGG", "GGG", "GGG"
            , "GGG", "GGG", "GGG", "GGG", "GGG"
    };
    ArrayList<Dish> dishes = new ArrayList<>();
    Dish dish;
    GridView gridView;
    GridViewAdapter gridViewAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soup,container,false);
//        for (int i = 0; i < names.length; i++) {
//            dish = new Dish(names[i]);
//            dishes.add(dish);
//        }
        DatabaseHelper.xuLiSaoChepCSDL(getActivity());
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        dishes = databaseHelper.getListDishes("2");
        gridView = view.findViewById(R.id.gvCake);
        gridViewAdapter = new GridViewAdapter(getActivity(),R.layout.item_gv,dishes);
        gridView.setAdapter(gridViewAdapter);

        return view;
    }
}
