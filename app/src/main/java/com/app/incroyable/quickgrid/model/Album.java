package com.app.incroyable.quickgrid.model;

public class Album {

    String name, cover, count;

    public Album(String name, String cover, String count) {
        this.name = name;
        this.cover = cover;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
