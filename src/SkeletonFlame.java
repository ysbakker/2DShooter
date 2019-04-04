import nl.han.ica.oopg.objects.Sprite;

public class SkeletonFlame extends Enemy {
    /** Enemy -> SkeletonFlame
     * Maakt nieuwe Flame Skeleton aan
     *
     * @param world huidige wereld
     */
    public SkeletonFlame(ShooterApp world) {
        super(world, new Sprite("media/sprite_skeleton_flame.png"), 9);

        walkingSpeed = 3;
        maxHealth = 50;
        currentHealth = maxHealth;
        walkingSpeed = world.random(walkingSpeed-0.3F, walkingSpeed+0.3F);
        attackDamage = 1;
        setxSpeed(-walkingSpeed);
    }

}
