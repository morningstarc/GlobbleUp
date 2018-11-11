package Model;

import java.awt.*;

public class HealthBar extends GameFigure implements IObservable {
    //This should be max 100 usually and min 0.
    // This does not reflect actual health score for the player character, but is used for rendering purposes.
    int blocks = 3;

    public HealthBar()
    {
        LoadImage("blocks.png");
    }

    @Override
    public void render(Graphics2D g2) {
        Font font = new Font("Serif", Font.PLAIN, 25);
        g2.setFont(font);
        g2.setColor(Color.YELLOW);

        g2.drawString("Lives", 10, 420);
        int offset = 33;
        //Draw the amount of blocks we need correctly
        for ( int i = 0; i < blocks; i++) {
            g2.drawImage(this.image, (75 + offset * i), 394, null);
        }
    }

    @Override
    public void update() {
        //If we need to do math, lets do it here.
    }

    @Override
    public int getCollisionRadius() {
        //you can't collide with this.
        return 0;
    }

    @Override
    public int getSize() {
        //Maybe necessary later at some point.
        return 0;
    }

    @Override
    public void notify(String event) {
        if(event == "Die")
        {
            //Handle
            decrementBlocks();
        }
    }

    @Override
    public void notify(GameEvent event) {

        if(event.name == "Die")
        {
            //Handle
            decrementBlocks();
        }
        else if(event instanceof DeathEvent)
        {
            //Handle
            decrementBlocks();
        }

    }

    private void decrementBlocks()
    {
        this.blocks -= 1;
    };
}
