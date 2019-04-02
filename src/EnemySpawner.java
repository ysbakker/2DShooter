import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

import java.util.ArrayList;

public class EnemySpawner implements IAlarmListener {

    private float enemiesPerSecond;
    private ShooterApp world;
    private Species type;
    private int enemiesSpawned;
    public Alarm spawnTimer;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemySpawner(ShooterApp world, float enemiesPerSecond, Species type){
        this.enemiesPerSecond = enemiesPerSecond;
        this.world = world;
        this.type = type;
        enemiesSpawned = 0;
        startAlarm();
    }

    private void startAlarm(){
        spawnTimer = new Alarm("New Enemy", 1/enemiesPerSecond);
        spawnTimer.addTarget(this);
        spawnTimer.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
        Enemy e = null;
        if (type == Species.SKELETON) {
            e = new Skeleton(world);
        } else if (type == Species.ORC) {
            e = new Orc(world);
        } else if (type == Species.TROLL) {
            e = new Troll(world);
        } else if (type == Species.SKELETON_FLAME) {
            e = new SkeletonFlame(world);
        }

        world.addGameObject(e, world.getWorldBoundaries()[2], world.random(world.getWorldBoundaries()[1], world.getWorldBoundaries()[3] - e.getHeight()));
        enemiesSpawned++;
        enemies.add(e);
        startAlarm();
    }

    public int getEnemiesSpawned() {
        return enemiesSpawned;
    }

    public boolean allEnemiesDead() {
        for (Enemy e: enemies) {
            if (e.getLiving()) {
                return false;
            }
        }
        return true;
    }

    public void stop() {
        spawnTimer.stop();
    }
}