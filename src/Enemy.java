import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

import java.util.List;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithGameObjects, EntityWithHealth{
    private ShooterApp world;
    private int currentFrame;
    protected float maxHealth;
    protected float currentHealth;
    protected HealthBar healthBar;
    protected float attackDamage;
    protected float walkingSpeed;
    private boolean living;
    private float previousX;
    private int despawnCounter = 100;
    private Sound hitSound;
    private Sound deathSound;
    private Sound spawnSound;


    public Enemy(ShooterApp world, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.world = world;
        healthBar = new HealthBar(this, world);
        currentFrame = 1;
        living = true;
        previousX = world.getWorldBoundaries()[2];
        hitSound = new Sound(world, "media/enemy_loses_hp.mp3");
        deathSound = new Sound(world, "media/enemy_dies.mp3");
        spawnSound = new Sound(world, "media/enemy_spawns.mp3");
        spawnSound.play();
    }

    public void attack(GameObject g) {
        if(g instanceof Fortress) {
            Fortress fortress = ((Fortress) g);
            if(fortress.getCurrentHealth() > 0) {
                fortress.setCurrentHealth(fortress.getCurrentHealth() - attackDamage);
            }
        }
    }

    @Override
    public void update() {
        if (getX() + getWidth() <= world.getWorldBoundaries()[0]) {
            this.living = false;
        }

        if(currentHealth <= 0 && living){
            deathSound.play();
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
                hitSound.rewind();
                hitSound.play();
            }
            if (g instanceof Player && this.living) {
                // Enemy hit player
            }
            if (g instanceof Fortress && this.living) {
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
