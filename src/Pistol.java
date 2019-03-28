public class Pistol extends Weapon {

    public Pistol(ShooterApp world, Player owner) {
        super(world, owner, "media/ak47.png");
        autoFire = true;
        autoFireDelay = 0.3;
        magSize = 0;
        particlefn = "media/sprite_rock.png";
        damage = 10;

    }
}
