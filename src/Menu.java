import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;

public class Menu extends Dashboard {

    private ShooterApp world;
    private ArrayList<Button> buttons = new ArrayList<>();

    public Menu(ShooterApp world) {
        super(0,0, world.getWidth(), world.getHeight());
        this.world = world;
        buttons.add(new Button(world, this.width/2, this.height/2 -100, 200, 50, 0xFF4E9544, "Start Game", Gamestate.IN_GAME));
        buttons.add(new Button(world, this.width/2, this.height/2 , 200, 50, 0xFFADA537, "High Scores", Gamestate.HIGH_SCORES));
        buttons.add(new Button(world, this.width/2, this.height/2 + 100, 200, 50, 0xFFDF342F, "Quit", Gamestate.QUIT_GAME));
    }

    @Override
    public void update(){

    }

    public Gamestate getButton(float x, float y){
        for(Button btn : buttons){
            if(btn.isOverButton(x,y)){
                world.deleteGameObject(this);
                return btn.getGameState();
            }
        }
        return Gamestate.MAIN_MENU;
    }

    @Override
    public void draw(PGraphics g){
        for (Button btn : buttons){
            btn.draw(g);
        }
    }
}
