package com.vnshine.phoenix.cooking.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.vnshine.phoenix.cooking.Model.Dish;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by phoenix on 12/10/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    Context mContext;
    static String DB_NAME = "cooking2.sqlite";
    static String DB_PATH = "/databases/";
    static InputStream inputStream;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        mContext = context;
        doCreateDb();
    }

    public void doCreateDb() {
        db = mContext.openOrCreateDatabase("cooking2.sqlite", MODE_PRIVATE, null);
        System.out.println("MMMM" + db);
        if (db != null) {
            Log.e("bbb", db.toString());
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS" + "Product");
        onCreate(db);
    }

    public static void xuLiSaoChepCSDL(Context context) {
        File file = context.getDatabasePath(DB_NAME);
        if (!file.exists()) {
            try {
                copyCSDLtuAssetvaoApp(context);
                System.out.println("thanh cong");
            } catch (Exception e) {
                System.out.println("loi1111:" + e.toString());
            }
        }
    }

    public static void copyCSDLtuAssetvaoApp(Context context) {
        try {
            inputStream = context.getAssets().open(DB_NAME);
            File f = new File(context.getApplicationInfo().dataDir + DB_PATH);
            if (!f.exists())
                f.mkdir();
            OutputStream os = new FileOutputStream(getUrl(context));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
            os.close();
            inputStream.close();

        } catch (Exception e) {
            System.out.println("loi2222:" + e.toString());
        }
    }

    public static String getUrl(Context context) {
        return context.getApplicationInfo().dataDir + DB_PATH + DB_NAME;
    }


    public static void saveToSdCard(Context context) {
        File f = new File(context.getApplicationInfo().dataDir + DB_PATH + DB_NAME);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            String outFileName = Environment.getExternalStorageDirectory() + "/database.db";
            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);
            // Transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            System.out.println("aaaaaaaaaaaaaaaaaaa");
            output.flush();
            output.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        // Close the streams
    }

    @Override
    public synchronized void close() {
        super.close();
        db.close();
    }

    public ArrayList<Dish> getListDishes(String type) {
        ArrayList<Dish> dishes = new ArrayList<>();
        Dish dish;
        try {
            Cursor cursor = db.query("Student", null,"classID = ?",new String[]{type},null,null,null);
            while (cursor.moveToNext()) {
                // System.out.println("---------------------------------");
                dish = new Dish(cursor.getInt(0), cursor.getString(1), cursor.getString(2)
                        , cursor.getInt(3), cursor.getString(4));
                dishes.add(dish);

            }
            cursor.close();
//            System.out.println("tinh thanh: "+dishes.size());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return dishes;
    }
}
