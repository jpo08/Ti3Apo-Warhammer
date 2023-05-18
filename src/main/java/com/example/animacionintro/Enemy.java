package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Enemy extends Drawing{

    private Image sprite;

    private int nLifes;

    private boolean isDead;

    public Enemy(Vector pos){
        String uri = "file:"+ HelloApplication.class.getResource("alien.gif").getPath();
        this.pos=pos;
        sprite=new Image(uri);
        nLifes=3;
    }
    @Override
    public void draw(GraphicsContext gc,boolean m) {
        gc.drawImage(sprite,pos.getX()-25, pos.getY()-25, 50,50);

    }

    public int getnLifes() {
        return nLifes;
    }

    public void setnLifes(int nLifes) {
        this.nLifes = nLifes;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
