import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class Weapon extends SpriteObject {
    // Player owner is de speler die het wapen 'vast' heeft
    // Het wapen kan een projectiel afvuren wat gespawned wordt op de huidige locatie van de speler
    private Player owner;
    private String name;
    private int magSize;

    public Weapon(String name, int magSize, Player owner) {
        super(new Sprite("media/empty.png"));
        this.owner = owner;
        this.name = name;
        this.magSize = magSize;
    }

    public Weapon(String name, int magSize, Player owner, String filename) {
        super(new Sprite(filename));
        this.owner = owner;
        this.name = name;
        this.magSize = magSize;
    }

    public abstract void fire();
}
