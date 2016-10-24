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
    protected int dx;
    protected int dy;
    private Image characterImage;

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

    abstract boolean checkMapEdge();


}
