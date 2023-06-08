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
import javafx.util.Pair;

import java.io.File;
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
    private Label levelNameL;
    @FXML
    private Canvas canvas;

    private MusicPlayer musicPlayer=new MusicPlayer();
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
        Level l1 = new Level(0,canvas);
        ammoBox.add(new AmmoBox(canvas));
        portal = new Portal(canvas);
        for (int i=0;i<numberEnemysLV1;i++){
            int posicion= random.nextInt(3);
            if (posicion==1){
                Enemy e = new Enemy(new Vector(random.nextInt(300)+600,random.nextInt(400)),avatar,2);
                new Thread(e).start();
                l1.getEnemies().add(e);
            }else if (posicion==2){
                Enemy e = new Enemy(new Vector(random.nextInt(600),random.nextInt(200)+400),avatar,2);
                new Thread(e).start();
                l1.getEnemies().add(e);
            }else {
                Enemy e = new Enemy(new Vector(random.nextInt(300)+600,random.nextInt(200)+400),avatar,2);
                new Thread(e).start();
                l1.getEnemies().add(e);
            }

        }
        levels.add(l1);

        //generar segundo mapa
        Level l2 = new Level(1,canvas);
        l2.setColor(Color.GRAY);

        levels.add(l2);

        //generar tercer mapa
        Level l3 = new Level(2,canvas);
        l3.setColor(Color.GRAY);
        levels.add(l3);
        musicPlayer.playSound(new File("src/main/resources/com/example/animacionintro/music/battleMusic.wav"));

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

                    detectedCollisionBW(level);
                    for (Wall w : level.getWalls()) {
                        w.draw();
                    }

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
                    if (avatar.getVida()==0){
                        avatar.getDeadAnimation().draw(gc,true);
                    }


                });

                //colisiones

                detectColission(level);


                if (avatar.isArmed()==false){
                    double distanceBox=Math.sqrt(
                            Math.pow(avatar.pos.getX()-ammoBox.get(0).pos.getX(),2)+ Math.pow(avatar.pos.getY()-ammoBox.get(0).pos.getY(),2)
                    );
                    if (distanceBox<25){
                        musicPlayer.playSound2(new File("src/main/resources/com/example/animacionintro/music/ammoSound.wav"));
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

                for (int i=0;i<level.getEnemies().size();i++){
                    if (level.getEnemies().get(i).pos.getX()>canvas.getWidth()-25){
                        avatar.pos.setX(canvas.getWidth()-25);
                    }
                    if (level.getEnemies().get(i).pos.getX()<25){
                        avatar.pos.setX(25);
                    }
                    if (level.getEnemies().get(i).pos.getY()>canvas.getHeight()-25){
                        avatar.pos.setY(canvas.getHeight()-25);
                    }
                    if (level.getEnemies().get(i).pos.getY()<25){
                        avatar.pos.setY(25);
                    }
                }

                //muros
                avatarWallCollision(level);
                EnemyWallCollisions();


                if(Wpressed){
                    avatar.pos.setY(avatar.pos.getY()-1);
                }
                if (Apressed) {
                    avatar.pos.setX(avatar.pos.getX()-1);
                }
                if (Spressed) {
                    avatar.pos.setY(avatar.pos.getY()+1);
                }
                if (Dpressed) {
                    avatar.pos.setX(avatar.pos.getX()+1);
                }
                if (Epressed){
                    musicPlayer.stopSound2();
                    musicPlayer.playSound2(new File("src/main/resources/com/example/animacionintro/music/ammoSound.wav"));
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
                    Thread.sleep(10);
                } catch (InterruptedException e) {e.printStackTrace();}
            }
        });
        ae.start();
    }


    public void avatarWallCollision(Level level){

        for (Wall wall : level.getWalls()) {
            if (avatar.detectCollision(wall)) {
                double overlapX = avatar.calculateOverlapX(wall);
                double overlapY = avatar.calculateOverlapY(wall);

                if (overlapX < overlapY) {
                    if (Apressed && !Dpressed && avatar.pos.getX() > wall.getX()) {
                        avatar.pos.setX(wall.getX() + wall.getHitbox().getWidth() + 30);
                    } else if (Dpressed && !Apressed && avatar.pos.getX() < wall.getX()) {
                        avatar.pos.setX(wall.getX() - 30);
                    }
                } else {
                    if (Wpressed && !Spressed && avatar.pos.getY() > wall.getY()) {
                        avatar.pos.setY(wall.getY() + wall.getHitbox().getHeight() + 30);
                    } else if (Spressed && !Wpressed && avatar.pos.getY() < wall.getY()) {
                        avatar.pos.setY(wall.getY() - 30);
                    }
                }
            }
        }
    }


    public void EnemyWallCollisions() {
        Level level = levels.get(currentLevel);


        for (Wall wall : level.getWalls()) {
            for (Enemy enemy : level.getEnemies()) {
                if (enemy.isColliding(wall)) {
                    double overlapX = enemy.getOverlapX(wall);

                    double overlapY = enemy.getOverlapY(wall);

                    if (overlapX < overlapY) {

                        if (enemy.pos.getX() > wall.getX()) {

                            enemy.pos.setX(wall.getX() + wall.getHitbox().getWidth() + 25);
                        } else if (enemy.pos.getX() < wall.getX()) {

                            enemy.pos.setX(wall.getX() - 25);
                        }
                    } else {

                        if (enemy.pos.getY() > wall.getY()) {

                            enemy.pos.setY(wall.getY() + wall.getHitbox().getHeight() + 25);
                        } else if (enemy.pos.getY() < wall.getY()) {

                            enemy.pos.setY(wall.getY() - 25);
                        }
                    }
                }
            }
        }
    }




    private void detectedCollisionBW(Level level){
        for (Wall w:level.getWalls()) {
            for (Bullet b: level.getBullets()){
                if (b.getHitbox().intersects( w.getX(), w.getY(),50, 50)){
                    if (w.getShield()<1){
                        musicPlayer.stopSound3();
                        musicPlayer.playSound3(new File("src/main/resources/com/example/animacionintro/music/explosionSound.wav"));
                        level.getWalls().remove(w);
                    }else {
                        level.getBullets().remove(b);
                        w.setShield(w.getShield()-1);
                    }


                }
            }
            for (Bullet be: level.getEnemyBullets()){
                if (be.getHitbox().intersects( w.getX(), w.getY(),50, 50)){
                    if (w.getShield()<1){
                        musicPlayer.stopSound3();
                        musicPlayer.playSound3(new File("src/main/resources/com/example/animacionintro/music/explosionSound.wav"));
                        level.getWalls().remove(w);
                    }else {
                        level.getEnemyBullets().remove(be);
                        w.setShield(w.getShield()-1);
                    }


                }
            }
        }
    }

    public void enemyshoot(Level level){
        Thread enemy = new Thread(()->{
            while (isAlive){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {e.printStackTrace();}
            for (int i=0;i<level.getEnemies().size();i++){
                if (level.getEnemies().get(i).getType()==2 || level.getEnemies().get(i).getType()==3){
                    level.getEnemyBullets().add(level.getEnemies().get(i).shoot());
                }else {
                    for (int j=1;j<8;j++){
                        double angle = j * 45;
                        Vector diff = new Vector(level.getEnemies().get(i).pos.getX(),level.getEnemies().get(i).pos.getY());

                        diff.setX(1*Math.cos(angle));
                        diff.setY(1*Math.sin(angle));



                        level.getEnemyBullets().add(
                                new Bullet(new Vector(
                                        level.getEnemies().get(i).pos.getX(),level.getEnemies().get(i).pos.getY()),diff
                                )
                        );
                    }
                }

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
        musicPlayer.stopSound();
        HelloApplication.openWindow("victory-view.fxml");
        isAlive=false;
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
                    if (currentLevel==1){ nivel2(levels.get(currentLevel)); levelNameL.setText("Lv2. Klendathu");}
                    if (currentLevel==2){nivel3(levels.get(currentLevel)); levelNameL.setText("Lv3. Solaris");}

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
        musicPlayer.stopSound();
        HelloApplication.openWindow("defeated-view.fxml");
        isAlive=false;
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
                        musicPlayer.playSound2(new File("src/main/resources/com/example/animacionintro/music/deathSound.wav"));

                        isAlive=false;
                        lossMessage();

                    }

                }
        }

    }

    public void nivel2(Level l2){
        for (int i=0;i<numberEnemysLV2;i++){
            int posicion= random.nextInt(3);
            if (posicion==1){
                Enemy e = new Enemy(new Vector(random.nextInt(300)+600,random.nextInt(400)),avatar,3);
                new Thread(e).start();
                l2.getEnemies().add(e);
            }else if (posicion==2){
                Enemy e = new Enemy(new Vector(random.nextInt(600),random.nextInt(200)+400),avatar,3);
                new Thread(e).start();
                l2.getEnemies().add(e);
            }else {
                Enemy e = new Enemy(new Vector(random.nextInt(300)+600,random.nextInt(200)+400),avatar,3);
                new Thread(e).start();
                l2.getEnemies().add(e);
            }

        }
    }

    public void nivel3(Level l3){
        for (int i=0;i<numberEnemysLV3;i++){
            int posicion= random.nextInt(3);
            if (posicion==1){
                Enemy e = new Enemy(new Vector(random.nextInt(300)+600,random.nextInt(400)),avatar,2);
                new Thread(e).start();
                l3.getEnemies().add(e);
            }else if (posicion==2){
                Enemy e = new Enemy(new Vector(random.nextInt(600),random.nextInt(200)+400),avatar,2);
                new Thread(e).start();
                l3.getEnemies().add(e);
            }else {
                Enemy e = new Enemy(new Vector(random.nextInt(300)+600,random.nextInt(200)+400),avatar,2);
                new Thread(e).start();
                l3.getEnemies().add(e);
            }


        }
        Enemy a = new Enemy(new Vector(canvas.getWidth()/2,canvas.getHeight()/2),avatar,1);
        new Thread(a).start();
        l3.getEnemies().add(a);
    }


}






