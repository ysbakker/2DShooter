import java.util.ArrayList;
import java.util.Random;

public class Wave {
    private ShooterApp world;
    private int enemyCount, enemiesPerSecond;
    private Species[] species;
    private ArrayList<EnemySpawner> spawners = new ArrayList<>();

    public Wave(ShooterApp world, int enemyCount, int enemiesPerSecond, Species[] species) {
        this.world = world;
        this.enemyCount = enemyCount;
        this.enemiesPerSecond = enemiesPerSecond;
        this.species = species;
    }

    public void start() {
        createSpawners();
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
}
