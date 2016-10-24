package gameplay.characters;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.lang.*;

/**
 * Created by Cave on 2016.10.22..
 */
public class Player extends Character {

    private int stepCounter = 0;

    public Player(int posX, int posY, int speed, String... moveFrameArray) {
        super(posX, posY, speed, moveFrameArray);
    }

    public Player(int posX, int posY, String... moveFrameArray) {
        super(posX, posY, moveFrameArray);
    }


    public void moving() {
        if (stepCounter == 3) {
            shiftMovementFrame();
            stepCounter = 0;
        }
        if (!leftEdgeCollision() && left && !up && !down) posX -= speed;
        else if (!leftEdgeCollision() && left) posX -= (int) Math.ceil((double) speed / Math.sqrt(2));

        if (!rightEdgeCollision() && right && !up && !down) posX += speed;
        else if (!rightEdgeCollision() && right) posX += (int) Math.ceil((double) speed /  Math.sqrt(2));


        if (!topEdgeCollision() && up && !left && !right) posY -= speed;
        else if (!topEdgeCollision() && up) posY -= (int) Math.ceil((double) speed /  Math.sqrt(2));

        if (!bottomEdgeCollision() && down && !left && !right) posY += speed;
        else if (!bottomEdgeCollision() && down) posY += (int) Math.ceil((double) speed /  Math.sqrt(2));

        if (left || right || up || down) stepCounter++;
        if (!left && !right && !up && !down) {
            toggleStandingFrame();
            stepCounter = 0;
        }
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
        if (posY > windowHeight - 41) return true;
        return false;
    }
}
