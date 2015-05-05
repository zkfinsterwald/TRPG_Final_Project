package my.trpg.entity;

import java.util.ArrayList;
import java.util.List;

import my.gop.main.Vector2F;
import my.trpg.generator.Map;
import my.trpg.generator.Tile;



public class EnemyAI extends Player implements Strategy {

	public EnemyAI (String name, Map map, Tile base, Alignment standing) {
		super(name, map, base, standing);
	}

	@Override
	public List<Move> getMoves(List<Unit> enemyUnits) {
		
		List<Move> moves = new ArrayList<Move>();
		
		for (Unit u : units) {
			int j = Math.abs(rand.nextInt())%4;
            
			Move m = new Move(null, 'w');
			char tile;
			
			if(j == 0){
				tile = 'a';
				m = new Move(u, tile);
			}
			else if(j == 1){
				tile = 'd';
				m = new Move(u, tile);
			}
            
			else if(j == 2){
				tile = 's';
				m = new Move(u, tile);
			}
			else if(j == 3) {
				tile = 'w';
				m = new Move(u, tile);
			}
            
			moves.add(m);
		}
		
		return moves;
	}
   
	
   public List<Move> getSmartMoves(List<Unit> enemyUnits) {
		
		List<Move> moves = new ArrayList<Move>();
		
		for (Unit u : units) {
			
			Unit target = enemyUnits.get(Math.abs(rand.nextInt()%enemyUnits.size()));
			char directNS = 'n';
			char directEW = 'n';
			Tile tileNS = null;
			Tile tileEW = null;
			
			//TODO: adjust target for Capture the Flag and King of the Hill
			
			if (target.getTile().getRow() > u.getTile().getRow()) {
				tileEW = map.tiles.getTile(u.getTile().getRow()+1, u.getTile().getCol());
				directEW = 'd';
			}
			else if (target.getTile().getRow() < u.getTile().getRow()) {
				tileEW = map.tiles.getTile(u.getTile().getRow()-1, u.getTile().getCol());
				directEW = 'a';
			}
			
			if (target.getTile().getCol() > u.getTile().getCol()) {
				tileNS = map.tiles.getTile(u.getTile().getRow(), u.getTile().getCol()+1);
				directNS = 's';
			}
			else if (target.getTile().getCol() < u.getTile().getCol()) {
				tileNS = map.tiles.getTile(u.getTile().getRow(), u.getTile().getCol()-1);
				directNS = 'w';
			}
			
			if (rand.nextBoolean()) {
				if (tileEW != null && tileEW.isEmpty() && !tileEW.isSolid()) 
					moves.add(new Move(u, directEW));
				else if (tileNS != null && tileNS.isEmpty() && !tileNS.isSolid()) 
					moves.add(new Move(u, directNS));
			}
			else {
				if (tileNS != null && tileNS.isEmpty() && !tileNS.isSolid()) 
					moves.add(new Move(u, directNS));
				else if (tileEW != null && tileEW.isEmpty() && !tileEW.isSolid()) 
					moves.add(new Move(u, directEW));
			}
			
			if ( (tileEW == null || !tileEW.isEmpty() || tileEW.isSolid()) &&
					(tileNS == null || !tileNS.isEmpty() || tileNS.isSolid()) ) {
				if (rand.nextBoolean()) {
					if (directEW == 'a') moves.add(new Move(u, 'd'));
					else if (directEW == 'd') moves.add(new Move(u, 'a'));
					else {
						if (directNS == 'w') moves.add(new Move(u, 's'));
						else moves.add(new Move(u, 'w'));
					}
				}
				else {
					if (directNS == 'w') moves.add(new Move(u, 's'));
					else if (directNS == 's') moves.add(new Move(u, 'w'));
					else {
						if (directEW == 'a') moves.add(new Move(u, 'd'));
						else if (directEW == 'd') moves.add(new Move(u, 'a'));
					}
				}
			}
		}
		return moves; 
		
   }       

	@Override
	public List<Attack> getAttacks(List<Unit> enemyUnits) {
		
		List<Attack> attacks = new ArrayList<Attack>();
		
		for (Unit u : units) {
			for (Unit e : enemyUnits) {
				double distance = Vector2F.getDistanceOnScreen(u.pos, e.pos);;
				if (distance <= u.getAttackRange()) {
					attacks.add(new Attack(u, e));
					break;
				}
			}
		}
		
		return attacks;
	}
	
	@Override
	public List<Buy> getBuys() {
		
		List<Buy> buys = new ArrayList<Buy>();
		return buys;
	}
}
