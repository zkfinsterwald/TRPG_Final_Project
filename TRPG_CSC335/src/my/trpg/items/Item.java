package my.trpg.items;

import java.awt.image.BufferedImage;

public abstract class Item {

	protected String name;
	protected String effect;
	protected int cost;
	protected int boost;
	protected BufferedImage itemImg;
	
	public Item(){
		name = "";
		effect = "";
		cost = 0;
		boost = 0;
		itemImg = null;
	}
	
	public BufferedImage getItemImg(){
		return itemImg;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEffect(){
		return effect;
	}
	
	public int getCost(){
		return cost;
	}
	
	public int getBoost(){
		return boost;
	}
}
