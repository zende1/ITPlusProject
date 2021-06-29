package structures.basic;

/**
 * This contains information for playing a Unit's animation, e.g.
 * move, attack, or idle. One of these animations as a start and
 * end index in the animation frames array, an fps (indicating animation
 * speed) and whether that animation should loop (e.g. move does, attack
 * does not).
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class UnitAnimation {

	int[] frameStartEndIndices;
	int fps;
	boolean loop;
	
	public UnitAnimation() {}

	public UnitAnimation(int[] frameStartEndIndices, int fps, boolean loop) {
		super();
		this.frameStartEndIndices = frameStartEndIndices;
		this.fps = fps;
		this.loop = loop;
	}

	public int[] getFrameStartEndIndices() {
		return frameStartEndIndices;
	}

	public void setFrameStartEndIndices(int[] frameStartEndIndices) {
		this.frameStartEndIndices = frameStartEndIndices;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	};
	
	
	
	
	
	
}
