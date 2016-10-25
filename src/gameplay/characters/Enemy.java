package gameplay.characters;


/**
 * Created by cave on 2016.10.25..
 */
public class Enemy extends Character{
    private Player player;
    private double ownSpeed;

    public Enemy(Player player, int posX, int posY, int speed, int inCellX, int inCellY,
                 String[] leftMovementFramesArray, String[] rightMovementFramesArray) {
        super(posX, posY, speed, inCellX, inCellY, leftMovementFramesArray, rightMovementFramesArray);
        this.ownSpeed = speed;
        this.player = player;
    }

    public double getOwnSpeed() {
        return ownSpeed;
    }

    public void moving() {
        if (player.posX > posX) posX += ownSpeed;
        if (player.posX < posX) posX -= ownSpeed;
        if (player.posY > posY) posY += ownSpeed;
        if (player.posY < posY) posY -= ownSpeed;
        ownSpeed += 0.001;
    }

}
