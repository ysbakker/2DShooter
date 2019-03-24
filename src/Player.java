import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.ArrayList;

public class Player extends AnimatedSpriteObject {

    private final int size;
    private final ShooterApp world;

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

        size = 50;
        walkingSpeed = 4;
        currentFrame = 0;
        facingDirection[0] = 1;
        facingDirection[1] = 0;

        currentWeapon = new Weapon(world, this, 0, "media/sprite_rock.png");
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
        if (getX() >= world.width - size) {
            setxSpeed(0);
            setX(world.width - size);
        }
        if (getY() >= world.height - size - 25) {
            setySpeed(0);
            setY(world.height - size - 25);
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
            if (facingDirection[0] == -1) {
                loopFramesLeft();
            } else {
                loopFramesRight();
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

    private void loopFramesRight() {
        if(currentFrame > 7) {
            currentFrame = 0;
        }
        if (currentFrame == 7) {
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

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }
}

