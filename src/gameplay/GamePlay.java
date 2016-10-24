package gameplay;

import gameplay.characters.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Cave on 2016.10.22..
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private Timer timer;
    private int delay;
    private Player player;

    public GamePlay() {
        this.delay = 100;
        this.timer = new Timer(delay, this);
        this.player = new Player(24, 74, 5, "src/gameplay/characters/assets/duck01.png");
        timer.start();
    }

    public void paint(Graphics graphics) {
        player.getCharacterImage().paintIcon(this, graphics, player.getPosX(), player.getPosY());
        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
