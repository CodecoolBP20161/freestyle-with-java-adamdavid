package gameplay.characters;

import gameplay.environment.BackgroundCell;
import gameplay.environment.Plant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.*;


public class Player extends Character {


    private int plantingCounter = 1;
    private boolean planting = false;
    private long plantingStartTime = 0;
    private Image[] leftPlantingFrames = null;
    private Image[] rightPlantingFrames = null;
    private Image[] plantingFrames = null;
    private int recentPlantingFrameIndex = 0;
    private Joint joint = null;
    private Image jointImage;


    public Player(int posX, int posY, int speed, int inCellX, int inCellY,
                  String[] leftMovementFramesArray, String[] rightMovementFramesArray,
                  String[] leftPlantingFramesArray, String[] rightPlantingFramesArray) {
        super(posX, posY, speed, inCellX, inCellY, leftMovementFramesArray, rightMovementFramesArray);

        // left planting
        this.leftPlantingFrames = new Image[leftPlantingFramesArray.length];
        for (int i = 0; i < leftPlantingFramesArray.length; i++) {
            this.leftPlantingFrames[i] = new ImageIcon(leftPlantingFramesArray[i]).getImage();
        }

        // right planting
        this.rightPlantingFrames = new Image[rightPlantingFramesArray.length];
        for (int i = 0; i < rightPlantingFramesArray.length; i++) {
            this.rightPlantingFrames[i] = new ImageIcon(rightPlantingFramesArray[i]).getImage();
        }
        this.plantingFrames = rightPlantingFrames;
    }

    public Joint getJoint() {
        return joint;
    }

    public Image getJointImage() {
        return jointImage;
    }

    public void setJointImage(Image jointImage) {
        this.jointImage = jointImage;
    }

    public void moving() {
        if (stepCounter == 3) {
            shiftMovementFrame();
            stepCounter = 0;
        }
        if (!leftEdgeCollision() && left) posX -= speed;
        else if (leftEdgeCollision()) posX = 0;

        if (!rightEdgeCollision() && right) posX += speed;
        else if (rightEdgeCollision()) posX = gameMap.getWindowWidth() - characterImage.getWidth(null);


        if (!topEdgeCollision() && up) posY -= speed;
        else if (topEdgeCollision()) posY = 0;

        if (!bottomEdgeCollision() && down) posY += speed;
        else if (bottomEdgeCollision()) posY = gameMap.getWindowHeight() - (characterImage.getHeight(null) + 5);

        if (left || right || up || down) stepCounter++;
        if (!left && !right && !up && !down && !planting) {
            toggleStandingFrame();
            stepCounter = 0;
        }

        couldRollJoint();
        if (joint != null) joint.move();

    }

    private boolean leftEdgeCollision() {
        return posX < 1;
    }

    private boolean rightEdgeCollision() {
        int windowWidth = JFrame.getWindows()[0].getWidth();
        return posX > windowWidth - (characterImage.getWidth(null) + 10);
    }

    private boolean topEdgeCollision() {
        return posY < 1;
    }

    private boolean bottomEdgeCollision() {
        int windowHeight = JFrame.getWindows()[0].getHeight();
        return posY > windowHeight - (characterImage.getHeight(null) + 3 + speed);
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
        BackgroundCell cell = gameMap.getBackgroundCells()[inCell[0]][inCell[1]];
        if (cell.getStatus().equals("planted")) stopPlanting();
        else Planting();
    }

    private void Planting() {
        planting = true;
        characterImage = plantingFrames[recentPlantingFrameIndex];
        if (plantingCounter % 10 == 0) shiftPlantingFrameIndex();
        plantingCounter++;
        if (plantingStartTime == 0) plantingStartTime = System.currentTimeMillis();
        if (System.currentTimeMillis() - plantingStartTime > 1000) {
            finishPlanting();
        }
    }

    private void finishPlanting() {
        resetPlantingAttributes();
        BackgroundCell cell = gameMap.getBackgroundCells()[inCell[0]][inCell[1]];
        cell.setStatus("planted");
        cell.setImage("assets/environment/plantedCell.png");
        Plant.addNewPlant(new int[]{inCell[0], inCell[1]});
    }

    private void stopPlanting() {
        resetPlantingAttributes();
    }

    private void shiftPlantingFrameIndex() {
        if (recentPlantingFrameIndex == plantingFrames.length - 1) {
            recentPlantingFrameIndex = 0;
        } else {
            recentPlantingFrameIndex++;
        }
    }

    public void plantingDirectionCheck() {
        if (left) plantingFrames = leftPlantingFrames;
        else if (right) plantingFrames = rightPlantingFrames;
    }

    private void resetPlantingAttributes() {
        plantingCounter = 1;
        recentPlantingFrameIndex = 0;
        planting = false;
        plantingStartTime = 0;
    }

    public void checkDeath(Character enemy, int border) {
        if (collideWithAnotherCharacter(enemy, border)) {
            System.exit(0);
        }
    }

    private void couldRollJoint() {
        if (points % 5 == 0 && points > 0 && joint == null) {
            joint = new Joint(this, 15, 17, "assets/characters/left_duck/joint.png", "assets/characters/right_duck/joint.png");
            jointImage = joint.getImages()[0];
        }
    }

    private void shoot() {

    }
}
