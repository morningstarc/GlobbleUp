package Model;

import Controller.Main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static Model.Sprite.*;


public class Bomb extends GameFigure {

    public static final int UNIT_MOVE = 5;
    public static final int STATE_DROPPED = 0;
    public static final int STATE_DETONATED = 1;
    public static final int STATE_DONE = 2;
    public final int INIT_SIZE = 8;
    public final int FINAL_SIZE = 50;
    public static int state;
    Point2D.Float target;
    Color color;
    int size = INIT_SIZE;
    //Sprite
    // Images for each animation
    private BufferedImage[] dropped = {Sprite.getSprite(0, 0), Sprite.getSprite(2, 0)}; // Gets the upper left images of my sprite sheet

    private BufferedImage[] detonated = {Sprite.getSprite(1, 1), Sprite.getSprite(2, 1)};

    // These are animation states
    private Animation drop = new Animation(dropped, 20);
    private Animation detonate = new Animation(detonated, 20);

    // This is the actual animation
    private Animation animation = drop;




    public Bomb(){
        PlayerCharacter player = (PlayerCharacter) Main.gameData.fixedObject.get(Main.INDEX_PLAYER);
        super.location.x = player.location.x + 5;
        super.location.y = player.location.y + 5;
        target = new Point2D.Float(super.location.x, super.location.y);
        LoadImage("bomb.png");
        state = STATE_DROPPED;

    }
    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(animation.getSprite(), (int) super.location.x - size / 2, (int) super.location.y - size / 2, null);
    }

    @Override
    public void update() {
        updateState();
        animation.update();
        if(state == STATE_DETONATED){
            ++size;
        }
    }

    private void updateState(){
        if(state == STATE_DROPPED){
            animation = drop;
            animation.start();

        }else if(state == STATE_DETONATED){
            animation = detonate;
            animation.start();
            if(size >= FINAL_SIZE){
                state = STATE_DONE;
                //animation.stop();
            }
            state = STATE_DONE;

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
