package com.myowndev.main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by Iwan on 02.08.2016.
 */
public class Sprite {

    private Image img;
    private double x;
    private double y;
    private int w;
    private int h;

    public Sprite(double x, double y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    private void setImage(Image i) {
        img = i;
        w = (int) img.getWidth();
        h = (int) img.getHeight();
    }
    public void setImage(String s) {
        Image i = new Image(s);
        setImage(i);
    }
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
}
