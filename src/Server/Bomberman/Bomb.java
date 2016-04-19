package Server.Bomberman;

public class Bomb {
	
	private int x,y;
	private int range;
	private int belongTo;
	private boolean readyToExplode;
	private int timeToLive;
	
	public Bomb(int x, int y, int range, int belongTo, boolean readyToExplode, int timeToLive){
		this.x = x;
		this.y = y;
		this.range = range;
		this.belongTo = belongTo;
		this.readyToExplode = readyToExplode;
		this.timeToLive = timeToLive;
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

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public int getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(int belongTo) {
		this.belongTo = belongTo;
	}

	public boolean isReadyToExplode() {
		return readyToExplode;
	}

	public void setReadyToExplode(boolean readyToExplode) {
		this.readyToExplode = readyToExplode;
	}

	public int getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}
	
	
}
