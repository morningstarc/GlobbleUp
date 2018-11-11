package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public abstract class GameFigure implements Subject {
    BufferedImage image;
    public Point2D.Float location;
    public boolean done = false;
    public int hitCount = 0;
    public  GameFigure(float x, float y) {
        location = new Point2D.Float(x, y);
    }

    public GameFigure(){
        this(0,0);
    }

    public void setLocation(float x, float y){
        location.x = x;
        location.y = y;
    }

    public boolean collideWith(GameFigure o){
        double distance = this.location.distance(o.location);
        if(distance <= this.getCollisionRadius() + o.getCollisionRadius()){
            return true;
        }else{
            return false;
        }
    }

    public abstract void render(Graphics2D g2);
    public abstract void update();
    public abstract int getCollisionRadius();
    public abstract int getSize();


    public BufferedImage LoadImage(String filename)
    {
        BufferedImage img = null;
        try {

            img = ImageIO.read(getClass().getResource("/resources/Images/" + filename));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if(img != null)
        {
            this.image = img;
        }
        else
        {
            System.out.println("The background image didn't load because...");
        }
        return img;
    }

    @Override
    public void NotifyObservers(String event) {
        for(int i = 0 ; i < this.observables.size(); i++)
        {

            observables.get(i).notify(event);
        }
    }


    //Do not use in a loop, use after a loop.
    @Override
    public void RegisterObserver(IObservable observer) {
          observables.add(observer);
    }

    @Override
    public void DeregisterObserver(IObservable observer) {
        observables.remove(observables.indexOf(observer));
    }

    public BufferedImage getImage(){
        return image;
    }
}
