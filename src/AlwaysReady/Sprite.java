package AlwaysReady;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected BufferedImage image;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        vis = true;
    }

    protected void loadImage(String imageName) {
        try {
            image = ImageIO.read(new FileImageInputStream(new File(imageName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void getImageDimensions() {

        width = image.getWidth(null)/21;
        height = image.getHeight(null)/29;
    }

    //GET&SET Metodları...
    public Image getImage()
    {
        return image;
    }

    public int getX() {

        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean isVisible() {

        return vis;
    }

    public void setVisible(Boolean visible)
    {
        vis = visible;
    }
}