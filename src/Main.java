import gameplay.GamePlay;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Cave on 2016.10.22..
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 840, 630);
        GamePlay gamePlay = new GamePlay(frame.getWidth(), frame.getHeight());
        frame.setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePlay);
    }
}