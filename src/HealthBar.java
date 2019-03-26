import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class HealthBar extends GameObject {
    private float maxHealth;
    private float currentHealth;
    private Enemy owner;


    public HealthBar(Enemy owner, ShooterApp world) {
        this.owner = owner;
        maxHealth = owner.getMaxHealth();
        currentHealth = owner.getCurrentHealth();
        world.addGameObject(this);
    }

    @Override
    public void draw(PGraphics g) {
        float missingHealthBarLength = (owner.getWidth()/2);
        float missingHealthBarX = owner.getCenterX() - missingHealthBarLength / 4;
        float missingHealthBarY = owner.getCenterY() - owner.getHeight() / 2;
        float missingHealthBarWidth = 4;
        g.fill(214, 17, 17);
        g.rect(missingHealthBarX, missingHealthBarY, missingHealthBarLength, missingHealthBarWidth);

        float currentHealthBarLength = missingHealthBarLength * (currentHealth / maxHealth);
        float currentHealthBarX = missingHealthBarX;
        float currentHealthBarY = missingHealthBarY;
        float currentHealthBarWidth = missingHealthBarWidth;
        g.fill(36, 214, 17);
        g.rect(currentHealthBarX, currentHealthBarY, currentHealthBarLength, currentHealthBarWidth);


    }

    @Override
    public void update() {
        maxHealth = owner.getMaxHealth();
        currentHealth = owner.getCurrentHealth();
    }
}
