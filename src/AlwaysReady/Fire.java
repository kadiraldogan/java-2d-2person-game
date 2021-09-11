package AlwaysReady;


public class Fire extends Sprite{

    private final int BOARD_WIDTH = 790;
    private final int FİRE_SPEED = 2;

    public Fire(int x, int y){
        super(x,y);
        loadImage();
    }
    public void loadImage(){
      loadImage("fire.png");
      getImageDimensions();
    }
    public void move() {

        x += FİRE_SPEED;

        if (x > BOARD_WIDTH) {
            vis = false;
        }
    }

}
