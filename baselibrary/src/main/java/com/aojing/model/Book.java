package com.aojing.model;

/**
 * book的实体类
 * Created by wxw on 2016/12/28.
 */
public class Book {
    private int imageView ;
    private String name;

    public Book() {
    }

    public Book(int imageView, String name) {
        this.imageView = imageView;
        this.name = name;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
