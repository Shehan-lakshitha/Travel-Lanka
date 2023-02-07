package com.example.travellanka;

import android.graphics.drawable.Drawable;

import org.w3c.dom.Text;

public class ProvinceModel {
    private String name;
    private Text image;

    public ProvinceModel(){}

    public ProvinceModel(String name,Text image){
        this.name =name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return (Drawable) image;
    }

    public void setImage(Text image) {
        this.image = image;
    }
}
