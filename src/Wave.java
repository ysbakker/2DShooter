import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.TextObject;

import java.util.ArrayList;

public class Wave implements IAlarmListener {
    private ShooterApp world;
    private int enemyCount;
    private float enemiesPerSecond;
    private Species[] species;
    private ArrayList<EnemySpawner> spawners = new ArrayList<>();
    private TextObject text;

    public Wave(ShooterApp world, int enemyCount, float enemiesPerSecond, Species[] species) {
        this.world = world;
        this.enemyCount = enemyCount;
        this.enemiesPerSecond = enemiesPerSecond;
        this.species = species;
    }

    public void start(int waveCount) {
        text = new TextObject("Wave " + waveCount, 60);
        text.setX(world.getWidth());
        text.setY(world.getHeight()/2);
        text.setxSpeed(-4);
        text.setForeColor(255, 255, 255, 255);
        world.addGameObject(text);
        addAlarm();
    }

    public void createSpawners() {
        for (int i = 0; i < species.length; i++) {
            spawners.add(new EnemySpawner(world, enemiesPerSecond / species.length, species[i]));
        }
    }

    public void stopSpawning() {
        for(EnemySpawner es: spawners) {
            es.stop();
        }
    }

    public boolean allEnemiesSpawned() {
        int totalEnemiesSpawned = 0;
        for (EnemySpawner es: spawners) {
            totalEnemiesSpawned += es.getEnemiesSpawned();
        }

        return totalEnemiesSpawned >= enemyCount;
    }

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
