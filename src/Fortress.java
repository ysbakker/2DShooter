import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Fortress extends GameObject {
    private ShooterApp world;
    private float x = 0;
    private float y = 0;

    public Fortress(ShooterApp world) {
        this.world = world;
        setX(x);
        setY(y);
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

    }


}
