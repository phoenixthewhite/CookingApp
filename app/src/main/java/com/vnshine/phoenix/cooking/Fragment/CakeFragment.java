package com.vnshine.phoenix.cooking.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import com.vnshine.phoenix.cooking.Adapter.GridViewAdapter;
import com.vnshine.phoenix.cooking.DataBase.DatabaseHelper;
import com.vnshine.phoenix.cooking.Model.Dish;
import com.vnshine.phoenix.cooking.R;

import java.util.ArrayList;

/**
 * Created by phoenix on 03/10/17.
 */

public class CakeFragment extends Fragment {
//    String[] names = new String[]{
//            "AAA", "AAA", "AAA", "AAA", "AAA", "AAA", "AAA", "AAA", "AAA", "AAA", "AAA", "AAA"
//            , "AAA", "AAA", "AAA", "AAA", "AAA"
//    };
    ArrayList<Dish> dishes = new ArrayList<>();
    EditText editText;
    GridView gridView;
    GridViewAdapter gridViewAdapter;
    DatabaseHelper databaseHelper;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cake, container, false);
        editText = view.findViewById(R.id.search);
        databaseHelper = new DatabaseHelper(getActivity());
        dishes = databaseHelper.getListDishes("4");
        gridView = view.findViewById(R.id.gvCake);
        gridViewAdapter = new GridViewAdapter(getActivity(), R.layout.item_gv, dishes);
        gridView.setAdapter(gridViewAdapter);
//        toolbar = view.findViewById(R.id.searchTB);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        gridView.isNestedScrollingEnabled();
        return view;
    }

}
