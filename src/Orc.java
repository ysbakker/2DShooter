import nl.han.ica.oopg.objects.Sprite;

public class Orc extends Enemy {
    public Orc(ShooterApp world) {
        super(world, new Sprite("media/sprite_orc.png"), 9);

        walkingSpeed = 1;
        maxHealth = 50;
        currentHealth = maxHealth;
        walkingSpeed = world.random(walkingSpeed-0.3F, walkingSpeed+0.3F);
        setxSpeed(-walkingSpeed);
    }

    @Override
    public void attack() {

    }
}