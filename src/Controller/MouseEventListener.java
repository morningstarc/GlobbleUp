package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEventListener extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e){
        InputEvent inputEvent = new InputEvent();
        inputEvent.event = e;
        inputEvent.type = InputEvent.MOUSE_PRESSED;
        Main.playerInput.queue.add(inputEvent);
    }




}
