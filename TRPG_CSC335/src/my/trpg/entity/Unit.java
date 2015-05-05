package my.trpg.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import my.gop.main.Vector2F;
import my.trpg.generator.Tile;
import my.trpg.items.Item;
import my.trpg.main.Animator;

public abstract class Unit {
    
    protected Tile onTile;
    protected Tile nextTile;
    protected int health;
    protected int max_health;
    protected List<Item> items;
    protected int offense;
    protected int defense;
    protected int armor;
    protected int move_range;
    protected int attack_range;
    protected int level;
    protected int experience;    
    
	Vector2F pos = new Vector2F();
	protected int width = 32;
	protected int height = 32;
	private static boolean up, down, left, right;
    
    // Animation stuff
	protected AnimationState state;
	protected ArrayList<BufferedImage> listUp;
	protected ArrayList<BufferedImage> listDown;
	protected ArrayList<BufferedImage> listRight;
	protected ArrayList<BufferedImage> listLeft;
	protected ArrayList<BufferedImage> listIdle;
	protected Animator ani_up;
	protected Animator ani_down;
	protected Animator ani_right;
	protected Animator ani_left;
	protected Animator ani_idle;
	
    
    private static Random rand = new Random();
    
    protected Player.Alignment standing;
    
 
    
    //abstract methods
    abstract public void levelUp();
    
    //implemented methods
    public Unit(Tile tile, Player.Alignment standing) {
    	this.standing = standing;
    	tile.setUnit(this);
    	pos = tile.pos;
    	this.onTile = tile;
    	this.level = 1;
    	this.armor = 0;
    	this.items = new ArrayList<Item>();
    	init();
    }
    
    public void init() {
    	up = false;
    	down = false;
    	right = false;
    	left = false;
    }
    
    public void attack(Unit unit) {
    	
    	double distance = Vector2F.getDistanceOnScreen(onTile.pos, unit.getTile().pos);
        int offense = (rand.nextInt()%(this.offense/3)) + (this.offense/3);
        int defense = unit.defend();
        
        int damage = 0;
        
        if (offense > defense)
            damage = offense-defense;
        if(distance > attack_range*Tile.TILE_SIZE){
        	System.out.println("Cannot attack, too far!");
        } else {
        	unit.takeDamage(12);
        }
    }
    
    public int defend() {
        return (rand.nextInt()%(defense+armor)) + (rand.nextInt()%(defense/2));
    }
    
    protected void takeDamage(int dmg){
        health -= dmg;
        if (health < 0)
            health = 0;
        System.out.println("Health: " + health);
    }
    
    public void heal(int hp){
        health += hp;
        if (health > max_health)
            health = max_health;
    }
    
    public boolean move(Tile tile) {

        if (tile.isSolid() || !tile.isEmpty())
           return false;
      

       double distance = Vector2F.getDistanceOnScreen(onTile.pos, tile.pos);
        
        if (distance > this.getMoveRange()*Tile.TILE_SIZE) {
        System.out.println("Too Far");
         return false;
       } else {

        	nextTile = tile;
        	onTile.removeUnit();
            onTile = tile;
            onTile.setUnit(this);
            System.out.println("Moved to: " + onTile);
            onTile.setEmpty(false);
            pos = tile.pos;
            return true;
        }
    }
    
	public void tick() {
		
		if (up) {
			pos.ypos--;
			state = AnimationState.UP;
		}
		if (down) {
			pos.ypos++;
			state = AnimationState.DOWN;
		}
		if (left) {
			pos.xpos--;
			state = AnimationState.LEFT;
		}
		if (right) {
			pos.xpos++;
			state = AnimationState.RIGHT;
		}
		
		if (!up && !down && !right && !left) {
			state = AnimationState.IDLE;
		}
		
	}
	
	public void render(Graphics2D g) {
		
		switch(state){
		
		case UP: 
			g.drawImage(ani_up.sprite, (int)pos.xpos, (int)pos.ypos, width, height, null);
			if(up) {
				ani_up.update(System.currentTimeMillis());
			}
			break;
		case DOWN:
			g.drawImage(ani_down.sprite, (int)pos.xpos, (int)pos.ypos, width, height, null);
			if(down) {
				ani_down.update(System.currentTimeMillis());
			}
			break;
		case RIGHT:
			g.drawImage(ani_right.sprite, (int)pos.xpos, (int)pos.ypos, width, height, null);
			if(right) {
				ani_right.update(System.currentTimeMillis());
			}
			break;
		case LEFT:
			g.drawImage(ani_left.sprite, (int)pos.xpos, (int)pos.ypos, width, height, null);
			if(left) {
				ani_left.update(System.currentTimeMillis());
			}
			break;
		case IDLE:
			g.drawImage(ani_idle.sprite, (int)pos.xpos, (int)pos.ypos, width, height, null);
			ani_idle.update(System.currentTimeMillis());
			break;
		}
	}

    
    public void gainExperience(int xp) {
    	experience += xp;
    }
    
    public void removeItem(Item item) {
    	items.remove(item);
    }
    
    //getMethods
    public int getOffense() {
    	return offense;
    }
    public int getDefense() {
    	return defense;
    }
    public int getHealth() {
    	return health;
    }
    public int getArmor() {
    	return armor;
    }
    public int getAttackRange() {
    	return attack_range;
    }
    public int getMoveRange() {
    	return move_range;
    }
    public static int getCost() {
    	return 0;
    }
    public Tile getTile() {
    	return this.onTile;
    }
    public int getLocationX(){
    	return onTile.getRow();
    }
    
    public int getLocationY(){
    	return onTile.getCol();
    }
    
	public enum AnimationState {
		UP,
		DOWN,
		LEFT,
		RIGHT,
		IDLE
	}
}
