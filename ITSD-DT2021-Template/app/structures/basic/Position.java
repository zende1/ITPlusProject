package structures.basic;

/**
 * This contains the positional information for a unit
 * that is sitting on a tile. tilex/y are the index position
 * of the tile the unit is sitting upon. x/ypos are the pixel
 * position of the unit.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class Position {

	int xpos;
	int ypos;
	int tilex;
	int tiley;
	
	public Position() {}
	
	public Position(int xpos, int ypos, int tilex, int tilexy) {
		super();
		this.xpos = xpos;
		this.ypos = ypos;
		this.tilex = tilex;
		this.tiley = tilexy;
	}
	public int getXpos() {
		return xpos;
	}
	public void setXpos(int xpos) {
		this.xpos = xpos;
	}
	public int getYpos() {
		return ypos;
	}
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	public int getTilex() {
		return tilex;
	}
	public void setTilex(int tilex) {
		this.tilex = tilex;
	}
	public int getTiley() {
		return tiley;
	}
	public void setTiley(int tilexy) {
		this.tiley = tilexy;
	}
	
	
	
	
	
}
