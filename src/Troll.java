import nl.han.ica.oopg.objects.Sprite;

public class Troll extends Enemy {
    public Troll(ShooterApp world) {
        super(world, new Sprite("media/sprite_troll.png"), 9);

        walkingSpeed = 5;
        maxHealth = 15;
        currentHealth = maxHealth;
        walkingSpeed = world.random(walkingSpeed-0.3F, walkingSpeed+0.3F);
        attackDamage = 0.5F;
        setxSpeed(-walkingSpeed);
    }


}
