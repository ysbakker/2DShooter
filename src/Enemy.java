import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private ShooterApp world;
    private int currentFrame;
    protected int walkingSpeed = 2;

    private float previousX;

    public Enemy(ShooterApp world, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.world = world;
        setxSpeed(-walkingSpeed);
        currentFrame = 1;
        previousX = world.getWorldWidth();
    }

    public abstract void attack();

    @Override
    public void update() {
        if (getX() + getWidth() <= 0) {
            world.deleteGameObject(this);
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
                world.deleteGameObject(this);
            }
            if (g instanceof Player) {
                // Enemy hit player
            }
        }
    }
}
