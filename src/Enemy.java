import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public abstract class Enemy extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private ShooterApp world;
    private int currentFrame;
    protected int walkingSpeed = -2;

    public Enemy(ShooterApp world, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.world = world;
        setxSpeed(walkingSpeed);
        currentFrame = 0;
    }

    public abstract void attack();

    @Override
    public void update() {
        if (getX() + getWidth() <= 0) {
            world.deleteGameObject(this);
            System.out.println("Enemy despawned.");
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
                System.out.println("Enemy died.");
                world.deleteGameObject(this);
            }
            if (g instanceof Player) {
                System.out.println("Enemy hit player!");
            }
        }
    }
}
