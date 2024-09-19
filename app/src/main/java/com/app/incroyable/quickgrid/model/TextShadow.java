package com.app.incroyable.quickgrid.model;

public class TextShadow {

    int radius, left, right;

    public TextShadow(int radius, int left, int right) {
        this.radius = radius;
        this.left = left;
        this.right = right;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
