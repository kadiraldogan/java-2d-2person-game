package AlwaysReady;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements ActionListener {
        public Rectangle playButton = new Rectangle(350,470,100,50);
        private BufferedImage bckground;
        public void initBoard(Graphics g) {
            Graphics2D g2d= (Graphics2D) g;
            setFocusable(true);
             try {
                bckground = ImageIO.read(new FileImageInputStream(new File("menu.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2d.drawImage(bckground,0,0,800,600, this);
            Font fnt1= new Font("arial", Font.BOLD,20);
            g.setColor(Color.white);
            g.setFont(fnt1);
            g.drawString("BAŞLA", playButton.x+20,playButton.y+30);
            g2d.setColor(Color.white);
            g2d.setPaint(new GradientPaint(5, 25, Color.yellow, 20, 2, Color.red, true));
            g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
            g2d.draw(playButton);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
