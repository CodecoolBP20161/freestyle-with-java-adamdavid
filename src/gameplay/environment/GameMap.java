package gameplay.environment;
import gameplay.GamePlay;
import java.awt.*;


// gameMap singleton
public class GameMap {
    private static GameMap instance = null;

    private BackgroundCell[][] backgroundCells;
    private int windowHeight;
    private int windowWidth;

    private GameMap(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.backgroundCells = generateCells();
    }

    private GameMap() {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.backgroundCells = generateCells();
    }

    public static GameMap getInstance(int windowWidth, int windowHeight) {
        if(instance == null) {
            instance = new GameMap(windowWidth, windowHeight);
        }
        return instance;
    }

    public static GameMap getInstance() {
        if(instance == null) {
            instance = new GameMap();
        }
        return instance;
    }

    // getters
    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public BackgroundCell[][] getBackgroundCells() {
        return backgroundCells;
    }

    // generate BackgroundCell objects
    private BackgroundCell[][] generateCells() {

        int cellsInRow = 12;
        int cellsInColumn = 9;
        int cellWidth = windowWidth / cellsInRow;
        int cellHeight= windowHeight / cellsInColumn;
        backgroundCells = new BackgroundCell[cellsInColumn][cellsInRow];

        // fill up BackgroundCell array with BackgroundCell objects
        for (int i = 0; i < cellsInColumn; i++) {
            int posY = i * cellHeight;
            for (int j = 0; j < cellsInRow; j++) {
                int posX = j * cellWidth;
                backgroundCells[i][j] = new BackgroundCell("assets/environment/emptyCell.jpg", posX, posY,
                        cellWidth, cellHeight, "empty");
            }
        }
        return backgroundCells;
    }

    // draw the cells on the window
    public void drawMap(Graphics2D g2d, GamePlay gamePlay) {
        int cellBorderWidth = 2;

        for (int i = 0; i < backgroundCells.length; i++) {
            for (int j = 0; j < backgroundCells[i].length; j++) {
                BackgroundCell cell =  backgroundCells[i][j];
                g2d.drawImage(cell.getImage(), cell.getPosX() + cellBorderWidth,
                        cell.getPosY() + cellBorderWidth, cell.getWidth() - cellBorderWidth * 2,
                        cell.getHeight() - cellBorderWidth * 2, gamePlay);
            }
        }
    }
}
