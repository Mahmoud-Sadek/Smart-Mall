package com.sadek.orxstradev.smartmall.model.body;

public class OptionModel {
    String color, txt;

    public OptionModel(String color, String txt) {
        this.color = color;
        this.txt = txt;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
