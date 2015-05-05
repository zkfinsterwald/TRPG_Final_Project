package my.trpg.entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import my.gop.main.SpriteSheet;
import my.trpg.generator.Tile;
import my.trpg.main.Animator;
import my.trpg.res.Assets;

public class Mercenary extends Unit{
	
	private static int cost = 15;
	
	public Mercenary(Tile tile, Player.Alignment standing ) {
		super(tile, standing);
		super.init();
		this.defense = 2;
		this.offense = 6;
		this.attack_range = 1;
		this.move_range = 3;
		this.max_health = 12;
		this.health = this.max_health;
		state = AnimationState.IDLE;
		init();
	}

	public void init() {
		SpriteSheet sprites = Assets.mercenary_bad;
		
		switch(standing){
			case GOOD: sprites = Assets.mercenary_good; break;
			case NEUTRAL: sprites = Assets.mercenary_good; break;
			case BAD: sprites = Assets.mercenary_bad; break;
		}
		
		listUp = new ArrayList<BufferedImage>();
		listDown = new ArrayList<BufferedImage>();
		listRight = new ArrayList<BufferedImage>();
		listLeft = new ArrayList<BufferedImage>();
		listIdle = new ArrayList<BufferedImage>();
		
		listUp.add(sprites.getTile(0, 576 , 64 , 64));
		listUp.add(sprites.getTile(64, 576 , 64 , 64));
		listUp.add(sprites.getTile(64*2, 576 , 64 , 64));
		listUp.add(sprites.getTile(64*3, 576 , 64 , 64));
		listUp.add(sprites.getTile(64*4, 576 , 64 , 64));
		listUp.add(sprites.getTile(64*5, 576 , 64 , 64));
		listUp.add(sprites.getTile(64*6, 576 , 64 , 64));
		listUp.add(sprites.getTile(64*7, 576 , 64 , 64));
		listUp.add(sprites.getTile(64*8, 576 , 64 , 64));

		listDown.add(sprites.getTile(0, 704 , 64 , 64));
		listDown.add(sprites.getTile(64, 704 , 64 , 64));
		listDown.add(sprites.getTile(64*2, 704 , 64 , 64));
		listDown.add(sprites.getTile(64*3, 704 , 64 , 64));
		listDown.add(sprites.getTile(64*4, 704 , 64 , 64));
		listDown.add(sprites.getTile(64*5, 704 , 64 , 64));
		listDown.add(sprites.getTile(64*6, 704 , 64 , 64));
		listDown.add(sprites.getTile(64*7, 704 , 64 , 64));
		listDown.add(sprites.getTile(64*8, 704 , 64 , 64));
		
		listRight.add(sprites.getTile(0, 768 , 64 , 64));
		listRight.add(sprites.getTile(64, 768 , 64 , 64));
		listRight.add(sprites.getTile(64*2, 768 , 64 , 64));
		listRight.add(sprites.getTile(64*3, 768 , 64 , 64));
		listRight.add(sprites.getTile(64*4, 768 , 64 , 64));
		listRight.add(sprites.getTile(64*5, 768 , 64 , 64));
		listRight.add(sprites.getTile(64*6, 768 , 64 , 64));
		listRight.add(sprites.getTile(64*7, 768 , 64 , 64));
		listRight.add(sprites.getTile(64*8, 768 , 64 , 64));
		
		listLeft.add(sprites.getTile(0, 640 , 64 , 64));
		listLeft.add(sprites.getTile(64, 640 , 64 , 64));
		listLeft.add(sprites.getTile(64*2, 640 , 64 , 64));
		listLeft.add(sprites.getTile(64*3, 640 , 64 , 64));
		listLeft.add(sprites.getTile(64*4, 640 , 64 , 64));
		listLeft.add(sprites.getTile(64*5, 640 , 64 , 64));
		listLeft.add(sprites.getTile(64*6, 640 , 64 , 64));
		listLeft.add(sprites.getTile(64*7, 640 , 64 , 64));
		listLeft.add(sprites.getTile(64*8, 640 , 64 , 64));
		
		listIdle.add(sprites.getTile(0, 704 , 64 , 64));
		listIdle.add(sprites.getTile(64, 704 , 64 , 64));
				
		ani_up = new Animator(listUp);
		ani_up.setSpeed(180);
		ani_up.play();
		
		ani_down = new Animator(listDown);
		ani_down.setSpeed(180);
		ani_down.play();
		
		ani_right = new Animator(listRight);
		ani_right.setSpeed(400);
		ani_right.play();
	
		ani_left = new Animator(listLeft);
		ani_left.setSpeed(180);
		ani_left.play();
		
		ani_idle = new Animator(listIdle);
		ani_idle.setSpeed(180);
		ani_idle.play();
		

	}

	@Override
	public void levelUp() {
		max_health += 5;
		health = max_health;
		offense += 3;
		defense += 2;
		level++;
	}

	public static int getCost() {
		return cost;
	}

}
