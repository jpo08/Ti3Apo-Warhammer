package com.example.animacionintro;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Wall {
    private Image sprite;
    private Rectangle hitbox;
    private int shield;
    private int x,y;
    private Canvas canvas;
    private GraphicsContext gc;

    public Wall(int x, int y, int type, Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        if (type==1){
            String uri = "file:"+ HelloApplication.class.getResource("wall.png").getPath();
            sprite=new Image(uri);
        } else if (type==2) {
            String uri = "file:"+ HelloApplication.class.getResource("rockblue.png").getPath();
            sprite=new Image(uri);
        }else {
            String uri = "file:"+ HelloApplication.class.getResource("blackwall.png").getPath();
            sprite=new Image(uri);
        }
        this.x = x*50;
        this.y = y*50;



        hitbox=new Rectangle(x,y,50,50);
        this.shield=3;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void draw() {
        hitbox=new Rectangle(x,y,50,50);

        gc.drawImage(sprite, x,y,50,50);

    }
}
