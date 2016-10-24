package gameplay.characters;

import java.awt.event.KeyEvent;

/**
 * Created by cave on 2016.10.24..
 */
abstract class Character {
    private int posX;
    private int posY;
    private int speed;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Character(int posX, int posY, int speed) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
    }

    public Character(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.speed = 1;
    }

    abstract void moving(KeyEvent e);

    abstract boolean checkMapEdge();


}
