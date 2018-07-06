package com.vnshine.phoenix.cooking.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.viewpagerindicator.CirclePageIndicator;
import com.vnshine.phoenix.cooking.Adapter.AutoPagerAdapter;
import com.vnshine.phoenix.cooking.Adapter.ListViewAdapter;
import com.vnshine.phoenix.cooking.DataBase.DatabaseHelper;
import com.vnshine.phoenix.cooking.Model.Dish;
import com.vnshine.phoenix.cooking.Model.LVModel;
import com.vnshine.phoenix.cooking.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by phoenix on 03/10/17.
 */

public class HomeFragment extends Fragment {

    int[] titles = new int[]{
            R.string.title_frag_2, R.string.title_frag_3, R.string.title_frag_4, R.string.title_frag_5
    };
    int[] imagesType = new int[]{
            R.drawable.mn_kho, R.drawable.mn_canh, R.drawable.mn_xao, R.drawable.mn_banh
    };
    int[] imagesBackground = new int[]{
            R.drawable.kho, R.drawable.canh, R.drawable.ran, R.drawable.banh
    };

    public static ArrayList<Dish> allDishes;

    ArrayList<Dish> dishes = new ArrayList<>();
    ArrayList<LVModel> models = new ArrayList<>();
    ListViewAdapter listViewAdapter;
    ListView listView;
    LVModel model;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        DatabaseHelper.xuLiSaoChepCSDL(getActivity());
        databaseHelper = new DatabaseHelper(getActivity());
        for (int i = 0; i < titles.length; i++) {
            model = new LVModel();
            model.setBackgroundType(imagesBackground[i]);
            model.setImageType(imagesType[i]);
            model.setTitle(getString(titles[i]));
            importListDishes((i+1)+"");
            model.setDishes(dishes);
            models.add(model);
        }
        listView = view.findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter(getActivity(), R.layout.item_lv, models);
        listView.setAdapter(listViewAdapter);
        init(view);
        return view;
    }

    private void importListDishes(String type) {
        dishes = databaseHelper.getListDishes(type);
    }

    private void init(View view) {
        ArrayList<Dish> autoDishes = getDishes();
        mPager = view.findViewById(R.id.autoPager);
        mPager.setAdapter(new AutoPagerAdapter(getActivity(), autoDishes));

        CirclePageIndicator indicator =
                view.findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);

        NUM_PAGES = autoDishes.size();

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    private ArrayList<Dish> getDishes() {
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("daily_dishes", Context.MODE_PRIVATE);

        ArrayList<Dish> autoDish = new ArrayList<>();
        ArrayList<Dish> tempList;
        tempList = databaseHelper.getListDishes("1");
        autoDish.add(tempList.get(sharedPreferences.getInt("kho",0)));
        tempList.clear();
        tempList = databaseHelper.getListDishes("2");
        autoDish.add(tempList.get(sharedPreferences.getInt("canh",0)));
        tempList.clear();
        tempList = databaseHelper.getListDishes("3");
        autoDish.add(tempList.get(sharedPreferences.getInt("xao",0)));
        autoDish.toString();
        return autoDish;
    }
}
