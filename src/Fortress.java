import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Fortress extends GameObject implements ICollidableWithGameObjects {
    private ShooterApp world;
    private int fortressHealth;

    public Fortress(ShooterApp world) {
        this.world = world;
        this.fortressHealth = 1000;
        setX(0);
        setY(0);
        setHeight(world.getHeight());
        setWidth(110);

        world.addGameObject(this);

    }

    @Override
    public void draw(PGraphics g) {
        g.rect(100, 0, 10, g.height);
    }

    @Override
    public void update() {
        System.out.println(fortressHealth);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if(g instanceof Enemy) {

            }
        }
    }

    public void setFortressHealth(int health) {
        this.fortressHealth = health;
    }

    public int getFortressHealth() {
        return fortressHealth;
    }


}
