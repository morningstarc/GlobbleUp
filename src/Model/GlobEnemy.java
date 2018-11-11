package Model;

import Controller.Main;

import java.awt.*;

public class GlobEnemy extends GameFigure {
    int x, y;
    int size = 40;
    int width, height;
    boolean movingRight = true;
    public static final int UNIT_MOVE = 5;
    public static final int UNIT_MOVE_FALLING = 5;
    public static final int STATE_FLYING = 0;
    public static final int STATE_FALLING = 1;
    public static final int STATE_DONE = 2;
    int state;
    Color color;

    public GlobEnemy(int x, int y){
        super(x,y);
        width = size;
        height = size/2;
        state = STATE_FLYING;
        color = Color.BLUE;
    }
    public GlobEnemy(int x, int y, int size){
        super(x,y);
        this.size = size;
        width = size;
        height = size/2;
        state = STATE_FLYING;
        color = Color.BLUE;
    }

    public GlobEnemy(int size){
        super();
        this.x = 100;
        this.y = 200;
        this.size = size;
        width = size;
        height = size/2;
        state = STATE_FLYING;
        color = Color.BLUE;
    }
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1));
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File("monster.png"));
//        } catch (IOException e) {
//        }
        g2.fillOval((int) location.x - width/2, (int) location.y - height/2, width, height);

    }

    @Override
    public void update() {
        updateState();

        if (state == STATE_FLYING) {
            if (location.x >= Main.win.canvas.width) {
                movingRight = false;

            } else if (location.x <= 0) {
                movingRight = true;
            }
            if (movingRight) {
                location.x += UNIT_MOVE;
            } else {
                location.x -= UNIT_MOVE;
            }
        }else if (state == STATE_FALLING){
            color = Color.ORANGE;
            location.y += UNIT_MOVE_FALLING;
        }
    }
    private void updateState(){
        if(state == STATE_FLYING) {
            if (hitCount > 0) {
                state = STATE_FALLING;
            }
        }else if(state == STATE_FALLING){
            if(location.y >= Main.win.canvas.height){
                state = STATE_DONE;
            }
        }else if(state == STATE_DONE){
            super.done = true;

        }
    }


    @Override
    public int getCollisionRadius() {
        return (int) (size/2 * 0.7);
    }

    public int getSize()  {
        return size;
    }



}
