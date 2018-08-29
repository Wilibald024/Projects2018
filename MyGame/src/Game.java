import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game  implements Runnable{

    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;


    private BufferStrategy bs;
    private Graphics g;

    //STATES
    public State gameState;
    public State menuState;

    //INPUT
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //CAMERA
    private GameCamera gameCamera;

    //HANDLER
    private Handler handler;

    //private BufferedImage testImage;
    //private SpriteSheet sheet;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        handler = new Handler(this);

        gameCamera = new GameCamera(handler, 0, 0);


        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);

        //testImage = ImageLoader.loadImage("/textures/sheet.png");
        //sheet = new SpriteSheet(testImage);
    }


    private void tick(){

        keyManager.tick();

        if(State.getState() != null)
            State.getState().tick();
    }


    private void render() {

        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0,0, width, height);
        //Draw here!

        if(State.getState() != null)
            State.getState().render(g);


        //g.drawImage(Assets.tree, 10, 10, null);


        //g.drawImage(sheet.crop(32, 0, 32, 32),20,20,null);


        /*
        g.fillRect(0,0, width, height);
        g.setColor(Color.red);
        g.drawRect(10,50,50,70);
        g.setColor(Color.black);
        g.fillRect(15,40,60,70);
        g.setColor(Color.red);
        g.drawRect(10,50,50,70);
        */


        //End drawing!
        bs.show();
        g.dispose();


    }

    public void run(){

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick; //10.
            timer += now - lastTime;
            lastTime = now;


            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }


    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;

        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
