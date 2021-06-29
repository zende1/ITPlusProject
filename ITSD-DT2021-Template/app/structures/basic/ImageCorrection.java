package structures.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Sprites when extracted are not full size (there is white space around the sprite). 
 * We need to correct for this as well as centre the sprite on the tile. This class
 * contains information to do this.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class ImageCorrection {

	double imgWidth;
	double imgHeight;
	double spriteTopLeftX;
	double spriteTopLeftY;
	double offsetX;
	double offsetY;
	double scale;
	boolean reflected;
	
	public ImageCorrection() {}
	
	public ImageCorrection(double imgWidth, double imgHeight, double spriteTopLeftX, double spriteTopLeftY,
			double offsetX, double offsetY, double scale, boolean reflected) {
		super();
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.spriteTopLeftX = spriteTopLeftX;
		this.spriteTopLeftY = spriteTopLeftY;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.scale = scale;
		this.reflected = reflected;
	}

	public double getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(double imgWidth) {
		this.imgWidth = imgWidth;
	}

	public double getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(double imgHeight) {
		this.imgHeight = imgHeight;
	}

	public double getSpriteTopLeftX() {
		return spriteTopLeftX;
	}

	public void setSpriteTopLeftX(double spriteTopLeftX) {
		this.spriteTopLeftX = spriteTopLeftX;
	}

	public double getSpriteTopLeftY() {
		return spriteTopLeftY;
	}

	public void setSpriteTopLeftY(double spriteTopLeftY) {
		this.spriteTopLeftY = spriteTopLeftY;
	}

	public double getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}

	public double getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public boolean isReflected() {
		return reflected;
	}

	public void setReflected(boolean reflected) {
		this.reflected = reflected;
	}

	@JsonIgnore
	public double getCorrectedImgWidth() {
		return imgWidth*(1+(spriteTopLeftX/imgWidth));
	}
	
	@JsonIgnore
	public double getCorrectedImgHeight() {
		return imgHeight*(1+(spriteTopLeftY/imgHeight));
	}
	
	
	
}
