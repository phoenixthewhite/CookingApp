package com.vnshine.phoenix.cooking.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vnshine.phoenix.cooking.R;
import com.vnshine.phoenix.cooking.Ultity.AdMod;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView textView;
    ImageView imageView;
    int id;
    String title;
    String imageLink;
    String instruction;

    //    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        getContent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        textView = (TextView) findViewById(R.id.text_huongdan);
//        int identifier = getResources().getIdentifier("@string/" + ("mon" + String.valueOf(id)), "string", getPackageName());
//        textView.setText(Html.fromHtml(getResources().getString(identifier)));
        textView.setText(instruction);
        imageView = (ImageView) findViewById(R.id.dishImageinfo);
        Picasso.with(this).load(imageLink)
                .placeholder(R.drawable.mn_home)
                .resize(Resources.getSystem().getDisplayMetrics().widthPixels,600)
                .into(imageView);
        AdMod adMod=new AdMod(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        if (id == R.id.nav_home2) {
            intent.putExtra("Home", 0);
//            finish();
        } else if (id == R.id.nav_stew2) {
            intent.putExtra("Home", 1);
//            finish();
        } else if (id == R.id.nav_soup2) {
            intent.putExtra("Home", 2);
//            finish();
        } else if (id == R.id.nav_fried2) {
            intent.putExtra("Home", 3);
//            finish();
        } else if (id == R.id.nav_cake2) {
            intent.putExtra("Home", 4);
//            finish();
        }
//        else if (id == R.id.nav_share2) {
//
//        } else if (id == R.id.nav_send2) {
//
//        }
        intent.setAction("trove");
        startActivity(intent);
        finish();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void getContent() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        id =  bundle.getInt("dishID");
        title = bundle.getString("dishName");
        imageLink = bundle.getString("dishLink");
        instruction = bundle.getString("dishInstruction");
    }
}


