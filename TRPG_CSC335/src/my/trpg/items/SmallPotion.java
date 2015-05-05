package my.trpg.items;

import my.trpg.entity.Unit;
import my.trpg.res.Assets;

public class SmallPotion extends Item {

	public SmallPotion(){
		name = "Small Potion";
		effect = "Increases a units health by 5";
		cost = 10;
		boost = 5;
		itemImg = Assets.getSmall_health();
	}
	
	public void use(Unit unit){
		unit.heal(boost);
	}
}
