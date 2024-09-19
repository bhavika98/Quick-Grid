package com.app.incroyable.quickgrid.model;

public class Grid3D {

    int thumb, layout, imageNumber, size, id;

    public Grid3D(int thumb, int layout, int imageNumber, int size, int id) {
        this.thumb = thumb;
        this.layout = layout;
        this.imageNumber = imageNumber;
        this.size = size;
        this.id = id;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
