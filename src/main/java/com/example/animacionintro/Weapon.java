package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Weapon extends Drawing {

    private Vector direction;
    private Vector pos;
    private Image sprite;
    private Image spriteL;

    Puntero puntero;
    private  boolean state;


    public Weapon(Vector pos, int type) {
        String uri = "file:"+ HelloApplication.class.getResource("gun 1.png").getPath();
        String uriL = "file:"+ HelloApplication.class.getResource("gun 1L.png").getPath();
        String uri2 = "file:"+ HelloApplication.class.getResource("gun 2.png").getPath();
        String uri2L = "file:"+ HelloApplication.class.getResource("gun 2L.png").getPath();
        this.pos=pos;
        direction = new Vector(1,1);
        if (type==1){
            sprite=new Image(uri);
            spriteL=new Image(uriL);
        }else {
            sprite=new Image(uri2);
            spriteL= new Image(uri2L);
        }




    }

    public Image getSprite() {
        return sprite;
    }

    public void setPuntero(Puntero puntero) {
        this.puntero = puntero;
    }


    public void changeAngle(double a){
        double amp = direction.getAmplitude();
        double angle = direction.getAngle();
        angle += a;
        direction.setX(amp*Math.cos(Math.toRadians(angle)));
        direction.setY(amp*Math.sin(Math.toRadians(angle)));
    }


    @Override
    public void draw(GraphicsContext gc, boolean move) {
        if (puntero.pos.getX()> pos.getX()){
            gc.drawImage(sprite, pos.getX(), pos.getY()-15, 30,22);
        }else {
            gc.drawImage(spriteL, pos.getX()-30,pos.getY()-15, 30,22);
        }

    }
}
