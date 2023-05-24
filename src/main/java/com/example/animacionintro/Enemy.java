package com.example.animacionintro;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Enemy extends Drawing implements Runnable{

    private Image sprite;

    private int nLifes;

    private boolean isDead;
    private Avatar player;
    private int type;

    public Enemy(Vector pos, Avatar player,int type){
        if (type==1){
            String uri = "file:"+ HelloApplication.class.getResource("magine.gif").getPath();
            sprite=new Image(uri);
            nLifes=10;
        }
        if (type==2){
            String uri = "file:"+ HelloApplication.class.getResource("alien.gif").getPath();
            sprite=new Image(uri);
            nLifes=3;
        }
        if (type==3){
            String uri = "file:"+ HelloApplication.class.getResource("alien 2.gif").getPath();
            sprite=new Image(uri);
            nLifes=3;
        }
        this.type=type;
        this.pos=pos;
        this.player = player;
    }
    @Override
    public void draw(GraphicsContext gc,boolean m) {
        if (type==2 ){
            gc.drawImage(sprite,pos.getX()-25, pos.getY()-25, 50,50);
        }
        if (type==3){
            gc.drawImage(sprite,pos.getX()-35, pos.getY()-25, 70,50);
        }
        if (type==1){
            gc.drawImage(sprite,pos.getX()-75, pos.getY()-45, 150,90);
        }


    }

    @Override
    public void run(){
        //Tercer plano
        while (!isDead){
            double deltaX = Math.random()*6-3;
            double deltaY = Math.random()*6-3;

            pos.setY(pos.getY()+deltaY);
            pos.setX(pos.getX()+deltaX);
            move();


            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    public int getType() {
        return type;
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

    public void move(){
        if(player.pos.getX()-pos.getX()>0) pos.setX(pos.getX()+1);
        else if(player.pos.getX()-pos.getX()<0) pos.setX(pos.getX()-1);

        if(player.pos.getY()-pos.getY()>0) pos.setY(pos.getY()+1);
        else if(player.pos.getY()-pos.getY()<0) pos.setY(pos.getY()-1);
    }

    public Bullet shoot(){
        double diffx = player.pos.getX()-pos.getX();;
        double diffy = player.pos.getY()-pos.getY();
        Vector diff = new Vector(diffx,diffy);
        diff.normalize();
        diff.setMag(4);

        Bullet bullet = new Bullet( new Vector(pos.getX(), pos.getY() ),diff);

        return bullet;

    }

}
