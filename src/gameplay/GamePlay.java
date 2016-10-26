package gameplay;

import gameplay.characters.Enemy;
import gameplay.characters.Player;
import gameplay.environment.GameMap;
import gameplay.environment.Plant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private Player player;
    private Enemy enemy;
    private GameMap gameMap;


    public GamePlay(int windowWidth, int windowHeight){

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

        // make player object
        this.player = new Player(gameMap.getBackgroundCells()[5][5].getPosX(),
                gameMap.getBackgroundCells()[5][5].getPosY(), 10, 5, 5,
                leftSprites, rightSprites,
                leftPlantingSprites, rightPlantingSprites);


        String[] rightEnemySprites = new String[]
                {
                        "assets/characters/drugSlugRight.png",
                        "assets/characters/drugSlugRight2.png"
                };
        String[] leftEnemySprites = new String[]
                {
                        "assets/characters/drugSlugLeft.png",
                        "assets/characters/drugSlugLeft2.png"
                };
        //make enemy
        this.enemy  = new Enemy(this.player,
                gameMap.getBackgroundCells()[0][0].getPosX(),
                gameMap.getBackgroundCells()[0][0].getPosY(),
                1, 0, 0,
                leftEnemySprites, rightEnemySprites);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 40;
        Timer timer = new Timer(delay, this);
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

        // draw the player and enemy
        g2d.drawImage(player.getCharacterImage(), player.getPosX(), player.getPosY(), this);
        g2d.drawImage(enemy.getCharacterImage(), enemy.getPosX(), enemy.getPosY(), this);

        // test rows
        g.setColor(Color.red);
        g.setFont(new Font("arial", Font.PLAIN, 14));
//        g.drawString("collide: " + player.collideWithSprite(enemy.getCharacterImage(), enemy.getPosX(), enemy.getPosY(), 10), 600, 100);
//        g.drawString("collide: " + enemy.collideWithSprite(player.getCharacterImage(), player.getPosX(), player.getPosY()), 600, 150);

        Toolkit.getDefaultToolkit().sync();
    }

    // run on 'time tick'
    @Override
    public void actionPerformed(ActionEvent e) {
        player.moving();
        enemy.moving();
        player.checkDeath(enemy, 22);
        player.directionCheck();
        enemy.directionCheck();
        player.checkPosition();
        enemy.checkPosition();
        player.plantingDirectionCheck();

        Plant.growingPlants();
        player.harvestIfPossible();
        enemy.eatIfPossible();
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
