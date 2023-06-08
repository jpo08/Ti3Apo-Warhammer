package com.example.animacionintro;

import javafx.scene.canvas.Canvas;
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


    public Level(int id, Canvas canvas){
        this.id =id;
        enemies=new ArrayList<>();
        enemyBullets=new ArrayList<>();
        bullets=new ArrayList<>();
        walls = new ArrayList<>();
        walletsp= new ArrayList<>();
        if (id==0){
            String uriBack ="file:"+ HelloApplication.class.getResource("fondoLv1v2.png").getPath();
            fondo=new Image(uriBack);

            Integer obstaclesInMap[][] = new Integer[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };
            wallsMap = new Wall[20][12];
            for (int i = 0; i < wallsMap.length ; i++) {
                for (int j = 0; j < wallsMap[0].length ; j++) {
                    if(obstaclesInMap[i][j] == 0){
                        wallsMap[i][j] = null;
                    } else if(obstaclesInMap[i][j] == 1) {

                        wallsMap[i][j] = new Wall(i,j,1,canvas);
                        walls.add(wallsMap[i][j]);

                    }
                }
            }

        }else if (id==1){
            String uriBack ="file:"+ HelloApplication.class.getResource("fondoLv2v2.png").getPath();
            fondo=new Image(uriBack);

            Integer obstaclesInMap[][] = new Integer[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1},
                    {1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };
            wallsMap = new Wall[20][12];
            for (int i = 0; i < wallsMap.length ; i++) {
                for (int j = 0; j < wallsMap[0].length ; j++) {
                    if(obstaclesInMap[i][j] == 0){
                        wallsMap[i][j] = null;
                    } else if(obstaclesInMap[i][j] == 1) {

                        wallsMap[i][j] = new Wall(i,j,2,canvas);
                        walls.add(wallsMap[i][j]);

                    }
                }
            }

        }else{
            String uriBack ="file:"+ HelloApplication.class.getResource("fondoLv3v2.png").getPath();
            fondo=new Image(uriBack);

            Integer obstaclesInMap[][] = new Integer[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };
            wallsMap = new Wall[20][12];
            for (int i = 0; i < wallsMap.length ; i++) {
                for (int j = 0; j < wallsMap[0].length ; j++) {
                    if(obstaclesInMap[i][j] == 0){
                        wallsMap[i][j] = null;
                    } else if(obstaclesInMap[i][j] == 1) {

                        wallsMap[i][j] = new Wall(i,j,3,canvas);
                        walls.add(wallsMap[i][j]);

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
