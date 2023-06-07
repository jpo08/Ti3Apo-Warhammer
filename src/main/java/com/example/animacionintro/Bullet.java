package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Drawing{
    Image sprite;
    private Vector dir;

    private Circle hitbox;

    public Bullet(Vector pos,Vector dir) {
        String uri = "file:"+ HelloApplication.class.getResource("bullet.png").getPath();
        this.dir =dir;
        this.pos=pos;
        sprite=new Image(uri);
        hitbox = new Circle(pos.getX(), pos.getY(), 15);
    }

    @Override
    public void draw(GraphicsContext gc,boolean m) {
        hitbox = new Circle(pos.getX(), pos.getY(), 15);
        gc.drawImage(sprite,pos.getX()-7.5,pos.getY()-7.5,15,15);


        pos.setX(pos.getX()+dir.getX());
        pos.setY(pos.getY()+dir.getY());

    }

    public Circle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Circle hitbox) {
        this.hitbox = hitbox;
    }
}
