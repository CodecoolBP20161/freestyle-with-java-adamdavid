package gameplay.characters;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cave on 2016.10.24..
 */
abstract class Character {
    protected int posX;
    protected int posY;
    protected int speed;
    private Image characterImage;
    private Image[] movementFrames = null;
    private int recentMovementFrameIndex = 0;
    protected boolean right = false;
    protected boolean left = false;
    protected boolean up = false;
    protected boolean down = false;

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

    public void setCharacterImage(Image characterImage) {
        this.characterImage = characterImage;
    }

    public Character(int posX, int posY, int speed, String... movementFramesArray) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.movementFrames = new Image[movementFramesArray.length];
        for (int i = 0; i < movementFramesArray.length; i++) {
            this.movementFrames[i] = new ImageIcon(movementFramesArray[i]).getImage();
        }
        this.characterImage = movementFrames[0];
    }

    public Character(int posX, int posY, String... movementFramesArray) {
        this.posX = posX;
        this.posY = posY;
        this.speed = 1;
        this.movementFrames = new Image[movementFramesArray.length];
        for (int i = 0; i < movementFramesArray.length; i++) {
            this.movementFrames[i] = new ImageIcon(movementFramesArray[i]).getImage();
        }
        this.characterImage = movementFrames[0];

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


}
