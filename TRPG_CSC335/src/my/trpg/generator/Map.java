package my.trpg.generator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import my.gop.main.LoadImage;
import my.gop.main.Vector2F;
import my.trpg.generator.Tile.TileType;
import my.trpg.items.SmallPotion;
import my.trpg.res.Assets;

public class Map {

	public static final int MAP_WIDTH = 60;
	public static final int MAP_HEIGHT = 34;

	public TileManager tiles = new TileManager();
	public Map() {

	}

	public void init() {
		
		BufferedImage map = null;

		try {
			map = LoadImage.loadImageFrom(Assets.class, "grass_map.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int x = 0; x < MAP_WIDTH; x++) {
			for (int y = 0; y < MAP_HEIGHT; y++) {
				int color = map.getRGB(x, y);
				switch (color & 0xFFFFFF) {
				case 0x808080: // gray - stone
					TileManager.tiles.add(new Tile(new Vector2F(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE), TileType.STONE_1, x, y));
					tiles.getTile(x, y).setItem(new SmallPotion());
					break;
				case 0x0000ff: // WATER
					TileManager.tiles.add(new Tile(new Vector2F(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE), TileType.WATER_1, x, y));
					break;
				case 0x00007f: // WATER LEFT SIDE
					TileManager.tiles.add(new Tile(new Vector2F(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE), TileType.WATER_1L, x, y));
					break;
				case 0x00ffff: // WATER RIGHT SIDE
					TileManager.tiles.add(new Tile(new Vector2F(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE), TileType.WATER_1R, x, y));
					break;
				case 0x00ff00: // green - grass
					TileManager.tiles.add(new Tile(new Vector2F(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE), TileType.GRASS_1, x, y));
					break;
					
				case 0xffff00: // yellow - sand
					TileManager.tiles.add(new Tile(new Vector2F(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE), TileType.SAND_1, x, y));
					break;
					
				case 0xffffff: // white - path
					TileManager.tiles.add(new Tile(new Vector2F(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE), TileType.PATH_1, x, y));
					break;

				}
			}
		}
	}
	
	/*public Item getRandomItem(){
	//	Random rand = new Random();
	} */

	public void tick(double deltaTime) {
		tiles.tick(deltaTime);
	}

	public void render(Graphics2D g) {
		tiles.render(g);
	}
	
	public TileManager getTiles() {
		return tiles;
	}
}
