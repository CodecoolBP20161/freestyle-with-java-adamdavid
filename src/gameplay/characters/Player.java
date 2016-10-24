package gameplay.characters;

import gameplay.controller.ControllerState;

import java.awt.event.KeyEvent;
import java.lang.*;

/**
 * Created by Cave on 2016.10.22..
 */
public class Player extends Character {

    public Player(int posX, int posY, int speed, String imageSource) {
        super(posX, posY, speed, imageSource);
    }

    public Player(int posX, int posY, String imageSource) {
        super(posX, posY, imageSource);
    }


    public void moving() {
        posX += dx;
        posY += dy;
    }

    public void keyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: dx = speed; break;
            case KeyEvent.VK_LEFT: dx = -1 * speed; break;
            case KeyEvent.VK_UP: dy = -1 * speed; break;
            case KeyEvent.VK_DOWN: dy = speed; break;
        }
    }

    public void keyRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: dx = 0; break;
            case KeyEvent.VK_LEFT: dx = 0; break;
            case KeyEvent.VK_UP: dy = 0; break;
            case KeyEvent.VK_DOWN: dy = 0; break;
        }
    }

    @Override
    boolean checkMapEdge() {
        //TODO: check if the player is at the edge of the map. If yes return true if not return false.

        // if you feel the power then implement this private function into the moving method,
        // so the player can't move further than the edge coords of the map :)

        return false;
    }

    public void method() {
    }
}
