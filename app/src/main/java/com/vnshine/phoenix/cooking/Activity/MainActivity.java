package com.vnshine.phoenix.cooking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.vnshine.phoenix.cooking.Adapter.FragmentAdapter;
import com.vnshine.phoenix.cooking.DataBase.DatabaseHelper;
import com.vnshine.phoenix.cooking.Model.Dish;
import com.vnshine.phoenix.cooking.R;
import com.vnshine.phoenix.cooking.Ultity.MyApplication;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager mPager;
    FragmentAdapter fragmentAdapter;
    TabLayout tabLayout;
    Toolbar toolbar;
    TextView navName;
    public ArrayList<Dish> dishes = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private MyApplication myApplication;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
//        fullDetailDishes();
        setViewPager();
        setNavView();
        myApplication = (MyApplication) getApplication();
        mTracker = myApplication.getDefaultTracker();
        mTracker.setScreenName("Android_HomeScreen_CookingApp");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            toolbar.setTitle(R.string.title_frag_1);
//            toolbar.setBackgroundColor(Color.BLUE);
            mPager.setCurrentItem(0);

        } else if (id == R.id.nav_stew) {
//            toolbar.setBackgroundColor(Color.GREEN);
            toolbar.setTitle(R.string.title_frag_2);
            mPager.setCurrentItem(1);

        } else if (id == R.id.nav_soup) {
//            toolbar.setBackgroundColor(Color.MAGENTA);
            toolbar.setTitle(R.string.title_frag_3);
            mPager.setCurrentItem(2);
        } else if (id == R.id.nav_fried) {
//            toolbar.setBackgroundColor(Color.YELLOW);
            toolbar.setTitle(R.string.title_frag_4);
            mPager.setCurrentItem(3);
        } else if (id == R.id.nav_cake) {
//            toolbar.setBackgroundColor(Color.WHITE);
            toolbar.setTitle(R.string.title_frag_5);
            mPager.setCurrentItem(4);
        }
//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = this.getIntent();
        if(intent!=null){
            if(intent.getAction().toString().equalsIgnoreCase("trove")){
                Bundle bundle = intent.getExtras();
                if (bundle!= null){
                    int position = (int)bundle.get("Home");
                    mPager.setCurrentItem(position);
                }
            }

        }
    }

    void setIconTabLayout(){
        View icon0 = getLayoutInflater().inflate(R.layout.customtab,null);
        icon0.findViewById(R.id.iconTabLayout).setBackgroundResource(R.drawable.ic_home);
        tabLayout.getTabAt(0).setCustomView(icon0);
        View icon1 = getLayoutInflater().inflate(R.layout.customtab,null);
        icon1.findViewById(R.id.iconTabLayout).setBackgroundResource(R.drawable.mn_kho);
        tabLayout.getTabAt(1).setCustomView(icon1);
        View icon2 = getLayoutInflater().inflate(R.layout.customtab,null);
        icon2.findViewById(R.id.iconTabLayout).setBackgroundResource(R.drawable.mn_canh);
        tabLayout.getTabAt(2).setCustomView(icon2);
        View icon3 = getLayoutInflater().inflate(R.layout.customtab,null);
        icon3.findViewById(R.id.iconTabLayout).setBackgroundResource(R.drawable.mn_xao);
        tabLayout.getTabAt(3).setCustomView(icon3);
        View icon4 = getLayoutInflater().inflate(R.layout.customtab,null);
        icon4.findViewById(R.id.iconTabLayout).setBackgroundResource(R.drawable.mn_banh);
        tabLayout.getTabAt(4).setCustomView(icon4);
    }

    void setListenerTablayout(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    toolbar.setTitle(R.string.title_frag_1);
                }else if(tab.getPosition() == 1){
                    toolbar.setTitle(R.string.title_frag_2);
                }else if (tab.getPosition() == 2){
                    toolbar.setTitle(R.string.title_frag_3);
                }else if (tab.getPosition() == 3){
                    toolbar.setTitle(R.string.title_frag_4);
                }else if (tab.getPosition() == 4){
                    toolbar.setTitle(R.string.title_frag_5);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    void setNavView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    void setViewPager(){
        mPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        mPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(mPager);
        setListenerTablayout();
        setIconTabLayout();
    }


}
