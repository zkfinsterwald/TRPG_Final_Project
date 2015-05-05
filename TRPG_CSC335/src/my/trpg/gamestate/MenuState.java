package my.trpg.gamestate;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import my.gop.main.LoadImage;
import my.trpg.main.Main;
import my.trpg.manangers.MouseManager;
import my.trpg.res.Assets;

public class MenuState extends GameState {

	GameStateButton startGame;
	GameStateButton options;
	GameStateButton exit;
	
	MouseManager mouse;
	BufferedImage background = LoadImage.loadImageFrom(Assets.class, "8bit_background.png");
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	@Override
	public void init() {
		mouse = new MouseManager();
		startGame = new GameStateButton(Main.width / 2 - GameStateButton.width / 2, 300, new TrpgLevelLoader(gsm), gsm, "Start Game");
		options = new GameStateButton(Main.width / 2 - GameStateButton.width / 2, 450, "Options");
		exit = new GameStateButton(Main.width / 2 - GameStateButton.width / 2, 600, new ExitState(gsm), gsm, "Exit");
	}

	@Override
	public void tick(double deltaTime) {
		mouse.tick();
		startGame.tick();
		options.tick();
		exit.tick();
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		startGame.render(g);
		options.render(g);
		exit.render(g);
		mouse.render(g);
	//	g.clipRect(0, 0, Main.width, Main.height);
	}

}
