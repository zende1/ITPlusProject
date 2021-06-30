package structures.basic;

import akka.actor.ActorRef;
import commands.BasicCommands;

public class CreatureCard extends Card{
	int attack;
	int health;
	public CreatureCard() {
		super();
	}
	public CreatureCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard) {
		super(id,cardname,manacost,miniCard,bigCard);
	}
	public CreatureCard(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard,int attack,int health) {
		super(id,cardname,manacost,miniCard,bigCard);
		this.attack = attack;
		this.health = health;
	}
	public Creature getCreature() {
		return new Creature(this.attack,this.health);
	}
	public void summon(ActorRef out,Tile tile) {
		BasicCommands.drawTile(out, tile, 0);
		Creature current = this.getCreature();
		BasicCommands.drawUnit(out, current, tile);
		BasicCommands.setUnitAttack(out, current, this.attack);
		BasicCommands.setUnitHealth(out, current, this.health);
	}

}
