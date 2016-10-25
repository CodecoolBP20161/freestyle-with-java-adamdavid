package gameplay;

import gameplay.characters.Player;
import gameplay.environment.GameMap;
import gameplay.environment.Plant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private Timer timer;
    private int delay;
    private Player player;
    private GameMap gameMap;


    public GamePlay(int windowWidth, int windowHeight){
        this.delay = 40;
        this.gameMap = GameMap.getInstance(windowWidth, windowHeight);

        // sprite images
        String[] rightSprites = new String[] {
                "assets/characters/right_duck/duck01.png",
                "assets/characters/right_duck/duck02.png",
                "assets/characters/right_duck/duck03.png"
        };
        String[] leftSprites = new String[] {
                "assets/characters/left_duck/duck01.png",
                "assets/characters/left_duck/duck02.png",
                "assets/characters/left_duck/duck03.png"
        };
        String[] leftPlantingSprites = new String[] {
                "assets/characters/left_duck/harvesting01.png",
                "assets/characters/left_duck/harvesting02.png",
                "assets/characters/left_duck/harvesting03.png"
        };

        String[] rightPlantingSprites = new String[] {
                "assets/characters/right_duck/harvesting01.png",
                "assets/characters/right_duck/harvesting02.png",
                "assets/characters/right_duck/harvesting03.png"
        };

        this.player = new Player(gameMap.getBackgroundCells()[5][5].getPosX(),
                gameMap.getBackgroundCells()[5][5].getPosY(), 10, 5, 5,
                leftSprites, rightSprites,
                leftPlantingSprites, rightPlantingSprites);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // draw the map
        g2d.drawImage((new ImageIcon("assets/environment/background.PNG")).getImage(), 0, 0,
                gameMap.getWindowWidth(), gameMap.getWindowHeight(), this);
        gameMap.drawMap(g2d, this);

        // draw the player
        g2d.drawImage(player.getCharacterImage(), player.getPosX(), player.getPosY(), this);

        // test rows
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("cell: " + player.getInCell()[0] + " " + player.getInCell()[1], 600, 25);
        g.drawString("planting: " + player.isPlanting(), 600, 50);
        g.drawString("left: " + player.isLeft(), 600, 75);
        g.drawString("right: " + player.isRight(), 600, 100);
        g.drawString("up: " + player.isUp(), 600, 125);
        g.drawString("down: " + player.isDown(), 600, 150);
        g.setColor(Color.red);
        g.drawString("index: " + player.getRecentPlantingFrameIndex(), 600, 400);
        if (Plant.plantedPlants.size() > 0) {
            g.drawString("plantstatus: " + Plant.plantedPlants.get(0).getStatus(), 600, 175);
            g.drawString("plantcounter: " + Plant.plantedPlants.get(0).getGrowingCounter(), 600, 20);
            g.drawString("plantcellx: " + Plant.plantedPlants.get(0).getInCell()[0], 600, 225);
            g.drawString("plantcelly: " + Plant.plantedPlants.get(0).getInCell()[1], 600, 250);
            g.drawString("plantcelly: " + System.currentTimeMillis(), 600, 300);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    // run on 'time tick'
    @Override
    public void actionPerformed(ActionEvent e) {
        player.moving();
        player.directionCheck();
        player.checkPosition();
        player.plantingDirectionCheck();
        Plant.growingPlants();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // handle key pressing events
    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPress(e);
    }

    // handle key release events
    @Override
    public void keyReleased(KeyEvent e) {
        player.keyRelease(e);
    }
}
