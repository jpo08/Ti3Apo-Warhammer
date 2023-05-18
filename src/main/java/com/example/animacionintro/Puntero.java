package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Puntero extends Drawing {

    private Image sprite;
    public Puntero(Vector pos) {
        String uri = "file:"+ HelloApplication.class.getResource("punteroArma.png").getPath();
        this.pos=pos;
        sprite=new Image(uri);
    }



    @Override
    public void draw(GraphicsContext gc, boolean move) {
        gc.drawImage(sprite,pos.getX()-10,pos.getY()-10,20,20);

    }
}
