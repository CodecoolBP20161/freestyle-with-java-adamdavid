package gameplay.characters;

import gameplay.environment.BackgroundCell;
import gameplay.environment.GameMap;
import gameplay.environment.Plant;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.lang.*;

/**
 * Created by Cave on 2016.10.22..
 */
public class Player extends Character {

    private int stepCounter = 0;
    private boolean planting = false;
    private long plantingStartTime = 0;

    public Player(int posX, int posY, int speed, int inCellX, int inCellY, String... moveFrameArray) {
        super(posX, posY, speed, inCellX, inCellY, moveFrameArray);
    }

    public Player(int posX, int posY, int inCellX, int inCellY, String... moveFrameArray) {
        super(posX, posY, inCellX, inCellY, moveFrameArray);
    }

    public boolean isPlanting() {
        return planting;
    }

    public void moving() {
        if (stepCounter == 3) {
            shiftMovementFrame();
            stepCounter = 0;
        }
        if (!leftEdgeCollision() && left && !up && !down) posX -= speed;
        else if (!leftEdgeCollision() && left) posX -= (int) Math.ceil((double) speed / Math.sqrt(2));
        else if (leftEdgeCollision()) posX = 0;

        if (!rightEdgeCollision() && right && !up && !down) posX += speed;
        else if (!rightEdgeCollision() && right) posX += (int) Math.ceil((double) speed /  Math.sqrt(2));
        else if (rightEdgeCollision()) posX = JFrame.getWindows()[0].getWidth() - spriteWidth;


        if (!topEdgeCollision() && up && !left && !right) posY -= speed;
        else if (!topEdgeCollision() && up) posY -= (int) Math.ceil((double) speed /  Math.sqrt(2));
        else if (topEdgeCollision()) posY = 0;

        if (!bottomEdgeCollision() && down && !left && !right) posY += speed;
        else if (!bottomEdgeCollision() && down) posY += (int) Math.ceil((double) speed /  Math.sqrt(2));
        else if (bottomEdgeCollision()) posY = JFrame.getWindows()[0].getHeight() - (spriteHeight + 5);

        if (left || right || up || down) stepCounter++;
        if (!left && !right && !up && !down) {
            toggleStandingFrame();
            stepCounter = 0;
        }
    }

    private boolean leftEdgeCollision() {
        if (posX < 1) return true;
        return false;
    }

    private boolean rightEdgeCollision() {
        int windowWidth = JFrame.getWindows()[0].getWidth();
        if (posX > windowWidth - (spriteWidth + 10)) return true;
        return false;
    }

    private boolean topEdgeCollision() {
        if (posY < 1) return true;
        return false;
    }

    private boolean bottomEdgeCollision() {
        int windowHeight = JFrame.getWindows()[0].getHeight();
        if (posY > windowHeight - (spriteHeight + 3 + speed)) return true;
        return false;
    }

    public void keyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE && !left && !right && !up && !down) startPlanting();
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                stopPlanting();
                right = true;
                break;
            case KeyEvent.VK_LEFT:
                stopPlanting();
                left = true;
                break;
            case KeyEvent.VK_UP:
                stopPlanting();
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                stopPlanting();
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
            case KeyEvent.VK_SPACE: stopPlanting(); break;
        }
    }

    private void startPlanting() {
        GameMap gameMap = GameMap.getInstance();
        BackgroundCell cell = gameMap.getBackgroundCells()[inCell[0]][inCell[1]];
        if (cell.getStatus().equals("planted")) stopPlanting();
        else Planting();
    }

    private void Planting() {
        planting = true;
        if (plantingStartTime == 0) plantingStartTime = System.currentTimeMillis();
        if (System.currentTimeMillis() - plantingStartTime > 1500) {
            finishPlanting();
        }
    }

    private void finishPlanting() {
        planting = false;
        plantingStartTime = 0;
        GameMap gameMap = GameMap.getInstance();
        BackgroundCell cell = gameMap.getBackgroundCells()[inCell[0]][inCell[1]];
        cell.setStatus("planted");
        cell.setImage("assets/environment/plantedCell.png");
        Plant.addNewPlant(new int[]{inCell[0], inCell[1]});
    }

    private void stopPlanting() {
        planting = false;
        plantingStartTime = 0;
    }
}
