import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import java.util.ArrayList;

public class Player extends AnimatedSpriteObject {

    private final int size = 25;
    private final ShooterApp world;

    private int currentFrame;
    private final ArrayList<Key> keys = new ArrayList<>();
    private ArrayList<Key> keysPressed = new ArrayList<>();

    private final int walkingSpeed = 4;

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
            loopFrames();
            movePlayer();
        } else {
            stopPlayer();
            currentFrame = 0;
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

    public void movePlayer() {
        stopPlayer();
        for (Key key: keysPressed) {
            if (key.getKeyCode() == 'a') {
                setxSpeed(-walkingSpeed);
            }
            if (key.getKeyCode() == 'w') {
                setySpeed(-walkingSpeed);
            }
            if (key.getKeyCode() == 's') {
                setySpeed(walkingSpeed);
            }
            if (key.getKeyCode() == 'd') {
                setxSpeed(walkingSpeed);
            }
        }
    }

    public void stopPlayer() {
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

}

