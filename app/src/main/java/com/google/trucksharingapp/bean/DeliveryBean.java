package com.google.trucksharingapp.bean;

import androidx.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

public class DeliveryBean extends LitePalSupport {
    private String name;
    private String dateTime;
    private String location;
    private String weight;
    private String type;
    private String width;
    private String height;
    private String length;

    public DeliveryBean(String name, String dateTime, String location, String weight, String type, String width, String height, String length) {
        this.name = name;
        this.dateTime = dateTime;
        this.location = location;
        this.weight = weight;
        this.type = type;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    @NonNull
    @Override
    public String toString() {
        return "DeliveryBean{" +
                "name='" + name + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", location='" + location + '\'' +
                ", weight='" + weight + '\'' +
                ", type='" + type + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", length='" + length + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
