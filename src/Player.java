import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;

import java.util.ArrayList;

public class Player extends AnimatedSpriteObject {

    final int size = 25;
    int currentFrame = 0;
    float rotatiehoek = 0;
    private final ShooterApp world;

    private int currentFrame;
    private final ArrayList<Key> keys = new ArrayList<>();
    private ArrayList<Key> keyspressed = new ArrayList<>();

    private final int walkingSpeed = 5;

    public Player(ShooterApp world) {
        super(new Sprite("media/human.png"), 16);
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

    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 5;
        if (keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
            loopFramesLeft();
        }
        if (keyCode == world.UP) {
            setDirectionSpeed(0, speed);
        }
        if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
            loopFramesRight();
        }
        for (Key key : keys) {
            if (key.isPressed() && !keyspressed.contains(key)) {
                keyspressed.add(key);
            } else if (!key.isPressed() && keyspressed.contains(key)) {
                keyspressed.remove(key);
            }
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

    public void loopFramesRight() {
        if(currentFrame > 7) {
            currentFrame = 0;
        }
        if (currentFrame == 7) {
            currentFrame = 0;
        } else {
            currentFrame++;
        }
    }

    public void loopFramesLeft() {
        if(currentFrame < 8) {
            currentFrame = 8;
        }
        if(currentFrame == 15 ) {
            currentFrame = 8;
        } else {
            currentFrame ++;
        }
    }

}

