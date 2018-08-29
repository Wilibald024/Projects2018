import javax.print.attribute.HashAttributeSet;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //ANIMATIONS
    private Animation animDown, animUp, animRight, animLeft;
    public static int position = 4;

    // Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    // Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //ANIMATIONS
        animDown = new Animation(360, Assets.player_down);
        animUp = new Animation(360, Assets.player_up);
        animRight = new Animation(360, Assets.player_right);
        animLeft = new Animation(360, Assets.player_left);

        inventory = new Inventory(handler);
    }

    @Override
    public void tick() {

        //Animations
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        //Movement

        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        // Attack
        checkAttacks();

        inventory.tick();


        /*
        if(game.getKeyManager().up){
            y -= 3;
        }
        if(game.getKeyManager().down){
            y += 3;
        }
        if(game.getKeyManager().left){
            x -= 3;
        }
        if(game.getKeyManager().right){
            x += 3;
        }
        */
    }

    private void checkAttacks() {

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }

        if(inventory.isActive())
            return;

        Rectangle cb = getCollisionBounds(0, 0); //cb collisionbounds
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().aUp) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        }else if (handler.getKeyManager().aDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }else if (handler.getKeyManager().aLeft) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / - arSize / 2;
        } else if (handler.getKeyManager().aRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0,0).intersects(ar)) {
                e.hurt(1);
                return;
            }
        }


    }

    @Override
    public void die() {
        System.out.println("You lose");
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if(inventory.isActive())
            return;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
        //       (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }

    public void postRender(Graphics g) {
        inventory.render(g);
    }

    public Inventory getInventory() {
        return inventory;
    }

    private BufferedImage getCurrentAnimationFrame() {

        //1=Left 2=Right 3=Up 4=Down

        if(xMove < 0){
            position = 1;
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            position = 2;
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            position = 3;
            return animUp.getCurrentFrame();
        } else if (xMove == 0 && yMove == 0) {
            if (position == 1) {
                return animLeft.getCurrentFrame();
            } else if (position == 2) {
                return animRight.getCurrentFrame();
            } else if (position == 3) {
                return animUp.getCurrentFrame();
            } else {
                return animDown.getCurrentFrame();
            }
        } else {
            position = 4;
            return animDown.getCurrentFrame();
        }



    }





}
