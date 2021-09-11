package AlwaysReady;

import java.util.Random;

public class Enemy extends Sprite{
    private Random rnd = new Random();
    private final int BOARD_WIDTH = 100;
    private final int ENEMY_SPEED = -1;


    public Enemy(int x, int y, int sayi){
        super(x,y);
        this.x=750;
        this.y=sayi+rnd.nextInt(200);
        loadImage();
    }
    public void loadImage(){
        loadImage("object.png");
        getImageDimensions();
    }

    public void move() {
        x += ENEMY_SPEED;
        if (x < BOARD_WIDTH) {
            vis = false;
        }
    }




        // Get/Set Metodları.
    public int getX(){
        return x;
    }

    public void setX(int x) {
        this.x=x;
    }

    public int getY(){
        return y;
    }
}
