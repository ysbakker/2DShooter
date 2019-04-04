import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.List;

public class Fortress extends GameObject implements ICollidableWithGameObjects, EntityWithHealth{
    private ShooterApp world;
    private float currentHealth;
    private float maxHealth;
    private HealthBar healthBar;
    private PImage fortressWall;
    private PImage treasureChest;
    private PImage goldPile;
    private PImage diamond;

    public Fortress(ShooterApp world) {
        this.world = world;
        currentHealth = 1000;
        maxHealth = 1000;
        setX(0);
        setY(0);
        setHeight(world.getHeight());
        setWidth(110);

        healthBar = new HealthBar(this, world);
        healthBar.setHealthBarPosition(10,8, 80, 15);

        fortressWall = world.loadImage("media/sprite_fortress_wall.png");
        treasureChest = world.loadImage("media/sprite_treasure_chest.png");
        goldPile = world.loadImage("media/sprite_gold.png");
        diamond = world.loadImage("media/sprite_diamond.png");
        world.addGameObject(this);
    }

    @Override
    public void draw(PGraphics g) {
        g.image(fortressWall, 100, 30);
        g.image(treasureChest, 30, 50);
        g.image(treasureChest, 30, 100);
        g.image(treasureChest, 30, world.getHeight() - 100);
        g.image(treasureChest, 30, world.getHeight() - 150);
        g.image(goldPile, 25, 160);
        g.image(goldPile, 25, 290);
        g.image(diamond, 30, 220);



    }

    @Override
    public void update() {

    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if(g instanceof Enemy) {

            }
        }
    }

    public void setCurrentHealth(float health) {
        currentHealth = health;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public boolean isDestroyed() {
        return currentHealth <= 0;
    }
}
