import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private ShooterApp world;
    private int currentFrame;
    protected int walkingSpeed = 2;
    protected float maxHealth;
    protected float currentHealth;
    protected HealthBar healthBar;


    public Enemy(ShooterApp world, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.world = world;
        setxSpeed(-walkingSpeed);
        currentFrame = 0;
        healthBar = new HealthBar(this, world);
    }

    public abstract void attack();

    @Override
    public void update() {
        if (getX() + getWidth() <= 0) {
            world.deleteGameObject(this);
            world.deleteGameObject(healthBar);

        }

        if(currentHealth == 0) {
            world.deleteGameObject(this);
            world.deleteGameObject(healthBar);
        }
        loopFrames();
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
}
