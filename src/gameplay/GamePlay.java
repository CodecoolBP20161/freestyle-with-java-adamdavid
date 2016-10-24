package gameplay;

import gameplay.characters.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private Timer timer;
    private int delay;
    private Player player;

    public GamePlay() {
        this.delay = 100;
        this.player = new Player(24, 74, 5, "src/gameplay/characters/assets/duck01.png");
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
        g2d.drawImage(player.getCharacterImage(), player.getPosX(), player.getPosY(), this);
        g.setColor(Color.black);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("left: " + player.isLeft(), 600, 25);
        g.drawString("right: " + player.isRight(), 600, 50);
        g.drawString("up: " + player.isUp(), 600, 75);
        g.drawString("down: " + player.isDown(), 600, 100);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.moving();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPress(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyRelease(e);
    }
}
