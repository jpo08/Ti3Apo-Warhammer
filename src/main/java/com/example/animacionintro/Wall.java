package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Wall extends Drawing{
    private Image sprite;
    private Rectangle hitbox;

    public Wall() {
        String uri = "file:"+ HelloApplication.class.getResource("wall.png").getPath();
    }

    @Override
    public void draw(GraphicsContext gc, boolean move) {

    }
}
