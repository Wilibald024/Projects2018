import java.awt.image.BufferedImage;

public class Animation {

    private int speed, index;
    private boolean sequenceOrder = true;
    private BufferedImage[] frames;
    private long lastTime, timer;


    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();


    }

    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            if (sequenceOrder) {
                index++;
            }
            else if (!sequenceOrder){
                index--;
            }

            timer = 0;
            if (index >= (frames.length) - 1) {
                sequenceOrder = false;
            }
            if (index <= 0) {
                sequenceOrder = true;
            }

        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}
