import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.sound.Sound;
import processing.core.PGraphics;

public class Button extends GameObject {

    private Gamestate targetState;
    private String description;
    private int color;
    private int defaultWidth = 200;
    private int defaultHeight = 50;
    private Sound buttonSound;

    /** creert knop met standaard hoogte
     * @param world huidige wereld
     * @param menu van welk dashboard de knop deel uitmaakt
     * @param x x-positie
     * @param y y-positie
     * @param color kleur
     * @param text tekst in knop
     * @param targetState welke toestand de knop het spel in zet
     */
    public Button(ShooterApp world, Dashboard menu, float x, float y, int color, String text, Gamestate targetState) {
        setHeight(defaultHeight);
        setWidth(defaultWidth);
        setX(x);
        setY(y);
        this.color = color;
        this.description = text;
        this.targetState = targetState;
        menu.addGameObject(this);
        buttonSound = new Sound(world, "media/button.mp3");
    }

    /**
     * @param world huidige wereld
     * @param menu van welk dashboard de knop deel uitmaakt
     * @param x x-positie
     * @param y y-positie
     * @param width breedte knop
     * @param height hoogte knop
     * @param color kleur
     * @param text tekst in knop
     * @param targetState welke toestand de knop het spel in zet
     */
    public Button(ShooterApp world, Dashboard menu, float x, float y, int width, int height, int color, String text, Gamestate targetState) {
        setHeight(height);
        setWidth(width);
        setX(x-(width/2F));
        setY(y-(height/2F));
        this.color = color;
        this.description = text;
        this.targetState = targetState;
        menu.addGameObject(this);
        buttonSound = new Sound(world, "media/button.mp3");
    }

    @Override
    public void update() {

    }

    /**
     * @param x x-positie om te vergelijken
     * @param y y-positie om te vergelijken
     * @return true als x en y 'binnen' knop is
     */
    public boolean isOverButton(float x, float y) {
        return x >= this.x && x <= (this.x + this.width) && y >= this.y && y <= (this.y + this.height);
    }

    /**
     * @return welke toestand het spel in gezet moet worden
     */
    public Gamestate getGameState(){
        return targetState;
    }

    public void playSound(){
        buttonSound.play();
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
