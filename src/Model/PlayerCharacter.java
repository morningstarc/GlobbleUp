package Model;

import Controller.Main;
import View.MyCanvas;
import View.MyWindow;

import java.awt.*;

public class PlayerCharacter extends GameFigure {
    private int size = 30;
    int width, height;
    public static final int UNIT_MOVE = 10;
    public static final int STATE_ALIVE = 0;
    private static final int STATE_DYING = 1;
    public static final int STATE_DONE = 2;
    int state;
    int lives;
    Color color = Color.DARK_GRAY;



    public PlayerCharacter (int x, int y) {
        super(x,y);
        width = size;
        lives = 3;
        height = size/2;
        state = STATE_ALIVE;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.fillOval((int) location.x - width/2, (int) location.y - height/2, width, height);
    }

    @Override
    public void update() {
        updateState();

        if(state == STATE_ALIVE){
            color = Color.DARK_GRAY;
        }
        if(state == STATE_DYING){
            --lives;
            color = Color.ORANGE;
            while (size > 0) {
                --size;
            }
            if (size == 0){
                setLocation(Main.win.getWidth()/2, Main.win.getHeight()/2);
                if(lives > 0) {
                    state = STATE_ALIVE;
                    hitCount = 0;
                    size = width;
                }else {
                    state = STATE_DONE;
                }
            }
        }
    }
    private void updateState() {
        if (state == STATE_ALIVE) {
            if (hitCount > 0) {
                state = STATE_DYING;
            }
        } else if (state == STATE_DYING) {
            if(lives == 0) {
                state = STATE_DONE;
            }
        }else if(state == STATE_DONE){
            super.done = true;
            Main.gameOver = true;
        }
    }
    @Override
    public int getCollisionRadius() {
        return (int) (size/2 * 0.7);
    }
    
    public int getSize() {
        return size;
    }

    public int getLives() {
        return lives;
    }




}
