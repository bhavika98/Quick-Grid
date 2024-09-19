package com.app.incroyable.quickgrid.model;

public class MyAlbum {

    String name, path;
    int id, total;

    public MyAlbum(String name, String path, int id, int total) {
        this.name = name;
        this.path = path;
        this.id = id;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
