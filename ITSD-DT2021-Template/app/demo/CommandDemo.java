package demo;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Card;
import structures.basic.EffectAnimation;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import structures.basic.UnitAnimationType;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/**
 * This class contains an illustration of calling all of the commands provided
 * in the ITSD Card Game Template
 * @author Dr. Richard McCreadie
 *
 */
public class CommandDemo {

	/**
	 * This is a demo of the various commands that can be executed
	 * 
	 * WARNING: This is a very long-running method, as it has a lot
	 * thread sleeps in it. The back-end will not respond to commands
	 * while this method is processing, e.g. if you refresh the game
	 * page or try to kill the server you will get a long delay before
	 * anything happens.
	 */
	public static void executeDemo(ActorRef out) {

		BasicCommands.addPlayer1Notification(out, "Command Demo", 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();} // these cause processing to wait for a number of milliseconds.

		// addPlayer1Notification
		BasicCommands.addPlayer1Notification(out, "addPlayer1Notification", 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawTile
		BasicCommands.addPlayer1Notification(out, "drawTile[3,2]", 2);
		Tile tile = BasicObjectBuilders.loadTile(3, 2);
		BasicCommands.drawTile(out, tile, 0);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		// drawUnit
		BasicCommands.addPlayer1Notification(out, "drawUnit", 2);
		Unit unit = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 0, Unit.class);
		unit.setPositionByTile(tile); 
		BasicCommands.drawUnit(out, unit, tile);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// setUnitAttack
		BasicCommands.addPlayer1Notification(out, "setUnitAttack", 2);
		BasicCommands.setUnitAttack(out, unit, 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// setUnitHealth
		BasicCommands.addPlayer1Notification(out, "setUnitHealth", 2);
		BasicCommands.setUnitHealth(out, unit, 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// playUnitAnimation [Move]
		BasicCommands.addPlayer1Notification(out, "playUnitAnimation [Move]", 2);
		BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.move);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// playUnitAnimation [Attack]
		BasicCommands.addPlayer1Notification(out, "playUnitAnimation [Attack]", 2);
		BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.attack);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// playUnitAnimation [Death]
		BasicCommands.addPlayer1Notification(out, "playUnitAnimation [Death]", 3);
		BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.death);
		try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}

		// deleteUnit
		BasicCommands.addPlayer1Notification(out, "deleteUnit", 2);
		BasicCommands.deleteUnit(out, unit);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawTile
		BasicCommands.addPlayer1Notification(out, "drawTile[8,4]", 2);
		Tile tile2 = BasicObjectBuilders.loadTile(8, 4);
		BasicCommands.drawTile(out, tile2, 0);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawUnit
		BasicCommands.addPlayer1Notification(out, "drawUnit", 2);
		Unit fire_spitter = BasicObjectBuilders.loadUnit(StaticConfFiles.u_fire_spitter, 1, Unit.class);
		fire_spitter.setPositionByTile(tile); 
		BasicCommands.drawUnit(out, fire_spitter, tile);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawUnit
		BasicCommands.addPlayer1Notification(out, "drawUnit", 2);
		Unit planar_scout = BasicObjectBuilders.loadUnit(StaticConfFiles.u_planar_scout, 2, Unit.class);
		planar_scout.setPositionByTile(tile2); 
		BasicCommands.drawUnit(out, planar_scout, tile2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// playProjectileAnimation
		BasicCommands.addPlayer1Notification(out, "playProjectileAnimation", 2);
		EffectAnimation projectile = BasicObjectBuilders.loadEffect(StaticConfFiles.f1_projectiles);
		BasicCommands.playUnitAnimation(out, fire_spitter, UnitAnimationType.attack);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playProjectileAnimation(out, projectile, 0, tile, tile2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		BasicCommands.playUnitAnimation(out, planar_scout, UnitAnimationType.death);

		// deleteUnit
		BasicCommands.addPlayer1Notification(out, "deleteUnit", 2);
		BasicCommands.deleteUnit(out, planar_scout);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// deleteUnit
		BasicCommands.addPlayer1Notification(out, "deleteUnit", 2);
		BasicCommands.deleteUnit(out, fire_spitter);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}


		// drawTile
		BasicCommands.addPlayer1Notification(out, "drawTile[3,2] Highlight", 2);
		BasicCommands.drawTile(out, tile, 1);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawTile
		BasicCommands.addPlayer1Notification(out, "drawTile[3,2] Red Highlight", 2);
		BasicCommands.drawTile(out, tile, 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawTile
		BasicCommands.addPlayer1Notification(out, "drawTile[3,2]", 2);
		BasicCommands.drawTile(out, tile, 0);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawTile
		BasicCommands.addPlayer1Notification(out, "drawTile[4,2]", 2);
		Tile tile3 = BasicObjectBuilders.loadTile(4, 2);
		BasicCommands.drawTile(out, tile3, 0);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawTile
		BasicCommands.addPlayer1Notification(out, "drawTile[5,2]", 2);
		Tile tile4 = BasicObjectBuilders.loadTile(5, 2);
		BasicCommands.drawTile(out, tile4, 0);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawTile
		BasicCommands.addPlayer1Notification(out, "drawTile[5,3]", 2);
		Tile tile5 = BasicObjectBuilders.loadTile(5, 3);
		BasicCommands.drawTile(out, tile5, 0);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawUnit
		BasicCommands.addPlayer1Notification(out, "drawUnit", 2);
		unit.setPositionByTile(tile); 
		BasicCommands.drawUnit(out, fire_spitter, tile);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// moveUnitToTile
		BasicCommands.addPlayer1Notification(out, "moveUnitToTile", 2);
		BasicCommands.moveUnitToTile(out, fire_spitter, tile5);
		unit.setPositionByTile(tile5); 
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}

		// moveUnitToTile
		BasicCommands.addPlayer1Notification(out, "moveUnitToTile (back)", 2);
		BasicCommands.moveUnitToTile(out, fire_spitter, tile);
		unit.setPositionByTile(tile); 
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}

		// deleteUnit
		BasicCommands.addPlayer1Notification(out, "deleteUnit", 2);
		BasicCommands.deleteUnit(out, fire_spitter);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		// Effects
		BasicCommands.addPlayer1Notification(out, "playEffectAnimation", 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		String[] effects = {
				StaticConfFiles.f1_buff,
				StaticConfFiles.f1_inmolation,
				StaticConfFiles.f1_martyrdom,
				StaticConfFiles.f1_summon
		};
		
		for (String effectFile : effects) {
			BasicCommands.addPlayer1Notification(out, effectFile, 2);
			EffectAnimation ef = BasicObjectBuilders.loadEffect(effectFile);
			BasicCommands.playEffectAnimation(out, ef, tile);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
			
		}

		// Player Cards
		BasicCommands.addPlayer1Notification(out, "Player Test", 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// setPlayer1Health
		BasicCommands.addPlayer1Notification(out, "setPlayer1Health", 2);
		Player humanPlayer = new Player(20, 0);
		BasicCommands.setPlayer1Health(out, humanPlayer);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// setPlayer1Health
		BasicCommands.addPlayer1Notification(out, "setPlayer2Health", 2);
		Player aiPlayer = new Player(20, 0);
		BasicCommands.setPlayer2Health(out, aiPlayer);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// Mana
		for (int m = 0; m<10; m++) {
			BasicCommands.addPlayer1Notification(out, "setPlayer1Mana ("+m+")", 1);
			humanPlayer.setMana(m);
			BasicCommands.setPlayer1Mana(out, humanPlayer);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		}

		// Mana
		for (int m = 0; m<10; m++) {
			BasicCommands.addPlayer1Notification(out, "setPlayer2Mana ("+m+")", 1);
			aiPlayer.setMana(m);
			BasicCommands.setPlayer2Mana(out, aiPlayer);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		}

		// Player Hand Test
		BasicCommands.addPlayer1Notification(out, "Player Hand Test", 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}


		// drawCard [1]
		BasicCommands.addPlayer1Notification(out, "drawCard [1u]", 2);
		Card hailstone_golem = BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 0, Card.class);
		BasicCommands.drawCard(out, hailstone_golem, 1, 0);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawCard [1] Highlight
		BasicCommands.addPlayer1Notification(out, "drawCard [1u] Highlight", 2);
		BasicCommands.drawCard(out, hailstone_golem, 1, 1);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// deleteCard [1]
		BasicCommands.addPlayer1Notification(out, "deleteCard", 2);
		BasicCommands.deleteCard(out, 1);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawCard [1]
		BasicCommands.addPlayer1Notification(out, "drawCard [1s]", 2);
		Card entropic_decay = BasicObjectBuilders.loadCard(StaticConfFiles.c_entropic_decay, 0, Card.class);
		BasicCommands.drawCard(out, entropic_decay, 1, 0);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// drawCard [1] Highlight
		BasicCommands.addPlayer1Notification(out, "drawCard [1s] Highlight", 2);
		BasicCommands.drawCard(out, entropic_decay, 1, 1);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		// deleteCard [1]
		BasicCommands.addPlayer1Notification(out, "deleteCard", 2);
		BasicCommands.deleteCard(out, 1);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		BasicCommands.addPlayer1Notification(out, "Player 1 Cards", 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		String[] deck1Cards = {
				StaticConfFiles.c_azure_herald,
				StaticConfFiles.c_azurite_lion,
				StaticConfFiles.c_comodo_charger,
				StaticConfFiles.c_fire_spitter,
				StaticConfFiles.c_hailstone_golem,
				StaticConfFiles.c_ironcliff_guardian,
				StaticConfFiles.c_pureblade_enforcer,
				StaticConfFiles.c_silverguard_knight,
				StaticConfFiles.c_sundrop_elixir,
				StaticConfFiles.c_truestrike
		};

		for (String deck1CardFile : deck1Cards) {
			// drawCard [1]
			BasicCommands.addPlayer1Notification(out, deck1CardFile, 2);
			Card card = BasicObjectBuilders.loadCard(deck1CardFile, 0, Card.class);
			BasicCommands.drawCard(out, card, 1, 0);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			// drawCard [1] Highlight
			BasicCommands.addPlayer1Notification(out, deck1CardFile+" Highlight", 2);
			BasicCommands.drawCard(out, card, 1, 1);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			// deleteCard [1]
			BasicCommands.addPlayer1Notification(out, "deleteCard", 2);
			BasicCommands.deleteCard(out, 1);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		}

		String[] deck2Cards = {
				StaticConfFiles.c_blaze_hound,
				StaticConfFiles.c_bloodshard_golem,
				StaticConfFiles.c_entropic_decay,
				StaticConfFiles.c_hailstone_golem,
				StaticConfFiles.c_planar_scout,
				StaticConfFiles.c_pyromancer,
				StaticConfFiles.c_serpenti,
				StaticConfFiles.c_rock_pulveriser,
				StaticConfFiles.c_staff_of_ykir,
				StaticConfFiles.c_windshrike,
		};

		for (String deck2CardFile : deck2Cards) {
			// drawCard [1]
			BasicCommands.addPlayer1Notification(out, deck2CardFile, 2);
			Card card = BasicObjectBuilders.loadCard(deck2CardFile, 0, Card.class);
			BasicCommands.drawCard(out, card, 1, 0);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			// drawCard [1] Highlight
			BasicCommands.addPlayer1Notification(out, deck2CardFile+" Highlight", 2);
			BasicCommands.drawCard(out, card, 1, 1);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			// deleteCard [1]
			BasicCommands.addPlayer1Notification(out, "deleteCard", 2);
			BasicCommands.deleteCard(out, 1);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		}


		// moveUnitToTile
		BasicCommands.addPlayer1Notification(out, "Deck 1 Units Test", 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		String[] deck1Units = {
				StaticConfFiles.u_comodo_charger,
				StaticConfFiles.u_hailstone_golem,
				StaticConfFiles.u_azure_herald,
				StaticConfFiles.u_azurite_lion,
				StaticConfFiles.u_pureblade_enforcer,
				StaticConfFiles.u_ironcliff_guardian,
				StaticConfFiles.u_silverguard_knight,
				StaticConfFiles.u_fire_spitter
		};

		int unitID = 3;
		for (String deck1CardFile : deck1Units) {
			BasicCommands.addPlayer1Notification(out, deck1CardFile, 2);
			unit = BasicObjectBuilders.loadUnit(deck1CardFile, unitID, Unit.class);
			unit.setPositionByTile(tile); 
			BasicCommands.drawUnit(out, unit, tile);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			// playUnitAnimation [Move]
			BasicCommands.addPlayer1Notification(out, "playUnitAnimation [Move]", 2);
			BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.move);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			// playUnitAnimation [Attack]
			BasicCommands.addPlayer1Notification(out, "playUnitAnimation [Attack]", 2);
			BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.attack);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			// playUnitAnimation [Death]
			BasicCommands.addPlayer1Notification(out, "playUnitAnimation [Death]", 3);
			BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.death);
			try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}

			// deleteUnit
			BasicCommands.addPlayer1Notification(out, "deleteUnit", 2);
			BasicCommands.deleteUnit(out, unit);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			unitID++;
		}

		// moveUnitToTile
		BasicCommands.addPlayer1Notification(out, "Deck 2 Units Test", 2);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

		String[] deck2Units = {
				StaticConfFiles.u_blaze_hound,
				StaticConfFiles.u_bloodshard_golem,
				StaticConfFiles.u_hailstone_golemR,
				StaticConfFiles.u_planar_scout,
				StaticConfFiles.u_pyromancer,
				StaticConfFiles.u_rock_pulveriser,
				StaticConfFiles.u_serpenti,
				StaticConfFiles.u_windshrike
		};

		for (String deck2CardFile : deck2Units) {
			BasicCommands.addPlayer1Notification(out, deck2CardFile, 2);
			unit = BasicObjectBuilders.loadUnit(deck2CardFile, unitID, Unit.class);
			unit.setPositionByTile(tile); 
			BasicCommands.drawUnit(out, unit, tile);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			// playUnitAnimation [Move]
			BasicCommands.addPlayer1Notification(out, "playUnitAnimation [Move]", 2);
			BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.move);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			// playUnitAnimation [Attack]
			BasicCommands.addPlayer1Notification(out, "playUnitAnimation [Attack]", 2);
			BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.attack);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			// playUnitAnimation [Death]
			BasicCommands.addPlayer1Notification(out, "playUnitAnimation [Death]", 3);
			BasicCommands.playUnitAnimation(out, unit, UnitAnimationType.death);
			try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}

			// deleteUnit
			BasicCommands.addPlayer1Notification(out, "deleteUnit", 2);
			BasicCommands.deleteUnit(out, unit);
			try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

			unitID++;
		}


	}

}
