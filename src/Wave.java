import java.util.ArrayList;
import java.util.Random;

public class Wave {
    private ShooterApp world;
    private int enemyCount, enemiesPerSecond;
    private Species[] species;
    private ArrayList<EnemySpawner> spawners = new ArrayList<>();
    private Random rand = new Random();

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
        int[] randomDistribution;
        if (species.length > 1) {
            randomDistribution = generateDistribution(species.length);
        } else {
            randomDistribution = new int[]{100};
        }

        for (int i = 0; i < species.length; i++) {
            spawners.add(new EnemySpawner(world, (randomDistribution[i] / 100F) * enemiesPerSecond, species[i]));
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

    public int[] generateDistribution(int speciesCount) {
        int s = 100;
        int val[] = new int[speciesCount];
        for (int i = 0; i < speciesCount--; i++) {
            int r = rand.nextInt(s);
            s -= r;
            val[i] = r;
        }
        val[val.length-1] = s;
        return val;
    }
}
