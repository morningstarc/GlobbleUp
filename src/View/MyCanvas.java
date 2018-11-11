package View;

import Controller.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;

public class MyCanvas extends JPanel {

    //canvas size
    public int width;
    public int height;

    public void render() {
        width = getSize().width;
        height = getSize().height;

        //off-screen double buffer image
        Image doubleBufferImage = createImage(width, height);
        if(doubleBufferImage == null) {
            System.out.println("Critical Error: doubleBufferImage is null");
            System.exit(1);
        }

        //off-screen rendering (next screen data)
        Graphics2D g2OffScreen = (Graphics2D) doubleBufferImage.getGraphics();
        if(g2OffScreen == null) {
            System.out.println("Critical Error: g2OffScreen is null");
            System.exit(1);
        }

        //image buffer initialization
        g2OffScreen.setBackground(Color.black);  //window background color
        g2OffScreen.clearRect(0,0, width, height);


        for(var fig: Main.gameData.fixedObject){
            fig.render(g2OffScreen);
        }

        for(var fig: Main.gameData.friendObject){
            fig.render(g2OffScreen);
        }

        for(var fig: Main.gameData.enemyObject){
            fig.render(g2OffScreen);
        }

        //Active Render (put buffer image on screen)
        Graphics gOnScreen;
        gOnScreen = this.getGraphics();
        if(gOnScreen != null){
            //offscreen moved to onscreen
            gOnScreen.drawImage(doubleBufferImage, 0, 0, null);
        }

        //sync the display on some systems
        Toolkit.getDefaultToolkit().sync();
        if(gOnScreen != null){
            gOnScreen.dispose();
        }




    }


}
