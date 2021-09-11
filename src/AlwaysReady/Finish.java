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

public class Finish extends JPanel implements ActionListener {
    private BufferedImage bckground;

    public void initBoard(Graphics g,SpaceShip winner) {
        try {
            bckground = ImageIO.read(new FileImageInputStream(new File("finish.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics2D g2d= (Graphics2D) g;
        setFocusable(true);
        g2d.drawImage(bckground,0,0,800,600, this);
        Font fnt1= new Font("arial", Font.BOLD,30);
        g.setColor(Color.red);
        g.setFont(fnt1);
        g.drawString("TEBRİKLER KAZANAN  ",220,220);
        g.drawString(winner.getName(),320,260);
        g.drawString("HARCANAN ATEŞ = "+winner.getSumFire(),220,300);
        g.drawString("VURULAN HEDEF = "+winner.getSumHit(),220,340);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}