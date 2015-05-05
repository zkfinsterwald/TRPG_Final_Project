package my.trpg.entity;

import my.trpg.generator.Tile;

public class UnitFactory {
	
	public static enum Type { ARCHER, MERCENARY, KNIGHT, MEDIC, THIEF }
	public UnitFactory() {}
	
	public Unit createUnit(Type type, Tile location, Player.Alignment standing) {
		switch (type) {
			case ARCHER: return new Archer(location, standing);
			case MERCENARY: return new Mercenary(location, standing);
			default: return null;
		}
	}
}
