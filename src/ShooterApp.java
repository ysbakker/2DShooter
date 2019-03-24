
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

public class ShooterApp extends GameEngine {
    private Player player;
    private EnemySpawner enemySpawner;

    public static void main(String[] args) {
        ShooterApp app = new ShooterApp();
        String[] arguments = {"ShooterApp"};
        PApplet.runSketch(arguments, app);
    }

    public void setupGame() {

        int worldWidth = 849;
        int worldHeight = 480;

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

    public void createEnemySpawner() {
        enemySpawner = new EnemySpawner(this, player, 1);
    }
}