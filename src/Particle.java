import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public class Particle extends SpriteObject implements ICollidableWithGameObjects {
    private ShooterApp world;
    private Weapon weapon;



    public Particle(ShooterApp world, Weapon weapon, String filename, float xspawn, float yspawn, int[] direction, int particleSpeedX, int particleSpeedY) {
        super(new Sprite(filename));
        this.weapon = weapon;
        this.world = world;
        setX(xspawn);
        setY(yspawn);
        setxSpeed(direction[0] * particleSpeedX);
        setySpeed(direction[1] * particleSpeedY);
    }

    public void update() {
        if (this.isParticleOutOfBounds(world.getView().getWorldWidth(), world.getView().getWorldHeight())) {
            world.deleteGameObject(this);
        }
    }

    public boolean isParticleOutOfBounds(float xmax, float ymax) {
        return getX() >= xmax || getY() >= ymax || getX() + getWidth() < 0 || getY() + getHeight() < 0;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for(GameObject g : collidedGameObjects) {
            if(g instanceof Enemy && ((Enemy) g).getLiving()){
                world.deleteGameObject(this);
            }
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
