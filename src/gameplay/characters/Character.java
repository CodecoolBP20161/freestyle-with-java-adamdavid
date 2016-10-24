package gameplay.characters;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by cave on 2016.10.24..
 */
abstract class Character {
    private int posX;
    private int posY;
    private int speed;
    private ImageIcon characterImage;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSpeed() {
        return speed;
    }

    public ImageIcon getCharacterImage() {
        return characterImage;
    }

    public Character(int posX, int posY, int speed, String imageSource) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.characterImage = new ImageIcon(imageSource);
    }

    public Character(int posX, int posY, String imageSource) {
        this.posX = posX;
        this.posY = posY;
        this.speed = 1;
        this.characterImage = new ImageIcon(imageSource);
    }

    abstract void moving(KeyEvent e);

    abstract boolean checkMapEdge();


}
