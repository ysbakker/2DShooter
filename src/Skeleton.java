import nl.han.ica.oopg.objects.Sprite;

public class Skeleton extends Enemy {
    public Skeleton(ShooterApp world) {
        super(world, new Sprite("media/sprite_human.png"), 16);
        System.out.println("skeleton spawned.");
    }

    @Override
    public void attack() {

    }

    @Override
    public void update() {
        super.update();
    }
}
