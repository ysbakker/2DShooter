import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class Skeleton extends Enemy {

    /** Enemy -> Skeleton
     * Maakt nieuwe skeleton aan
     *
     * @param world huidige wereld
     */
    public Skeleton(ShooterApp world) {
        super(world, new Sprite("media/sprite_skeleton.png"), 9);

        walkingSpeed = 3;
        maxHealth = 10;
        currentHealth = maxHealth;
        walkingSpeed = world.random(walkingSpeed-0.3F, walkingSpeed+0.3F);
        attackDamage = 0.1F;
        setxSpeed(-walkingSpeed);
    }


}
