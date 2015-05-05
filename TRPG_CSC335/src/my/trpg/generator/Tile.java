package my.trpg.generator;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import my.gop.main.Vector2F;
import my.trpg.entity.Unit;
import my.trpg.items.Item;
import my.trpg.res.Assets;



public class Tile extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1310262366695246037L;
	private BufferedImage tile;
	public Vector2F pos = new Vector2F();
	public static final int TILE_SIZE = 32;
	private TileType type;
	private boolean isSolid;
	private int row;
	private int col;
	private Unit unit;
	private boolean hasUnit;

	// merge from  final //
	private boolean empty;
	private Item item;
	private boolean hasItem;
	
	public Tile(Vector2F pos, TileType type, int row, int col) {
		this.pos = pos;
		this.type = type;
		this.row = row;
		this.col = col;
		hasUnit = false;
		hasItem = false;
		empty = true;
		init();
	}
	
	
	public void init() {
		switch(type) {
		case GRASS_1:
			tile = Assets.getGrass_1();
			isSolid = false;
			break;
		case WATER_1:
			tile = Assets.getWater_1();
			isSolid = true;
			break;
		case WATER_1L:
			tile = Assets.getWater_1l();
			isSolid = true;
			break;
		case WATER_1R:
			tile = Assets.getWater_1r();
			isSolid = true;
			break;
		case STONE_1:
			tile = Assets.getStone_1();
			isSolid = true;
			break;
		case SAND_1:
			tile = Assets.getSand_1();
			isSolid = false;
			break;
		case PATH_1:
			tile = Assets.getPath_1();
			isSolid = false;
			break;
		}
	}

	public void tick(double deltaTime) {
		setBounds((int)pos.xpos, (int)pos.ypos, TILE_SIZE, TILE_SIZE);
	}

	public void render(Graphics2D g) {
		g.drawImage(tile, (int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, TILE_SIZE, TILE_SIZE, null);
		if(hasItem){
			g.drawImage(item.getItemImg(), (int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, TILE_SIZE, TILE_SIZE, null );
		}
	}
	
	
	public enum TileType{
		STONE_1,
		WATER_1,
		WATER_1L,
		WATER_1R,
		GRASS_1,
		SAND_1,
		PATH_1
	}

	public boolean isSolid() {
		return isSolid;
	}

	public boolean isEmpty() {
		return empty;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
		hasItem = true;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		System.out.println("Unit set at " + this);
		this.unit = unit;
		hasUnit = true;
		empty = false;
	}
	
	public void removeUnit(){
		this.unit = null;
		hasUnit = false;
		empty = true;
	}

	public boolean hasUnit() {
		return hasUnit;
	}
	
	@Override
	public String toString() {
		return "("+row+","+col+")";
	}

}
