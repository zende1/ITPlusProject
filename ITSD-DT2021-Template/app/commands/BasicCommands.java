package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.actor.ActorRef;
import play.libs.Json;
import structures.basic.Card;
import structures.basic.EffectAnimation;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import structures.basic.UnitAnimationType;


/**
 * This is a utility class that simply provides short-cut methods for
 * running the basic command set for the game.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class BasicCommands {

	private static ObjectMapper mapper = new ObjectMapper(); // Jackson Java Object Serializer, is used to turn java objects to Strings
	
	/**
	 * You can consider the contents of the user’s browser window a canvas that can be drawn upon. drawTile will draw 
	 * the image of a board tile on the board. This command takes as input a Tile object and a visualisation mode (an 
	 * integer) that specifies which version of the tile to render (each tile has multiple versions, e.g. normal vs. 
	 * highlighted). This command can be used multiple times to change the visualisation mode for a tile.
	 * @param out
	 * @param tile
	 * @param mode
	 */
	@SuppressWarnings({"deprecation"})
	public static void drawTile(ActorRef out, Tile tile, int mode) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "drawTile");
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(tile)));
			returnMessage.put("mode", mode);
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * drawUnit will draw the sprite for a unit (a picture of that unit with its attack and health values) on the board. 
	 * This command takes as input a target Tile (a ‘square’ of the main game grid) to place the unit’s sprite upon, 
	 * and the instance of the Unit (which holds the needed information about how to draw that unit).
	 * @param out
	 * @param unit
	 * @param tile
	 */
	@SuppressWarnings({"deprecation"})
	public static void drawUnit(ActorRef out, Unit unit, Tile tile) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "drawUnit");
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(tile)));
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised attack value just under a unit’s sprite to a value between 0 
	 * and 20. The command takes in a unit instance. The associated values are read from the unit object.
	 * @param out
	 * @param unit
	 * @param attack
	 */
	@SuppressWarnings({"deprecation"})
	public static void setUnitAttack(ActorRef out, Unit unit, int attack) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setUnitAttack");
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			returnMessage.put("attack", attack);
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised health value just under a unit’s sprite to a value between 0 
	 * and 20. The command takes in a unit instance. The associated values are read from the unit object.
	 * @param out
	 * @param unit
	 * @param health
	 */
	@SuppressWarnings({"deprecation"})
	public static void setUnitHealth(ActorRef out, Unit unit, int health) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setUnitHealth");
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			returnMessage.put("health", health);
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command moves a unit sprite from one tile to another. It takes in the unit’s object and the target Tile. 
	 * Note that this command will start the movement, it may take multiple seconds for the movement to complete.
	 * @param out
	 * @param unit
	 * @param tile
	 */
	@SuppressWarnings({"deprecation"})
	public static void moveUnitToTile(ActorRef out, Unit unit, Tile tile) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "moveUnitToTile");
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(tile)));
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command makes a unit play a specified animation. It takes in the unit object which
	 * contains all of the data needed to play the animations, and a UnitAnimation that specifies
	 * which animation to switch to.
	 * @param out
	 * @param unit
	 * @param animation
	 */
	@SuppressWarnings({"deprecation"})
	public static void playUnitAnimation(ActorRef out, Unit unit, UnitAnimationType animationToPlay) {
		try {
			
			unit.setAnimation(animationToPlay);
			
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "playUnitAnimation");
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			returnMessage.put("animation", animationToPlay.toString());
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This will delete a unit instance from the board. It takes as input the unit object of the unit.
	 * @param out
	 * @param unit
	 */
	@SuppressWarnings({"deprecation"})
	public static void deleteUnit(ActorRef out, Unit unit) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "deleteUnit");
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised health value in the player’s information card to a value between 0 
	 * and 20. The command takes in a basic player instance. The associated values are read from the basic player 
	 * object.
	 * @param out
	 * @param player
	 */
	@SuppressWarnings({"deprecation"})
	public static void setPlayer1Health(ActorRef out, Player player) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setPlayer1Health");
			returnMessage.put("player", mapper.readTree(mapper.writeValueAsString(player)));
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised health value in the player’s information card to a value between 0 
	 * and 20. The command takes in a basic player instance. The associated values are read from the basic player 
	 * object.
	 * @param out
	 * @param player
	 */
	@SuppressWarnings({"deprecation"})
	public static void setPlayer2Health(ActorRef out, Player player) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setPlayer2Health");
			returnMessage.put("player", mapper.readTree(mapper.writeValueAsString(player)));
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised mana value in the player’s information card to a value between 0 
	 * and 9. The command takes in a basic player instance. The associated values are read from the basic player 
	 * object.
	 * @param out
	 * @param player
	 */
	@SuppressWarnings({"deprecation"})
	public static void setPlayer1Mana(ActorRef out, Player player) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setPlayer1Mana");
			returnMessage.put("player", mapper.readTree(mapper.writeValueAsString(player)));
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised mana value in the player’s information card to a value between 0 
	 * and 9. The command takes in a basic player instance. The associated values are read from the basic player 
	 * object.
	 * @param out
	 * @param player
	 */
	@SuppressWarnings({"deprecation"})
	public static void setPlayer2Mana(ActorRef out, Player player) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setPlayer2Mana");
			returnMessage.put("player", mapper.readTree(mapper.writeValueAsString(player)));
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command renders a card in the player’s hand. It takes as input a hand position (a value between 1-6), a 
	 * Card (which is an object containing basic information needed to visualise that card) and a visualisation mode 
	 * (similarly to a tile). This command can be issued multiple times to change the visualisation mode of a card.
	 * @param out
	 * @param card
	 * @param position
	 * @param mode
	 */
	@SuppressWarnings({"deprecation"})
	public static void drawCard(ActorRef out, Card card, int position, int mode) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "drawCard");
			returnMessage.put("card", mapper.readTree(mapper.writeValueAsString(card)));
			returnMessage.put("position", position);
			returnMessage.put("mode", mode);
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command deletes a card in the player’s hand. It takes as input a hand position (a value between 1-6).
	 * @param out
	 * @param position
	 */
	public static void deleteCard(ActorRef out, int position) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "deleteCard");
			returnMessage.put("position", position);
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays a specified EffectAnimation (such as an explosion) centred on a particular Tile. It takes as input an 
	 * EffectAnimation (an object with information about rendering the effect) and a target Tile.
	 * @param out
	 * @param effect
	 * @param tile
	 */
	@SuppressWarnings({"deprecation"})
	public static void playEffectAnimation(ActorRef out, EffectAnimation effect, Tile tile) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "playEffectAnimation");
			returnMessage.put("effect", mapper.readTree(mapper.writeValueAsString(effect)));
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(tile)));
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command creates a notification box next to the portrait for the player 1 which contains
	 * the specified text. It will be displayed for a number of seconds before being removed.
	 * object.
	 * @param out
	 * @param text
	 * @param displayTimeSeconds
	 */
	public static void addPlayer1Notification(ActorRef out, String text, int displayTimeSeconds) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "addPlayer1Notification");
			returnMessage.put("text", text);
			returnMessage.put("seconds", displayTimeSeconds);
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays a projectile fire animation between two tiles
	 * @param out
	 * @param effect
	 * @param tile
	 */
	@SuppressWarnings({"deprecation"})
	public static void playProjectileAnimation(ActorRef out, EffectAnimation effect, int mode, Tile startTile, Tile targetTile) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "drawProjectile");
			returnMessage.put("effect", mapper.readTree(mapper.writeValueAsString(effect)));
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(startTile)));
			returnMessage.put("targetTile", mapper.readTree(mapper.writeValueAsString(targetTile)));
			returnMessage.put("mode", mapper.readTree(mapper.writeValueAsString(mode)));
			out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
