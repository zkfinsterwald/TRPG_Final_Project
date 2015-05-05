package my.trpg.gamestate;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import my.gop.main.LoadImage;
import my.trpg.main.Main;
import my.trpg.manangers.MouseManager;
import my.trpg.res.Assets;

public class ExitState extends GameState {
	
	GameStateButton yes;
	GameStateButton no;
	
	MouseManager mouse;
	
	BufferedImage background = LoadImage.loadImageFrom(Assets.class, "8bit_background.png");
	
	public ExitState(GameStateManager gsm) {
		super(gsm);
	}
	
	@Override
	public void init() {
		mouse = new MouseManager();
		yes = new GameStateButton(Main.width / 2 - GameStateButton.width / 2 - 200, 450, "Yes!");
		no = new GameStateButton(Main.width / 2 - GameStateButton.width / 2 + 200, 450, "No!");
	}

	@Override
	public void tick(double deltaTime) {
		mouse.tick();
		yes.tick();
		no.tick();
		
		if(yes.isHeldOver()){
			if(yes.isPressed()){
				System.exit(1);
			}
		}
		
		if(no.isHeldOver()){
			if(no.isPressed()){
				//gsm.states.push(new MenuState(gsm));
				gsm.states.pop();
				//gsm.states.peek().init();
			}
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		yes.render(g);
		no.render(g);
		mouse.render(g);
		g.clipRect(0, 0, Main.width, Main.height);
	}

}
