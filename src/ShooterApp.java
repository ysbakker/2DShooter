
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

import java.util.ArrayList;

public class ShooterApp extends GameEngine implements IAlarmListener {
    private Player player;
    private Gamestate state;
    private Menu mainMenu;
    private Sound backgroundSound;
    private int waveDelay;
    private boolean delayTriggered = true;
    private int[] worldBoundaries;
    private Fortress fortress;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int currentWave;
    private boolean initialized = false;

    public static void main(String[] args) {
        ShooterApp app = new ShooterApp();
        String[] arguments = {"ShooterApp"};
        PApplet.runSketch(arguments, app);
    }

    public void setupGame() {
        int worldWidth = 849;
        int worldHeight = 500;
        initializeSound();
        worldBoundaries = new int[]{105,10,worldWidth,worldHeight - 50}; // xmin, ymin, xmax, ymax
        waveDelay = 2;
        createViewWithoutViewport(worldWidth, worldHeight);

        setGameState(Gamestate.MAIN_MENU);
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(loadImage("media/scene.png"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    public void update() {
        switch (state) {
            case MAIN_MENU:
                break;
            case IN_GAME:
                if (!initialized) {
                    return;
                }
                if (fortress.isDestroyed()) {
                    setGameState(Gamestate.MAIN_MENU);
                    return;
                }
                if (currentWave < waves.size() - 1 && !delayTriggered) {
                    if (waves.get(currentWave).allEnemiesSpawned()) {
                        waves.get(currentWave).stopSpawning();
                        if (waves.get(currentWave).allEnemiesKilled()) {
                            startDelay();
                            delayTriggered = true;
                        }
                    }
                }
                if (currentWave >= waves.size() - 1) {
                    if (waves.get(currentWave).allEnemiesSpawned()) {
                        waves.get(currentWave).stopSpawning();
                        if (waves.get(currentWave).allEnemiesKilled()) {
                            setGameState(Gamestate.MAIN_MENU);
                        }
                    }
                }
                break;
            case QUIT_GAME:
                System.exit(0);
                break;
        }
    }

    private void setGameState(Gamestate state) {
        this.state = state;
        switch (state) {
            case MAIN_MENU:
                clearView();
                mainMenu = new Menu(this);
                this.addGameObject(mainMenu);
                break;
            case IN_GAME:
                clearView();
                startGame();
                break;
        }
    }

    private void clearView() {
        this.deleteAllGameOBjects();
        clearWaves();
        initialized = false;
    }

    private void startGame() {
        currentWave = -1;
        createObjects();
        createWaves();
        startDelay();
        createFortress();
        delayTriggered = true;
        initialized = true;
    }

    private void createObjects() {
        player = new Player(this);
        addGameObject(player, 100, 100);
    }

    public int[] getWorldBoundaries() {
        return worldBoundaries;
    }

    private void createWaves() {
        waves.add(new Wave(this, player, WeaponType.ROCK, 1, 1, new Species[]{Species.SKELETON}));
        waves.add(new Wave(this, player, WeaponType.ROCK, 5, 1, new Species[]{Species.SKELETON}));
        waves.add(new Wave(this, player, WeaponType.ROCK, 10, 1, new Species[]{Species.SKELETON}));
        waves.add(new Wave(this, player, WeaponType.ROCK, 10, 2, new Species[]{Species.SKELETON}));
        waves.add(new Wave(this, player, WeaponType.ROCK, 1, 1, new Species[]{Species.ORC}));
        waves.add(new Wave(this, player, WeaponType.PISTOL, 2, 1, new Species[]{Species.SKELETON, Species.ORC}));
        waves.add(new Wave(this, player, WeaponType.PISTOL, 3, 1, new Species[]{Species.SKELETON, Species.ORC}));
        waves.add(new Wave(this, player, WeaponType.PISTOL, 4, 1, new Species[]{Species.SKELETON, Species.ORC}));
        waves.add(new Wave(this, player, WeaponType.PISTOL, 4, 2, new Species[]{Species.ORC}));
        waves.add(new Wave(this, player, WeaponType.PISTOL, 3, 1, new Species[]{Species.ORC, Species.SKELETON, Species.TROLL}));
        waves.add(new Wave(this, player, WeaponType.PISTOL, 4, 1, new Species[]{Species.ORC, Species.SKELETON, Species.TROLL}));
        waves.add(new Wave(this, player, WeaponType.PISTOL, 4, 2, new Species[]{Species.ORC, Species.SKELETON, Species.TROLL}));
        waves.add(new Wave(this, player, WeaponType.PISTOL, 6, 1.5F, new Species[]{Species.TROLL}));
        waves.add(new Wave(this, player, WeaponType.PISTOL, 2, 1, new Species[]{Species.SKELETON_FLAME}));
        waves.add(new Wave(this, player, WeaponType.SHOTGUN, 3, 1, new Species[]{Species.SKELETON_FLAME}));
        waves.add(new Wave(this, player, WeaponType.SHOTGUN, 5, 1, new Species[]{Species.SKELETON_FLAME}));
        waves.add(new Wave(this, player, WeaponType.SHOTGUN, 7, 2, new Species[]{Species.SKELETON_FLAME, Species.SKELETON}));
        waves.add(new Wave(this, player, WeaponType.SHOTGUN, 10, 2, new Species[]{Species.ORC}));
        waves.add(new Wave(this, player, WeaponType.SHOTGUN, 10, 3, new Species[]{Species.TROLL}));
        waves.add(new Wave(this, player, WeaponType.SHOTGUN, 7, 2, new Species[]{Species.ORC, Species.SKELETON, Species.TROLL, Species.SKELETON_FLAME}));
        waves.add(new Wave(this, player, WeaponType.SHOTGUN, 10, 2, new Species[]{Species.ORC, Species.SKELETON, Species.TROLL, Species.SKELETON_FLAME}));
        waves.add(new Wave(this, player, WeaponType.SHOTGUN, 10, 10, new Species[]{Species.ORC}));
    }

    private void clearWaves() {
        waves.clear();
    }

    private void createFortress() {
        fortress = new Fortress(this);
    }

    private void startDelay() {
        Alarm delay = new Alarm("Next wave", waveDelay);
        delay.addTarget(this);
        delay.start();
    }

    public void triggerAlarm(String s) {
        currentWave++;
        this.deleteAllGameObjectsOfType(Particle.class);
        this.deleteAllGameObjectsOfType(Enemy.class);
        waves.get(currentWave).start(currentWave + 1);
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

    private void initializeSound() {
        backgroundSound = new Sound(this, "media/bg_music.mp3");
        backgroundSound.loop(-1);
    }
}