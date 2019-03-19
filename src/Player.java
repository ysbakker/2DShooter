import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import java.util.ArrayList;

public class Player extends AnimatedSpriteObject {

    private final int size = 25;
    private final ShooterApp world;

    private int currentFrame;
    private final ArrayList<Key> keys = new ArrayList<>();
    private ArrayList<Key> keyspressed = new ArrayList<>();

    private final int walkingSpeed = 5;

    public Player(ShooterApp world) {
        super(new Sprite("media/human.png"), 8);
        this.world = world;
        setFriction(0);

        currentFrame = 0;

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
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }
        if (getX() >= world.width - size) {
            setxSpeed(0);
            setX(world.width - size);
        }
        if (getY() >= world.height - size) {
            setySpeed(0);
            setY(world.height - size);
        }

        // update frames van player sprite
        setCurrentFrameIndex(currentFrame);

        for (Key key : keys) {
            if (key.isPressed() && !keyspressed.contains(key)) {
                keyspressed.add(key);
            } else if (!key.isPressed() && keyspressed.contains(key)) {
                keyspressed.remove(key);
            }
        }

        System.out.println(keyspressed);
        setDirectionSpeed(determineDirection(keyspressed), determineSpeed(keyspressed));

        if (determineSpeed(keyspressed) > 0) {
            loopFrames();
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

    public void loopFrames() {
        if (currentFrame == 7) {
            currentFrame = 0;
        } else {
            currentFrame++;
        }
    }

    public int determineDirection(ArrayList<Key> keys) {
        int totalDirection = 0;
        int walkingKeysPressed = 0;

        for (Key key: keys) {
            if (key.getKeyCode() == 'a') {
                totalDirection += 270;
                walkingKeysPressed++;
            }
            if (key.getKeyCode() == 'w') {
                totalDirection += 360;
                walkingKeysPressed++;
            }
            if (key.getKeyCode() == 's') {
                totalDirection += 180;
                walkingKeysPressed++;
            }
            if (key.getKeyCode() == 'd') {
                totalDirection += 90;
                walkingKeysPressed++;
            }
        }

        if (walkingKeysPressed == 0) {
            return 0;
        } else {
            return totalDirection / walkingKeysPressed;
        }
    }

    public int determineSpeed(ArrayList<Key> keys) {
        int speed = 0;

        for (Key key: keys) {
            if (key.getKeyCode() == 'a') {
                speed = walkingSpeed;
            }
            if (key.getKeyCode() == 'w') {
                speed = walkingSpeed;
            }
            if (key.getKeyCode() == 's') {
                speed = walkingSpeed;
            }
            if (key.getKeyCode() == 'd') {
                speed = walkingSpeed;
            }
        }
        return speed;
    }

}

