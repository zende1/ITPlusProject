package structures.basic;

import java.util.List;

/**
 * This class is the base for an effect that can be played on a game
 * tile. It has a list of animation frames (animationTextures), and
 * a correction object that has information about centering the frames
 * on the tile. It has has an fps value that specifies how quickly to
 * play the animation.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class EffectAnimation {

	List<String> animationTextures;
	ImageCorrection correction;
	int fps;
	
	public EffectAnimation() {}
	
	public EffectAnimation(List<String> animationTextures, ImageCorrection correction, int fps) {
		super();
		this.animationTextures = animationTextures;
		this.correction = correction;
		this.fps = fps;
	}
	public List<String> getAnimationTextures() {
		return animationTextures;
	}
	public void setAnimationTextures(List<String> animationTextures) {
		this.animationTextures = animationTextures;
	}
	public ImageCorrection getCorrection() {
		return correction;
	}
	public void setCorrection(ImageCorrection correction) {
		this.correction = correction;
	}
	public int getFps() {
		return fps;
	}
	public void setFps(int fps) {
		this.fps = fps;
	}
	
	
}
