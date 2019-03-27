
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

import java.util.ArrayList;

public class ShooterApp extends GameEngine {
    private Player player;
    private EnemySpawner enemySpawner;
    private Gamestate state;

    private int worldWidth, worldHeight;
    private int[] worldBoundaries;

    private ArrayList<Wave> waves = new ArrayList<>();
    private int currentWave = 0;

    public static void main(String[] args) {
        ShooterApp app = new ShooterApp();
        String[] arguments = {"ShooterApp"};
        PApplet.runSketch(arguments, app);
    }

    public void setupGame() {
        worldWidth = 849;
        worldHeight = 500;

        worldBoundaries = new int[]{0,10,849,450}; // xmin, ymin, xmax, ymax

        createObjects();
        createViewWithoutViewport(worldWidth, worldHeight);
        createWaves();
        waves.get(currentWave).start();
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(loadImage("media/scene.png"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    public void update() {
        if (waves.get(currentWave).allEnemiesSpawned()) {
            waves.get(currentWave).stopSpawning();
            if (waves.get(currentWave).allEnemiesKilled()) {
                if (currentWave < waves.size() - 1) {
                    currentWave++;
                    waves.get(currentWave).start();
                }
            }
        }
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

    public int[] getWorldBoundaries() {
        return worldBoundaries;
    }

    public void createWaves() {
        waves.add(new Wave(this, 5, 1, new Species[]{Species.SKELETON}));
        waves.add(new Wave(this, 50, 10, new Species[]{Species.SKELETON}));
    }
}