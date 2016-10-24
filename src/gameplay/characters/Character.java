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

    public Character(int posX, int posY, int speed, String imageSource) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.characterImage = new ImageIcon(imageSource).getImage();
    }

    public Character(int posX, int posY, String imageSource) {
        this.posX = posX;
        this.posY = posY;
        this.speed = 1;
        this.characterImage = new ImageIcon(imageSource).getImage();
    }


}
