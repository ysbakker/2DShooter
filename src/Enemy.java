import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Enemy extends AnimatedSpriteObject {

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
}
