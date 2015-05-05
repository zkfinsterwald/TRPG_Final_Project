package my.trpg.entity;

import my.trpg.generator.Tile;

public class Move {

	private Unit unit;
	private Tile tile;
	private char direction;
	
	public Move(Unit u, Tile t) {
		u.move(t);
	}
	
	public Move(Unit u, char d) {
		this.direction = d;
		this.unit = u;
	}
	
	public Unit getUnit() {
		return unit;
	}
	public Tile getTile() {
		return tile;
	}
}