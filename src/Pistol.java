public class Pistol extends Weapon {

    public Pistol(ShooterApp world, Player owner) {
        super(world, owner, "media/sprite_pistol.png");
        autoFire = false;
        autoFireDelay = 0.3;
        magSize = 0;
        particlefn = "media/sprite_bullet.png";
        damage = 10;
        particleSpeed = 10;

        particleOffsetX = 42;
        particleOffsetY = 25;
        weaponOffsetX = 42;
        weaponOffsetY = 25;
    }
}
