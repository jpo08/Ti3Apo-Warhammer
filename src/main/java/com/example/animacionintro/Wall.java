package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Wall extends Drawing{
    private Image sprite;
    private Rectangle hitbox;

    public Wall(int x, int y) {
        String uri = "file:"+ HelloApplication.class.getResource("wall.png").getPath();
        sprite=new Image(uri);
        this.pos.setY(y*50);
        this.pos.setX(x*50);
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

    @Override
    public void draw(GraphicsContext gc, boolean move) {
        gc.drawImage(sprite, pos.getY(),pos.getX(),50,50);

    }
}
