import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class HealthBar extends GameObject {
    private float maxHealth;
    private float currentHealth;
    private GameObject owner;

    float missingHealthBarLength;
    float missingHealthBarWidth;
    float missingHealthBarX;
    float missingHealthBarY;

    private boolean fixedPosition;


    /** Maakt een healthbar aan voor een eigenaar (EntityWithHealth)
     * @param owner eigenaar healthbar
     * @param world huidige wereld
     */
    public HealthBar(GameObject owner, ShooterApp world) {
        this.owner = owner;
        fixedPosition = false;
        world.addGameObject(this);
    }

    @Override
    public void draw(PGraphics g) {

        g.fill(214, 17, 17);
        g.rect(missingHealthBarX, missingHealthBarY, missingHealthBarLength, missingHealthBarWidth);

        float currentHealthBarLength = missingHealthBarLength * (currentHealth / maxHealth);
        g.fill(36, 214, 17);
        g.rect(missingHealthBarX, missingHealthBarY, currentHealthBarLength, missingHealthBarWidth);
    }

    @Override
    public void update() {
        maxHealth = ((EntityWithHealth) owner).getMaxHealth();
        currentHealth = ((EntityWithHealth) owner).getCurrentHealth();

        if(fixedPosition == false) {
            missingHealthBarLength = (owner.getWidth()/2);
            missingHealthBarX = owner.getCenterX() - missingHealthBarLength / 4;
            missingHealthBarY = owner.getCenterY() - owner.getHeight() / 2;
            missingHealthBarWidth = 4;
        }

    }

    /** Update de huidige positie van de HealthBar
     * @param x x-positie
     * @param y y-positie
     * @param width breedte
     * @param height hoogte
     */
    public void setHealthBarPosition(float x, float y, float width, float height) {
        missingHealthBarX = x;
        missingHealthBarY = y;
        missingHealthBarLength = width;
        missingHealthBarWidth = height;
        fixedPosition = true;
    }
}
