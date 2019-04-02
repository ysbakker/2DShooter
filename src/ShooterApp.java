
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

import java.util.ArrayList;

public class ShooterApp extends GameEngine implements IAlarmListener {
    private Player player;
    private EnemySpawner enemySpawner;
    private Gamestate state;
    private Menu mainMenu;
    private int waveDelay;
    private boolean delayTriggered;
    private int[] worldBoundaries;

    private ArrayList<Wave> waves = new ArrayList<>();
    private int currentWave = 0;

    public static void main(String[] args) {
        ShooterApp app = new ShooterApp();
        String[] arguments = {"ShooterApp"};
        PApplet.runSketch(arguments, app);
    }

    public void setupGame() {
        int worldWidth = 849;
        int worldHeight = 500;

        worldBoundaries = new int[]{0,10,849,450}; // xmin, ymin, xmax, ymax
        createViewWithoutViewport(worldWidth, worldHeight);

        this.setGameState(Gamestate.MAIN_MENU);
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(loadImage("media/scene.png"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    public void update() {
        switch (state){
            case MAIN_MENU:
                break;
            case IN_GAME:
                if (waves.get(currentWave).allEnemiesSpawned()) {
                    waves.get(currentWave).stopSpawning();
                    if (waves.get(currentWave).allEnemiesKilled()) {
                        if (currentWave < waves.size() - 1 && !delayTriggered) {
                            startDelay();
                            delayTriggered = true;
                        }
                    }
                }
                break;
            case QUIT_GAME:
                System.exit(0);
                break;
            case PAUSE_GAME:
                break;
            case HIGH_SCORES:
                break;
        }
    }

    private void setGameState(Gamestate state) {
        this.state = state;
        switch (state){
            case MAIN_MENU:
                mainMenu = new Menu(this);
                this.addGameObject(mainMenu);
                break;
            case IN_GAME:
                createObjects();
                createWaves();
                waves.get(currentWave).start();
                waveDelay = 5;
                delayTriggered = false;
                break;
            case QUIT_GAME:
                break;
            case PAUSE_GAME:
                break;
            case HIGH_SCORES:
                break;
        }
    }

    private void createObjects() {
        player = new Player(this);
        addGameObject(player, 100, 100);
    }

    public int[] getWorldBoundaries() {
        return worldBoundaries;
    }

    public void createWaves() {
        waves.add(new Wave(this, 100, 10, new Species[]{Species.SKELETON, Species.ORC, Species.TROLL, Species.SKELETON_FLAME}));
    }

    public void startDelay() {
        Alarm delay = new Alarm("Next wave", waveDelay);
        delay.addTarget(this);
        delay.start();
    }

    public void triggerAlarm(String s) {
        currentWave++;
        waves.get(currentWave).start();
        delayTriggered = false;
    }

    public void mouseClicked(){
        switch (state){
            case MAIN_MENU:
                setGameState(mainMenu.getButton(mouseX, mouseY));
                break;
            case IN_GAME:
                break;
        }
    }
}