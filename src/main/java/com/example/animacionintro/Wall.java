package com.example.animacionintro;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Wall {
    private Image sprite;
    private Image sprite1;
    private Image sprite2;
    private Rectangle hitbox;
    private int shield;
    private int x,y;
    private Canvas canvas;
    private GraphicsContext gc;

    private Explosion ex;

    public Wall(int x, int y, int type, Canvas canvas) {
        ex= new Explosion(x,y);
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        if (type==1){
            String uri = "file:"+ HelloApplication.class.getResource("wall.png").getPath();
            String uri1 = "file:"+ HelloApplication.class.getResource("wall2.png").getPath();
            String uri2= "file:"+ HelloApplication.class.getResource("wall3.png").getPath();
            sprite=new Image(uri);
            sprite1=new Image(uri1);
            sprite2=new Image(uri2);
        } else if (type==2) {
            String uri = "file:"+ HelloApplication.class.getResource("rockblue.png").getPath();
            String uri1 = "file:"+ HelloApplication.class.getResource("rockblue2.png").getPath();
            String uri2 = "file:"+ HelloApplication.class.getResource("rockblue3.png").getPath();
            sprite=new Image(uri);
            sprite1=new Image(uri1);
            sprite2=new Image(uri2);
        }else {
            String uri = "file:"+ HelloApplication.class.getResource("blackwall1.png").getPath();
            String uri1 = "file:"+ HelloApplication.class.getResource("blackwall2.png").getPath();
            String uri2 = "file:"+ HelloApplication.class.getResource("blackwall3.png").getPath();
            sprite=new Image(uri);
            sprite1=new Image(uri1);
            sprite2=new Image(uri2);
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

    public Explosion getEx() {
        return ex;
    }

    public void setEx(Explosion ex) {
        this.ex = ex;
    }

    public void draw() {

        hitbox=new Rectangle(x,y,50,50);
        if (shield==3){
            gc.drawImage(sprite, x,y,50,50);
        }else if(shield==2){
            gc.drawImage(sprite1, x,y,50,50);
        }else {
            gc.drawImage(sprite2, x,y,50,50);
        }



    }
}
