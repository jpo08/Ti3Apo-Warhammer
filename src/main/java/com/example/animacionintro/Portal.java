package com.example.animacionintro;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Portal extends Drawing{
    private Image sprite;

    public Portal(Canvas cv) {
        String uri = "file:"+ HelloApplication.class.getResource("portalLV.gif").getPath();
        sprite=new Image(uri);
        pos.setX(cv.getWidth()/2);
        pos.setY(cv.getHeight()/2);
    }

    @Override
    public void draw(GraphicsContext gc, boolean move) {
        gc.drawImage(sprite,pos.getX()-30,pos.getY()-30,60,60);

    }
}
