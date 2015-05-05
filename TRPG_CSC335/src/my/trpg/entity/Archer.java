package my.trpg.entity;

import my.trpg.generator.Tile;


public class Archer extends Unit {
	
	private static int cost = 12;
	
	public Archer(Tile tile, Player.Alignment standing) {
		
		super(tile, standing); //set location, armor, level, and location
		
		this.defense = 2;
		this.offense = 6;
		this.attack_range = 5;
		this.move_range = 3;
		this.max_health = 7;
		this.health = this.max_health;
	}
	
	public void levelUp() {
		max_health += 3;
		health = max_health;
		offense += 2;
		defense += 1;
		level++;
	}
	
	public static int getCost() {
		return cost;
	}
}
