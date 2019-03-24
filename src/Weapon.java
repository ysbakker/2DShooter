import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.ArrayList;

public class Weapon extends SpriteObject implements IAlarmListener {
    private ShooterApp world;

    // Player owner is de speler die het wapen 'vast' heeft
    // Het wapen kan een projectiel afvuren wat gespawned wordt op de huidige locatie van de speler
    protected Player owner;
    protected int magSize;
    protected String particlefn;
    protected int[] firingDirection = new int[2]; // [x, y]

    private boolean canFire = true;

    public Weapon(ShooterApp world, Player owner, int magSize, String particlefn) {
        super(new Sprite("media/empty.png"));
        this.world = world;
        this.owner = owner;
        this.magSize = magSize;
        this.particlefn = particlefn;
    }

    public Weapon(ShooterApp world, Player owner, int magSize, String particlefn, String weaponfn) {
        super(new Sprite(weaponfn));
        this.world = world;
        this.owner = owner;
        this.magSize = magSize;
        this.particlefn = particlefn;
    }

    @Override
    public void update() {
        updateFiringDirection();
    }

    public void updateFiringDirection() {
        if (!owner.isWalking()) {
            if (owner.getFacingDirection()[0] == -1) {
                // Speler kijkt naar links
                firingDirection[0] = -1;
                firingDirection[1] = 0;
            } else {
                // Speler kijkt niet naar links, dus schiet naar rechts
                firingDirection[0] = 1;
                firingDirection[1] = 0;
            }
        } else {
            firingDirection[0] = owner.getFacingDirection()[0];
            firingDirection[1] = owner.getFacingDirection()[1];
        }
    }

    public void fire() {
        if (canFire) {
            world.addGameObject(new Particle(world, particlefn, owner.getX(), owner.getY() + owner.getHeight() / 2, firingDirection));
            addParticleAlarm();
            canFire = false;
        }
    }

    public void addParticleAlarm() {
        Alarm nextParticle = new Alarm("Next particle", .3);
        nextParticle.addTarget(this);
        nextParticle.start();
    }

    @Override
    public void triggerAlarm(String s) {
        canFire = true;
    }
}
