public class Rock extends Weapon {
    public Rock(ShooterApp world, Player owner) {
        super(world, owner);
        autoFire = true;
        autoFireDelay = 0.3;
        magSize = 0;
        particlefn = "media/sprite_rock.png";
    }
}