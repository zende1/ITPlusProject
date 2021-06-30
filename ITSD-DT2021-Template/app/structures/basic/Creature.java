package structures.basic;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import commands.BasicCommands;
import utils.BasicObjectBuilders;

public class Creature extends Unit {
	int attack;
	int health;

	public Creature() {

	}

	public Creature(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id, animations, correction);
	}

	public Creature(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile) {
		super(id, animations, correction, currentTile);
	}

	public Creature(int id, UnitAnimationSet animations, ImageCorrection correction, Tile currentTile, int attack,
			int health) {
		super(id, animations, correction, currentTile);
		this.attack = attack;
		this.health = health;
	}

	public Creature(int attack, int health) {
		this.attack = attack;
		this.health = health;
	}

	public void attack(ActorRef out, Creature target) {

		BasicCommands.playUnitAnimation(out, this, UnitAnimationType.attack);
		target.health -= this.attack;
		BasicCommands.setUnitHealth(out, target, target.health);
		if (target instanceof Avatar) {
			if (((Avatar) target).player instanceof Player) {
				BasicCommands.setPlayer1Health(out, ((Avatar) target).player);
			} else {
				BasicCommands.setPlayer2Health(out, ((Avatar) target).player);
			}
		}
		if (target.health <= 0) {
			BasicCommands.playUnitAnimation(out, target, UnitAnimationType.death);
			BasicCommands.deleteUnit(out, target);
			if (target instanceof Avatar) {
				// how to represent game over?
				return;
			}
		} else {// counter attack
			BasicCommands.playUnitAnimation(out, target, UnitAnimationType.attack);
			this.health -= target.attack;
			BasicCommands.setUnitHealth(out, this, this.health);
			if (this.health <= 0) {
				BasicCommands.playUnitAnimation(out, this, UnitAnimationType.death);
				BasicCommands.deleteUnit(out, this);
			}
		}

	}

	public List<Tile> showMoveTile(ActorRef out) {
		int x = this.position.tilex;
		int y = this.position.tiley;
		int[][] tiles = new int[12][2];
		List<Tile> canMoveTiles = new ArrayList<>();
		tiles[0] = new int[] { x, y + 1 };
		tiles[1] = new int[] { x + 1, y + 1 };
		tiles[2] = new int[] { x + 1, y };
		tiles[3] = new int[] { x + 1, y - 1 };
		tiles[4] = new int[] { x, y - 1 };
		tiles[5] = new int[] { x - 1, y - 1 };
		tiles[6] = new int[] { x - 1, y };
		tiles[7] = new int[] { x - 1, y + 1 };
		tiles[8] = new int[] { x, y + 2 };
		tiles[9] = new int[] { x + 2, y };
		tiles[10] = new int[] { x, y - 2 };
		tiles[11] = new int[] { x - 2, y };
		for (int i = 0; i < 12; i++) {
			if (onBoard(tiles[i][0], tiles[i][1])) {
				Tile tile = BasicObjectBuilders.loadTile(tiles[i][0], tiles[i][1]);
				canMoveTiles.add(tile);
				BasicCommands.drawTile(out, tile, 0);
			}
		}
		return canMoveTiles;

	}

	public boolean onBoard(int x, int y) {
		if ((x >= 1 && x <= 9) && (y >= 1 && y <= 5)) {
			return true;
		} else
			return false;
	}

	public void move(ActorRef out, Tile dest) {
		if (showMoveTile(out).contains(dest)) {
			BasicCommands.moveUnitToTile(out, this, dest);
			this.setPositionByTile(dest);
		}else {
			BasicCommands.addPlayer1Notification(out, "This unit can't get there", 2);
		}
	}
}
