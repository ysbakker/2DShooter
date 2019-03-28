import nl.han.ica.oopg.objects.Sprite;

public class Troll extends Enemy {
    public Troll(ShooterApp world) {
        super(world, new Sprite("media/sprite_troll.png"), 9);

        walkingSpeed = 2;
        maxHealth = 10;
        currentHealth = maxHealth;
        walkingSpeed = world.random(walkingSpeed-0.3F, walkingSpeed+0.3F);
        setxSpeed(-walkingSpeed);
    }

    @Override
    public void attack() {

    }
}
