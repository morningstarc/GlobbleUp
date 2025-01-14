package Controller;

import Model.*;
import View.MyWindow;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static MyWindow win;
    public static GameData gameData;
    public static boolean running;
    public static int FPS = 20;
    public static PlayerInputEventQueue playerInput;
    public static int INDEX_PLAYER = 0;
    public static boolean gameOver = false;


    public static void main(String[] args){

        win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        gameData = new GameData();
        playerInput = new PlayerInputEventQueue();
        startScreen();
        initGame();
        gameLoop();


    }

    static void startScreen(){
        //iniital message
        Font font = new Font("Courier New", Font.PLAIN, 40);
        gameData.friendObject.add(new Text("Press Start to Begin", 100, 200, Color.YELLOW, font));
        while(!running){
            Main.win.canvas.render();
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //finish
    }

    static void initGame() {
        gameData.clear();
        int x = Main.win.getWidth() / 2;
        int y = Main.win.getHeight() - 150;
        gameData.fixedObject.add(new PlayerCharacter(x, y));
        gameData.enemyObject.add(new GlobEnemy(150, 300, 50));
        gameData.enemyObject.add(new GlobEnemy( 200, 120, 25));
        gameData.enemyObject.add(new GlobEnemy(30));

    }

    static void gameLoop(){
        running = true;
        while(running){
            if(gameOver) {
                Font font = new Font("Courier New", Font.PLAIN, 40);
                gameData.friendObject.add(new Text("Game Over", 200, 200, Color.RED, font));
                running = false;
            }
                long startTime = System.currentTimeMillis();

                playerInput.processInputEvents();
                processCollisions();
                gameData.update();
                win.canvas.render();

                long endTime = System.currentTimeMillis();
                long timeSpent = endTime - startTime;
                long sleepTime = (long) (1000.0 / FPS - timeSpent);
                try {
                    if (sleepTime > 0) Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    static void processCollisions() {

        //wrap this in a check to make sure that if the player does not exist, we do not ask for them
        if(Main.gameData.fixedObject.size() > Main.INDEX_PLAYER) {
            var player = (PlayerCharacter) Main.gameData.fixedObject.get(Main.INDEX_PLAYER);
            for (var enemy : Main.gameData.enemyObject) {
                if (player.collideWith(enemy)) {
                    if (player.getSize() > enemy.getSize()) {
                        ++enemy.hitCount;
                    } else if (player.getSize() < enemy.getSize()) {
                        ++player.hitCount;
                    } else if (player.getSize() == enemy.getSize()) {
                        player.hitCount = 0;
                        enemy.hitCount = 0;
                    }
                }
            }
        }





    }

}
