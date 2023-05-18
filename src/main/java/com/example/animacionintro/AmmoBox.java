package com.example.animacionintro;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AmmoBox extends Drawing{
    private Image sprite;

    public AmmoBox(Canvas cv) {
        String uri ="file:"+ HelloApplication.class.getResource("weaponBox.png").getPath();
        sprite=new Image(uri);
        pos.setX(cv.getWidth()/2);
        pos.setY(cv.getHeight()/2);
    }

    @Override
    public void draw(GraphicsContext gc, boolean move) {
        gc.drawImage(sprite,pos.getX()-20,pos.getY()-20,40,40);

    }
}
