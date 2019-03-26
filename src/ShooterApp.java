
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

public class ShooterApp extends GameEngine {
    private Player player;
    private EnemySpawner enemySpawner;
    private Gamestate state;

    private int worldWidth;
    private int worldHeight;

    public static void main(String[] args) {
        ShooterApp app = new ShooterApp();
        String[] arguments = {"ShooterApp"};
        PApplet.runSketch(arguments, app);
    }

    public void setupGame() {

        worldWidth = 849;
        worldHeight = 480;

        createObjects();
        createViewWithoutViewport(worldWidth, worldHeight);
        createEnemySpawner();
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(loadImage("media/scene.png"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    public void update() {
    }

    private void createObjects() {
        player = new Player(this);
        addGameObject(player, 100, 100);
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public void createEnemySpawner() {
        enemySpawner = new EnemySpawner(this, 20);
    }
}