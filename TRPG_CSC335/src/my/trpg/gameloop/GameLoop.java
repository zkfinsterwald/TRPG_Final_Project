package my.trpg.gameloop;

import my.gop.main.IDGameLoop;
import my.gop.main.Vector2F;
import my.trpg.gamestate.GameStateManager;
import my.trpg.res.Assets;

public class GameLoop extends IDGameLoop {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7854096628276036118L;
	GameStateManager gsm;
	public static Assets assets = new Assets();
	public static Vector2F map = new Vector2F();

	public GameLoop(int fwidth, int fheight) {
		super(fwidth, fheight);
	}

	@Override
	public void init() {
		assets.init();
		Vector2F.setWorldVariables(map.xpos, map.ypos);
		gsm = new GameStateManager();
		gsm.init();
		super.init();
	}

	@Override
	public void tick(double deltaTime) {
		Vector2F.setWorldVariables(map.xpos, map.ypos);
		gsm.tick(deltaTime);
	}

	@Override
	public void render() {
		super.render();
		gsm.render(graphics2D);
		clear();
	}

	@Override
	public void clear() {
		super.clear();
	}
}
