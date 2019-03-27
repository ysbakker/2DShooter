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
    private boolean living;
    protected float walkingSpeed = 2;
    private float previousX;

    public Enemy(ShooterApp world, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.world = world;
        walkingSpeed = world.random(walkingSpeed-0.8F, walkingSpeed+0.8F);
        setxSpeed(-walkingSpeed);
        healthBar = new HealthBar(this, world);
        currentFrame = 1;
        living = true;
        previousX = world.getWorldBoundaries()[2];
    }

    public abstract void attack();

    @Override
    public void update() {
        if (getX() + getWidth() <= world.getWorldBoundaries()[0]) {
            world.deleteGameObject(this);
            world.deleteGameObject(healthBar);
            this.living = false;
        }

        if(currentHealth == 0) {
            world.deleteGameObject(this);
            world.deleteGameObject(healthBar);
            this.living = false;
        }

        if (getX() < previousX - walkingSpeed*3) {
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
            if (g instanceof Particle) {
                Weapon weapon = ((Particle) g).getWeapon();
                currentHealth -= weapon.getDamage();
            }
            if (g instanceof Player) {
                // Enemy hit player
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
