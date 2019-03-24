import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public class Particle extends SpriteObject implements ICollidableWithGameObjects {
    float xspawn;
    float yspawn;
    int[] direction;

    public Particle(String filename, float xspawn, float yspawn, int[] direction) {
        super(new Sprite(filename));
        this.xspawn = xspawn;
        this.yspawn = yspawn;
        this.direction = direction;
        setX(xspawn);
        setY(yspawn);
        setxSpeed(direction[0] * 10);
        setySpeed(direction[1] * 10);
    }

    public void update() {

    }

    public boolean isParticleOutOfBounds(float xmax, float ymax) {
        return getX() >= xmax || getY() >= ymax || getX() + getWidth() < 0 || getY() + getHeight() < 0;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for(GameObject g : collidedGameObjects) {
            if(g instanceof Enemy){

            }
        }
    }
}
