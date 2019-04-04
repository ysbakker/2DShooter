import nl.han.ica.oopg.objects.Sprite;

public class Orc extends Enemy {
    /** Enemy -> Orc
     * Maakt nieuwe orc aan
     *
     * @param world huidige wereld
     */
    public Orc(ShooterApp world) {
        super(world, new Sprite("media/sprite_orc.png"), 9);

        walkingSpeed = 1;
        maxHealth = 100;
        currentHealth = maxHealth;
        walkingSpeed = world.random(walkingSpeed-0.3F, walkingSpeed+0.3F);
        attackDamage = 1.5F;
        setxSpeed(-walkingSpeed);
    }


}
