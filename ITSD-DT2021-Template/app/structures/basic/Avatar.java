package structures.basic;

import akka.actor.ActorRef;
import commands.BasicCommands;

public class Avatar extends Creature{
	int attack;
	int health;
	Player player;
	
	public Avatar() {
		
	}
	public Avatar(int id, UnitAnimationSet animations, ImageCorrection correction) {
		super(id,animations,correction);
	}
	public Avatar(int id, UnitAnimationSet animations, ImageCorrection correction,Tile currentTile) {
		super(id,animations,correction,currentTile);
	}
	public Avatar(int id, UnitAnimationSet animations, ImageCorrection correction,Tile currentTile,Player player) {
		super(id,animations,correction,currentTile);
		this.player = player;
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
			if (this.player instanceof Player) {
				BasicCommands.setPlayer1Health(out, ((Avatar) target).player);
			} else {
				BasicCommands.setPlayer2Health(out, ((Avatar) target).player);
			}
			if (this.health <= 0) {
				BasicCommands.playUnitAnimation(out, this, UnitAnimationType.death);
				BasicCommands.deleteUnit(out, this);
				//end the game
				return;
			}
		}

	}
}
