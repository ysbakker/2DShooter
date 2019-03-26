import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects {
    private final ShooterApp world;

    private float[] previousLocation = new float[2]; // [x, y]
    private int[] facingDirection = new int[2]; // [x, y]
    private int currentFrame;
    private final ArrayList<Key> keys = new ArrayList<>();
    private ArrayList<Key> keysPressed = new ArrayList<>();
    private Weapon currentWeapon;

    private final int walkingSpeed;

    public Player(ShooterApp world) {
        super(new Sprite("media/sprite_human.png"), 16);
        this.world = world;
        setFriction(0);

        walkingSpeed = 4;
        currentFrame = 0;
        facingDirection[0] = 1;
        facingDirection[1] = 0;

        currentWeapon = new Rock(world, this);
        world.addGameObject(currentWeapon);

        // Gebruikte toetsen
        keys.add(new Key('w'));
        keys.add(new Key('a'));
        keys.add(new Key('s'));
        keys.add(new Key('d'));
        keys.add(new Key(' '));
    }

    @Override
    public void update() {
        if (getX() <= 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() <= 10) {
            setySpeed(0);
            setY(10);
        }
        if (getX() >= world.width - getWidth()) {
            setxSpeed(0);
            setX(world.width - getWidth());
        }
        if (getY() >= world.height - getHeight()*2) {
            setySpeed(0);
            setY(world.height - getHeight()*2);
        }
      
        for (Key key : keys) {
            if (key.isPressed() && !keysPressed.contains(key)) {
                keysPressed.add(key);
            } else if (!key.isPressed() && keysPressed.contains(key)) {
                keysPressed.remove(key);
            }
        }
        // update frames van player sprite
        setCurrentFrameIndex(currentFrame);

        if (isWalking()) {
            updateDirection();
            if (ShooterApp.dist(previousLocation[0], previousLocation[1], getX(), getY()) > walkingSpeed * 2) {
                if (facingDirection[0] == -1) {
                    loopFramesLeft();
                } else {
                    loopFramesRight();
                }
                previousLocation[0] = getX();
                previousLocation[1] = getY();
            }
            movePlayer();
        } else {
            if (facingDirection[0] == -1) {
                currentFrame = 15;
            } else {
                currentFrame = 0;
            }
            stopPlayer();
        }

        if (isShooting()) {
            currentWeapon.fire();
        } else if (!isShooting() && !currentWeapon.getAutoFire()) {
            currentWeapon.setCanFire(true);
        }
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        for (Key keyp: keys) {
            if (key == keyp.getKeyCode()) {
                keyp.press();
            }
        }
    }

    public void keyReleased(int keyCode, char key) {
        for (Key keyr: keys) {
            if (key == keyr.getKeyCode()) {
                keyr.release();
            }
        }
    }

    private void updateDirection() {
        facingDirection[0] = 0;
        facingDirection[1] = 0;
        for (Key key: keysPressed) {
            if (key.getKeyCode() == 'a') {
                facingDirection[0] = -1;
            }
            if (key.getKeyCode() == 'w') {
                facingDirection[1] = -1;
            }
            if (key.getKeyCode() == 's') {
                facingDirection[1] = 1;
            }
            if (key.getKeyCode() == 'd') {
                facingDirection[0] = 1;
            }
        }
    }

    private void movePlayer() {
        setxSpeed(facingDirection[0] * walkingSpeed);
        setySpeed(facingDirection[1] * walkingSpeed);
    }

    private void stopPlayer() {
        setxSpeed(0);
        setySpeed(0);
    }

    public boolean isWalking() {
        for (Key key: keysPressed) {
            if (key.getKeyCode() == 'a' || key.getKeyCode() == 'w' || key.getKeyCode() == 's' || key.getKeyCode() == 'd') {
                return true;
            }
        }
        return false;
    }

    public boolean isShooting() {
        for (Key key: keysPressed) {
            if (key.getKeyCode() == ' ') {
                return true;
            }
        }
        return false;
    }

    public void loopFramesRight() {
        if(currentFrame >= 7) {
            currentFrame = 0;
        } else {
            currentFrame++;
        }
    }
  
    private void loopFramesLeft() {
        if(currentFrame < 8) {
            currentFrame = 8;
        }
        if(currentFrame == 15 ) {
            currentFrame = 8;
        } else {
            currentFrame ++;
        }
    }

    public int[] getFacingDirection() {
        return facingDirection;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Enemy) {
                // Enemy hit player
            }
        }
    }
}

