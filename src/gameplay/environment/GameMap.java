package gameplay.environment;

import gameplay.GamePlay;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cave on 2016.10.24..
 */
public class GameMap {
    private BackgroundCell[][] backgroundCells;
    private int windowHeight;
    private int windowWidth;

    public GameMap(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.backgroundCells = generateCells();
    }

    private BackgroundCell[][] generateCells() {
        int cellsInRow = 12;
        int cellsInColumn = 9;
        int cellWidth = windowWidth / cellsInRow - 1;
        int cellHeight= windowHeight / cellsInColumn - 1;
        backgroundCells = new BackgroundCell[cellsInColumn][cellsInRow];
        for (int i = 0; i < cellsInColumn; i++) {
            int posY = i * (cellHeight + 1);
            for (int j = 0; j < cellsInRow; j++) {
                int posX = j * (cellWidth + 1);
                backgroundCells[i][j] = new BackgroundCell("assets/environment/emptyCell.jpg", posX, posY,
                        cellWidth, cellHeight, "empty");
            }
        }
        System.out.println(backgroundCells[0][1].posX + "  " + backgroundCells[0][1].image + "  " + backgroundCells[0][1].width);
        return backgroundCells;
    }

    public void drawMap(Graphics2D g2d, GamePlay gamePlay) {
        for (int i = 0; i < backgroundCells.length; i++) {
            for (int j = 0; j < backgroundCells[i].length; j++) {
                BackgroundCell cell =  backgroundCells[i][j];
                g2d.drawImage(cell.image, cell.posX, cell.posY, cell.width, cell.height, gamePlay);
            }
        }
    }


    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    private class BackgroundCell {
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

    }

}
