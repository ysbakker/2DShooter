import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Enemy extends AnimatedSpriteObject {

    private final int size;
    private ShooterApp world;

    private int currentFrame;

    public Enemy(ShooterApp world, Sprite sprite, int totalFrames) {
        super(sprite, totalFrames);
        this.world = world;
        setFriction(0);
        setxSpeed(10);
        size = 50;
        currentFrame = 0;
    }

    public abstract void attack();

    @Override
    public void update(){
        if (getX() <= size) {
            world.deleteGameObject(this);
            System.out.println("Enemy despawned.");
        }
        setCurrentFrameIndex(currentFrame);
    }
}
