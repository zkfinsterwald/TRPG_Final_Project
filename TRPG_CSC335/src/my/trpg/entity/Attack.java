package my.trpg.entity;

public class Attack {
	
	private Unit attacker;
	private Unit victim;
	
	public Attack(Unit a, Unit v) {
		attacker = a;
		victim = v;
	}
	
	public Unit getAttacker() {
		return attacker;
	}
	public Unit getVictim() {
		return victim;
	}
}
