package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DeadAnimation extends Drawing {
    private Image sprite;

    public DeadAnimation(int type,Vector posicion) {
        String uri ="file:"+ HelloApplication.class.getResource("blood.gif").getPath();
        sprite=new Image(uri);
        pos.setX(posicion.getX());
        pos.setY(posicion.getY());

    }

    @Override
    public void draw(GraphicsContext gc, boolean move) {
        gc.drawImage(sprite,pos.getX()-15,pos.getY()-35,50,50);

    }
}
