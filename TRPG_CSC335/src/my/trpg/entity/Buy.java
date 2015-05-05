package my.trpg.entity;

import my.trpg.entity.UnitFactory.Type;
import my.trpg.items.Item;



public class Buy {

	private Type type;
	private Item item;
	
	public Buy (Type t) {
		type = t;
	}
	
	public Buy (Item i) {
		item = i;
	}
	
	public Type getType() {
		return type;
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getCost() {
		switch (type) {
			case ARCHER: return Archer.getCost();
			case MERCENARY: return Mercenary.getCost();
			default: return Unit.getCost();
		}
	}
}
