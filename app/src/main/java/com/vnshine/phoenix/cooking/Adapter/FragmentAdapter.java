package com.vnshine.phoenix.cooking.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vnshine.phoenix.cooking.Fragment.CakeFragment;
import com.vnshine.phoenix.cooking.Fragment.FriedFragment;
import com.vnshine.phoenix.cooking.Fragment.HomeFragment;
import com.vnshine.phoenix.cooking.Fragment.SoupFragment;
import com.vnshine.phoenix.cooking.Fragment.StewFrament;

/**
 * Created by phoenix on 03/10/17.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    String [] titles = new String[]{
            "Trang chu","Kho","Canh","Xao","Banh"
    };

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new HomeFragment();
            case 1: return new StewFrament();
            case 2: return new SoupFragment();
            case 3: return new FriedFragment();
            case 4: return new CakeFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles[position];
//    }
}
