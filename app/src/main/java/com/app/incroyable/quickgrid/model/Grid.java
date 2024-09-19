package com.app.incroyable.quickgrid.model;

public class Grid {

    int thumb, layout, title, type;

    public Grid(int thumb, int layout, int title, int type) {
        this.thumb = thumb;
        this.layout = layout;
        this.title = title;
        this.type = type;
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

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
