import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Particle extends SpriteObject {
    ShooterApp world;

    float xspawn;
    float yspawn;
    int[] direction;

    public Particle(ShooterApp world, String filename, float xspawn, float yspawn, int[] direction) {
        super(new Sprite(filename));
        this.world = world;
        this.xspawn = xspawn;
        this.yspawn = yspawn;
        this.direction = direction;
        setX(xspawn);
        setY(yspawn);
        setxSpeed(direction[0] * 10);
        setySpeed(direction[1] * 10);
    }

    public void update() {
        if (this.isParticleOutOfBounds(world.getWorldWidth(), world.getWorldHeight())) {
            world.deleteGameObject(this);
        }
    }

    public boolean isParticleOutOfBounds(float xmax, float ymax) {
        return getX() >= xmax || getY() >= ymax || getX() + getWidth() < 0 || getY() + getHeight() < 0;
    }
}
