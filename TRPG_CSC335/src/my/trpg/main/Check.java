package my.trpg.main;

import java.awt.Point;

import my.trpg.generator.Tile;
import my.trpg.generator.TileManager;

public class Check {
	
	public static boolean CollisionCursorBlock(Point p1, Point p2) {
		for (Tile block : TileManager.tiles) {
			if(block.isSolid()) {
				if(block.contains(p1) || block.contains(p2)) {
					return true;
				}
			}
		}
		return false;
	}
}
