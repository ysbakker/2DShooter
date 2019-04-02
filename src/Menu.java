import nl.han.ica.oopg.dashboard.Dashboard;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;

public class Menu extends Dashboard {

    private ShooterApp world;
    private ArrayList<Button> buttons = new ArrayList<>();
    private PImage logo;
    private float logoWidth = 200;
    private float logoHeight = 200;

    public Menu(ShooterApp world) {
        super(0,0, world.getWidth(), world.getHeight());
        this.world = world;
        logo = world.loadImage("media/logo.png", "png");
        buttons.add(new Button(world, this.width/2, this.height/2 -100, 200, 50, 0xFF4E9544, "Start Game", Gamestate.IN_GAME));
        buttons.add(new Button(world, this.width/2, this.height/2 , 200, 50, 0xFFADA537, "Credits", Gamestate.QUIT_GAME));
        buttons.add(new Button(world, this.width/2, this.height/2 + 100, 200, 50, 0xFFDF342F, "Quit", Gamestate.QUIT_GAME));
    }

    @Override
    public void update(){

    }

    public Gamestate getButton(float x, float y){
        for(Button btn : buttons){
            if(btn.isOverButton(x,y)){
                world.deleteGameObject(this);
                btn.playSound();
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
        g.image(logo, (this.width/2)-logoWidth/2, this.y, logoWidth, logoHeight);
    }
}