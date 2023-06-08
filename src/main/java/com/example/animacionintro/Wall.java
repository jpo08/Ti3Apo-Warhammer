package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Wall extends Drawing{
    private Image sprite;
    private Rectangle hitbox;
    private int shield;
    private int x,y;

    public Wall(int x, int y) {
        String uri = "file:"+ HelloApplication.class.getResource("wall.png").getPath();
        sprite=new Image(uri);
        this.pos.setY(y*50);
        this.pos.setX(x*50);
        hitbox=new Rectangle(pos.getY(),pos.getX(),50,50);
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

    @Override
    public void draw(GraphicsContext gc, boolean move) {
        hitbox=new Rectangle(pos.getY()+5,pos.getX()+5,40,40);
        gc.drawImage(sprite, pos.getY(),pos.getX(),50,50);

    }
}
