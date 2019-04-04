import nl.han.ica.oopg.sound.Sound;

public class Rock extends Weapon {
    /** Weapon -> Rock
     * Maakt nieuwe Rock aan
     *
     * @param world huidige wereld
     * @param owner eigenaar wapen
     */
    public Rock(ShooterApp world, Player owner) {
        super(world, owner);
        autoFire = false;
        fireDelay = 0.2;
        magSize = 0;
        particlefn = "media/sprite_rock.png";
        damage = 5;
        particleSpeed = 10;
        weaponSound = new Sound(world, "media/weapon.mp3");
    }
}
