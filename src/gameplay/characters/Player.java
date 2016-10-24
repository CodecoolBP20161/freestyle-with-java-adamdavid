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

    @Override
    void moving(KeyEvent e) {
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_RIGHT: moveRight(); break;
//            case KeyEvent.VK_LEFT: moveLeft(); break;
//            case KeyEvent.VK_UP: moveUp(); break;
//            case KeyEvent.VK_DOWN: moveDown(); break;
//        }
        // TODO: Move to the given direction ("right", "left", "up", "right") in the coord system by the given speed.
        // Example: if posX = 0 and the player moves to the right then the new posX will be 0 + speed
        // the x=0 and the y=0 are at the upper left corner of the field
        // No display yet, so test it manually :)
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
