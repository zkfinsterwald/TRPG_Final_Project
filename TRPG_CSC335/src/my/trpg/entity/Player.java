package my.trpg.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import my.trpg.entity.UnitFactory.Type;
import my.trpg.gamestate.TrpgLevelLoader;
import my.trpg.generator.Map;
import my.trpg.generator.Tile;





abstract public class Player {

	protected Tile base;
	protected int coins;
	public String name;
	protected boolean isTurn;
	protected List<Unit> units;
	protected TrpgLevelLoader model;
	protected UnitFactory factory;
	protected static Random rand = new Random();
	protected int turnsLeft;
	protected int turnCnt;
	
	public enum Alignment {GOOD, BAD, NEUTRAL};
	Alignment standing;
	
	protected Map map;
	public Player() {
		
	}
	
	public Player (String name, Map map, Tile base, Alignment standing) {
		this.map = map;
		this.name = name;
		this.standing = standing;
		this.factory = new UnitFactory();
		this.units = new ArrayList<Unit>();
		this.base = base;
		this.coins = 1000;
		turnCnt = 5;
		turnsLeft = turnCnt;
		buyUnit(Type.MERCENARY);
		buyUnit(Type.MERCENARY);
		buyUnit(Type.MERCENARY);
		buyUnit(Type.MERCENARY);
		buyUnit(Type.MERCENARY);
		this.coins = 50;
	}
	
	public boolean moveUnit(Unit unit, Tile location) {
		return unit.move(location);
	}

	
	public boolean attackUnit(Unit attacker, Unit victim) {
		
		if (victim == null || attacker == null)
			return false;
		
		double distX, distY;
		int distance;
		distX = Math.abs(attacker.getTile().x-victim.getTile().x);
		distY = Math.abs(attacker.getTile().y-victim.getTile().y);
		distance = (int) Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
		
		if (distance >= attacker.getAttackRange())
			return false;
		
		attacker.attack(victim);
		return true;
	}
	
	public boolean buyUnit(Type type) {
			for (int i = -2; i <= 2; i++) {
				for (int j = -2; j <= 2; j++) {
					int x = base.getRow() + j;
					int y = base.getCol() + i;
					Tile tile = null;
					try{
					if ( (x >= 0 && x < map.MAP_WIDTH) && (y >= 0 && y < map.MAP_HEIGHT) )
						tile = map.tiles.getTile(x, y);
					} catch (NullPointerException e) {System.out.println(x +","+ y);}
					
					if (tile != null) {
						if (tile.isEmpty()) {
							Unit u = factory.createUnit(type, tile, standing);
							if (coins >= u.getCost()) {
								units.add(u);
								turnCnt++;
								turnsLeft--;
								coins -= u.getCost();
								return true;
							}
							return false;
						}
					}
				}
			}
			
			return false;
	}
	
	public Unit getUnit(int x, int y) {
		for (Unit u : units)
			if (u.getTile().x == x && u.getTile().y == y) return u;
		
		return null;
	}
	
	public List<Unit> getUnitList(){
		return units;
	}
	
	public void tick(double deltaTime) {
		checkTurn();
		removeDead();
		//checkTurn();
	}
	
	public void removeDead() {
		for (Unit unit : units) {
			unit.tick();
			if (unit.getHealth() == 0) {
				units.remove(unit);
				unit.getTile().removeUnit();
				unit.getTile().setEmpty(true);
				break;
			}
		}
	}
	
	public void render(Graphics2D g) {
		for(Unit unit : units) {
			unit.render(g);
		}
	}
	
	public Tile getBase() {
		return base;
	}
	
	public void checkTurn(){
		if (turnsLeft <= 0){
			endTurn();
		}
		//startTurn();
	}
	
	public boolean isTurn(){
		return isTurn;
	}
	
	public void endTurn() {
	//	System.out.println(name + " Turn is Over.");
		isTurn = false;
	}
	public void startTurn() {
		System.out.println(name + "'s Turn!");
		turnsLeft = turnCnt;
		isTurn = true;
	}
	public int getCoins(){
		return coins;
	}
	public int getNumUnits() {
		return units.size();
	}
}
