package com.example.animacionintro;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label bulletsNoLB;
    @FXML
    private ImageView gunIconIV;
    @FXML
    private Label livesNoLB;
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Level> levels;
    private int currentLevel=0;
    Random random = new Random();
    private int numberEnemysLV1= random.nextInt(4)+3;
    private int numberEnemysLV2= random.nextInt(4)+3;
    private int numberEnemysLV3= random.nextInt(4)+3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(this::onKeyPressed);
        canvas.setOnKeyReleased(this::onKeyReleased);
        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseMoved(this::onMouseMoved);
        avatar = new Avatar();
        levels=new ArrayList<>();
        livesNoLB.setText(avatar.getVida()+"");

        //generar mapa level 1
        Level l1 = new Level(0);
        ammoBox.add(new AmmoBox(canvas));
        portal = new Portal(canvas);
        for (int i=0;i<numberEnemysLV1;i++){
            int posicion= random.nextInt(3);
            if (posicion==1){
                Enemy e = new Enemy(new Vector(random.nextInt(300)+300,random.nextInt(200)),avatar);
                new Thread(e).start();
                l1.getEnemies().add(e);
            }else if (posicion==2){
                Enemy e = new Enemy(new Vector(random.nextInt(300),random.nextInt(200)+200),avatar);
                new Thread(e).start();
                l1.getEnemies().add(e);
            }else {
                Enemy e = new Enemy(new Vector(random.nextInt(300)+300,random.nextInt(200)+200),avatar);
                new Thread(e).start();
                l1.getEnemies().add(e);
            }

        }
        levels.add(l1);

        //generar segundo mapa
        Level l2 = new Level(1);
        l2.setColor(Color.GRAY);

        for (int i=0;i<numberEnemysLV2;i++){
            int posicion= random.nextInt(3);
            if (posicion==1){
                Enemy e = new Enemy(new Vector(random.nextInt(300)+300,random.nextInt(200)),avatar);
                new Thread(e).start();
                l2.getEnemies().add(e);
            }else if (posicion==2){
                Enemy e = new Enemy(new Vector(random.nextInt(300),random.nextInt(200)+200),avatar);
                new Thread(e).start();
                l2.getEnemies().add(e);
            }else {
                Enemy e = new Enemy(new Vector(random.nextInt(300)+300,random.nextInt(200)+200),avatar);
                new Thread(e).start();
                l2.getEnemies().add(e);
            }

        }
        levels.add(l2);

        //generar tercer mapa
        Level l3 = new Level(2);
        l3.setColor(Color.GRAY);

        for (int i=0;i<numberEnemysLV3;i++){
            int posicion= random.nextInt(3);
            if (posicion==1){
                Enemy e = new Enemy(new Vector(random.nextInt(300)+300,random.nextInt(200)),avatar);
                new Thread(e).start();
                l3.getEnemies().add(e);
            }else if (posicion==2){
                Enemy e = new Enemy(new Vector(random.nextInt(300),random.nextInt(200)+200),avatar);
                new Thread(e).start();
                l3.getEnemies().add(e);
            }else {
                Enemy e = new Enemy(new Vector(random.nextInt(300)+300,random.nextInt(200)+200),avatar);
                new Thread(e).start();
                l3.getEnemies().add(e);
            }

        }
        levels.add(l3);

        draw();
        enemyshoot(levels.get(currentLevel));
    }
    private String getBulletsText(){
        gunIconIV.setImage(gun1.getSprite());
        return avatar.getAmmo()+"";
    }

    private void onMouseMoved(MouseEvent e){
        double xM= e.getX();
        double yM = e.getY();
        puntero=new Puntero(new Vector(xM,yM));
        avatar.setPuntero(puntero);
        gun1=new Weapon(avatar.pos,avatar.getSecondWeapon());
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

                levels.get(currentLevel).getBullets().add(
                        new Bullet(new Vector(avatar.pos.getX(),avatar.pos.getY()),diff )
                );
                levels.get(currentLevel).getBullets().add(
                        new Bullet(new Vector(avatar.pos.getX(),avatar.pos.getY()),diff1 )
                );
                levels.get(currentLevel).getBullets().add(
                        new Bullet(new Vector(avatar.pos.getX(),avatar.pos.getY()),diff2 )
                );
                avatar.setAmmo(avatar.getAmmo()-5);

            }else if (avatar.getSecondWeapon()==1){
                double diffx = e.getX()-avatar.pos.getX();
                double diffy = e.getY()-avatar.pos.getY();
                Vector diff = new Vector(diffx,diffy);
                diff.normalize();
                diff.setMag(4);

                levels.get(currentLevel).getBullets().add(
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
                Level level=  levels.get(currentLevel);
                //Dibujar en el lienzo
                Platform.runLater(()->{//Runnable
                    //Lo que hagamos aqui, corre en el main thread
                    gc.save();
                    gc.drawImage(level.getFondo(), 0, 0, canvas.getWidth(), canvas.getHeight());
                    gc.restore();
                    detectColission2(level);
                    livesNoLB.setText(avatar.getVida()+"");

                    for (int i=0;i<ammoBox.size();i++){
                        ammoBox.get(i).draw(gc,true);
                    }
                    if (level.getEnemies().size()<1){
                        portal.draw(gc,true);
                        detectPortalColision(level);

                    }


                   if (avatar.isArmed()){
                       bulletsNoLB.setText(getBulletsText());
                       gun1.draw(gc,true);
                   }
                    puntero.draw(gc,true);


                    if (Wpressed==true||Apressed==true||Dpressed==true||Spressed==true){
                        avatar.draw(gc,true);
                    }else {
                        avatar.draw(gc,false);
                    }
                    for (int i = 0; i<level.getBullets().size();i++){
                        level.getBullets().get(i).draw(gc,true);
                        if (isOutside(level.getBullets().get(i).pos.getX(),level.getBullets().get(i).pos.getY())){
                            level.getBullets().remove(i);
                        }
                    }
                    for (int i= 0; i<level.getEnemies().size();i++){
                        level.getEnemies().get(i).draw(gc,true);
                    }
                    for (int i = 0; i<level.getEnemyBullets().size();i++){
                        level.getEnemyBullets().get(i).draw(gc,true);
                        if (isOutside(level.getEnemyBullets().get(i).pos.getX(),level.getEnemyBullets().get(i).pos.getY())){
                            level.getEnemyBullets().remove(i);
                        }
                    }

                });

                //colisiones

                detectColission(level);

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

                //Paredes
                if (avatar.pos.getX()>canvas.getWidth()-20){
                    avatar.pos.setX(canvas.getWidth()-20);
                }
                if (avatar.pos.getX()<25){
                    avatar.pos.setX(25);
                }
                if (avatar.pos.getY()>canvas.getHeight()-25){
                    avatar.pos.setY(canvas.getHeight()-25);
                }
                if (avatar.pos.getY()<28){
                    avatar.pos.setY(28);
                }


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

    public void enemyshoot(Level level){
        Thread enemy = new Thread(()->{
            while (isAlive){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {e.printStackTrace();}
            for (int i=0;i<level.getEnemies().size();i++){

                level.getEnemyBullets().add(level.getEnemies().get(i).shoot());
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {e.printStackTrace();}
            }
        });
        enemy.start();

    }

    public boolean isOutside(double x, double y){
        return x<-15 || y<-15 || x>canvas.getWidth() || y>canvas.getHeight();
    }

    public void victoryMessage(){
        HelloApplication.openWindow("victory-view.fxml");
        Stage stage = (Stage) canvas.getScene().getWindow();
        stage.close();
    }

    public void detectPortalColision(Level level){
        if (level.getEnemies().size()<1){
            double distancePortal=Math.sqrt(
                    Math.pow(avatar.pos.getX()-portal.pos.getX(),2)+ Math.pow(avatar.pos.getY()-portal.pos.getY(),2)
            );
            if (distancePortal<25){
                if (currentLevel<2){
                    currentLevel=currentLevel+1;
                    avatar.pos.setX(100);
                    avatar.pos.setY(100);
                    enemyshoot(levels.get(currentLevel));
                }else {
                    //completo el juego
                    isAlive=false;
                   victoryMessage();
                }


            }
        }

    }

    public void detectColission(Level level){
        for (int i=0; i<level.getBullets().size();i++){
            Bullet bn = level.getBullets().get(i);
            for (int j = 0; j<level.getEnemies().size();j++){
                Enemy en = level.getEnemies().get(j);

                double distance=Math.sqrt(
                        Math.pow(en.pos.getX()-bn.pos.getX(),2)+ Math.pow(en.pos.getY()-bn.pos.getY(),2)
                );

                if (distance<25){
                    level.getBullets().remove(i);
                    en.setnLifes(en.getnLifes()-1);
                    if (en.getnLifes() == 0){
                        level.getEnemies().remove(j);

                    }

                }
            }
        }

    }

    public void lossMessage(){
        HelloApplication.openWindow("defeated-view.fxml");
        Stage stage = (Stage) canvas.getScene().getWindow();
        stage.close();
    }
    public void detectColission2(Level level){
        for (int i=0; i<level.getEnemyBullets().size();i++){
            Bullet bn = level.getEnemyBullets().get(i);

                double distance=Math.sqrt(
                        Math.pow(avatar.pos.getX()-bn.pos.getX(),2)+ Math.pow(avatar.pos.getY()-bn.pos.getY(),2)
                );

                if (distance<25){
                    level.getEnemyBullets().remove(i);
                    avatar.setVida(avatar.getVida()-1);
                    if (avatar.getVida() == 0){
                        lossMessage();

                    }

                }
        }

    }


}






