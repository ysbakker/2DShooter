import nl.han.ica.oopg.objects.Sprite;

public class Skeleton extends Enemy {

    public Skeleton(ShooterApp world) {
        super(world, new Sprite("media/sprite_skeleton.png"), 9);

        walkingSpeed = 2;
        maxHealth = 10;
        currentHealth = 10;
    }

    @Override
    public void attack() {

    }
}
