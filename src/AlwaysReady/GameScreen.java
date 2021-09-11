package AlwaysReady;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JFrame;

public class GameScreen extends JFrame {
    private BufferedImage image;

    public GameScreen() {
        initUI();
    }

    private void initUI() {

        try {
            image = ImageIO.read(new FileImageInputStream(new File("rocket.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle("ALWAYS READY");
        setSize(800, 600);
        setIconImage(image);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game screen = new Game();
        screen.requestFocus();
        screen.setFocusable(true);
        add(new Game());
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            GameScreen ex = new GameScreen();
            ex.setVisible(true);
        });
    }
}
