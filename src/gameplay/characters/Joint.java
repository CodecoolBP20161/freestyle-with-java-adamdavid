package gameplay.characters;

import javax.swing.*;


public class Joint extends Character{
    private Player player;
    private int posX;
    private int posY;
    private int placementX;
    private int placementY;
    private boolean shoot = false;
    private String shootDirection = "right";

    public Joint(Player player, int placementX, int placementY, int posX, int posY, int speed, int inCellX, int inCellY,
                 String[] leftMovementFramesArray, String[] rightMovementFramesArray) {
        super(posX, posY, speed, inCellX, inCellY, leftMovementFramesArray, rightMovementFramesArray);
        this.player = player;
        this.placementX = placementX;
        this.placementY = placementY;
        this.characterImage = new ImageIcon(leftMovementFramesArray[0]).getImage();
        checkPlayersDirection();
        if (shootDirection.equals("left")) this.movementFrames = leftMovementFrames;
        else this.movementFrames = rightMovementFrames;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    void move() {
        if (!shoot) followPlayer();
        else shootMovement();

    }

    private void followPlayer() {
        checkPlayersDirection();
        directionCheck();
        shiftMovementFrame();
        if (shootDirection.equals("left")) {
            posX = player.getPosX() - placementX;
            posY = player.getPosY() + placementY;
        } else {
            int jointCoversPlayer = characterImage.getWidth(null) - placementX;
            posX = player.getPosX() + player.getCharacterImage().getWidth(null) - jointCoversPlayer;
            posY = player.getPosY() + placementY;
        }
    }

    private void checkPlayersDirection() {
        if (player.isRight()) {
            shootDirection = "right";
            right = true;
            left = false;
        }
        if (player.isLeft()) {
            shootDirection = "left";
            left = true;
            right = false;
        }
    }

    private void shootMovement() {
        if (shootDirection.equals("right")) {
            posX += player.getSpeed() * 2;
        } else {
            posX -= player.getSpeed() * 2;
        }

        if (posX < 1 || posX > gameMap.getWindowWidth()) {
            player.setShotJoint(null);
        }
    }
}
