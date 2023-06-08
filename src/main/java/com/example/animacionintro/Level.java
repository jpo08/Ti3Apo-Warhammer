package com.example.animacionintro;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;

public class Level {
    private int id;
    private Color color;

    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> enemyBullets;
    private Portal portal;
    private Image fondo;
    private Wall[][] wallsMap;
    private ArrayList<Wall> walls;

    private ArrayList<Pair<Double,Double>> walletsp;


    public Level(int id){
        this.id =id;
        enemies=new ArrayList<>();
        enemyBullets=new ArrayList<>();
        bullets=new ArrayList<>();
        walls = new ArrayList<>();
        walletsp= new ArrayList<>();
        if (id==0){
            String uriBack ="file:"+ HelloApplication.class.getResource("fondoLv1.png").getPath();
            fondo=new Image(uriBack);

            Integer obstaclesInMap[][] = new Integer[][]{
                    {null,null,null,null,null,null,null,null,null,null,null,null},
                    {null,null,null,null,null,null,null,null,null,1,null,null},
                    {null,null,null,null,1,1,null,null,null,1,null,null},
                    {null,null,null,null,1,null,null,null,null,1,null,null},
                    {null,null,null,null,null,null,null,null,null,null,null,null},
                    {null,null,1,null,null,null,null,1,null,null,null,null},
                    {null,null,1,null,null,null,1,1,null,null,null,null},
                    {null,null,1,null,null,null,null,null,null,null,null,null},
                    {null,null,null,null,null,null,null,null,null,null,null,null}
            };
            wallsMap = new Wall[9][12];
            for (int i = 0; i < wallsMap.length ; i++) {
                for (int j = 0; j < wallsMap[0].length ; j++) {
                    if(obstaclesInMap[i][j] == null){
                        wallsMap[i][j] = null;
                    } else if(obstaclesInMap[i][j] == 1) {

                        wallsMap[i][j] = new Wall(i,j);
                        walls.add(wallsMap[i][j]);
                        walletsp.add(new Pair<>(wallsMap[i][j].pos.getX(), wallsMap[i][j].pos.getY()));
                    }
                }
            }

        }else if (id==1){
            String uriBack ="file:"+ HelloApplication.class.getResource("fondoLv2.png").getPath();
            fondo=new Image(uriBack);

            Integer obstaclesInMap[][] = new Integer[][]{
                    {null,null,null,null,null,null,null,null,null,null,null,null},
                    {null,null,null,null,null,null,null,null,null,1,null,null},
                    {null,null,null,null,1,1,null,null,null,1,null,null},
                    {null,null,null,null,1,null,null,null,null,1,null,null},
                    {null,null,null,null,null,null,null,null,null,null,null,null},
                    {null,null,1,null,null,null,null,1,null,null,null,null},
                    {null,null,1,null,null,null,1,1,null,null,null,null},
                    {null,null,1,null,null,null,null,null,null,null,null,null},
                    {null,null,null,null,null,null,null,null,null,null,null,null}
            };
            wallsMap = new Wall[9][12];
            for (int i = 0; i < wallsMap.length ; i++) {
                for (int j = 0; j < wallsMap[0].length ; j++) {
                    if(obstaclesInMap[i][j] == null){
                        wallsMap[i][j] = null;
                    } else if(obstaclesInMap[i][j] == 1) {

                        wallsMap[i][j] = new Wall(i,j);
                        walls.add(wallsMap[i][j]);
                        walletsp.add(new Pair<>(wallsMap[i][j].pos.getX(), wallsMap[i][j].pos.getY()));
                    }
                }
            }

        }else{
            String uriBack ="file:"+ HelloApplication.class.getResource("fondoLv3.png").getPath();
            fondo=new Image(uriBack);

            Integer obstaclesInMap[][] = new Integer[][]{
                    {null,null,null,null,null,null,null,null,null,null,null,null},
                    {null,null,null,null,null,null,null,null,null,1,null,null},
                    {null,null,null,null,1,1,null,null,null,1,null,null},
                    {null,null,null,null,1,null,null,null,null,1,null,null},
                    {null,null,null,null,null,null,null,null,null,null,null,null},
                    {null,null,1,null,null,null,null,1,null,null,null,null},
                    {null,null,1,null,null,null,1,1,null,null,null,null},
                    {null,null,1,null,null,null,null,null,null,null,null,null},
                    {null,null,null,null,null,null,null,null,null,null,null,null}
            };
            wallsMap = new Wall[9][12];
            for (int i = 0; i < wallsMap.length ; i++) {
                for (int j = 0; j < wallsMap[0].length ; j++) {
                    if(obstaclesInMap[i][j] == null){
                        wallsMap[i][j] = null;
                    } else if(obstaclesInMap[i][j] == 1) {

                        wallsMap[i][j] = new Wall(i,j);
                        walls.add(wallsMap[i][j]);
                        walletsp.add(new Pair<>(wallsMap[i][j].pos.getX(), wallsMap[i][j].pos.getY()));
                    }
                }
            }
        }



    }

    public Wall[][] getWallsMap() {
        return wallsMap;
    }

    public void setWallsMap(Wall[][] wallsMap) {
        this.wallsMap = wallsMap;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public ArrayList<Pair<Double, Double>> getWalletsp() {
        return walletsp;
    }

    public void setWalletsp(ArrayList<Pair<Double, Double>> walletsp) {
        this.walletsp = walletsp;
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
