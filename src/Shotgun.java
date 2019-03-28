public class Shotgun extends Weapon {

    public Shotgun(ShooterApp world, Player owner) {
        super(world, owner, "media/sprite_shotgun.png");
        autoFire = false;
        fireDelay = 0.5;
        magSize = 0;
        particlefn = "media/sprite_bullet.png";
        damage = 20;
        particleSpeed = 10;

        particleOffsetX = 20;
        particleOffsetY = 25;
        weaponOffsetX = 20;
        weaponOffsetY = 30;
    }

    @Override
    public void fire() {
        if (!autoFire && canFire && shootingDelayPassed) {
            Particle middleBullet = new Particle(world, this, particlefn, particleSpawnLocationX, particleSpawnLocationY, firingDirection, particleSpeed, particleSpeed);
            world.addGameObject(middleBullet);
            if(middleBullet.getySpeed() == 0 ) {
                Particle topBullet = new Particle(world, this, particlefn, particleSpawnLocationX, particleSpawnLocationY, firingDirection, particleSpeed,  - 1);
                Particle bottomBullet = new Particle(world, this, particlefn, particleSpawnLocationX, particleSpawnLocationY, firingDirection, particleSpeed, 1);
                topBullet.setySpeed(-1.5f);
                bottomBullet.setySpeed(1.5f);
                world.addGameObject(topBullet);
                world.addGameObject(bottomBullet);
            }

            addParticleAlarm();
            canFire = false;
            shootingDelayPassed = false;
        }
    }
}
