package com.vnshine.phoenix.cooking.Model;

import java.util.ArrayList;

/**
 * Created by phoenix on 04/10/17.
 */

public class LVModel {
    int imageType;
    int backgroundType;
    String title;
    ArrayList<Dish> dishes;

    public LVModel(int imageType, int backgroundType, String title, ArrayList<Dish> dishes) {
        this.imageType = imageType;
        this.backgroundType = backgroundType;
        this.title = title;
        this.dishes = dishes;
    }

    public LVModel() {
    }

    public int getImageType() {
        return imageType;
    }

    public int getBackgroundType() {
        return backgroundType;
    }

    public void setBackgroundType(int backgroundType) {
        this.backgroundType = backgroundType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }
}
