import java.awt.*;

public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManaget().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 200, 196, 64, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManaget().setUiManager(null);
                State.setState(handler.getGame().gameState);

            }
        }));

    }

    @Override
    public void tick(){
        uiManager.tick();
        //System.out.println(handler.getMouseManaget().getMouseX() + "   " + handler.getMouseManaget().getMouseY());
        //if (handler.getMouseManaget().isLeftPressed() && handler.getMouseManaget().isRightPressed())
        //    State.setState(handler.getGame().gameState);

    }

    @Override
    public void render(Graphics g){
        uiManager.render(g);
        //g.setColor(Color.RED);
       //g.fillRect(handler.getMouseManaget().getMouseX(), handler.getMouseManaget().getMouseY(), 8, 8);

    }

}
