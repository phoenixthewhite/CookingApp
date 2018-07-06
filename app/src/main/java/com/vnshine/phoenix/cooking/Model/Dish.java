package com.vnshine.phoenix.cooking.Model;

/**
 * Created by phoenix on 03/10/17.
 */

public class Dish {
    int id;
    String name;
    String dishImageLink;
    int type;
    String instruction;
    public Dish() {
    }


    public Dish(int id, String name, String dishImageLink, int type, String instruction) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.dishImageLink = dishImageLink;
        this.instruction = instruction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDishImageLink() {
        return dishImageLink;
    }

    public void setDishImageLink(String dishImageLink) {
        this.dishImageLink = dishImageLink;
    }

    public Dish(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", type=" + type +
                ", dishImageLink='" + dishImageLink + '\'' +
                '}';
    }
}
