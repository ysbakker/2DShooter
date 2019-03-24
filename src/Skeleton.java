import nl.han.ica.oopg.objects.Sprite;

public class Skeleton extends Enemy {

    public Skeleton(ShooterApp world) {
        super(world, new Sprite("media/sprite_skeleton.png"), 9);
        System.out.println("skeleton spawned.");

        walkingSpeed = -2;
    }

    @Override
    public void attack() {

    }
}
