package Controller;

import Model.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class PlayerInputEventQueue {

    public LinkedList<InputEvent> queue = new LinkedList<>();

    public void processInputEvents() {
        while (!queue.isEmpty()) {
            InputEvent inputEvent = queue.removeFirst();

            switch (inputEvent.type) {
                case InputEvent.MOUSE_PRESSED:
                    MouseEvent e = (MouseEvent) inputEvent.event;
                    //Player will eat smaller enemies
                    break;

                case InputEvent.KEY_PRESSED:
                    var player = Main.gameData.fixedObject.get(Main.INDEX_PLAYER);
                    KeyEvent ke = (KeyEvent) inputEvent.event;
                    switch (ke.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            player.location.y -= PlayerCharacter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_DOWN:
                            player.location.y += PlayerCharacter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_RIGHT:
                            player.location.x += PlayerCharacter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_LEFT:
                            player.location.x -= PlayerCharacter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_SPACE:
                            Main.gameData.friendObject.add(new Bomb());
                            break;
                        case KeyEvent.VK_ALT:
                            Bomb.state = Bomb.STATE_DETONATED;
                            break;
                    }


            }}}}