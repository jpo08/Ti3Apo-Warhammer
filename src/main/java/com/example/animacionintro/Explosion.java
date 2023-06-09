package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;

public class Explosion extends Drawing{
    private Image sprite;
    private int x,y;

    private MusicPlayer mp=new MusicPlayer();

    public Explosion(int x, int y) {
        this.x=x;
        this.y=y;
        String uri = "file:"+ HelloApplication.class.getResource("boom.gif").getPath();
        sprite= new Image(uri);
    }

    @Override
    public void draw(GraphicsContext gc, boolean move) {
        gc.drawImage(sprite,x, y, 60,60);
        mp.stopSound2();
        mp.playSound2(new File("src/main/resources/com/example/animacionintro/music/explosionSound.wav"));


    }
}
