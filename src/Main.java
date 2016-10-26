import gameplay.GamePlay;
import sun.audio.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


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
        music();
    }

    public static void music() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream backGroundMusic;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream("assets/music/01.wav");
            backGroundMusic = new AudioStream(test);
            AudioPlayer.player.start(backGroundMusic);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MGP.start(loop);
    }
}