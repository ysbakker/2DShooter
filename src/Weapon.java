import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PImage;

import java.util.ArrayList;

public class Weapon extends SpriteObject implements IAlarmListener {
    private ShooterApp world;

    // Player owner is de speler die het wapen 'vast' heeft
    // Het wapen kan een projectiel afvuren wat gespawned wordt op de huidige locatie van de speler
    private Player owner;
    protected String particlefn;

    private int[] firingDirection = new int[2]; // [x, y]
    private boolean canFire = true;
    protected boolean autoFire;
    protected double autoFireDelay;
    protected int magSize;
    protected int damage;

    protected int particleSpeed;
    protected float particleSpawnLocationX;
    protected float particleSpawnLocationY;
    protected float weaponSpawnLocationX;
    protected float weaponSpawnLocationY;

    protected float particleOffsetX;
    protected float particleOffsetY;
    protected float weaponOffsetX;
    protected float weaponOffsetY;

    public Weapon(ShooterApp world, Player owner) {
        super(new Sprite("media/empty.png"));
        this.world = world;
        this.owner = owner;
    }

    public Weapon(ShooterApp world, Player owner, String weaponfn) {
        super(new Sprite(weaponfn));
        this.world = world;
        this.owner = owner;
    }

    @Override
    public void update() {
        updateFiringDirection();
        updateWeaponPosition();



    }

    private void updateWeaponPosition() {

        particleSpawnLocationX = owner.getX() + particleOffsetX;
        particleSpawnLocationY = owner.getY() + particleOffsetY;
        weaponSpawnLocationX = owner.getX() + weaponOffsetX;
        weaponSpawnLocationY = owner.getY() + weaponOffsetY;

        setX(weaponSpawnLocationX);
        setY(weaponSpawnLocationY);
        setZ(owner.getZ() + 1);
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
            world.addGameObject(new Particle(world, this, particlefn, particleSpawnLocationX, particleSpawnLocationY, firingDirection, particleSpeed));
            if (autoFire) {
                addParticleAlarm();
            }
            canFire = false;
        }
    }

    public void addParticleAlarm() {
        Alarm nextParticle = new Alarm("Next particle", autoFireDelay);
        nextParticle.addTarget(this);
        nextParticle.start();
    }

    @Override
    public void triggerAlarm(String s) {
        canFire = true;
    }

    public void setCanFire(boolean val) {
        canFire = val;
    }

    public boolean getAutoFire() {
        return autoFire;
    }

    public int getDamage() {
        return damage;
    }
}
