package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Avatar extends Drawing{

    private Image sprite;
    private Image sprite1;
    private Image sprite3;
    private Image sprite4;
    private int ammo;

    Puntero puntero;

    boolean armed;

    private int secondWeapon;



    public Avatar(){
        armed=false;
        String uri = "file:"+ HelloApplication.class.getResource("marine nogunWalkRight.gif").getPath();
        String uri2 = "file:"+ HelloApplication.class.getResource("marine nogunstandRight.png").getPath();
        String uri3 = "file:"+ HelloApplication.class.getResource("marine nogunWalkleft.gif").getPath();
        String uri4 = "file:"+ HelloApplication.class.getResource("marine nogunstandLeft.png").getPath();

        sprite=new Image(uri);
        sprite1=new Image(uri2);
        sprite3=new Image(uri3);
        sprite4=new Image(uri4);

        pos.setX(100);
        pos.setY(100);
        ammo=5;
        secondWeapon=1;
    }

    public void setPuntero(Puntero puntero) {
        this.puntero = puntero;
    }

    public void setArmed(boolean armed) {
        this.armed = armed;
    }

    public boolean isArmed() {
        return armed;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getSecondWeapon() {
        return secondWeapon;
    }

    public void setSecondWeapon(int secondWeapon) {
        this.secondWeapon = secondWeapon;
    }

    @Override
    public void draw(GraphicsContext gc,boolean move) {

        if (move==true){
            if (puntero.pos.getX()< pos.getX()){
                gc.drawImage(sprite3,pos.getX()-20, pos.getY()-27,40,54);
            }else {
                gc.drawImage(sprite,pos.getX()-20, pos.getY()-27,40,54);
            }

        }else {
            if (puntero.pos.getX()< pos.getX()){
                gc.drawImage(sprite4,pos.getX()-18, pos.getY()-28,36,56);
            }else {
                gc.drawImage(sprite1,pos.getX()-18, pos.getY()-28,36,56);
            }
        }
    }

}
