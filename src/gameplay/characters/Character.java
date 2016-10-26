package gameplay.characters;

import gameplay.environment.BackgroundCell;
import gameplay.environment.GameMap;
import gameplay.environment.Plant;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cave on 2016.10.24..
 */
abstract class Character {
    int posX;
    int posY;
    int speed;
    Image characterImage;
    Image[] leftMovementFrames = null;
    Image[] rightMovementFrames = null;
    Image[] movementFrames = null;
    private int recentMovementFrameIndex = 0;
    boolean right = false;
    boolean left = false;
    boolean up = false;
    boolean down = false;
    int[] inCell;
    int stepCounter = 0;
    GameMap gameMap;
    int points = 0;

    public int getPoints() {
        return points;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSpeed() {
        return speed;
    }

    public Image getCharacterImage() {
        return characterImage;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public int[] getInCell() {
        return inCell;
    }


    public Character(int posX, int posY, int speed, int inCellX, int inCellY,
                     String[] leftMovementFramesArray, String[] rightMovementFramesArray) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.inCell = new int[]{inCellX, inCellY};

        // right movement frames
        this.rightMovementFrames = new Image[rightMovementFramesArray.length];
        for (int i = 0; i < rightMovementFramesArray.length; i++) {
            this.rightMovementFrames[i] = new ImageIcon(rightMovementFramesArray[i]).getImage();
        }

        // left movement frames
        this.leftMovementFrames = new Image[leftMovementFramesArray.length];
        for (int i = 0; i < leftMovementFramesArray.length; i++) {
            this.leftMovementFrames[i] = new ImageIcon(leftMovementFramesArray[i]).getImage();
        }

        this.movementFrames = rightMovementFrames;
        this.characterImage = movementFrames[0];

        gameMap = GameMap.getInstance();
    }

    void shiftMovementFrame() {
        if (recentMovementFrameIndex == movementFrames.length - 1) {
            recentMovementFrameIndex = 0;
        } else {
            recentMovementFrameIndex++;
        }
        characterImage = movementFrames[recentMovementFrameIndex];
    }

    void toggleStandingFrame() {
        recentMovementFrameIndex = 0;
        characterImage = movementFrames[recentMovementFrameIndex];
    }

    public void checkPosition() {
        int firstRowToCheck = (inCell[0] == 0) ? 0 : inCell[0] - 1;
        int lastRowToCheck = (inCell[0] == gameMap.getBackgroundCells().length - 1) ? inCell[0] : inCell[0] + 1;
        int firstColumnToCheck = (inCell[1] == 0) ? 0 : inCell[1] - 1;
        int lastColumnToCheck = (inCell[1] == gameMap.getBackgroundCells()[0].length - 1) ? inCell[1] : inCell[1] + 1;

        for (int i = firstRowToCheck; i <= lastRowToCheck; i++) {
            for (int j = firstColumnToCheck; j <= lastColumnToCheck; j++) {
                if (checkCell(i, j)) return;
            }
        }
    }

    private boolean checkCell(int row, int column) {
        BackgroundCell cell = gameMap.getBackgroundCells()[row][column];
        int[] characterFoot = {posX + characterImage.getWidth(null) / 2, posY + characterImage.getHeight(null)};

        if ((characterFoot[0] >= cell.getPosX()) && (characterFoot[0] <= (cell.getPosX() + cell.getWidth())) &&
                (characterFoot[1] >= cell.getPosY() && characterFoot[1] <= cell.getPosY() + cell.getHeight())) {
            setNewPosition(row, column);
            return true;
        }
        return false;
    }

    private void setNewPosition(int row, int column) {
        inCell[0] = row;
        inCell[1] = column;
    }

    public void directionCheck() {
        if (left) movementFrames = leftMovementFrames;
        else if (right) movementFrames = rightMovementFrames;
    }

    // Harvest or Eat
    public void harvestIfPossible() {
        if (gameMap.getBackgroundCells()[inCell[0]][inCell[1]].getStatus().equals("grown")) pickupPlant();
    }

    public void eatIfPossible() {
        BackgroundCell actualCell = gameMap.getBackgroundCells()[inCell[0]][inCell[1]];
        if (actualCell.getStatus().equals("grown") || actualCell.getStatus().equals("planted")) pickupPlant();
    }

    private void pickupPlant() {
        int plantIndex = findPlantToHarvest();
        if (plantIndex != -1) {
            Plant.removePlant(plantIndex);
            BackgroundCell cell = gameMap.getBackgroundCells()[inCell[0]][inCell[1]];
            cell.setImage("assets/environment/emptyCell.jpg");
            cell.setStatus("empty");
            points++;
        }
    }

    private int findPlantToHarvest() {
        int index = 0;
        Plant plant;
        while (index < Plant.getPlantedPlants().size()) {
            plant = Plant.getPlantedPlants().get(index);
            if (plant.getInCell()[0] == inCell[0] && plant.getInCell()[1] == inCell[1]) return index;
            index++;
        }
        return -1;
    }

    boolean collideWithEnemy(Enemy enemy, int border) {
        boolean statementUp = posY + characterImage.getHeight(null) - border < enemy.posY;
        boolean statementDown = posY > enemy.posY + enemy.getCharacterImage().getHeight(null) - border;
        boolean statementLeft = posX + characterImage.getWidth(null) - border < enemy.posX;
        boolean statementRight = posX > enemy.posX + enemy.getCharacterImage().getWidth(null) - border;

        return !(statementUp || statementDown || statementLeft || statementRight);
    }

    boolean collideWithEnemy(Joint shotJoint, int border) {
        boolean statementUp = posY + characterImage.getHeight(null) - border < shotJoint.posY;
        boolean statementDown = posY > shotJoint.posY + shotJoint.getCharacterImage().getHeight(null) - border;
        boolean statementLeft = posX + characterImage.getWidth(null) - border < shotJoint.posX;
        boolean statementRight = posX > shotJoint.posX + shotJoint.getCharacterImage().getWidth(null) - border;

        return !(statementUp || statementDown || statementLeft || statementRight);
    }

    boolean leftEdgeCollision() {
        return posX < 1;
    }

    boolean rightEdgeCollision() {
        return posX > gameMap.getWindowWidth() - (characterImage.getWidth(null) + 10);
    }

    boolean topEdgeCollision() {
        return posY < 1;
    }

    boolean bottomEdgeCollision() {
        return posY > gameMap.getWindowHeight() - (characterImage.getHeight(null) + 3 + speed);
    }


}
