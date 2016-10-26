package gameplay.characters;

import gameplay.environment.Plant;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cave on 2016.10.26..
 */
public class Joint {
    private Player player;
    private int posX;
    private int posY;
    private int placementX;
    private int placementY;
    private boolean shoot = false;
    private Image[] images;
    private String shootDirection = "right";


    Joint(Player player, int placementX, int placementY, String leftJointSource, String rightJointSource) {
        this.player = player;
        this.placementX = placementX;
        this.placementY = placementY;
        this.posX = player.getPosX();
        this.posY = player.getPosY();

        Image leftJoint = new ImageIcon(leftJointSource).getImage();
        Image rightJoint = new ImageIcon(rightJointSource).getImage();
        images = new Image[]{leftJoint, rightJoint};
        checkDirection();
    }

    public Image[] getImages() {
        return images;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    private void draw() {

    }

    public void move() {
        if (!shoot) followPlayer();
//            else shootMovement();

    }

    private void followPlayer() {
        checkDirection();
        if (shootDirection.equals("left")) {
            player.setJointImage(images[0]);
            posX = player.getPosX() - placementX;
            posY = player.getPosY() + placementY;
        } else {
            player.setJointImage(images[1]);
            int jointCoversPlayer = player.getJointImage().getWidth(null) - placementX;
            posX = player.getPosX() + player.getCharacterImage().getWidth(null) - jointCoversPlayer;
            posY = player.getPosY() + placementY;
        }
    }

    private void checkDirection() {
        if (player.isRight()) shootDirection = "right";
        if (player.isLeft()) shootDirection = "left";
    }

//        private void shootMovement() {
//            if (isRight()) ;
//            else if (isLeft()) ;
//        }
}
