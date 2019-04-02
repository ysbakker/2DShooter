import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithGameObjects {
    private ShooterApp world;
    private int currentFrame;
    protected float maxHealth;
    protected float currentHealth;
    protected HealthBar healthBar;
    protected int attackDamage;
    protected float walkingSpeed;
    private boolean living;
    private float previousX;
    private int despawnCounter = 100;


    public Enemy(ShooterApp world, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.world = world;
        healthBar = new HealthBar(this, world);
        currentFrame = 1;
        living = true;
        previousX = world.getWorldBoundaries()[2];
    }

    public void attack(GameObject g) {
        if(g instanceof Fortress) {
            Fortress fortress = ((Fortress) g);
            fortress.setFortressHealth(fortress.getFortressHealth() - attackDamage);
        }
    }

    @Override
    public void update() {
        if (getX() + getWidth() <= world.getWorldBoundaries()[0] || currentHealth <= 0) {
            this.living = false;
        }

        if (!living) {
            setxSpeed(0);
            currentFrame = 0;
            world.deleteGameObject(healthBar);
            if (despawnCounter <= 0) {
                world.deleteGameObject(this);
            } else {
                despawnCounter--;
            }
        }

        if (getX() < previousX - 10) {
            loopFrames();
            previousX = getX();
        }
        setCurrentFrameIndex(currentFrame);

    }

    public void loopFrames() {
        if(currentFrame >= 7) {
            currentFrame = 1;
        } else {
            currentFrame++;
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Particle && this.living) {
                Weapon weapon = ((Particle) g).getWeapon();
                currentHealth -= weapon.getDamage();
            }
            if (g instanceof Player && this.living) {
                // Enemy hit player
            }
            if (g instanceof Fortress) {
                setxSpeed(0);
                attack(g);
            }
        }
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }

    public boolean getLiving() {
        return living;
    }
}
