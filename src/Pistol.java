import nl.han.ica.oopg.sound.Sound;

public class Pistol extends Weapon {

    public Pistol(ShooterApp world, Player owner) {
        super(world, owner, "media/sprite_pistol.png");
        autoFire = true;
        fireDelay = 0.5;
        magSize = 0;
        particlefn = "media/sprite_bullet.png";
        damage = 10;
        particleSpeed = 10;

        particleOffsetX = 42;
        particleOffsetY = 25;
        weaponOffsetX = 42;
        weaponOffsetY = 25;
        weaponSound = new Sound(world, "media/weapon1.mp3");
    }
}
