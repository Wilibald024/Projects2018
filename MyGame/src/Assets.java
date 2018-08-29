import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static Font font28;

    public static BufferedImage dirt, grass, stone, tree;
    public static BufferedImage rock; //generator
    public static BufferedImage[] player_down, player_left, player_up, player_right;
    public static BufferedImage[] btn_start;
    public static BufferedImage wood;
    public static BufferedImage invenotryScreen;




    //GOING WITH 2 KEYS NOT WORKING

    public static BufferedImage[] player_AW;
    public static BufferedImage[] player_AS;
    public static BufferedImage[] player_DW;
    public static BufferedImage[] player_DS;

    public static int NUM_OF_ANIMATIONS = 5;

    public static void init() {

        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);

        invenotryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

        btn_start = new BufferedImage[2];

        SpriteSheet woodPic = new SpriteSheet(ImageLoader.loadImage("/textures/RTS_Crate.png"));

        SpriteSheet startPic = new SpriteSheet(ImageLoader.loadImage("/textures/start.png"));
        SpriteSheet startPicWow = new SpriteSheet(ImageLoader.loadImage("/textures/startWow.png"));

        btn_start[0] = startPic.crop(0, 0, width * 6, height * 2);
        btn_start[1] = startPicWow.crop(0, 0, width * 6, height * 2);

        SpriteSheet rockPic = new SpriteSheet(ImageLoader.loadImage("/textures/reactor.png"));

        SpriteSheet treePic = new SpriteSheet(ImageLoader.loadImage("/textures/tree.png"));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        SpriteSheet southDown = new SpriteSheet(ImageLoader.loadImage("/textures/playerSouth.png"));
        SpriteSheet westLeft = new SpriteSheet(ImageLoader.loadImage("/textures/playerWest.png"));
        SpriteSheet northUp = new SpriteSheet(ImageLoader.loadImage("/textures/playerNorth.png"));
        SpriteSheet eastRight = new SpriteSheet(ImageLoader.loadImage("/textures/playerEast.png"));
        SpriteSheet player_AWASDWDS = new SpriteSheet(ImageLoader.loadImage("/textures/player_die.png"));

        player_down = new BufferedImage[5];
        player_left = new BufferedImage[5];
        player_up = new BufferedImage[5];
        player_right = new BufferedImage[5];

        player_AW = new BufferedImage[5];
        player_AS = new BufferedImage[5];
        player_DW = new BufferedImage[5];
        player_DS = new BufferedImage[5];


        for (int h = 0; h < NUM_OF_ANIMATIONS; h++) {
            player_AW[h] = player_AWASDWDS.crop(7 * width, 0, width, height);
            player_AS[h] = player_AWASDWDS.crop(5 * width, 0, width, height);
            player_DW[h] = player_AWASDWDS.crop(width, 0, width, height);
            player_DS[h] = player_AWASDWDS.crop(3 * width, 0, width, height);
        }

        for (int i = 0; i < NUM_OF_ANIMATIONS; i++) {

            player_down[i] = southDown.crop(i * width, 0, width, height);
            player_left[i] = westLeft.crop(0, i * height, width, height);
            player_up[i] = northUp.crop(i * width, 0, width, height);
            player_right[i] = eastRight.crop(0, i * height, width, height);

        }

        rock = rockPic.crop(0, 0, 80, 80);
        wood = woodPic.crop(10, 10, 100, 100);

        grass = sheet.crop(32, 0, width, height);
        dirt = sheet.crop(64, 0, width, height);
        stone = sheet.crop(96, 0, width, height);
        tree = treePic.crop(0, 0, width, height);
    }
}
