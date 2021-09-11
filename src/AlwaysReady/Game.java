package AlwaysReady;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;


public class Game extends JPanel implements ActionListener {

    private Timer timer;
    private SpaceShip gamer1;
    private SpaceShip gamer2;
    private Menu menu = new Menu();
    private Finish finish = new Finish();
    private BufferedImage bckground;
    public static enum STATE{
        MENU,
        GAME,
    };
    public static STATE state = STATE.MENU;

    private final int DELAY = 10;
    public Game() {
        initBoard();

    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        addMouseListener(new MouseInput());
        setFocusable(true);
        setBackground(Color.BLACK);
        gamer1 = new SpaceShip(20,150,"COMPUTER");
        gamer2 = new SpaceShip(20,400,"KADİR");
        gamer1.setY(150);
        gamer2.setY(400);
        timer = new Timer(DELAY,this);
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        if(state==STATE.GAME)
        {
            try {
                bckground = ImageIO.read(new FileImageInputStream(new File("game.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2d.drawImage(bckground,0,0,800,600,this);
            g2d.drawImage(gamer1.getImage(),gamer1.getX(),gamer1.getY(),gamer1.width,gamer1.height,this);
            ArrayList ms = gamer1.getFires();
            for (java.lang.Object m1 : ms) {
                Fire m = (Fire) m1;
                g2d.drawImage(m.getImage(),m.getX(),m.getY(),m.width,m.height,this);
            }
            g2d.drawImage(gamer2.getImage(),gamer2.getX(),gamer2.getY(),gamer2.width,gamer2.height,this);
            ArrayList ms2 = gamer2.getFires();
            for (java.lang.Object m1 : ms2) {
                Fire m = (Fire) m1;
                g2d.drawImage(m.getImage(),m.getX(),m.getY(),m.width,m.height,this);
            }
            ArrayList es = gamer1.getEnemys();
            for (java.lang.Object m1 : es) {
                Enemy e = (Enemy) m1;
                g2d.drawImage(e.getImage(),e.getX(),e.getY(),e.width,e.height,this);
            }
            ArrayList es2 = gamer2.getEnemys();
            for (java.lang.Object m1 : es2) {
                Enemy e = (Enemy) m1;
                g2d.drawImage(e.getImage(),e.getX(),e.getY(),e.width,e.height,this);
            }

            Control(g2d);
        }
        else if(state==STATE.MENU){
            menu.initBoard(g);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        updateEnemys();
        updateFires();
        gamer1.enemy(0);
        gamer2.enemy(300);
        updateShip();
        repaint();
    }
    public void Control (Graphics2D g) {
        ArrayList fr = gamer1.getFires();
        ArrayList es = gamer1.getEnemys();
        for (int i = 0; i < fr.size(); i++) {
            Fire f = (Fire) fr.get(i);
            for (int j=0; j< es.size() ; j++)
            {
                Enemy e = (Enemy) es.get(j);
                if(new Rectangle(f.getX(),f.getY(),f.width,f.height).intersects(new Rectangle(e.getX(),e.getY(),e.width,e.height))){
                    fr.remove(i);
                    es.remove(j);
                    shot(gamer1,g);
                }

            }
        }

        ArrayList fr2 = gamer2.getFires();
        ArrayList es2 = gamer2.getEnemys();
        for (int i = 0; i < fr2.size(); i++) {
            Fire f = (Fire) fr2.get(i);
            for (int j=0; j< es2.size() ; j++)
            {
                Enemy e = (Enemy) es2.get(j);
                if(new Rectangle(f.getX(),f.getY(),f.width,f.height).intersects(new Rectangle(e.getX(),e.getY(),e.width,e.height))){
                    fr2.remove(i);
                    es2.remove(j);
                    shot(gamer2,g);
                }

            }
        }
        ArrayList en = gamer1.getEnemys();
        for (int i=0; i< en.size() ; i++)
        {
            Enemy e = (Enemy) en.get(i);
            if(new Rectangle(gamer1.getX(),gamer1.getY(),gamer1.width,gamer1.height).intersects(new Rectangle(e.getX(),e.getY(),e.width,e.height))){
                en.remove(i);
                gamer1.setSumHit(-1);

            }

        }
        ArrayList enm = gamer2.getEnemys();
        for (int i=0; i< es2.size() ; i++)
        {
            Enemy e = (Enemy) enm.get(i);
            if(new Rectangle(gamer2.getX(),gamer2.getY(),gamer2.width,gamer2.height).intersects(new Rectangle(e.getX(),e.getY(),e.width,e.height))){
                enm.remove(i);
                gamer2.setSumHit(-1);
            }

        }
    }

    public void shot(SpaceShip winner, Graphics2D g){
        winner.setSumHit(1);
        System.out.println(winner.getName()+" "+winner.getSumHit());
        if(winner.getSumHit()==10){
            gameOver(winner,g);
        }
    }
    public void gameOver(SpaceShip winner,Graphics2D g){
        timer.stop();
        finish.initBoard(g,winner);
    }

    private void updateEnemys() {
        ArrayList es = gamer1.getEnemys();
        for (int i = 0; i < es.size(); i++) {
            Enemy m = (Enemy) es.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                es.remove(i);
            }
        }
        ArrayList es2 = gamer2.getEnemys();
        for (int i = 0; i < es2.size(); i++) {
            Enemy m = (Enemy) es2.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                es2.remove(i);
            }
        }
    }



    private void updateFires() {
        ArrayList fr = gamer1.getFires();
        for (int i = 0; i < fr.size(); i++) {
            Fire m = (Fire) fr.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                fr.remove(i);
            }
        }
        ArrayList fr2 = gamer2.getFires();
        for (int i = 0; i < fr2.size(); i++) {
            Fire m = (Fire) fr2.get(i);
            if (m.isVisible()) {
                m.move();
            } else {
                fr2.remove(i);
            }
        }
    }

    private void updateShip() {
        //1.oyunun sınırlar içinde kalmasını sağlıyoruz
        if(gamer1.getY()<=0)
            gamer1.setY(0);
        if(gamer1.getY()>=220)
            gamer1.setY(220);
        if(gamer1.getX()<=20)
            gamer1.setX(20);
        if(gamer1.getX()>=200)
            gamer1.setX(200);
        gamer1.move();

        //2.oyuncunun sınırlar içinde kalmasını sağlıyoruz
        if(gamer2.getY()<=270)
            gamer2.setY(270);
        if(gamer2.getY()>=520)
            gamer2.setY(520);
        if(gamer2.getX()<=20)
            gamer2.setX(20);
        if(gamer2.getX()>=200)
            gamer2.setX(200);
        gamer2.move();
    }
    private class TAdapter extends KeyAdapter {
        //Tuşa basmayı bıraktığını dinlemek.
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D ) {
                gamer2.keyReleased(e);
            }
            if (key == KeyEvent.VK_UP | key == KeyEvent.VK_DOWN | key == KeyEvent.VK_LEFT | key == KeyEvent.VK_RIGHT ) {
                gamer1.keyReleased(e);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //Herhangibir tuşa basıldığını dinlemek.
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D || key== KeyEvent.VK_SPACE) {
                gamer2.keyPressed(e);
            }
            if (key == KeyEvent.VK_UP | key == KeyEvent.VK_DOWN | key == KeyEvent.VK_LEFT | key == KeyEvent.VK_RIGHT || key==KeyEvent.VK_P) {
                gamer1.keyPressed(e);
            }
        }
    }
}
