package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Enemy extends Drawing implements Runnable{

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

    @Override
    public void run(){
        //Tercer plano
        while (!isDead){
            double deltaX = Math.random()*6-3;
            double deltaY = Math.random()*6-3;

            pos.setY(pos.getY()+deltaY);
            pos.setX(pos.getX()+deltaX);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
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
