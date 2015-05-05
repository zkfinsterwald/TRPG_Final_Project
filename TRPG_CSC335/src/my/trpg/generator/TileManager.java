package my.trpg.generator;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class TileManager {
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();
	public TileManager() {
		// TODO Auto-generated constructor stub
	}
	
	public Tile getTile(int row, int col) {
		
		for(Tile tile: tiles){
			if(tile.getRow() == row && tile.getCol() == col) {
				return tile;
			}
		}
		System.out.println("No tile found!");
		return null;
		
	}
	
	public void tick(double deltaTime) {
		for(Tile tile : tiles) {
			tile.tick(deltaTime);
		}
	}
	
	public void render(Graphics2D g) {
		for(Tile tile : tiles) {
			tile.render(g);
		}
	}

}
