package Model;

import java.util.ArrayList;

public class GameData {

    public ArrayList<GameFigure> fixedObject = new ArrayList<>();
    public ArrayList<GameFigure> friendObject = new ArrayList<>();
    public ArrayList<GameFigure> enemyObject = new ArrayList<>();


    public void update() {

        ArrayList<GameFigure> remove = new ArrayList<>();

        for(var fig: fixedObject){
            if(fig.done){
                remove.add(fig);
            }else {
                fig.update();
            }
        }
        fixedObject.removeAll(remove);
        remove.clear();

        for(var fig: friendObject){
            if(fig.done){
                remove.add(fig);
            }else {
                fig.update();
            }
        }
        friendObject.removeAll(remove);
        remove.clear();
        for(var fig: enemyObject){
            if(fig.done){
                remove.add(fig);
            }else {
                fig.update();
            }
        }
        enemyObject.removeAll(remove);
        remove.clear();

    }

    public void clear() {
        fixedObject.clear();
        friendObject.clear();
        enemyObject.clear();
    }
}
