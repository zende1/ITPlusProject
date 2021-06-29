package structures.basic;

/**
 * A mini-card is a visualisation of the card in a small square form factor
 * in the player's hand. It has a series of components. cardTextures are the
 * 'backing' image behind the sprite animation. animationFrames are the frames
 * if the unit/spell animation. fps is the speed at which to play the unit/spell
 * animation. index is the frame index in animationFrames when wanting a 'still'
 * version (non-highlighted).
 * 
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class MiniCard {

	String[] cardTextures;
	String[] animationFrames;
	int fps;
	int index;
	
	public MiniCard() {}

	public MiniCard(String[] cardTextures, String[] animationFrames, int fps, int index) {
		super();
		this.cardTextures = cardTextures;
		this.animationFrames = animationFrames;
		this.fps = fps;
		this.index = index;
	}

	public String[] getCardTextures() {
		return cardTextures;
	}

	public void setCardTextures(String[] cardTextures) {
		this.cardTextures = cardTextures;
	}

	public String[] getAnimationFrames() {
		return animationFrames;
	}

	public void setAnimationFrames(String[] animationFrames) {
		this.animationFrames = animationFrames;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
	
	
	
}
