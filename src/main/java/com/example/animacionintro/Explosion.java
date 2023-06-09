package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion extends Drawing{
    private Image sprite;
    private int x,y;

    public Explosion(int x, int y) {
        this.x=x;
        this.y=y;
        String uri = "file:"+ HelloApplication.class.getResource("explosion.gif").getPath();
        sprite= new Image(uri);
    }

    @Override
    public void draw(GraphicsContext gc, boolean move) {
        gc.drawImage(sprite,x, y, 50,50);


    }
}
