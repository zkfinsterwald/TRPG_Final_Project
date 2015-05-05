package my.trpg.gamestate;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import my.gop.main.Vector2F;
import my.trpg.manangers.MouseManager;
import my.trpg.res.Assets;

public class GameStateButton extends Rectangle {
	
	private GameState gamestate;
	private GameStateManager gsm;
	private Vector2F pos = new Vector2F();
	private boolean isHeldOver;
	static int width = 32*6;
	private int height = 32*2;
	private BufferedImage defaultImage;
	private String buttonMessage;
	
	public GameStateButton(float xpos, float ypos, GameState gamestate, GameStateManager gsm, String buttonMessage) {
		this.gamestate = gamestate;
		this.gsm = gsm;
		this.pos.xpos = xpos;
		this.pos.ypos = ypos;
		this.buttonMessage = buttonMessage;
		setBounds((int)pos.xpos, (int)pos.ypos, width, height);
		defaultImage = Assets.getButton();
	}
	
	public GameStateButton(int xpos, int ypos, String buttonMessage) {
		this.pos.xpos = xpos;
		this.pos.ypos = ypos;
		this.buttonMessage = buttonMessage;
		setBounds((int)pos.xpos, (int)pos.ypos, width, height);
	}

	public void tick() {
		if(getBounds().contains(MouseManager.mouse)) {
			isHeldOver = true;
		} else {
			isHeldOver = false;
		}
		
		if(isHeldOver) {
			if(defaultImage != Assets.getButton()) {
				defaultImage = Assets.getButton();
			}
		} else {
			if(defaultImage != Assets.getButton_pressed()) {
				defaultImage = Assets.getButton_pressed();
			}
		}
		
		if(gamestate != null) {
			if(isHeldOver) {
				if(isPressed()){
					gsm.states.push(gamestate);
					gsm.states.peek().init();
					isHeldOver = false;
					MouseManager.pressed = false;
				}
			}
		}
	}
	
	Font font = new Font("serif", 10, 30);
	
	public void render(Graphics2D g) {
		g.drawImage(defaultImage, (int)pos.xpos, (int)pos.ypos, width, height, null);
		g.setFont(font);
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int tw = (int) font.getStringBounds(buttonMessage, frc).getWidth();
		g.drawString(buttonMessage, (int)pos.xpos + width / 2 - tw / 2, (int)pos.ypos + height / 2 + 8);
	}
	
	public boolean isHeldOver() {
		return isHeldOver;
	}
	
	public boolean isPressed() {
		return MouseManager.pressed;
	}
	
}
