package gameplay.characters;

import gameplay.controller.ControllerState;

import javax.swing.*;
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
        if (!leftEdgeCollision() && left) posX -= speed;
        if (!rightEdgeCollision() && right) posX += speed;
        if (!topEdgeCollision() && up) posY -= speed;
        if (!bottomEdgeCollision() && down) posY += speed;
    }

    public void keyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    public void keyRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: right = false; break;
            case KeyEvent.VK_LEFT: left = false; break;
            case KeyEvent.VK_UP: up = false; break;
            case KeyEvent.VK_DOWN: down = false; break;
        }
    }

    private boolean leftEdgeCollision() {
        if (posX < 1) return true;
        return false;
    }

    private boolean rightEdgeCollision() {
        int windowWidth = JFrame.getWindows()[0].getWidth();
        if (posX > windowWidth - 41) return true;
        return false;
    }

    private boolean topEdgeCollision() {
        if (posY < 1) return true;
        return false;
    }

    private boolean bottomEdgeCollision() {
        int windowHeight = JFrame.getWindows()[0].getHeight();
        if (posX > windowHeight - 41) return true;
        return false;
    }

    public void method() {
    }
}
