/**
 * EntityWithHealth wordt gebruikt om de enemy en fortress te generaliseren. Deze entities hebben beide een HealthBar.
 */
public interface EntityWithHealth {
    float getCurrentHealth();
    float getMaxHealth();
}
