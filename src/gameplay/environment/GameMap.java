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

    // getters
    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    // generate BackgroundCell objects
    private BackgroundCell[][] generateCells() {

        int cellsInRow = 12;
        int cellsInColumn = 9;
        int cellBorderWidth = 10;
        int cellWidth = windowWidth / cellsInRow - cellBorderWidth;
        int cellHeight= windowHeight / cellsInColumn - cellBorderWidth;
        backgroundCells = new BackgroundCell[cellsInColumn][cellsInRow];

        // fill up BackgroundCell array with BackgroundCell objects
        for (int i = 0; i < cellsInColumn; i++) {
            int posY = cellBorderWidth / 2 + i * (cellHeight + cellBorderWidth);
            for (int j = 0; j < cellsInRow; j++) {
                int posX = cellBorderWidth / 2 + j * (cellWidth + cellBorderWidth);
                backgroundCells[i][j] = new BackgroundCell("assets/environment/emptyCell.jpg", posX, posY,
                        cellWidth, cellHeight, "empty");
            }
        }
        return backgroundCells;
    }

    // draw the cells on the window
    public void drawMap(Graphics2D g2d, GamePlay gamePlay) {
        for (int i = 0; i < backgroundCells.length; i++) {
            for (int j = 0; j < backgroundCells[i].length; j++) {
                BackgroundCell cell =  backgroundCells[i][j];
                g2d.drawImage(cell.image, cell.posX, cell.posY, cell.width, cell.height, gamePlay);
            }
        }
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
