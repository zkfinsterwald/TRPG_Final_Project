package my.trpg.res;

import java.awt.image.BufferedImage;

import my.gop.main.LoadImage;
import my.gop.main.SpriteSheet;

public class Assets {

	SpriteSheet blocks = new SpriteSheet();
	public static SpriteSheet mercenary_bad = new SpriteSheet();
	public static SpriteSheet mercenary_good = new SpriteSheet();
	public static SpriteSheet cursor = new SpriteSheet();

	// Blocks
	public static BufferedImage grass_1;
	public static BufferedImage water_1;
	public static BufferedImage water_1l;
	public static BufferedImage water_1r;
	public static BufferedImage stone_1;
	public static BufferedImage sand_1;
	public static BufferedImage path_1;
	
	// ITEMS
	public static BufferedImage small_health;
	
	// Map Cursor
	public static BufferedImage cursor_1;
	
	// Mouse Cursor
	public static BufferedImage mouse_unpressed;
	public static BufferedImage mouse_pressed;
	
	public static BufferedImage button_pressed;
	public static BufferedImage button;

	public void init() {
		blocks.setSpriteSheet(LoadImage.loadImageFrom(Assets.class,
				"map_sprites.png"));
		
		//MERCENARY
		mercenary_bad.setSpriteSheet(LoadImage.loadImageFrom(Assets.class, "mercenary_bad.png"));
		mercenary_good.setSpriteSheet(LoadImage.loadImageFrom(Assets.class, "mercenary_good.png"));
		
		cursor.setSpriteSheet(LoadImage.loadImageFrom(Assets.class, "cursor.png"));
		
		//Map cursor
		cursor_1 = cursor.getTile(0, 0, 16, 16);
		
		// Mouse cursor
		mouse_unpressed = blocks.getTile(0, 64, 32, 32);
		mouse_pressed = blocks.getTile(32, 64, 32, 32);
		
		// blocks
		grass_1 = blocks.getTile(0, 0, 16, 16);
		water_1 = blocks.getTile(16, 0, 16, 16);
		water_1l = blocks.getTile(16, 16, 16, 16);
		water_1r = blocks.getTile(16, 32, 16, 16);
		stone_1 = blocks.getTile(32, 0, 16, 16);
		sand_1 = blocks.getTile(48, 0, 16, 16);
		path_1 = blocks.getTile(64, 0, 16, 16);
		//Items
		small_health = blocks.getTile(0, 160, 16, 16);
		
		// Buttons
		button_pressed = blocks.getTile(0, 96, 190, 49);
		button = blocks.getTile(191, 96, 190, 49);
	}

	public Assets() {
		// TODO Auto-generated constructor stub
	}

	/* BLOCK GETTERS */
	public static BufferedImage getGrass_1() {
		return grass_1;
	}

	public static BufferedImage getWater_1() {
		return water_1;
	}

	public static BufferedImage getStone_1() {
		return stone_1;
	}

	public static BufferedImage getSand_1() {
		return sand_1;
	}

	public static BufferedImage getPath_1() {
		return path_1;
	}

	public static BufferedImage getWater_1l() {
		return water_1l;
	}

	public static BufferedImage getWater_1r() {
		return water_1r;
	}
	
	public static BufferedImage getCursor_1() {
		return cursor_1;
	}
	public static BufferedImage getMouse_pressed() {
		return mouse_pressed;
	}
	public static BufferedImage getMouse_unpressed() {
		return mouse_unpressed;
	}
	
	public static BufferedImage getButton() {
		return button;
	}
	
	public static BufferedImage getButton_pressed() {
		return button_pressed;
	}
	
	public static BufferedImage getSmall_health() {
		return small_health;
	}

}
