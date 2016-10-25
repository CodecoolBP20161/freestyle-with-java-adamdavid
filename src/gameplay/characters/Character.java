package gameplay.characters;

import gameplay.environment.BackgroundCell;
import gameplay.environment.GameMap;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cave on 2016.10.24..
 */
abstract class Character {
    protected int posX;
    protected int posY;
    protected int spriteWidth = 40;
    protected int spriteHeight = 40;
    protected int speed;
    private Image characterImage;
    private Image[] leftMovementFrames = null;
    private Image[] rightMovementFrames = null;
    private Image[] movementFrames = null;
    private int recentMovementFrameIndex = 0;
    protected boolean right = false;
    protected boolean left = false;
    protected boolean up = false;
    protected boolean down = false;
    protected int[] inCell;
    protected GameMap gameMap;

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

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public int[] getInCell() {
        return inCell;
    }

    public void setCharacterImage(Image characterImage) {
        this.characterImage = characterImage;
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

    public Character(int posX, int posY, int inCellX, int inCellY,
                     String[] leftMovementFramesArray, String[] rightMovementFramesArray) {
        this.posX = posX;
        this.posY = posY;
        this.speed = 1;
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

    protected void shiftMovementFrame() {
        if (recentMovementFrameIndex == movementFrames.length - 1) {
            recentMovementFrameIndex = 0;
        } else {
            recentMovementFrameIndex++;
        }
        setCharacterImage(movementFrames[recentMovementFrameIndex]);
    }

    protected void toggleStandingFrame() {
        recentMovementFrameIndex = 0;
        setCharacterImage(movementFrames[recentMovementFrameIndex]);
    }

    public void checkPosition() {
        int firstRowToCheck = (inCell[0] == 0) ? 0 : inCell[0] - 1;
        int lastRowToCheck = (inCell[0] == gameMap.getBackgroundCells().length - 1) ? inCell[0] : inCell[0] + 1;
        int firstColumnToCheck = (inCell[1] == 0) ? 0 : inCell[1] - 1;
        int lastColumnToCheck = (inCell[1] == gameMap.getBackgroundCells()[0].length - 1) ? inCell[1] : inCell[1] + 1;

        for (int i = firstRowToCheck; i <= lastRowToCheck; i++) {
            for (int j = firstColumnToCheck; j <= lastColumnToCheck; j++) {
                BackgroundCell cell = gameMap.getBackgroundCells()[i][j];

                int[] playerFoot = {posX + spriteWidth / 2, posY + spriteHeight};
                if ((playerFoot[0] >= cell.getPosX()) && (playerFoot[0] <= (cell.getPosX() + cell.getWidth())) &&
                        (playerFoot[1] >= cell.getPosY() && playerFoot[1] <= cell.getPosY() + cell.getHeight())) {
                    inCell[0] = i;
                    inCell[1] = j;
                    return;
                }
            }
        }
    }

    public void directionCheck() {
        if (left) movementFrames = leftMovementFrames;
        else if (right) movementFrames = rightMovementFrames;
    }
}
