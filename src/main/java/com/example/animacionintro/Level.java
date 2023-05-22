package com.example.animacionintro;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Level {
    private int id;
    private Color color;

    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> enemyBullets;
    private Portal portal;
    private Image fondo;

    public Level(int id){
        this.id =id;
        enemies=new ArrayList<>();
        enemyBullets=new ArrayList<>();
        bullets=new ArrayList<>();
        if (id==0){
            String uriBack ="file:"+ HelloApplication.class.getResource("fondoLv1.png").getPath();
            fondo=new Image(uriBack);

        }else if (id==1){
            String uriBack ="file:"+ HelloApplication.class.getResource("fondoLv2.png").getPath();
            fondo=new Image(uriBack);

        }else{
            String uriBack ="file:"+ HelloApplication.class.getResource("fondoLv3.png").getPath();
            fondo=new Image(uriBack);
        }

    }

    public Image getFondo() {
        return fondo;
    }

    public void setFondo(Image fondo) {
        this.fondo = fondo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public void setEnemyBullets(ArrayList<Bullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }
}
