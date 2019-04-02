import nl.han.ica.oopg.objects.Sprite;

public class SkeletonFlame extends Enemy {
    public SkeletonFlame(ShooterApp world) {
        super(world, new Sprite("media/sprite_skeleton_flame.png"), 9);

        walkingSpeed = 3;
        maxHealth = 50;
        currentHealth = maxHealth;
        walkingSpeed = world.random(walkingSpeed-0.3F, walkingSpeed+0.3F);
        setxSpeed(-walkingSpeed);
    }

}
