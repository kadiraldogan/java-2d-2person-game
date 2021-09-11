package AlwaysReady;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpaceShip extends Sprite implements ActionListener {
    private int dy;
    private int dx;
    private int x = 20;
    private int y = 20;
    private ArrayList fires ;
    private ArrayList enemys ;
    private int sumFire=0;
    private int sumHit=0;
    private int syc=0;
    private Timer timer;
    private String name;

    public SpaceShip(int x, int y, String n){
        super(x,y);
        name=n;
        loadImage();
        getImageDimensions();
    }
    private void loadImage() {
        timer = new Timer(1000,this);
        timer.start();
        fires = new ArrayList();
        enemys = new ArrayList();
        loadImage("ship.png");
    }
    public void move() {
        y+=dy;
        x+=dx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        syc++;
    }

    public void enemy(int sayi){
        if(syc==1)
        {
            enemys.add(new Enemy(x,y,sayi));
            syc=0;
        }

    }
    public void fire() {
        fires.add(new Fire(x + width-10, y + (height / 2)-4));
        setSumFire(1);

    }

    //Tuş dinleme olayları.
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT | key == KeyEvent.VK_A) {
            dx = -2;
        }
        if (key == KeyEvent.VK_RIGHT | key == KeyEvent.VK_D) {
            dx = 2;
        }
        if (key == KeyEvent.VK_UP | key == KeyEvent.VK_W) {
            dy=-2;
        }
        if (key == KeyEvent.VK_DOWN | key == KeyEvent.VK_S) {
            dy=2;
        }
        if(key == KeyEvent.VK_SPACE | key == KeyEvent.VK_P) {
            fire();
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT | key == KeyEvent.VK_A) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT | key == KeyEvent.VK_D) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP | key == KeyEvent.VK_W ) {
            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN | key == KeyEvent.VK_S ) {
            dy = 0;
        }
    }

    //Get/Set metodları.
    public int getX() {

        return x;
    }
    public void setX(int x)
    {
        this.x=x;
    }

    public ArrayList<Fire> getFires()
    {
        return fires;
    }

    public ArrayList<Enemy> getEnemys()
    {
        return enemys;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y=y;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public int getSumFire()
    {
        return sumFire;
    }
    public void setSumFire(int sumFire){

        this.sumFire+=sumFire;
    }

    public int getSumHit(){

        return sumHit;
    }
    public void setSumHit(int sumHit)
    {
        this.sumHit+=sumHit;
    }
    public String getName(){
        return name;
    }
}
