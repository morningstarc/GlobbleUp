package Model;

import Controller.Main;

import java.awt.*;
import java.awt.geom.Point2D;

public class Bomb extends GameFigure {

    public static final int UNIT_MOVE = 5;
    public static final int INIT_BOMB_SIZE = 8;
    public static final int MAX_BOMB_SIZE = 45;

    public static final int STATE_DROPPED = 0;
    public static final int STATE_DETONATED = 1;
    public static final int STATE_DONE = 2;

    int size = INIT_BOMB_SIZE;
    public static int state;
    Point2D.Float target;
    Color color;




    public Bomb(int x, int y){
        PlayerCharacter player = (PlayerCharacter) Main.gameData.fixedObject.get(Main.INDEX_PLAYER);
        super.location.x = player.location.x + 5;
        super.location.y = player.location.y + 5;

        target = new Point2D.Float(super.location.x, super.location.y);
        color = Color.CYAN;
        state = STATE_DROPPED;
    }
    public Bomb(){
        PlayerCharacter player = (PlayerCharacter) Main.gameData.fixedObject.get(Main.INDEX_PLAYER);
        super.location.x = player.location.x + 5;
        super.location.y = player.location.y + 5;
        target = new Point2D.Float(super.location.x, super.location.y);

        color = Color.CYAN;
        state = STATE_DROPPED;

    }
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1));
        g2.fillOval((int) super.location.x - size/2, (int) super.location.y - size/2, size, size);
    }

    @Override
    public void update() {
        updateState();
        if(state == STATE_DETONATED){
              color = Color.YELLOW;
                ++size;
        }

    }

    private void updateState(){
        if(state == STATE_DROPPED){

        }else if(state == STATE_DETONATED){
            if(size >= MAX_BOMB_SIZE){
                state = STATE_DONE;
            }
        }else if(state == STATE_DONE){
            super.done = true;
        }
    }


    @Override
    public int getCollisionRadius() {

        return size/2;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
