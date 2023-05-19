package com.example.animacionintro;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    private Image background;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(this::onKeyPressed);
        String uriBack ="file:"+ HelloApplication.class.getResource("fondoLv1.png").getPath();
        background=new Image(uriBack);
        canvas.setOnKeyReleased(this::onKeyReleased);
        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseMoved(this::onMouseMoved);
        avatar = new Avatar();
        ammoBox.add(new AmmoBox(canvas));
        portal = new Portal(canvas);
        enemies.add(new Enemy(new Vector(400,100)));
        enemies.add(new Enemy(new Vector(400,300)));
        draw();
    }

    private void onMouseMoved(MouseEvent e){
        double xM= e.getX();
        double yM = e.getY();
        puntero=new Puntero(new Vector(xM,yM));
        avatar.setPuntero(puntero);
        gun1=new Weapon(avatar.pos,1);
        gun1.setPuntero(puntero);





    }

    private void onMousePressed(MouseEvent e) {
        if (avatar.isArmed()==false){

        }else if (avatar.getAmmo()>0){System.out.println("X: "+ e.getX() + "Y: "+e.getY());

            if (avatar.getSecondWeapon()==2){
                double diffx = e.getX()-avatar.pos.getX();
                double diffy = e.getY()-avatar.pos.getY();
                Vector diff = new Vector(diffx,diffy);
                diff.normalize();
                diff.setMag(4);
                Vector diff1 = new Vector(diffx,diffy-15);
                diff1.normalize();
                diff1.setMag(4);
                Vector diff2 = new Vector(diffx,diffy+15);
                diff2.normalize();
                diff2.setMag(4);

                bullets.add(
                        new Bullet(new Vector(avatar.pos.getX(),avatar.pos.getY()),diff )
                );
                bullets.add(
                        new Bullet(new Vector(avatar.pos.getX(),avatar.pos.getY()),diff1 )
                );
                bullets.add(
                        new Bullet(new Vector(avatar.pos.getX(),avatar.pos.getY()),diff2 )
                );
                avatar.setAmmo(avatar.getAmmo()-5);
            }else if (avatar.getSecondWeapon()==1){
                double diffx = e.getX()-avatar.pos.getX();
                double diffy = e.getY()-avatar.pos.getY();
                Vector diff = new Vector(diffx,diffy);
                diff.normalize();
                diff.setMag(4);

                bullets.add(
                        new Bullet(new Vector(avatar.pos.getX(),avatar.pos.getY()),diff )
                );
                avatar.setAmmo(avatar.getAmmo()-1);

            }
        }

    }

    private void ammoBoxColission(){

        avatar.setArmed(true);


    }


    private boolean isAlive = true;

    private boolean Apressed = false;
    private boolean Wpressed = false;
    private boolean Spressed = false;
    private boolean Dpressed = false;
    private boolean Epressed=false;
    private boolean Qpressed=false;


    private Avatar avatar;
    private ArrayList<Bullet> bullets=new ArrayList<>();

    private ArrayList<Enemy> enemies = new ArrayList<>();

    private Puntero puntero;
    private Weapon gun1;

    private ArrayList<AmmoBox> ammoBox=new ArrayList<>();
    private  Portal portal;



    public void onKeyReleased(KeyEvent event){
        switch (event.getCode()){
            case W: Wpressed = false; break;
            case A: Apressed = false; break;
            case S: Spressed = false; break;
            case D: Dpressed = false; break;
            case E: Epressed = false; break;
            case Q: Qpressed = false; break;
        }
    }
    public void onKeyPressed(KeyEvent event){
        System.out.println(event.getCode());
        switch (event.getCode()){
            case W: Wpressed = true; break;
            case A: Apressed = true; break;
            case S: Spressed = true; break;
            case D: Dpressed = true; break;
            case E: Epressed = true; break;
            case Q: Qpressed = true; break;
        }
    }


    public void draw(){
        //
        Thread ae = new Thread(()->{
            while(isAlive){
                //Dibujar en el lienzo
                Platform.runLater(()->{//Runnable
                    gc.save();
                    gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
                    gc.restore();
                    //Lo que hagamos aqui, corre en el main thread
                    detectColission();
                    for (int i=0;i<ammoBox.size();i++){
                        ammoBox.get(i).draw(gc,true);
                    }

                   if (avatar.isArmed()){
                       gun1.draw(gc,true);
                   }
                    puntero.draw(gc,true);
                    if (Wpressed==true||Apressed==true||Dpressed==true||Spressed==true){
                        avatar.draw(gc,true);
                    }else {
                        avatar.draw(gc,false);
                    }
                    for (int i = 0; i<bullets.size();i++){
                        bullets.get(i).draw(gc,true);
                        if (isOutside(bullets.get(i).pos.getX(),bullets.get(i).pos.getY())){
                            bullets.remove(i);
                        }
                    }
                    for (int i= 0; i<enemies.size();i++){
                        enemies.get(i).draw(gc,true);
                    }

                });

                //colisiones

                detectColission();
                if (avatar.isArmed()==false){
                    double distanceBox=Math.sqrt(
                            Math.pow(avatar.pos.getX()-ammoBox.get(0).pos.getX(),2)+ Math.pow(avatar.pos.getY()-ammoBox.get(0).pos.getY(),2)
                    );
                    if (distanceBox<25){
                        ammoBoxColission();
                        ammoBox.remove(0);

                    }
                }

                //Calculos geometricos




                if(Wpressed){
                    avatar.pos.setY(avatar.pos.getY()-3);
                }
                if (Apressed) {
                    avatar.pos.setX(avatar.pos.getX()-3);
                }
                if (Spressed) {
                    avatar.pos.setY(avatar.pos.getY()+3);
                }
                if (Dpressed) {
                    avatar.pos.setX(avatar.pos.getX()+3);
                }
                if (Epressed){
                    avatar.setAmmo(5);
                }
                if (Qpressed){
                    if (avatar.getSecondWeapon()==1){
                        avatar.setSecondWeapon(2);
                    }else{
                        avatar.setSecondWeapon(1);
                    }
                    avatar.setAmmo(5);
                }


                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {e.printStackTrace();}
            }
        });
        ae.start();
    }

    public boolean isOutside(double x, double y){
        return x<-15 || y<-15 || x>canvas.getWidth() || y>canvas.getHeight();
    }


    public void detectColission(){
        for (int i=0; i<bullets.size();i++){
            Bullet bn = bullets.get(i);
            for (int j = 0; j<enemies.size();j++){
                Enemy en = enemies.get(j);

                double distance=Math.sqrt(
                        Math.pow(en.pos.getX()-bn.pos.getX(),2)+ Math.pow(en.pos.getY()-bn.pos.getY(),2)
                );

                if (distance<25){
                    bullets.remove(i);
                    en.setnLifes(en.getnLifes()-1);
                    if (en.getnLifes() == 0){
                        enemies.remove(j);
                    }

                }
            }
        }

    }


}






