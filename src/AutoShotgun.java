import nl.han.ica.oopg.sound.Sound;

public class AutoShotgun extends Weapon {
    /**
     * @param world huidige world
     * @param owner eigenaar wapen
     */
    public AutoShotgun(ShooterApp world, Player owner) {
        super(world, owner, "media/sprite_auto_shotgun.png");
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

        weaponSound = new Sound(world, "media/weapon2.mp3");
    }

    @Override
    public void fire() {
        if (autoFire && canFire) {
            Particle middleBullet = new Particle(world, this, particlefn, particleSpawnLocationX, particleSpawnLocationY, firingDirection, particleSpeed, particleSpeed);
            world.addGameObject(middleBullet);
            weaponSound.rewind();
            weaponSound.play();
            Particle topBullet = new Particle(world, this, particlefn, particleSpawnLocationX, particleSpawnLocationY, firingDirection, particleSpeed,  particleSpeed);
            Particle bottomBullet = new Particle(world, this, particlefn, particleSpawnLocationX, particleSpawnLocationY, firingDirection, particleSpeed, particleSpeed);

            float middleBulletxSpeed = middleBullet.getxSpeed();
            float middleBulletySpeed = middleBullet.getySpeed();

            float bulletSpread = 1.5f;

            // middle bullet travels horizontically
            if(middleBulletySpeed == 0) {
                // setting the velocities for the spreading bullets
                topBullet.setySpeed(-bulletSpread);
                bottomBullet.setySpeed(bulletSpread);
                if(middleBulletxSpeed < 0) {
                    // setting the rotation for the particle sprite
                    topBullet.setRotation(180);
                    middleBullet.setRotation(180);
                    bottomBullet.setRotation(180);
                }
            }

            // middle bullet travels vertically
            else if(middleBulletxSpeed == 0 ) {
                // setting the velocities for the spreading bullets
                topBullet.setxSpeed(-bulletSpread);
                bottomBullet.setxSpeed(bulletSpread);

                // setting the rotation for the particle sprite
                topBullet.setRotation(90);
                middleBullet.setRotation(90);
                bottomBullet.setRotation(90);
            }

            // middle bullet travels diagonally
            else if(middleBulletxSpeed != 0 && middleBulletySpeed != 0) {
                // upwards and right
                if(middleBulletxSpeed > 0 && middleBulletySpeed < 0) {
                    // setting the velocities for the spreading bullets
                    topBullet.setxSpeed(middleBulletxSpeed - bulletSpread);
                    topBullet.setySpeed(middleBulletySpeed);
                    bottomBullet.setxSpeed(middleBulletxSpeed);
                    bottomBullet.setySpeed(middleBulletySpeed + bulletSpread);

                    // setting the rotation for the particle sprite
                    topBullet.setRotation(315);
                    middleBullet.setRotation(315);
                    bottomBullet.setRotation(315);
                }
                // upwards and left
                if(middleBulletxSpeed < 0 && middleBulletySpeed < 0) {
                    // setting the velocities for the spreading bullets
                    topBullet.setxSpeed(middleBulletxSpeed + bulletSpread);
                    topBullet.setySpeed(middleBulletySpeed);
                    bottomBullet.setxSpeed(middleBulletxSpeed);
                    bottomBullet.setySpeed(middleBulletySpeed + bulletSpread);

                    // setting the rotation for the particle sprite
                    topBullet.setRotation(225);
                    middleBullet.setRotation(225);
                    bottomBullet.setRotation(225);
                }
                // downwards and left
                if(middleBulletxSpeed < 0 && middleBulletySpeed > 0) {
                    // setting the velocities for the spreading bullets
                    topBullet.setxSpeed(middleBulletxSpeed);
                    topBullet.setySpeed(middleBulletySpeed - bulletSpread);
                    bottomBullet.setxSpeed(middleBulletxSpeed + bulletSpread);
                    bottomBullet.setySpeed(middleBulletySpeed);

                    // setting the rotation for the particle sprite
                    topBullet.setRotation(135);
                    middleBullet.setRotation(135);
                    bottomBullet.setRotation(135);
                }
                // downwards and right
                if(middleBulletxSpeed > 0 && middleBulletySpeed > 0) {
                    // settting the velocities for the spreading bullets
                    topBullet.setxSpeed(middleBulletxSpeed);
                    topBullet.setySpeed(middleBulletySpeed - bulletSpread);
                    bottomBullet.setxSpeed(middleBulletxSpeed - bulletSpread);
                    bottomBullet.setySpeed(middleBulletySpeed);

                    // setting the rotation for the particle sprite
                    topBullet.setRotation(45);
                    middleBullet.setRotation(45);
                    bottomBullet.setRotation(45);
                }
            }

            world.addGameObject(topBullet);
            world.addGameObject(bottomBullet);

            addParticleAlarm();
            canFire = false;
        }
    }

}
