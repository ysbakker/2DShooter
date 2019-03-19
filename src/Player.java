import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;

public class Player extends AnimatedSpriteObject {

    final int size = 25;
    int currentFrame = 0;
    float rotatiehoek = 0;

    private final ShooterApp world;

    /**
     * Constructor
     *
     * @param world Referentie naar de wereld
     */
    public Player(ShooterApp world) {
        super(new Sprite("media/human.png"), 16);
        this.world = world;
        setCurrentFrameIndex(0);
        setFriction(0);
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
        setCurrentFrameIndex(currentFrame);
    }

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
        if (keyCode == world.DOWN) {
            setDirectionSpeed(180, speed);
        }
        if (key == ' ') {
            System.out.println("Spatie!");
        }
    }

    public void keyReleased(int keyCode, char key) {
        if (keyCode == world.LEFT) {
            setDirectionSpeed(270, 0);
        }
        if (keyCode == world.UP) {
            setDirectionSpeed(0, 0);
            currentFrame = 0;
        }
        if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, 0);
            currentFrame = 0;
        }
        if (keyCode == world.DOWN) {
            setDirectionSpeed(180, 0);
            currentFrame = 0;
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

