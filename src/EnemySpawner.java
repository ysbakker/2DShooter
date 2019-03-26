import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;

public class EnemySpawner implements IAlarmListener {

    private float enemiesPerSecond;
    private ShooterApp world;

    public EnemySpawner(ShooterApp world, float enemiesPerSecond){
        this.enemiesPerSecond = enemiesPerSecond;
        this.world = world;
        startAlarm();
    }

    private void startAlarm(){
        Alarm alarm = new Alarm("New Enemy", 1/enemiesPerSecond);
        alarm.addTarget(this);
        alarm.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
        Enemy skeleton = new Skeleton(world);
        world.addGameObject(skeleton, world.getWidth(), world.random(skeleton.getHeight() / 2, world.getHeight() - skeleton.getHeight() * 2F));
        startAlarm();
    }

}
