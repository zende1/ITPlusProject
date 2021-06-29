package structures.basic;

import java.util.List;

/**
 * This is a storage structure for the different animations that a
 * Unit can perform. A Unit has 6 possible animation states:
 *  - idle
 *  - death
 *  - attack
 *  - move
 *  - channel
 *  - hit
 *  
 * @author Dr. Richard McCreadie
 *
 */
public class UnitAnimationSet {

	List<String> allFrames;
	
	UnitAnimation idle;
	UnitAnimation death;
	UnitAnimation attack;
	UnitAnimation move;
	UnitAnimation channel;
	UnitAnimation hit;
	
	public UnitAnimationSet() {}
	
	public UnitAnimationSet(List<String> allFrames, UnitAnimation idle, UnitAnimation death, UnitAnimation attack,
			UnitAnimation move, UnitAnimation channel, UnitAnimation hit) {
		super();
		this.allFrames = allFrames;
		this.idle = idle;
		this.death = death;
		this.attack = attack;
		this.move = move;
		this.channel = channel;
		this.hit = hit;
	}
	public List<String> getAllFrames() {
		return allFrames;
	}
	public void setAllFrames(List<String> allFrames) {
		this.allFrames = allFrames;
	}
	public UnitAnimation getIdle() {
		return idle;
	}
	public void setIdle(UnitAnimation idle) {
		this.idle = idle;
	}
	public UnitAnimation getDeath() {
		return death;
	}
	public void setDeath(UnitAnimation death) {
		this.death = death;
	}
	public UnitAnimation getAttack() {
		return attack;
	}
	public void setAttack(UnitAnimation attack) {
		this.attack = attack;
	}
	public UnitAnimation getMove() {
		return move;
	}
	public void setMove(UnitAnimation move) {
		this.move = move;
	}
	public UnitAnimation getChannel() {
		return channel;
	}
	public void setChannel(UnitAnimation channel) {
		this.channel = channel;
	}
	public UnitAnimation getHit() {
		return hit;
	}
	public void setHit(UnitAnimation hit) {
		this.hit = hit;
	}
	
	
	
	
}
