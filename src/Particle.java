import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;
import processing.core.PGraphics;

public class Particle extends SpriteObject implements ICollidableWithGameObjects {
    private ShooterApp world;
    private Weapon weapon;
    private float rotationAngle = 0;


    public Particle(ShooterApp world, Weapon weapon, String filename, float xspawn, float yspawn, int[] direction, int particleSpeedX, int particleSpeedY) {
        super(new Sprite(filename));
        this.weapon = weapon;
        this.world = world;
        setX(xspawn);
        setY(yspawn);
        setxSpeed(direction[0] * particleSpeedX);
        setySpeed(direction[1] * particleSpeedY);
    }

    @Override
    public void draw(PGraphics g) {
        g.pushMatrix();
        g.translate(getCenterX(), getCenterY());
        g.rotate(world.radians(rotationAngle));
        g.image(getImage(), -width / 2, -height / 2);
        g.popMatrix();
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

    public void setRotation(float angle) {
        rotationAngle = angle;
    }
}
