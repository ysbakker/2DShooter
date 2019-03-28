import nl.han.ica.oopg.dashboard.Dashboard;
import processing.core.PGraphics;

import java.util.ArrayList;

public class Menu extends Dashboard {

    ShooterApp world;
    ArrayList<Button> buttons;

    public Menu(ShooterApp world) {
        super(0,0, world.getWidth(), world.getHeight());
        this.world = world;

        buttons.add(new Button(this.width/2, this.height/2, 200, 50, 0, "Main Menu", Gamestate.IN_GAME));
    }

    public void draw(PGraphics g){
        for (Button btn : buttons){
            btn.draw(g);
        }
    }
}
