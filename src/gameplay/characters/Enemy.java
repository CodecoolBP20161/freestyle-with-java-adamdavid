package gameplay.characters;


public class Enemy extends Character{
    private Player player;
    private double ownSpeed;

    public Enemy(Player player, int posX, int posY, int speed, int inCellX, int inCellY,
                 String[] leftMovementFramesArray, String[] rightMovementFramesArray) {
        super(posX, posY, speed, inCellX, inCellY, leftMovementFramesArray, rightMovementFramesArray);
        this.ownSpeed = speed;
        this.player = player;
    }

    public void moving() {
        if (stepCounter == 5){
            shiftMovementFrame();
            stepCounter = 0;
        }

        if (player.posX > posX) {
            posX += ownSpeed;
            left = false;
            right = true;
        }
        if (player.posX < posX) {
            posX -= ownSpeed;
            left = true;
            right = false;
        }
        if (player.posY > posY) {
            posY += ownSpeed;
        }
        if (player.posY < posY) {
            posY -= ownSpeed;
        }

        ownSpeed += 0.001;
        stepCounter++;
    }
}
