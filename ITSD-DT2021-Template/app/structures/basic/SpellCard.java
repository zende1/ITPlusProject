package structures.basic;

import akka.actor.ActorRef;
import commands.BasicCommands;

public class SpellCard extends Card{
	int attack;
	public SpellCard() {
		super();
	}
	public SpellCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id,cardname,manacost,miniCard,bigCard);
	}
	public SpellCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard,int attack) {
		super(id,cardname,manacost,miniCard,bigCard);
		this.attack = attack;
	}
	
	public void castSpell(ActorRef out,Creature target) {
		target.health -= this.attack;
		BasicCommands.setUnitHealth(out, target, target.health);
	}
	public void castSpell(ActorRef out,Avatar target) {
		target.health -= this.attack;
		target.player.health -= this.attack;
		BasicCommands.setUnitHealth(out, target, target.health);
		BasicCommands.setPlayer1Health(out, target.player);
	}
}
