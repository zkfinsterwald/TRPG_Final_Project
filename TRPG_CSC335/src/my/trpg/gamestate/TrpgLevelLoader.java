package my.trpg.gamestate;

import java.awt.Graphics2D;

import my.trpg.entity.EnemyAI;
import my.trpg.entity.Human;
import my.trpg.entity.Player;
import my.trpg.generator.Map;
import my.trpg.main.Main;
import my.trpg.manangers.MouseManager;

public class TrpgLevelLoader extends GameState {
	
	Map map;
	Human human;
	EnemyAI enemyAI;
	Player player1;
	Player player2;
	Player currentPlayer;
	Player nextPlayer;
	
	GameStateButton exit;
	MouseManager mouse;

	public TrpgLevelLoader(GameStateManager gsm) {
		super(gsm);
		exit = new GameStateButton(Main.width - 200, Main.height - 100, new ExitState(gsm), gsm, "Exit");
		mouse = new MouseManager();
	}

	@Override
	public void init() {
		map = new Map();
		map.init();
		human = new Human("Zac", map, map.tiles.getTile(10, 20), Player.Alignment.GOOD);
		currentPlayer = human;
		enemyAI = new EnemyAI("BOT", map, map.tiles.getTile(40,20), Player.Alignment.BAD);
		nextPlayer = enemyAI;
		
		human.startTurn();
	}
	
	public void play(){
		/*Determine whose turn it is*/
		if(!currentPlayer.isTurn()){
			Player temp = nextPlayer;
			nextPlayer = currentPlayer;
			currentPlayer = temp;
			currentPlayer.startTurn();
			System.out.println(nextPlayer.name + "'s Turn over!");
		}
		
		if(human.getUnitList().size() == 0){
			System.out.println("You Lost!");
		}
		if(enemyAI.getUnitList().size() == 0){
			System.out.println("All enemy units killed! You WIN!");
			System.exit(0);
		}
	}

	@Override
	public void tick(double deltaTime) {
		play();
		map.tick(deltaTime);
		human.tick(deltaTime);
		enemyAI.tick(deltaTime);
		mouse.tick();
		exit.tick();
	}

	@Override
	public void render(Graphics2D g) {
		map.render(g);
		human.render(g);
		enemyAI.render(g);
		exit.render(g);
		mouse.render(g);
	}

}
