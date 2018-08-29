import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;
    private Tree tree;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/world/world1.txt");
        handler.setWorld(world);
        //player = new Player(handler,100, 100);
        //tree = new Tree(handler, 100, 200);

        //game.getGameCamera().move(0, 0);
    }

    @Override
    public void tick(){
        world.tick();
        //player.tick();
        //tree.tick();

    }

    @Override
    public void render(Graphics g){
        world.render(g);
        //player.render(g);
        //tree.render(g);
        //Tile.tiles[2].render(g, 0, 0);

    }



}
