package my.trpg.items;

import my.trpg.entity.Unit;


public class LargePotion extends Item {
	
	public LargePotion(){
		name = "Large Potion";
		effect = "Increases a units health by 15";
		cost = 20;
		boost = 15;
	}
	
	public void use(Unit unit){
		unit.heal(boost);
	}
}
