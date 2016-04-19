package Server.Bomberman;

public class Explosion {
	
	private int x, y;
	private int[] length = new int[4];
	private int belongTo;
	private boolean hasExploded;
	private int timeToLive = 20; //test for proper TTL
	
	public Explosion(int x, int y, int[] length, int belongTo, boolean hasExploded){
		this.x = x;
		this.y = y;
		this.length = length;
		this.belongTo = belongTo;
		this.hasExploded = hasExploded;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int[] getLength() {
		return length;
	}

	public void setLength(int[] length) {
		this.length = length;
	}

	public int getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(int belongTo) {
		this.belongTo = belongTo;
	}

	public boolean isHasExploded() {
		return hasExploded;
	}

	public void setHasExploded(boolean hasExploded) {
		this.hasExploded = hasExploded;
	}

	public int getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}
}
