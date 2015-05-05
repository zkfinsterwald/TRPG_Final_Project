package my.trpg.entity;

import java.util.List;



public interface Strategy {

	public List<Move> getMoves(List<Unit> enemyUnits);
	public List<Move> getSmartMoves(List<Unit> enemyUnits);
	public List<Attack> getAttacks(List<Unit> enemyUnits);
	public List<Buy> getBuys();
}