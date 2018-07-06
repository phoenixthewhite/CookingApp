package com.vnshine.phoenix.cooking.Ultity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by canh7antt on 9/16/2017.
 */

public class DataUtil {
    Context context;
    public DataUtil(Context context) {
        this.context=context;
    }
    public void save(long idBe){
        SharedPreferences pre=context.getSharedPreferences("idBe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pre.edit();
        editor.putLong("idBe",idBe);
        editor.commit();
    }
    public long readIdBe(){
        SharedPreferences pre=context.getSharedPreferences("idBe", Context.MODE_PRIVATE);
        long id=0;
        id=pre.getLong("idBe",1);
        return id;
    }
    public void saveNotificationHours(int gio){
        SharedPreferences pre=context.getSharedPreferences("idBe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pre.edit();
        editor.putInt("gio",gio);
        editor.commit();
    }
    public int readNotificationHours(){
        SharedPreferences pre=context.getSharedPreferences("idBe", Context.MODE_PRIVATE);
        int id=0;
        id=pre.getInt("gio",7);
        return id;
    }
    public void saveNotificationMin(int phut){
        SharedPreferences pre=context.getSharedPreferences("idBe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pre.edit();
        editor.putInt("min",phut);
        editor.commit();
    }
    public int readNotificationMin(){
        SharedPreferences pre=context.getSharedPreferences("idBe", Context.MODE_PRIVATE);
        int id=0;
        id=pre.getInt("min",0);
        return id;
    }
    public void saveBatTat(boolean bat){
        SharedPreferences pre=context.getSharedPreferences("idBe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pre.edit();
        editor.putBoolean("bat",bat);
        editor.commit();
    }
    public boolean readBatTat(){
        SharedPreferences pre=context.getSharedPreferences("idBe", Context.MODE_PRIVATE);
        boolean id;
        id=pre.getBoolean("bat",true);
        return id;
    }
    public void saveDungGio(boolean bat){
        SharedPreferences pre=context.getSharedPreferences("idBe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pre.edit();
        editor.putBoolean("dunggio",bat);
        editor.commit();
    }
    public boolean readDungGio(){
        SharedPreferences pre=context.getSharedPreferences("idBe", Context.MODE_PRIVATE);
        boolean id;
        id=pre.getBoolean("dunggio",true);
        return id;
    }
    public  void writeKey(String key){
        SharedPreferences pre=context.getSharedPreferences("idBe",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pre.edit();
        editor.putString("key",key);
        editor.commit();
    }
    public  String readKey(){
        SharedPreferences pre=context.getSharedPreferences("key", Context.MODE_PRIVATE);
        String key=pre.getString("key","");
        return key;
    }
}
