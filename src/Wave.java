import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.TextObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Wave implements IAlarmListener {
    private ShooterApp world;
    private int enemyCount;
    private float enemiesPerSecond;
    private Species[] species;
    private ArrayList<EnemySpawner> spawners = new ArrayList<>();
    private TextObject text;
    private WeaponType weapon;
    private Player player;

    /**
     * @param world huidige wereld
     * @param player speler in de wave
     * @param weapon wapen wat gebruikt wordt in de wave
     * @param enemyCount hoeveelheid enemies
     * @param enemiesPerSecond hoeveelheid enemies per seconde
     * @param species array met soorten enemies
     */
    public Wave(ShooterApp world, Player player, WeaponType weapon, int enemyCount, float enemiesPerSecond, Species[] species) {
        this.world = world;
        this.enemyCount = enemyCount;
        this.enemiesPerSecond = enemiesPerSecond;
        this.species = species;
        this.weapon = weapon;
        this.player = player;
    }

    /** Begint de wave
     * @param waveCount welke wave het is
     */
    public void start(int waveCount) {
        player.setWeapon(weapon);
        text = new TextObject("Wave " + waveCount, 60);
        text.setX(world.getWidth());
        text.setY(world.getHeight()/2);
        text.setxSpeed(-4);
        text.setForeColor(255, 255, 255, 255);
        world.addGameObject(text);
        addAlarm();
    }

    /**
     * Maakt de spawners aan
     */
    public void createSpawners() {
        for (int i = 0; i < species.length; i++) {
            spawners.add(new EnemySpawner(world, enemiesPerSecond / species.length, species[i]));
        }
    }

    /**
     * Stop met spawnen van enemies
     */
    public void stopSpawning() {
        Iterator<EnemySpawner> spawnersIterator = spawners.iterator();
        while (spawnersIterator.hasNext()) {
            spawnersIterator.next().stop();
        }
    }

    /**
     * @return of alle enemies in de wave gespawnt zijn
     */
    public boolean allEnemiesSpawned() {
        int totalEnemiesSpawned = 0;
        for (EnemySpawner es: spawners) {
            totalEnemiesSpawned += es.getEnemiesSpawned();
        }

        return totalEnemiesSpawned >= enemyCount;
    }

    /**
     * @return of alle enemies in de wave dood zijn
     */
    public boolean allEnemiesKilled() {
        for(EnemySpawner es: spawners) {
            if (!es.allEnemiesDead()) {
                return false;
            }
        }
        return true;
    }

    public void addAlarm() {
        Alarm waveStart = new Alarm("Start wave", 5);
        waveStart.addTarget(this);
        waveStart.start();
    }

    public void triggerAlarm(String s) {
        createSpawners();
        world.deleteGameObject(text);
    }
}
