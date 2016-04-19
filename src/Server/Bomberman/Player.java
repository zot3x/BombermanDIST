package Server.Bomberman;

public class Player {

	private int x,y;
	private int hp;
	private int bombs;
	private int bombsLaid = 0;
	private int range;
	private boolean isAlive = true;
	private int id;
	private boolean hasMoved = false;
	
	public Player(int x, int y, int hp, int bombs, int range, int id){
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.bombs = bombs;
		this.range = range;
		this.id = id;
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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	public int getBombsLaid() {
		return bombsLaid;
	}

	public void setBombsLaid(int bombsLaid) {
		this.bombsLaid = bombsLaid;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

}
