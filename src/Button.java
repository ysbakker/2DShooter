import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Button extends GameObject {

    private Gamestate targetState;
    private String description;
    private int color;
    private int defaultWidth = 200;
    private int defaultHeight = 50;
    private ShooterApp world;

    public Button(ShooterApp world, float x, float y, int color, String text, Gamestate targetState) {
        setHeight(defaultHeight);
        setWidth(defaultWidth);
        setX(x);
        setY(y);
        this.color = color;
        this.description = text;
        this.targetState = targetState;
        this.world = world;
        world.addGameObject(this);
    }

    public Button(ShooterApp world, float x, float y, int width, int height, int color, String text, Gamestate targetState) {
        setHeight(height);
        setWidth(width);
        setX(x-(width/2F));
        setY(y-(height/2F));
        this.color = color;
        this.description = text;
        this.targetState = targetState;
        this.world = world;
        world.addGameObject(this);
    }

    @Override
    public void update() {

    }

    public boolean isOverButton(float x, float y) {
        return x >= this.x && x <= (this.x + this.width) && y >= this.y && y <= (this.y + this.height);
    }

    public Gamestate getGameState(){
        return targetState;
    }

    @Override
    public void draw(PGraphics g) {
        g.rectMode(g.CORNER);
        g.stroke(color);
        g.fill(color);
        g.rect(this.x, this.y, this.width, this.height);
        g.fill(0xFFFFFFFF);
        g.textAlign(g.CENTER, g.CENTER);
        g.text(description, this.x+this.width/2, this.y+this.height/2);
    }
}
