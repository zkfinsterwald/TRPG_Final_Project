package my.trpg.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import my.gop.main.Vector2F;
import my.trpg.gamestate.ExitState;
import my.trpg.gamestate.GameStateButton;
import my.trpg.generator.Map;
import my.trpg.generator.Tile;
import my.trpg.main.Main;
import my.trpg.manangers.MouseManager;
import my.trpg.res.Assets;

public class Human extends Player implements KeyListener {
	
	Vector2F pos = new Vector2F();

	private static boolean up, down, left, right, move, buy, attack;
	private static int width = Tile.TILE_SIZE;
	private static int height = Tile.TILE_SIZE;
	private static Tile onTile;
	private static Unit selectedUnit;
	private static boolean hasSelectedUnit;
	private int row;
	private int col;
	
	private static BufferedImage mapCursor;
	
	
	public Human(){
		
	}
	
	public Human(String name, Map map, Tile base, Alignment standing ) {
		super(name, map, base, standing);
		turnCnt = 25; // FOR DEBUGGING
		onTile = base;
		row = onTile.getRow();
		col = onTile.getCol();
		pos = onTile.pos;
		hasSelectedUnit = false;
		mapCursor = Assets.getCursor_1();
	}

	public void tick(double deltaTime) {
		super.tick(deltaTime);
		
		/*UP*/
		if (up) {
			if(!map.tiles.getTile(row, col - 1).isSolid()){
				onTile = map.tiles.getTile(row, col - 1);
				row = onTile.getRow();
				col = onTile.getCol();
				pos = onTile.pos;
				up = false;
			}
		}
		
		/*DOWN*/
		if (down) {
			if(!map.tiles.getTile(row, col + 1).isSolid()) {
				onTile = map.tiles.getTile(row, col + 1);
				row = onTile.getRow();
				col = onTile.getCol();
				pos = onTile.pos;
				down = false;
			}
		}
		
		/*LEFT*/
		if (left) {
			if(!map.tiles.getTile(row - 1, col).isSolid()) {
				onTile = map.tiles.getTile(row - 1, col);
				row = onTile.getRow();
				col = onTile.getCol();
				pos = onTile.pos;
				left = false;
			}
		}
		
		/*RIGHT*/
		if (right) {
			if(!map.tiles.getTile(row + 1, col).isSolid()){
				onTile = map.tiles.getTile(row + 1, col);
				row = onTile.getRow();
				col = onTile.getCol();
				pos = onTile.pos;
				right = false;
			}
		}
		/*ENTER*/
		if(move && isTurn) {
			if(hasSelectedUnit && units.contains(selectedUnit)){
				selectedUnit.move(onTile);
				turnsLeft--;
				selectedUnit = null;
				hasSelectedUnit = false;
			} else if (onTile.hasUnit()) {
				System.out.println("Unit!");
				selectedUnit = onTile.getUnit();
				hasSelectedUnit = true;
			}
			move = false;
		}
		/*BUY*/
		if(buy && isTurn) {
			if(onTile.isEmpty()){
				units.add(new Mercenary(onTile, standing));
				turnsLeft--;
				buy = false;
			} else {
				buy = false;
			}
		}
		
		if(attack && isTurn) {
			if( hasSelectedUnit && onTile.hasUnit() && !units.contains(onTile.getUnit()) ){
				selectedUnit.attack(onTile.getUnit());
				turnsLeft--;
				attack = false;
				selectedUnit = null;
				hasSelectedUnit = false;
			} else {
				attack = false;
			}
		}
	}

	public void render(Graphics2D g) {
		super.render(g);
		g.drawImage(mapCursor, (int) onTile.pos.xpos, (int) onTile.pos.ypos, width, height, null);
		if(onTile.hasUnit()){
			g.drawString("Health: " + onTile.getUnit().getHealth(), 150, 990);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
	
			if (key == KeyEvent.VK_W) {
				up = true;
			}
			if (key == KeyEvent.VK_S) {
				down = true;
			}
			if (key == KeyEvent.VK_A) {
				left = true;
			}
			if (key == KeyEvent.VK_D) {
				right = true;
			}
			if (key == KeyEvent.VK_ENTER){
				move = false;
			}
			if (key == KeyEvent.VK_B) {
				buy = false;
			}
			
			if (key == KeyEvent.VK_X) {
				attack = false;
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
	
			if (key == KeyEvent.VK_W) {
				up = false;
			}
			if (key == KeyEvent.VK_S) {
				down = false;
			}
			if (key == KeyEvent.VK_A) {
				left = false;
			}
			if (key == KeyEvent.VK_D) {
				right = false;
			}
			
			if (key == KeyEvent.VK_ENTER){
				move = true;
			}
			
			if (key == KeyEvent.VK_B) {
				buy = true;
			}
			
			if (key == KeyEvent.VK_X) {
				attack = true;
			}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}


}
