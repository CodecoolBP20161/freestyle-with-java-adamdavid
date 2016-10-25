package gameplay.environment;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cave on 2016.10.25..
 */
public class BackgroundCell {
    private Image image;
    private int posX;
    private int posY;
    private int width;
    private int height;
    private String status;

    public BackgroundCell(String imageSource, int posX, int posY, int width, int height, String status) {
        this.image = new ImageIcon(imageSource).getImage();
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.status = status;
    }

    public Image getImage() {
        return image;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getStatus() {
        return status;
    }
}
