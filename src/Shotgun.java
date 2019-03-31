public class Shotgun extends Weapon {

    public Shotgun(ShooterApp world, Player owner) {
        super(world, owner, "media/sprite_shotgun.png");
        autoFire = false;
        fireDelay = 0.5;
        magSize = 0;
        particlefn = "media/sprite_shotgun_shell.png";
        damage = 20;
        particleSpeed = 10;

        particleOffsetX = 47;
        particleOffsetY = 33;
        weaponOffsetX = 20;
        weaponOffsetY = 30;
    }

    @Override
    public void fire() {
        if (!autoFire && canFire && shootingDelayPassed) {
            Particle middleBullet = new Particle(world, this, particlefn, particleSpawnLocationX, particleSpawnLocationY, firingDirection, particleSpeed, particleSpeed);
            world.addGameObject(middleBullet);

            Particle topBullet = new Particle(world, this, particlefn, particleSpawnLocationX, particleSpawnLocationY, firingDirection, particleSpeed,  particleSpeed);
            Particle bottomBullet = new Particle(world, this, particlefn, particleSpawnLocationX, particleSpawnLocationY, firingDirection, particleSpeed, particleSpeed);

            float middleBulletxSpeed = middleBullet.getxSpeed();
            float middleBulletySpeed = middleBullet.getySpeed();

            float bulletSpread = 1.5f;

            // middle bullet travels horizontically
            if(middleBulletySpeed == 0 ) {
                topBullet.setySpeed(-bulletSpread);
                bottomBullet.setySpeed(bulletSpread);
            }

            // middle bullet travels vertically
            if(middleBulletxSpeed == 0 ) {
                topBullet.setxSpeed(-bulletSpread);
                bottomBullet.setxSpeed(bulletSpread);
            }

            // middle bullet travels diagonally
            if(middleBulletxSpeed != 0 && middleBulletySpeed != 0) {
                // upwards and right
                if(middleBulletxSpeed > 0 && middleBulletySpeed < 0) {
                    topBullet.setxSpeed(middleBullet.getxSpeed() - bulletSpread);
                    topBullet.setySpeed(middleBullet.getySpeed());
                    bottomBullet.setxSpeed(middleBullet.getxSpeed());
                    bottomBullet.setySpeed(middleBullet.getySpeed() + bulletSpread);
                }
            }



            world.addGameObject(topBullet);
            world.addGameObject(bottomBullet);

            addParticleAlarm();
            canFire = false;
            shootingDelayPassed = false;
        }
    }
}
