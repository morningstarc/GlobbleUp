package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SkyBox extends GameFigure {

    //Maybe later we can change the skybox depending on the level.
    private int level;

    public SkyBox(int level)
    {   super();
        this.level = level;
        LoadImage("skybox.png");
    }


    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(this.image, 0, 0,  null);
    }

    @Override
    public void update() {

        //WE can use this to figure out what level we are on or to react to various effects for the skybox. Weather patterns could be handled here as well.
    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
