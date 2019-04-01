public class AutoShotgun extends Weapon {

    public AutoShotgun(ShooterApp world, Player owner) {
        super(world, owner, "media/sprite_auto_shotgun");
        autoFire = true;
        fireDelay = 0.2;
        magSize = 0;
        particlefn = "media/sprite_shotgun_shell.png";
        damage = 20;
        particleSpeed = 10;

        particleOffsetX = 47;
        particleOffsetY = 33;
        weaponOffsetX = 20;
        weaponOffsetY = 30;
    }


}
